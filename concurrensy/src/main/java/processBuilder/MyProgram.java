package processBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyProgram {
    public static void main(String[] args) {
        JFrame f = new JFrame("Our application");
        f.setSize(400, 400);
        f.setLocation(600, 200);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Help");
        JMenuItem about = new JMenuItem("About my program...");
        menu.add(about);
        menuBar.add(menu);
        f.setJMenuBar(menuBar);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyWindow panel = new MyWindow();
        about.addActionListener(MyProgram::showReadme);
        f.setContentPane(panel);
        f.setVisible(true);
    }

    private static void showReadme(ActionEvent actionEvent) {
        //throw new RuntimeException("Not yet implemented");
        Readme.shoeReadme();
    }
}
