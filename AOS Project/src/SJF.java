import java.util.*;

public class SJF {
    private int nextEligibleProcess(List<Process> processes, HashSet<Integer> visited) {
        int min = Integer.MAX_VALUE;
        int res = -1;
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).getRunTime() < min && !visited.contains(i)) {
                res = i;
                min = processes.get(i).getRunTime();
            }
        }
        return res;
    }

    public Stat run(List<Process> processes) {
        int totalTurnAroundTime = 0;
        int totalWaitingTime = 0;
        int totalResponseTime = 0;
        StringBuilder processesRunning = new StringBuilder();
        HashSet<Integer> visited = new HashSet<>();

        System.out.println("\n\nRunning Shortest Job First");
        System.out.println("Name" + "\t" + "Arrival Time" + "\t" + "Run Time" + "\t" + "Priority" + "\t" + "Turn Around Time" + "\t" + "Waiting Time" + "\t" + "Response Time");

        int nextIdx = nextEligibleProcess(processes, visited);
        int clock = processes.get(nextIdx).getArrivalTime();

        while (nextIdx >= 0) {
            clock = clock + processes.get(nextIdx).getRunTime();
            processes.get(nextIdx).setCompletionTime(clock);
            processes.get(nextIdx).setTurnAroundTime(processes.get(nextIdx).getCompletionTime() - processes.get(nextIdx).getArrivalTime());
            int wt = processes.get(nextIdx).getTurnAroundTime() - processes.get(nextIdx).getRunTime();
            if(wt<0){
                wt=0;
            }
            processes.get(nextIdx).setWaitingTime(wt);
            processes.get(nextIdx).setResponseTime(processes.get(nextIdx).getTurnAroundTime() - processes.get(nextIdx).getRunTime());

            processesRunning.append(processes.get(nextIdx).getName());
            visited.add(nextIdx);
            nextIdx = nextEligibleProcess(processes, visited);
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

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of process:");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n]; // at means arrival time
        int bt[] = new int[n]; // bt means burst time
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int st = 0, tot = 0;
        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("enter process " + (i + 1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println("enter process " + (i + 1) + " brust time:");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
            f[i] = 0;
        }

        boolean a = true;
        while (true) {
            int c = n, min = 999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;

            for (int i = 0; i < n; i++) {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min
                 * That process will be executed first
                 */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
                }
            }

            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c == n)
                st++;
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c] - at[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                tot++;
            }
        }

        System.out.println("\npid  arrival brust  complete turn waiting");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgta += ta[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }
        System.out.println("\naverage tat is " + (float) (avgta / n));
        System.out.println("average wt is " + (float) (avgwt / n));
        sc.close();
    }
}