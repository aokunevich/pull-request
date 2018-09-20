package akunevich.pullrequest.detector;

import akunevich.pullrequest.integration.bitbucket.PullRequest;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NewPullRequestDetector implements Detector {

    @Override
    public void detect(List<PullRequest> pullRequests,
                       List<PullRequest> newPullRequests,
                       Function<PullRequest, Void> function) {

        List<Integer> pullRequestsId = pullRequests.stream().mapToInt(PullRequest::getId).boxed().collect(Collectors.toList());

        List<Integer> ids = newPullRequests
                .stream()
                .mapToInt(PullRequest::getId)
                .filter(o -> !pullRequestsId.contains(o))
                .boxed()
                .collect(Collectors.toList());

        List<PullRequest> result = newPullRequests
                .stream()
                .filter(pullRequest -> ids.contains(pullRequest.getId()))
                .collect(Collectors.toList());

        result
                .forEach(function::apply);

    }
}
