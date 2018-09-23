package akunevich.pullrequest.settings;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MultiSettingsForm {
    private JPanel panel;
    private JCheckBox enabledCheckBox;
    private JTextField urlTextField;
    private JTextField projectTextField;
    private JTextField repositoryTextField;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton addNewConfigurationButton;
    private JTable savedSettingsTable;
    private JTextField nameTextField;

    private MultiSettings multiSettings;


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    public MultiSettings apply() {
        multiSettings.setEnabled(enabledCheckBox.isSelected());
        return multiSettings;
    }

    public JComponent getPanel() {
        return panel;
    }

    public MultiSettings reset() {
        return new MultiSettings();
    }

    public void create(MultiSettings multiSettings) {
        this.multiSettings = multiSettings;
        enabledCheckBox.setSelected(multiSettings.getEnabled());

        String[] columnNames = fillTable();

        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                ((DefaultTableModel) table.getModel()).removeRow(modelRow);

                multiSettings.getSettings().remove(modelRow);
            }
        };

        ButtonColumn deleteButton = new ButtonColumn(savedSettingsTable, delete, columnNames.length - 1);
        deleteButton.setMnemonic(KeyEvent.VK_D);

        addNewConfigurationButton.addActionListener(e -> {
            multiSettings.getSettings().add(new Settings(
                    nameTextField.getText(),
                    urlTextField.getText(),
                    projectTextField.getText(),
                    repositoryTextField.getText(),
                    usernameTextField.getText(),
                    String.valueOf(passwordTextField.getPassword())
            ));
            clearNewSettingFields();
            fillTable();
        });

    }

    private void clearNewSettingFields() {
        nameTextField.setText("");
        urlTextField.setText("");
        projectTextField.setText("");
        repositoryTextField.setText("");
        usernameTextField.setText("");
        passwordTextField.setText("");
    }

    @NotNull
    private String[] fillTable() {
        String[] columnNames = {"Name", "URL", "Project", "Repository", "Username", ""};

        Object[][] data = multiSettings.getSettings().stream().map(savedSettingsItem ->
                new Object[]{
                        savedSettingsItem.getName(),
                        savedSettingsItem.getUrl(),
                        savedSettingsItem.getProject(),
                        savedSettingsItem.getRepository(),
                        savedSettingsItem.getUsername(),
                        "delete"
                }).toArray(Object[][]::new);


        DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames);

        savedSettingsTable.setModel(defaultTableModel);
        return columnNames;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(9, 2, new Insets(0, 0, 0, 0), -1, -1));
        enabledCheckBox = new JCheckBox();
        enabledCheckBox.setText("Enabled");
        panel.add(enabledCheckBox, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("URL");
        panel.add(label1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        urlTextField = new JTextField();
        urlTextField.setText("");
        panel.add(urlTextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Project");
        panel.add(label2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Repository");
        panel.add(label3, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Username");
        panel.add(label4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Password");
        panel.add(label5, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        projectTextField = new JTextField();
        panel.add(projectTextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        repositoryTextField = new JTextField();
        panel.add(repositoryTextField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        usernameTextField = new JTextField();
        panel.add(usernameTextField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordTextField = new JPasswordField();
        panel.add(passwordTextField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addNewConfigurationButton = new JButton();
        addNewConfigurationButton.setText("Add New Configuration");
        panel.add(addNewConfigurationButton, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        savedSettingsTable = new JTable();
        panel.add(savedSettingsTable, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Name");
        panel.add(label6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTextField = new JTextField();
        panel.add(nameTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
