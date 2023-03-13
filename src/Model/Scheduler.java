package Model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final List<Server> servers;
    private final int maxTasksPerServer = 10;
    public Scheduler(int nrServers) {
        servers = new ArrayList<>(nrServers);
        for (int i = 0; i < nrServers; i++) {
            Server s = new Server(maxTasksPerServer);
            servers.add(s);
            Thread thread = new Thread(s);
            thread.start();
        }
    }
    public int dispatchTask(Task t) {
        int minWaitingTime = Integer.MAX_VALUE;
        Server bestServerForTask = null;
        for (Server s : servers) {
            if (s.getWaitingPeriod().get( ) < minWaitingTime && s.getTasks().size() < maxTasksPerServer) {
                minWaitingTime = s.getWaitingPeriod().get();
                bestServerForTask = s;
            }
        }
        if (bestServerForTask != null) {
            bestServerForTask.addTask(t);
            return minWaitingTime;
        }
        return 0;
    }
    public List<Server> getServers() {
        return servers;
    }
}
