import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Process implements Comparable<Process> {

    private static char ch = 'A';
    private static Random rand = new Random(0);

    private char name;
    private int arrivalTime;
    private int runTime;
    private int priority;
    private int turnAroundTime;
    private int waitingTime;
    private int responseTime;
    private int completionTime;

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    Process(char name, int arrivalTime, int runTime, int priority) {
        super();
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.runTime = runTime;
        this.priority = priority;
        this.turnAroundTime = 0;
        this.waitingTime = 0;
        this.responseTime = 0;
        this.completionTime = 0;
    }

    public static void setCh(char ch) {
        Process.ch = ch;
    }

    public char getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getRunTime() {
        return runTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public int compareTo(Process o) {
        if (this.arrivalTime == o.arrivalTime) {
            return 0;
        }
        return this.arrivalTime > o.arrivalTime ? 1 : -1;
    }

    @Override
    public String toString() {
        return this.name + "\t\t" + this.arrivalTime + "\t\t\t\t" + this.runTime + "\t\t\t\t" + this.priority + "\t\t\t\t" + this.turnAroundTime + "\t\t\t\t" + this.waitingTime + "\t\t\t\t" + this.responseTime;
    }

    private static Process getRandomProcess() {
        return new Process(ch++, generateRandomInRange(0, 99), generateRandomInRange(1, 10), generateRandomInRange(1, 4));
    }

    private static int generateRandomInRange(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static List<Process> generateProcesses(int count) {
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            processes.add(getRandomProcess());
        }
        Collections.sort(processes);
        System.out.println("Processes:");
        System.out.println("Name" + "\t" + "Arrival Time" + "\t" + "Run Time" + "\t" + "Priority" + "\t" + "Turn Around Time" + "\t" + "Waiting Time" + "\t" + "Response Time");
        for(Process p : processes){
            System.out.println(p.toString());
        }
        return processes;
    }
}
