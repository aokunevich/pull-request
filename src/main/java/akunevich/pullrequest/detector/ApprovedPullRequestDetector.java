package akunevich.pullrequest.detector;

import akunevich.pullrequest.integration.bitbucket.PullRequest;
import akunevich.pullrequest.integration.bitbucket.PullRequestReviewer;
import akunevich.pullrequest.integration.bitbucket.PullRequestReviewerStatus;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ApprovedPullRequestDetector implements Detector {

    @Override
    public void detect(List<PullRequest> pullRequests, List<PullRequest> newPullRequests, Function<PullRequest, Void> function) {
        Map<Integer, PullRequest> newPullRequestMap = Maps.newHashMap();
        newPullRequests.forEach(pullRequest -> newPullRequestMap.put(pullRequest.getId(), pullRequest));

        pullRequests.forEach(pullRequest -> Optional.ofNullable(newPullRequestMap.get(pullRequest.getId()))
                .ifPresent(newPullRequest -> verifyApprovals(pullRequest, newPullRequest, function)));
    }

    private void verifyApprovals(PullRequest pullRequest, PullRequest newPullRequest, Function<PullRequest, Void> function) {
        List<PullRequestReviewer> newPullRequestReviewersApproved = newPullRequest.getReviewers()
                .stream()
                .filter(pullRequestReviewer -> pullRequestReviewer.getStatus() == PullRequestReviewerStatus.APPROVED)
                .collect(Collectors.toList());

        if (newPullRequestReviewersApproved.isEmpty()) {
            return;
        }

        Map<Long, PullRequestReviewer> newPullRequestReviewersMap = Maps.newHashMap();
        newPullRequest.getReviewers()
                .forEach(pullRequestReviewer -> newPullRequestReviewersMap.put(pullRequestReviewer.getUser().getId(), pullRequestReviewer));


        pullRequest.getReviewers()
                .forEach(pullRequestReviewer -> {
                    Optional.ofNullable(newPullRequestReviewersMap.get(pullRequestReviewer.getUser().getId()))
                            .ifPresent(newPullRequestReviewer -> {
                                if (pullRequestReviewer.getStatus() != newPullRequestReviewer.getStatus() &&
                                        newPullRequestReviewer.getStatus() == PullRequestReviewerStatus.APPROVED) {
                                    function.apply(pullRequest);
                                }
                            });
                });


    }

}
