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
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


// todo add 'enabled' to settings


public class Main implements ProjectComponent {

    private List<PullRequest> pullRequests = new ArrayList<>();


    private Project project;

    public Main(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {
        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(this::doProcess, 5, 10, TimeUnit.SECONDS);
    }

    private void doProcess() {
        List<PullRequest> loadedPullRequests = loadPullRequests();

        if (pullRequests.size() != loadedPullRequests.size()) {
            pullRequests.clear();
            pullRequests.addAll(loadedPullRequests);

            Notifications.Bus.notify(new Notification("Pull Request Plugin",
                    "New pull request was created",
                    "",
                    NotificationType.INFORMATION));

        }
    }

    private List<PullRequest> loadPullRequests() {
        Settings settings = new Settings();
        settings.loadState(ServiceManager.getService(project, Settings.class));


        BitBucket bitBucket = new BitBucket();
        PullRequests pullRequests = bitBucket.list(settings.getUrl(), settings.getUsername(), settings.getPassword());

        return pullRequests.getValues();
    }


}
