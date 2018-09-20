package akunevich.pullrequest.integration.bitbucket;

import java.util.List;

public class PullRequestLinks {
    private List<PullRequestLinkSelf> self;

    public List<PullRequestLinkSelf> getSelf() {
        return self;
    }

    public void setSelf(List<PullRequestLinkSelf> self) {
        this.self = self;
    }
}
