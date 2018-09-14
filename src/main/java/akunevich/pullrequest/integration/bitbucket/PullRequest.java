package akunevich.pullrequest.integration.bitbucket;

import java.util.List;

public class PullRequest {
    private int id;
    private String title;
    private String description;
    private PullRequestState state;
    private boolean open;
    private boolean closed;
    private PullRequestAuthor author;
    private List<PullRequestReviewer> reviewers;

    public List<PullRequestReviewer> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<PullRequestReviewer> reviewers) {
        this.reviewers = reviewers;
    }

    public PullRequestAuthor getAuthor() {
        return author;
    }

    public void setAuthor(PullRequestAuthor author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PullRequestState getState() {
        return state;
    }

    public void setState(PullRequestState state) {
        this.state = state;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
