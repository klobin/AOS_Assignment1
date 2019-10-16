import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HPFP {

    /**
     * Driver for HPF
     *
     * @param processes
     */
    public Stat run(List<Process> processes) {
        int totalTurnAroundTime = 0;
        int totalWaitingTime = 0;
        int totalResponseTime = 0;
        int elapsedTime = processes.get(0).getArrivalTime();

        Queue<Process> q1 = new LinkedList<>();
        Queue<Process> q2 = new LinkedList<>();
        Queue<Process> q3 = new LinkedList<>();
        Queue<Process> q4 = new LinkedList<>();
        StringBuilder processesRunning = new StringBuilder();
        System.out.println("\n\nRunning HPF Preeemptive");
        System.out.println("Name" + "\t" + "Arrival Time" + "\t" + "Run Time" + "\t" + "Priority" + "\t"
                + "Turn Around Time" + "\t" + "Waiting Time" + "\t" + "Response Time");

        for (int i = 0; i < processes.size(); i++) {
            switch (processes.get(i).getPriority()) {
                case 1: {
                    q1.add(processes.get(i));
                    break;
                }
                case 2: {
                    q2.add(processes.get(i));
                    break;
                }
                case 3: {
                    q3.add(processes.get(i));
                    break;
                }
                case 4: {
                    q4.add(processes.get(i));
                    break;
                }
                default: {
                    break;
                }
            }
        }
//        System.out.println("q1 " + q1 + "\n q2 " + q2 + "\n q3: " + q3 + "\n q4 " + q4);
//        processes.get(0).setWaitingTime(0);
//        processes.get(0).setResponseTime(0);
//        processes.get(0).setTurnAroundTime(processes.get(0).getRunTime());
//        elapsedTime = processes.get(0).getRunTime();
//        processesRunning.append(processes.get(0).getName());

        List<Process> ranProcesses = new ArrayList<>();

        int i = 0;
        while (i < processes.size()) {
            Process x = findSmallestArrivalTime(q1, q2, q3, q4, elapsedTime);

            x.setRunTime(x.getRunTime() - 1);

            processesRunning.append(x.getName());
            int wt;
            if (i == 0) {
                wt = 0;
            } else {
                wt = ranProcesses.get(i - 1).getRunTime() + ranProcesses.get(i - 1).getArrivalTime() + ranProcesses.get(i - 1).getWaitingTime();
                wt = wt - x.getArrivalTime();
                if (wt < 0) {
                    wt = 0;
                }
            }
            x.setWaitingTime(wt);
            x.setResponseTime(wt);
            x.setTurnAroundTime(x.getWaitingTime() + x.getRunTime());

            totalResponseTime = totalResponseTime + x.getResponseTime();
            totalWaitingTime = totalWaitingTime + x.getWaitingTime();
            totalTurnAroundTime = totalTurnAroundTime + x.getTurnAroundTime();
            elapsedTime = x.getArrivalTime() + x.getTurnAroundTime();
            ranProcesses.add(x);
            i++;
        }

        for (Process p : ranProcesses) {
            System.out.println(p.toString());
        }

        System.out.println("Process: " + processesRunning.toString());
        System.out.println("\nAverage Turnaround Time: " + ((float) totalTurnAroundTime / (float) processes.size()));
        System.out.println("Average Waiting Time: " + ((float) totalWaitingTime / (float) processes.size()));
        System.out.println("Average Response Time: " + ((float) totalResponseTime / (float) processes.size()));
        return new Stat((float) totalTurnAroundTime / (float) processes.size(),
                (float) totalResponseTime / (float) processes.size(),
                (float) totalWaitingTime / (float) processes.size());
    }

    public Process findSmallestArrivalTime(Queue<Process> q1, Queue<Process> q2, Queue<Process> q3, Queue<Process> q4,
                                           int elapsedTime) {

        if (!q1.isEmpty() && q1.peek().getArrivalTime() <= elapsedTime) {
            return q1.poll();
        }

        if (!q2.isEmpty() && q2.peek().getArrivalTime() <= elapsedTime) {
            return q2.poll();
        }

        if (!q3.isEmpty() && q3.peek().getArrivalTime() <= elapsedTime) {
            return q3.poll();
        }

        if (!q4.isEmpty() && q4.peek().getArrivalTime() <= elapsedTime) {
            return q4.poll();
        }

        int min = Integer.MAX_VALUE;

        Queue<Process> minQue = new LinkedList<>();

        if (!q1.isEmpty() && q1.peek().getArrivalTime() < min) {
            min = q1.peek().getArrivalTime();
            minQue = q1;
        }

        if (!q2.isEmpty() && q2.peek().getArrivalTime() < min) {
            min = q2.peek().getArrivalTime();
            minQue = q2;
        }

        if (!q3.isEmpty() && q3.peek().getArrivalTime() < min) {
            min = q3.peek().getArrivalTime();
            minQue = q3;
        }

        if (!q4.isEmpty() && q4.peek().getArrivalTime() < min) {
            minQue = q4;
        }

        if (minQue.peek().getRunTime() == 1) {
            return minQue.poll();
        }

        Process p = minQue.peek();
        p.setRunTime(p.getRunTime() - 1);
        return p;
    }
}