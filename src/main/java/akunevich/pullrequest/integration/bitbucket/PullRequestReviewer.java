package akunevich.pullrequest.integration.bitbucket;

public class PullRequestReviewer {
    private BitBucketUser user;
    private PullRequestReviewerStatus status;

    public PullRequestReviewerStatus getStatus() {
        return status;
    }

    public void setStatus(PullRequestReviewerStatus status) {
        this.status = status;
    }

    public BitBucketUser getUser() {
        return user;
    }

    public void setUser(BitBucketUser user) {
        this.user = user;
    }
}
