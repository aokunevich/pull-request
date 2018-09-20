package akunevich.pullrequest;

import akunevich.pullrequest.detector.ApprovedPullRequestDetector;
import akunevich.pullrequest.detector.NewPullRequestDetector;
import akunevich.pullrequest.event.EventBusFactory;
import akunevich.pullrequest.event.PluginDisabledEvent;
import akunevich.pullrequest.event.PluginEnabledEvent;
import akunevich.pullrequest.integration.bitbucket.BitBucket;
import akunevich.pullrequest.integration.bitbucket.PullRequest;
import akunevich.pullrequest.integration.bitbucket.PullRequests;
import akunevich.pullrequest.settings.Settings;
import com.google.common.eventbus.Subscribe;
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
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main implements ProjectComponent {

    private static final Logger logger = Logger.getInstance(Main.class);

    private List<PullRequest> pullRequests = new ArrayList<>();
    private Project project;
    private Settings settings = new Settings();
    private ScheduledExecutorService executorService;

    public Main(Project project) {
        this.project = project;

        settings.loadState(ServiceManager.getService(project, Settings.class));

        EventBusFactory.INSTANCE.eventBus().register(this);
    }

    @Subscribe
    public void pluginEnabled(PluginEnabledEvent pluginEnabledEvent) {
        startPlugin();
    }

    @Subscribe
    public void pluginDisabled(PluginDisabledEvent pluginDisabledEvent) {
        stopPlugin();
    }

    private void stopPlugin() {
        logger.info("Stop plugin");

        if (executorService != null && !executorService.isTerminated()) {
            executorService.shutdown();
        }
    }

    private void startPlugin() {
        logger.info("Start plugin");

        if (executorService == null || executorService.isTerminated()) {
            executorService = Executors.newScheduledThreadPool(1);
        }
        executorService.scheduleAtFixedRate(this::doProcess, 5, 10, TimeUnit.SECONDS);
    }

    @Override
    public void projectOpened() {
        logger.debug("Project was opened");

        if (settings != null && settings.isEnabled()) {
            startPlugin();
        } else {
            stopPlugin();
        }
    }

    private void doProcess() {
        logger.debug("Processing...");

        List<PullRequest> loadedPullRequests = loadPullRequests();

        logger.debug("Pull requests were loaded: " + loadedPullRequests.size());


        AtomicBoolean isChangesDetected = new AtomicBoolean(false);

        new NewPullRequestDetector().detect(pullRequests, loadedPullRequests, pullRequest -> {
            isChangesDetected.set(true);
            Notifications.Bus.notify(new Notification("Pull Request Plugin: Created",
                            "New Pull Request Was Created",
                            pullRequest.getAuthor().getUser().getDisplayName() + "<br>" + pullRequest.getTitle(),
                            NotificationType.INFORMATION),
                    project);

            logger.info("Project: " + project.getName() +
                    ". New pull request found." +
                    pullRequest.getId() + " " + pullRequest.getAuthor() + " " + pullRequest.getTitle());

            return null;
        });

        new ApprovedPullRequestDetector().detect(pullRequests, loadedPullRequests, pullRequest -> {
            isChangesDetected.set(true);

            Notifications.Bus.notify(new Notification("Pull Request Plugin: Approved",
                            "Pull Request Was Approved",
                            pullRequest.getAuthor().getUser().getDisplayName() + "<br>" + pullRequest.getTitle(),
                            NotificationType.INFORMATION),
                    project);

            logger.info("Project: " + project.getName() +
                    ". Approved." +
                    pullRequest.getId() + " " + pullRequest.getAuthor() + " " + pullRequest.getTitle());

            return null;

        });


        if (isChangesDetected.get()) {
            pullRequests.clear();
            pullRequests.addAll(loadedPullRequests);
        }

    }

    private List<PullRequest> loadPullRequests() {

        List<PullRequest> result = new ArrayList<>();

        if (settings.getProject() == null ||
                settings.getRepository() == null ||
                settings.getUsername() == null ||
                settings.getPassword() == null) {
            logger.warn("Project: " + project.getName() + ". Settings are invalid. " + settings);
            stopPlugin();
        } else {
            BitBucket bitBucket = new BitBucket();
            PullRequests pullRequests = bitBucket.list(settings.getProject(), settings.getRepository(),
                    settings.getUsername(), settings.getPassword());
            result.addAll(pullRequests.getValues());
        }
        return result;
    }

}
