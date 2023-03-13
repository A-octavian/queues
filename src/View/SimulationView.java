package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SimulationView extends JFrame {
    private final JPanel contentPane;
    private final JLabel[] serverLabels;
    private final JLabel[] taskLabels;
    private final JLabel timeLabel;

    public SimulationView(int nrQs, int nrClients) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel( );
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        timeLabel = new JLabel("TIME=0");
        timeLabel.setBounds(40, 0, 70, 15);
        contentPane.add(timeLabel);

        JLabel remainingClientsLabel = new JLabel("REMAINING CLIENTS");
        remainingClientsLabel.setBounds(650, 0, 200, 15);
        contentPane.add(remainingClientsLabel);
        JLabel clientFormatLabel = new JLabel("(ID, ARRIVAL TIME, SERVICE TIME)");
        clientFormatLabel.setBounds(650, 20, 200, 15);
        contentPane.add(clientFormatLabel);

        serverLabels = new JLabel[nrQs];
        for (int i = 0; i < nrQs; i++) {
            serverLabels[i] = new JLabel("");
            serverLabels[i].setBounds(40, 20 * (i + 2), 700, 15);
            contentPane.add(serverLabels[i]);
        }
        taskLabels = new JLabel[nrClients];
        for (int i = 0; i < nrClients; i++) {
            taskLabels[i] = new JLabel("");
            taskLabels[i].setBounds(750, 20 * (i + 2), 100, 15);
            contentPane.add(taskLabels[i]);
        }
        this.setVisible(true);
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JLabel[] getServerLabels() {
        return serverLabels;
    }

    public JLabel[] getTaskLabels() {
        return taskLabels;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }
}
