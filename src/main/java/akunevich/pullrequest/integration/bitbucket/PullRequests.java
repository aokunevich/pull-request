package akunevich.pullrequest.integration.bitbucket;

import java.util.List;

public class PullRequests {

    private int size;
    private int limit;
    private boolean isLastPage;

    private List<PullRequest> values;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public List<PullRequest> getValues() {
        return values;
    }

    public void setValues(List<PullRequest> values) {
        this.values = values;
    }
}


