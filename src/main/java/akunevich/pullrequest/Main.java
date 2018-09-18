package akunevich.pullrequest;

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

        if (pullRequests.size() != loadedPullRequests.size()) {
            pullRequests.clear();
            pullRequests.addAll(loadedPullRequests);

            Notifications.Bus.notify(new Notification("Pull Request Plugin",
                    "New pull request was created",
                    "",
                    NotificationType.INFORMATION));

        }

        logger.info("Changes in pull requests were found.");
    }

    private List<PullRequest> loadPullRequests() {
        Settings settings = new Settings();
        settings.loadState(ServiceManager.getService(project, Settings.class));

        List<PullRequest> result = new ArrayList<>();

        if (settings.getUrl() == null || settings.getUsername() == null || settings.getPassword() == null) {
            logger.warn("Settings are invalid. " + settings);
        } else {
            BitBucket bitBucket = new BitBucket();
            PullRequests pullRequests = bitBucket.list(settings.getUrl(), settings.getUsername(), settings.getPassword());
            result.addAll(pullRequests.getValues());
        }
        return result;
    }


}
