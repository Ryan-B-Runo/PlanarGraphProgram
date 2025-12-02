import javax.swing.*;

public class Driver {

    public static void main(String[] args) {
        MainForm mainForm = new MainForm();
        mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainForm.pack();
        mainForm.setVisible(true);
    }

}
