package akunevich.pullrequest.settings;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PullRequestConfigurable implements Configurable {


    private MultiSettings multiSettings;
    private MultiSettingsForm multiSettingsForm;

    public PullRequestConfigurable(Project project) {
        multiSettings = ServiceManager.getService(project, MultiSettings.class);
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
        multiSettingsForm = new MultiSettingsForm();
        multiSettings.loadState(multiSettings);

        multiSettingsForm.create(multiSettings);
        return multiSettingsForm.getPanel();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
        multiSettings = multiSettingsForm.apply();

/*
        if (settings.isEnabled()) {
            EventBusFactory.INSTANCE.eventBus().post(new PluginEnabledEvent());
        } else {
            EventBusFactory.INSTANCE.eventBus().post(new PluginDisabledEvent());
        }
*/
    }

    @Override
    public void reset() {
        multiSettings = multiSettingsForm.reset();
    }

    @Override
    public void disposeUIResources() {
        multiSettings = null;
    }
}
