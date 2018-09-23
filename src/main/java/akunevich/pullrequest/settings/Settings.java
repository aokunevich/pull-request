package akunevich.pullrequest.settings;

public class Settings {

    private String name;
    private String url;
    private String project;
    private String repository;
    private String username;
    private String password;


    public Settings() {
    }


    public Settings(String name, String url, String project, String repository, String username, String password) {
        this.name = name;
        this.url = url;
        this.project = project;
        this.repository = repository;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Settings{" +
                " name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", project='" + project + '\'' +
                ", repository='" + repository + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
