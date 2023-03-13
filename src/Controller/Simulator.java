package Controller;

import Model.Scheduler;
import Model.Server;
import Model.Task;
import View.SimulationView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Simulator implements Runnable {
    private final int nrServers;
    private final int nrTasks;
    private final int simulationT;
    private final int minArrivalT;
    private final int maxArrivalT;
    private final int minServiceT;
    private final int maxServiceT;
    private final Scheduler scheduler;
    private BlockingQueue<Task> generatedTasks;
    private float avgWaitingT;
    private float avgServiceT;
    private int peakHour;
    private int howCrowdedIsPeakHour;
    private PrintWriter pw;
    private final SimulationView view;

    public Simulator(int nrQs, int nrClients, int simulationT, int minArrivalT, int maxArrivalT, int minServiceT, int maxServiceT) {
        this.nrServers = nrQs;
        this.nrTasks = nrClients;
        this.simulationT = simulationT;
        this.minArrivalT = minArrivalT;
        this.maxArrivalT = maxArrivalT;
        this.minServiceT = minServiceT;
        this.maxServiceT = maxServiceT;
        scheduler = new Scheduler(nrServers);
        generateRandomTasks();
        setAvgServiceT();
        view = new SimulationView(nrServers, nrTasks);
    }
    @Override
    public void run() {
        int currentTime = 0;
        createOutputFile();
        while (currentTime <= simulationT) {
            for (Task task : generatedTasks) {
                if (task.getTarrival() <= currentTime) {
                    avgWaitingT += scheduler.dispatchTask(task);
                    generatedTasks.remove(task);
                }
            }
            checkPeakHour(currentTime);
            pw.println(currentSituation(currentTime));
            updateView(view, currentTime);
            decrementFirstClientsServiceTime();
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (allClientsAreDone() && currentTime < simulationT) {
                pw.println(currentSituation(currentTime));
                pw.println("Simulation ended earlier, all clients are done!");
                pw.println(simulationResults());
                pw.close();
            }
        }
        pw.println(simulationResults());
        pw.close();
    }
    private void generateRandomTasks( ) {
        generatedTasks = new ArrayBlockingQueue<>(nrTasks);
        List<Task> tasksToSort = new ArrayList<>(nrTasks);
        for (int i = 0; i < nrTasks; i++) {
            Task t = new Task(minArrivalT, maxArrivalT, minServiceT, maxServiceT);
            tasksToSort.add(t);
        }
        Collections.sort(tasksToSort);
        for (int i = 0; i < nrTasks; i++) {
            generatedTasks.add(tasksToSort.get(i));
            tasksToSort.get(i).setId();
        }
    }
    private String currentSituation(int currentTime) {
        StringBuilder output = new StringBuilder();
        output.append("Time ").append(currentTime).append("\n");
        if (generatedTasks.size() > 0) {
            for (Task t : generatedTasks) {
                output.append(t.toString());
                output.append(" ");
            }
        } else {
            output.append("-empty-");
        }
        output.append('\n');
        for (Server s : scheduler.getServers()) {
            output.append(s.toString());
            output.append('\n');
        }
        return output.toString();
    }
    private String simulationResults() {
        StringBuilder output = new StringBuilder();
        output.append("The average service time was ");
        output.append(this.avgServiceT);
        output.append(".\nThe average waiting time was ");
        setAvgWaitingT();
        output.append(this.avgWaitingT);
        output.append(".\nThe peak hour was at time ");
        output.append(peakHour);
        output.append(".");
        return output.toString();
    }

    private void setAvgServiceT() {
        for (Task t : generatedTasks) {
            this.avgServiceT += t.getTservice();
        }
        this.avgServiceT /= (float) nrTasks;
    }


    private void decrementFirstClientsServiceTime() {
        for (Server s : scheduler.getServers()) {
            if (s.getTasks().peek() != null)
                s.getTasks().peek().decrementTservice();
        }
    }

    private void setAvgWaitingT() {
        avgWaitingT /= (float) nrTasks;
    }
    private void checkPeakHour(int currentTime) {
        int timeUntilAllQsAreEmpty = 0;
        for (Server s : scheduler.getServers()) {
            for (Task t : s.getTasks()) {
                timeUntilAllQsAreEmpty += t.getTservice();
            }
        }
        if (timeUntilAllQsAreEmpty > howCrowdedIsPeakHour) {
            peakHour = currentTime;
            howCrowdedIsPeakHour = timeUntilAllQsAreEmpty;
        }
    }

    private void createOutputFile() {
        File file = new File("simulationLog.txt");
        try {
            FileWriter fw = new FileWriter(file);
            pw = new PrintWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean allClientsAreDone() {
        if (generatedTasks.isEmpty()) {
            for (Server s : scheduler.getServers()) {
                if (s.getTasks().size() > 0) {
                    return false;
                }
            }
            return true;
        } else
            return false;
    }
    private void updateView(SimulationView view, int currentTime) {
        if(allClientsAreDone())
            return;
        view.getTimeLabel().setText("TIME=" + currentTime);
        for (int i = 0; i < nrServers; i++) {
            view.getServerLabels()[i].setText(scheduler.getServers().get(i).toString());
        }
        if (generatedTasks.isEmpty()) {
            view.getTaskLabels()[0].setText("- no clients left -");
            for (int i = 1; i < nrTasks; i++) {
                view.getTaskLabels()[i].setText("");
            }
        } else {
            int i = 0;
            for (Task t : generatedTasks) {
                view.getTaskLabels()[i].setText(t.toString());
                i++;
            }
            if (i < nrTasks) {
                for (int j = i; j < nrTasks; j++) {
                    view.getTaskLabels()[j].setText("");
                }
            }
        }
    }
}
