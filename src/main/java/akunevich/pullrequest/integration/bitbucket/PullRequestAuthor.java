package akunevich.pullrequest.integration.bitbucket;

public class PullRequestAuthor {

    private BitBucketUser user;

    public BitBucketUser getUser() {
        return user;
    }

    public void setUser(BitBucketUser user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "PullRequestAuthor{" +
                "user=" + user +
                '}';
    }
}
