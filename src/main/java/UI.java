import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI extends JFrame
{
    
    private static void gatherParts(){
        UI ui = new UI();
        JPanel panel = new JPanel();
        JTextField field = new JTextField();
        JButton button = new JButton("generate");
        panel.setLayout(new BorderLayout());
        panel.add(field, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        ui.add(panel);
        ui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ui.setSize(500, 500);
        ui.setVisible(true);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run() {
                gatherParts();
            }
        });
        
    }
}
