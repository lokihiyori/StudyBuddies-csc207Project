import view.LoggedInView;

import javax.swing.*;

public class Main_UI_test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoggedInView welcomeUI = new LoggedInView();
                welcomeUI.setVisible(true);
            }
        });
    }

}
