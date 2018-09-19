package akunevich.pullrequest.integration.bitbucket;

import java.util.List;

public class PullRequestBuilder {
    private PullRequest pullRequest = new PullRequest();

    public PullRequest build() {
        return pullRequest;
    }

    public PullRequestBuilder withId(int id) {
        pullRequest.setId(id);
        return this;
    }

    public PullRequestBuilder withTitle(String title) {
        pullRequest.setTitle(title);
        return this;
    }

    public PullRequestBuilder withDescription(String description) {
        pullRequest.setDescription(description);
        return this;
    }

    public PullRequestBuilder withOpen(boolean open) {
        pullRequest.setOpen(open);
        return this;
    }

    public PullRequestBuilder withClosed(boolean closed) {
        pullRequest.setClosed(closed);
        return this;
    }

    public PullRequestBuilder withState(PullRequestState state) {
        pullRequest.setState(state);
        return this;
    }

    public PullRequestBuilder withAuthor(PullRequestAuthor pullRequestAuthor) {
        pullRequest.setAuthor(pullRequestAuthor);
        return this;
    }

    public PullRequestBuilder withRewei(List<PullRequestReviewer> reviewers) {
        pullRequest.setReviewers(reviewers);
        return this;
    }
}
