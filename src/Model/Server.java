package Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private static int nrServers;
    private final int id;
    private final BlockingQueue<Task> tasks;
    private final AtomicInteger waitingPeriod;

    public Server(int maxClientsPerServer) {
        nrServers++;
        id = nrServers;
        tasks = new ArrayBlockingQueue<>(maxClientsPerServer);
        waitingPeriod = new AtomicInteger(0);
    }
    @Override
    public void run() {
        while (true) {
            if (!tasks.isEmpty()) {
                try {
                    int time = tasks.peek().getTservice();
                    Thread.sleep(time * 1000);
                    Task t = tasks.take( );
                    waitingPeriod.getAndAdd((-1) * t.getTservice());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.getAndAdd(newTask.getTservice());
    }
    public BlockingQueue<Task> getTasks() {
        return tasks;
    }
    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Queue ");
        output.append(this.id);
        output.append(": ");
        if (this.getTasks().size() > 0) {
            for (Task t : this.getTasks()) {
                output.append(t.toString());
                output.append(" ");
            }
        } else {
            output.append("-empty-");
        }
        return output.toString();
    }
}
