package akunevich.pullrequest;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN;

public class HelloToolWindow implements ToolWindowFactory {
    JPanel panel = new JPanel();


    public HelloToolWindow() {

    }


    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {


        JTable jTable = new JBTable(new PullRequestsTableModel(
                Stream.of(
                        new PullRequestsTableModelItem("1", "11"),
                        new PullRequestsTableModelItem("1", "11"),
                        new PullRequestsTableModelItem("1", "11"),
                        new PullRequestsTableModelItem("1", "11"),
                        new PullRequestsTableModelItem("1", "11"),
                        new PullRequestsTableModelItem("1", "11"),
                        new PullRequestsTableModelItem("1", "11"),
                        new PullRequestsTableModelItem("2", "22"))
                        .collect(Collectors.toList())
        ));
        jTable.setAutoResizeMode(AUTO_RESIZE_LAST_COLUMN);


        JScrollPane scrollPane = new JBScrollPane(jTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(panel, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public void init(ToolWindow window) {

    }

    @Override
    public boolean shouldBeAvailable(@NotNull Project project) {
        return true;
    }

    @Override
    public boolean isDoNotActivateOnStart() {
        return true;
    }

}
