import view.LoginUI;

import javax.swing.*;

public class LoginUI_test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginUI loginUI = new LoginUI();
                loginUI.setVisible(true);
            }
        });
    }
}


