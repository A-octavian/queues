package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class SetupView extends JFrame{
    private final JPanel contentPane;
    private final JButton runButton;
    private final JTextField nrQsTextField;
    private final JTextField numberClientsTextField;
    private final JTextField simTimeTextField;
    private final JTextField minArrivalTextField;
    private final JTextField maxArrivalTextField;
    private final JTextField minServiceTextField;
    private final JTextField maxServiceTextField;

    public SetupView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 260, 360);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel nrQsLabel = new JLabel("Number of queues:");
        nrQsLabel.setBounds(10, 60, 120, 20);
        contentPane.add(nrQsLabel);

        nrQsTextField = new JTextField();
        nrQsTextField.setBounds(137, 61, 96, 19);
        contentPane.add(nrQsTextField );
        nrQsTextField.setColumns(10);

        JLabel nrClientsNewLabel = new JLabel("Number of clients:");
        nrClientsNewLabel.setBounds(10, 90, 120, 20);
        contentPane.add(nrClientsNewLabel);

        numberClientsTextField = new JTextField();
        numberClientsTextField.setBounds(137, 91, 96, 19);
        contentPane.add(numberClientsTextField);
        numberClientsTextField.setColumns(10);

        JLabel simTimeLabel = new JLabel("Simulation time:");
        simTimeLabel.setBounds(10, 120, 120, 20);
        contentPane.add(simTimeLabel);

        simTimeTextField = new JTextField();
        simTimeTextField.setBounds(137, 121, 96, 19);
        contentPane.add(simTimeTextField);
        simTimeTextField.setColumns(10);

        JLabel titleLabel = new JLabel("SIMULATION PARAMETERS");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        titleLabel.setBounds(10, 20, 223, 30);
        contentPane.add(titleLabel);

        JLabel minArrivalLabel = new JLabel("MIN arrival time:");
        minArrivalLabel.setBounds(10, 150, 120, 20);
        contentPane.add(minArrivalLabel);

        JLabel maxArrivalLabel = new JLabel("MAX arrival time:");
        maxArrivalLabel.setBounds(10, 180, 120, 20);
        contentPane.add(maxArrivalLabel);

        JLabel minServiceLabel = new JLabel("MIN service time:");
        minServiceLabel.setBounds(10, 210, 120, 20);
        contentPane.add(minServiceLabel);

        JLabel maxServiceLabel = new JLabel("MAX service time:");
        maxServiceLabel.setBounds(10, 240, 120, 20);
        contentPane.add(maxServiceLabel);

        minArrivalTextField = new JTextField();
        minArrivalTextField.setBounds(137, 151, 96, 19);
        contentPane.add(minArrivalTextField);
        minArrivalTextField.setColumns(10);

        maxArrivalTextField = new JTextField();
        maxArrivalTextField.setBounds(137, 181, 96, 19);
        contentPane.add(maxArrivalTextField);
        maxArrivalTextField.setColumns(10);

        minServiceTextField = new JTextField();
        minServiceTextField.setBounds(137, 211, 96, 19);
        contentPane.add(minServiceTextField);
        minServiceTextField.setColumns(10);

        maxServiceTextField = new JTextField();
        maxServiceTextField.setBounds(137, 241, 96, 19);
        contentPane.add(maxServiceTextField);
        maxServiceTextField.setColumns(10);

        runButton = new JButton("RUN SIMULATION");
        runButton.setBounds(44, 283, 170, 25);
        contentPane.add(runButton);
        this.setVisible(true);
    }

    public void runButtonListener(ActionListener actionListener) {
        this.runButton.addActionListener(actionListener);
    }
    public String getNrQs(){
        return nrQsTextField.getText();
    }
    public String getNrClients(){
        return numberClientsTextField.getText();
    }
    public String getSimulationTime(){
        return simTimeTextField.getText();
    }
    public String getMinArrivalTime(){
        return minArrivalTextField.getText();
    }
    public String getMaxArrivalTime(){
        return maxArrivalTextField.getText();
    }
    public String getMinServiceTime(){
        return minServiceTextField.getText();
    }
    public String getMaxServiceTime(){
        return maxServiceTextField.getText();
    }
}
