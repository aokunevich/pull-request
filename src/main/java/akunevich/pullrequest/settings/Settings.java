package akunevich.pullrequest.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "PullRequestSettings")
public class Settings implements PersistentStateComponent<Settings> {

    private Boolean enabled;
    private String project;
    private String repository;
    private String username;
    private String password;

    public Boolean getEnabled() {
        return enabled == null ? Boolean.FALSE : enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isEnabled() {
        return enabled == null ? Boolean.FALSE : enabled;
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

    @Nullable
    @Override
    public Settings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull Settings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @Override
    public String toString() {
        return "Settings{" +
                " enabled='" + enabled + '\'' +
                ", project='" + project + '\'' +
                ", repository='" + repository + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
