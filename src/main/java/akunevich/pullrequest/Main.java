package akunevich.pullrequest;

import akunevich.pullrequest.integration.bitbucket.PullRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {


    private List<PullRequest> pullRequests = new ArrayList<>();

    public void doStart() {

        // todo add 'enabled' to settings

        // run after project open


/*
        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(this::doProcess, 5, 10, TimeUnit.SECONDS);
*/



        // ProjectManager.addProjectManagerListener()

    }

    private void doProcess() {
        // load settings
        // load pullrequests

/*
        List<PullRequest> loadedPullRequests = loadPullRequests(url, username, password);

        if (pullRequests.size() != loadedPullRequests.size()) {
            pullRequests.clear();
            pullRequests.addAll(loadedPullRequests);

            // send notification
        }
*/

    }
}
