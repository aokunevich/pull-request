package akunevich.pullrequest.detector;

import akunevich.pullrequest.integration.bitbucket.PullRequest;
import akunevich.pullrequest.integration.bitbucket.PullRequestBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewPullRequestDetectorTest {

    @DisplayName("No changes")
    @Test
    public void test1() {

        List<PullRequest> pullRequests = Stream.of(
                new PullRequestBuilder().withId(1).build(),
                new PullRequestBuilder().withId(2).build())
                .collect(Collectors.toList());

        List<PullRequest> newPullRequests = Stream.of(
                new PullRequestBuilder().withId(1).build(),
                new PullRequestBuilder().withId(2).build())
                .collect(Collectors.toList());

        AtomicInteger count = new AtomicInteger(0);

        boolean result = new NewPullRequestDetector().detect(pullRequests, newPullRequests, new Function<PullRequest, Void>() {
            @Override
            public Void apply(PullRequest pullRequest) {
                count.incrementAndGet();
                return null;
            }
        });

        Assert.assertFalse(result);
        Assert.assertEquals(0, count.get());
    }

    @DisplayName("One new pull request")
    @Test
    public void test2() {

        List<PullRequest> pullRequests = Stream.of(
                new PullRequestBuilder().withId(1).build(),
                new PullRequestBuilder().withId(2).build())
                .collect(Collectors.toList());

        List<PullRequest> newPullRequests = Stream.of(
                new PullRequestBuilder().withId(1).build(),
                new PullRequestBuilder().withId(2).build(),
                new PullRequestBuilder().withId(3).build())
                .collect(Collectors.toList());

        AtomicInteger count = new AtomicInteger(0);

        boolean result = new NewPullRequestDetector().detect(pullRequests, newPullRequests, new Function<PullRequest, Void>() {
            @Override
            public Void apply(PullRequest pullRequest) {
                count.incrementAndGet();
                return null;
            }
        });

        Assert.assertTrue(result);
        Assert.assertEquals(1, count.get());
    }

    @DisplayName("One removed pull request")
    @Test
    public void test3() {

        List<PullRequest> pullRequests = Stream.of(
                new PullRequestBuilder().withId(1).build(),
                new PullRequestBuilder().withId(2).build())
                .collect(Collectors.toList());

        List<PullRequest> newPullRequests = Stream.of(
                new PullRequestBuilder().withId(1).build())
                .collect(Collectors.toList());

        AtomicInteger count = new AtomicInteger(0);

        boolean result = new NewPullRequestDetector().detect(pullRequests, newPullRequests, new Function<PullRequest, Void>() {
            @Override
            public Void apply(PullRequest pullRequest) {
                count.incrementAndGet();
                return null;
            }
        });

        Assert.assertFalse(result);
        Assert.assertEquals(0, count.get());
    }
}
