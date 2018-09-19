package akunevich.pullrequest.settings;

import akunevich.pullrequest.event.EventBusFactory;
import akunevich.pullrequest.event.PluginDisabledEvent;
import akunevich.pullrequest.event.PluginEnabledEvent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PullRequestConfigurable implements Configurable {


    private SettingsForm settingsForm;
    private Settings settings;

    @SuppressWarnings("FieldCanBeLocal")
    private Project project;

    public PullRequestConfigurable(Project project) {
        this.project = project;
        settings = ServiceManager.getService(project, Settings.class);
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Pull Request Plugin Settings";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "preference.PullRequestPluginConfigurable";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsForm = new SettingsForm();
        settings.loadState(settings);

        settingsForm.create(settings);
        return settingsForm.getPanel();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
        settings = settingsForm.apply();

        if (settings.isEnabled()) {
            EventBusFactory.INSTANCE.eventBus().post(new PluginEnabledEvent());
        } else {
            EventBusFactory.INSTANCE.eventBus().post(new PluginDisabledEvent());
        }
    }

    @Override
    public void reset() {
        settings = settingsForm.reset();
    }

    @Override
    public void disposeUIResources() {
        settingsForm = null;
    }
}
