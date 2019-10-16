import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
        System.out.println("\n\nRunning HPF Preemptive");
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

        List<Process> ranProcesses = new ArrayList<>();
        HashMap<Character, Integer> visited = new HashMap<Character, Integer>();
        HashMap<Character, Integer> runTime = new HashMap<Character, Integer>();

        for(Process p : processes) {
            runTime.put(p.getName(), p.getRunTime());
        }

        int i = 0;
        while (true) {
            Process x = findSmallestArrivalTime(q1, q2, q3, q4, elapsedTime);
            if(x == null) {
                break;
            }

            if(elapsedTime<= x.getArrivalTime()) {
                elapsedTime = x.getArrivalTime();
            }

            processesRunning.append(x.getName());

            if(!visited.containsKey(x.getName())) {
                x.setResponseTime(elapsedTime-x.getArrivalTime());
                visited.put(x.getName(), x.getArrivalTime());
                if(x.getRunTime() == 0) {
                    x.setTurnAroundTime(elapsedTime - x.getArrivalTime() + 1);
                }
            }else if(x.getRunTime() == 0){
                int wt = elapsedTime - runTime.get(x.getName()) - x.getArrivalTime();
                if(wt < 0) {
                    wt = 0;
                }
                x.setWaitingTime(wt);
                x.setTurnAroundTime(elapsedTime - x.getArrivalTime() + 1);
            }
            totalResponseTime = totalResponseTime + x.getResponseTime();
            totalWaitingTime = totalWaitingTime + x.getWaitingTime();
            totalTurnAroundTime = totalTurnAroundTime + x.getTurnAroundTime();
            elapsedTime = elapsedTime +1;
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
            Process p = q1.peek();
            p.setRunTime(p.getRunTime() - 1);
            if(p.getRunTime() == 0) {
                return q1.poll();
            }
            return q1.peek();
        }

        if (!q2.isEmpty() && q2.peek().getArrivalTime() <= elapsedTime) {
            Process p = q2.peek();
            p.setRunTime(p.getRunTime() - 1);
            if(p.getRunTime() == 0) {
                return q2.poll();
            }
            return q2.peek();
        }

        if (!q3.isEmpty() && q3.peek().getArrivalTime() <= elapsedTime) {
            Process p = q3.peek();
            p.setRunTime(p.getRunTime() - 1);
            if(p.getRunTime() == 0) {
                return q3.poll();
            }
            return q3.peek();
        }

        if (!q4.isEmpty() && q4.peek().getArrivalTime() <= elapsedTime) {
            Process p = q4.peek();
            p.setRunTime(p.getRunTime() - 1);
            if(p.getRunTime() == 0) {
                return q4.poll();
            }
            return q4.peek();
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

        if(minQue.peek() == null) {
            return null;
        }
        if (minQue.peek().getRunTime() == 1) {
            Process p = minQue.peek();
            p.setRunTime(p.getRunTime() - 1);
            return minQue.poll();
        }

        Process p = minQue.peek();
        if(p == null) {
            return null;
        }
        p.setRunTime(p.getRunTime() - 1);
        return p;
    }
}