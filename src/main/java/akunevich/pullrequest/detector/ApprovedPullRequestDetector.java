package akunevich.pullrequest.detector;

import akunevich.pullrequest.integration.bitbucket.PullRequest;
import akunevich.pullrequest.integration.bitbucket.PullRequestState;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ApprovedPullRequestDetector implements Detector {

    @Override
    public void detect(List<PullRequest> pullRequests, List<PullRequest> newPullRequests, Function<PullRequest, Void> function) {
        Map<Integer, PullRequest> map = Maps.newConcurrentMap();
        newPullRequests.forEach(pullRequest -> map.put(pullRequest.getId(), pullRequest));

        pullRequests.forEach(pullRequest -> {
            PullRequest newPullRequest = map.get(pullRequest.getId());
            PullRequestState newPullRequestState = newPullRequest.getState();
            if (pullRequest.getState() != PullRequestState.APPROVED && newPullRequestState == PullRequestState.APPROVED) {
                function.apply(newPullRequest);
            }
        });
    }

}
