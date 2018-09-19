package akunevich.pullrequest;

import akunevich.pullrequest.detector.NewPullRequestDetector;
import akunevich.pullrequest.integration.bitbucket.BitBucket;
import akunevich.pullrequest.integration.bitbucket.PullRequest;
import akunevich.pullrequest.integration.bitbucket.PullRequests;
import akunevich.pullrequest.settings.Settings;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


// todo add 'enabled' to settings


public class Main implements ProjectComponent {

    private static final Logger logger = Logger.getInstance(Main.class);

    private List<PullRequest> pullRequests = new ArrayList<>();


    private Project project;

    public Main(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {
        logger.debug("Project was opened");

        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(this::doProcess, 5, 10, TimeUnit.SECONDS);
    }

    private void doProcess() {
        logger.debug("Processing...");

        List<PullRequest> loadedPullRequests = loadPullRequests();

        logger.debug("Pull requests were loaded: " + loadedPullRequests.size());


        boolean result = new NewPullRequestDetector().detect(pullRequests, loadedPullRequests, new Function<PullRequest, Void>() {

            @Override
            public Void apply(PullRequest pullRequest) {

                Notifications.Bus.notify(new Notification("Pull Request Plugin",
                                "New Pull Request Was Created",
                                pullRequest.getAuthor() + ": " + pullRequest.getTitle(),
                                NotificationType.INFORMATION),
                        project);

                logger.info("Project: " + project.getName() +
                        ". New pull request found." +
                        pullRequest.getId() + " " + pullRequest.getAuthor() + " " + pullRequest.getTitle());

                return null;
            }
        });

        if (result) {
            pullRequests.clear();
            pullRequests.addAll(loadedPullRequests);

        }

    }

    private List<PullRequest> loadPullRequests() {
        Settings settings = new Settings();
        settings.loadState(ServiceManager.getService(project, Settings.class));

        List<PullRequest> result = new ArrayList<>();

        if (settings.getUrl() == null || settings.getUsername() == null || settings.getPassword() == null) {
            logger.warn("Project: " + project.getName() + ". Settings are invalid. " + settings);
        } else {
            BitBucket bitBucket = new BitBucket();
            PullRequests pullRequests = bitBucket.list(settings.getUrl(), settings.getUsername(), settings.getPassword());
            result.addAll(pullRequests.getValues());
        }
        return result;
    }


}
