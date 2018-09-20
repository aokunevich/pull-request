package akunevich.pullrequest.detector;

import akunevich.pullrequest.integration.bitbucket.PullRequest;
import akunevich.pullrequest.integration.bitbucket.PullRequestBuilder;
import akunevich.pullrequest.integration.bitbucket.PullRequestState;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApprovedPullRequestDetectorTest {

    @DisplayName("No changes. All pull requests are OPEN")
    @Test
    public void test1() {

        List<PullRequest> pullRequests = Stream.of(
                new PullRequestBuilder()
                        .withId(1)
                        .withState(PullRequestState.OPEN)
                        .build(),
                new PullRequestBuilder()
                        .withId(2)
                        .withState(PullRequestState.OPEN)
                        .build())
                .collect(Collectors.toList());

        List<PullRequest> newPullRequests = Stream.of(
                new PullRequestBuilder()
                        .withId(1)
                        .withState(PullRequestState.OPEN)
                        .build(),
                new PullRequestBuilder()
                        .withId(2)
                        .withState(PullRequestState.OPEN)
                        .build())
                .collect(Collectors.toList());

        AtomicInteger count = new AtomicInteger(0);

        new ApprovedPullRequestDetector().detect(pullRequests, newPullRequests, new Function<PullRequest, Void>() {
            @Override
            public Void apply(PullRequest pullRequest) {
                count.incrementAndGet();
                return null;
            }
        });

        Assert.assertEquals(0, count.get());
    }

    @DisplayName("One pull request is APPROVED")
    @Test
    public void test2() {

        List<PullRequest> pullRequests = Stream.of(
                new PullRequestBuilder()
                        .withId(1)
                        .withState(PullRequestState.OPEN)
                        .build(),
                new PullRequestBuilder()
                        .withId(2)
                        .withState(PullRequestState.OPEN)
                        .build())
                .collect(Collectors.toList());

        List<PullRequest> newPullRequests = Stream.of(
                new PullRequestBuilder()
                        .withId(1)
                        .withState(PullRequestState.OPEN)
                        .build(),
                new PullRequestBuilder()
                        .withId(2)
                        .withState(PullRequestState.APPROVED)
                        .build())
                .collect(Collectors.toList());

        AtomicInteger count = new AtomicInteger(0);

        new ApprovedPullRequestDetector().detect(pullRequests, newPullRequests, new Function<PullRequest, Void>() {
            @Override
            public Void apply(PullRequest pullRequest) {
                count.incrementAndGet();
                return null;
            }
        });

        Assert.assertEquals(1, count.get());
    }

}
