import java.util.List;

public class FCFS {

    /**
     * Driver for FCFS
     *
     * @param processes
     */
    public Stat run(List<Process> processes) {
        int totalTurnAroundTime = 0;
        int totalWaitingTime = 0;
        int totalResponseTime = 0;

        StringBuilder processesRunning = new StringBuilder();
        System.out.println("\n\nRunning First Come First Serve");
        System.out.println("Name" + "\t" + "Arrival Time" + "\t" + "Run Time" + "\t" + "Priority" + "\t" + "Turn Around Time" + "\t" + "Waiting Time" + "\t" + "Response Time");
        processes.get(0).setWaitingTime(0);
        processes.get(0).setResponseTime(0);
        processes.get(0).setTurnAroundTime(processes.get(0).getRunTime());
        for (int i = 1; i < processes.size(); i++) {
            processesRunning.append(processes.get(i).getName());

            int wt = processes.get(i - 1).getRunTime() + processes.get(i - 1).getArrivalTime() + processes.get(i - 1).getWaitingTime();
            wt = wt - processes.get(i).getArrivalTime();
            if (wt < 0) {
                wt = 0;
            }
            processes.get(i).setWaitingTime(wt);
            processes.get(i).setResponseTime(wt);

            processes.get(i).setTurnAroundTime(processes.get(i).getWaitingTime() + processes.get(i).getRunTime());

            totalResponseTime = totalResponseTime + processes.get(i).getResponseTime();
            totalWaitingTime = totalWaitingTime + processes.get(i).getWaitingTime();
            totalTurnAroundTime = totalTurnAroundTime + processes.get(i).getTurnAroundTime();
        }

        for (Process p : processes) {
            System.out.println(p.toString());
        }

        System.out.println("Processes Ran: " + processesRunning.toString());
        System.out.println("\nAverage Turnaround Time: " + ((float) totalTurnAroundTime / (float) processes.size()));
        System.out.println("Average Waiting Time: " + ((float) totalWaitingTime / (float) processes.size()));
        System.out.println("Average Response Time: " + ((float) totalResponseTime / (float) processes.size()));
        return new Stat((float) totalTurnAroundTime / (float) processes.size(),
                (float) totalResponseTime / (float) processes.size(),
                (float) totalWaitingTime / (float) processes.size());
    }
}
