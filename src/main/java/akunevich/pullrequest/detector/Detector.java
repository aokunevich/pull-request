package akunevich.pullrequest.detector;

import akunevich.pullrequest.integration.bitbucket.PullRequest;

import java.util.List;
import java.util.function.Function;

public interface Detector {
    void detect(List<PullRequest> pullRequests,
                List<PullRequest> newPullRequests,
                Function<PullRequest, Void> function);
}
