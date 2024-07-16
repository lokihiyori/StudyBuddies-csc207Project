import javax.swing.*;

public class Main_UI_test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main_UI welcomeUI = new Main_UI();
                welcomeUI.setVisible(true);
            }
        });
    }

}
