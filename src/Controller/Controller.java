package Controller;

import View.SetupView;

import javax.swing.*;

public class Controller {
    private final SetupView setupView;
    private Simulator simulator;

    public Controller() {
        setupView = new SetupView( );
        setupView.runButtonListener(e -> {
            startSimulator();
            Thread thread = new Thread(simulator);
            thread.start();
        });

    }
    private void startSimulator() {
        try {
            int nrQs = Integer.parseInt(setupView.getNrQs());
            int nrClients = Integer.parseInt(setupView.getNrClients());
            int simulationT = Integer.parseInt(setupView.getSimulationTime());
            int minArrivalT = Integer.parseInt(setupView.getMinArrivalTime());
            int maxArrivalT = Integer.parseInt(setupView.getMaxArrivalTime());
            int minServiceT = Integer.parseInt(setupView.getMinServiceTime());
            int maxServiceT = Integer.parseInt(setupView.getMaxServiceTime());
            simulator = new Simulator(nrQs, nrClients, simulationT, minArrivalT, maxArrivalT, minServiceT, maxServiceT);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(setupView, "Invalid input!");
        }
    }
}
