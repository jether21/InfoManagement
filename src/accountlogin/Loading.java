package accountlogin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loading extends JFrame {
     

Loading () {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Connecting , pls wait...");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 70);
            frame.setLocationRelativeTo(null);

            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);

            frame.setLayout(new BorderLayout());
            frame.add(progressBar, BorderLayout.CENTER);

            frame.setVisible(true);
            
            new Thread(() -> {
                try {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(10); 
                        final int progress = i;
                        SwingUtilities.invokeLater(() -> progressBar.setValue(progress));
                    }
                    
                    SwingUtilities.invokeLater(() -> {
                        frame.dispose();
                        new Viewing();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

   

}

