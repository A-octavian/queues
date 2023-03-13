package Model;

public class Task implements Comparable<Task> {
    private static int nrCustomers = 0;
    private final int Tarrival;
    private int Tservice;
    private int id;

    public Task(int minArrivalT, int maxArrivalT, int minServiceT, int maxServiceT) {
        Tarrival = (int) (Math.random() * (maxArrivalT - minArrivalT) + minArrivalT);
        Tservice = (int) (Math.random() * (maxServiceT - minServiceT) + minServiceT);
    }
    public void setId() {
        nrCustomers++;
        id = nrCustomers;
    }
    @Override
    public java.lang.String toString() {
        return "(" + id + "," + Tarrival + "," + Tservice + ")";
    }
    public int getTarrival() {
        return Tarrival;
    }
    public int getTservice() {
        return Tservice;
    }
    public void decrementTservice( ) {
        Tservice--;
    }
    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.getTarrival(), o.getTarrival());
    }
}
