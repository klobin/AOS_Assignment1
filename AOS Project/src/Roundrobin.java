import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Roundrobin {
    public Stat run(List<Process> processes) {
        int totalTurnAroundTime = 0;
        int totalWaitingTime = 0;
        int totalResponseTime = 0;

        return roundRobin(processes, 2);

//        return new Stat((float) totalTurnAroundTime / (float) processes.size(),
//                (float) totalResponseTime / (float) processes.size(),
//                (float) totalWaitingTime / (float) processes.size());
    }

    public static Stat roundRobin(List<Process> processes, int n) {
        // result of average times
        int res = 0;
        int resc = 0;
        Map<Character, Integer> startTimeMap = new HashMap<>();

        // for sequence storage
        String seq = new String();

        // copy the burst array and arrival array
        // for not effecting the actual array
        int res_b[] = new int[processes.size()];
        int res_a[] = new int[processes.size()];

        for (int i = 0; i < res_b.length; i++) {
            res_b[i] = processes.get(i).getRunTime();
            res_a[i] = processes.get(i).getArrivalTime();
        }

        // critical time of system
        int t = 0;

        // for store the waiting time
        int w[] = new int[processes.size()];

        // for store the Completion time
        int comp[] = new int[processes.size()];

        while (true) {
            boolean flag = true;
            for (int i = 0; i < processes.size(); i++) {

                // these condition for if
                // arrival is not on zero

                // check that if there come before qtime
                if (res_a[i] <= t) {
                    if (res_a[i] <= n) {
                        if (res_b[i] > 0) {
                            flag = false;
                            if (res_b[i] > n) {

                                // make decrease the b time
                                t = t + n;
                                res_b[i] = res_b[i] - n;
                                res_a[i] = res_a[i] + n;
                                seq += "->" + processes.get(i).getName();
                                startTimeMap.putIfAbsent(processes.get(i).getName(), t);
                            } else {

                                // for last time
                                t = t + res_b[i];

                                // store comp time
                                comp[i] = t - processes.get(i).getArrivalTime();

                                // store wait time
                                w[i] = t - processes.get(i).getRunTime() - processes.get(i).getArrivalTime();
                                res_b[i] = 0;

                                // add sequence
                                seq += "->" + processes.get(i).getName();
                                startTimeMap.putIfAbsent(processes.get(i).getName(), t);

                            }
                        }
                    } else if (res_a[i] > n) {

                        // is any have less arrival time
                        // the coming process then execute them
                        for (int j = 0; j < processes.size(); j++) {

                            // compare
                            if (res_a[j] < res_a[i]) {
                                if (res_b[j] > 0) {
                                    flag = false;
                                    if (res_b[j] > n) {
                                        t = t + n;
                                        res_b[j] = res_b[j] - n;
                                        res_a[j] = res_a[j] + n;
                                        seq += "->" + processes.get(j).getName();
                                        startTimeMap.putIfAbsent(processes.get(i).getName(), t);

                                    } else {
                                        t = t + res_b[j];
                                        comp[j] = t - processes.get(j).getArrivalTime();
                                        w[j] = t - processes.get(j).getRunTime() - processes.get(j).getArrivalTime();
                                        res_b[j] = 0;
                                        seq += "->" + processes.get(j).getName();
                                        startTimeMap.putIfAbsent(processes.get(i).getName(), t);

                                    }
                                }
                            }
                        }

                        // now the previous porcess according to
                        // ith is process
                        if (res_b[i] > 0) {
                            flag = false;

                            // Check for greaters
                            if (res_b[i] > n) {
                                t = t + n;
                                res_b[i] = res_b[i] - n;
                                res_a[i] = res_a[i] + n;
                                seq += "->" + processes.get(i).getName();
                                startTimeMap.putIfAbsent(processes.get(i).getName(), t);

                            } else {
                                t = t + res_b[i];
                                comp[i] = t - processes.get(i).getArrivalTime();
                                w[i] = t - processes.get(i).getRunTime() - processes.get(i).getArrivalTime();
                                res_b[i] = 0;
                                seq += "->" + processes.get(i).getName();
                                startTimeMap.putIfAbsent(processes.get(i).getName(), t);

                            }
                        }
                    }
                }

                // if no process is come on these critical
                else if (res_a[i] > t) {
                    t++;
                    i--;
                }
            }
            // for exit the while loop
            if (flag) {
                break;
            }
        }

        int turnAroundTime = 0;
        int responseTime = 0;
        int waitingTime = 0;

        System.out.println("name ctime wtime");
        for (int i = 0; i < processes.size(); i++) {
            System.out.println(" " + processes.get(i).getName() + " " + comp[i]+ " " + w[i]);
           processes.get(i).setTurnAroundTime(comp[i]); // Turnaround time
           processes.get(i).setResponseTime(startTimeMap.get(processes.get(i).getName()));
           processes.get(i).setWaitingTime(w[i]);

           turnAroundTime += processes.get(i).getTurnAroundTime();
           responseTime += processes.get(i).getResponseTime();
           waitingTime += processes.get(i).getWaitingTime();

            res = res + w[i];
            resc = resc + comp[i];
        }

        System.out.println("Average waiting time is " + (float) res / processes.size());
        System.out.println("Average compilation time is "  + (float) resc / processes.size());
        System.out.println("Sequence is like that " + seq);

        return new Stat(turnAroundTime/ processes.size(),
                responseTime / processes.size(),
                waitingTime /processes.size());

    }
} 
