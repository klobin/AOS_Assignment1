// Java program to implement Shortest Remaining 
// Time First 

import java.util.List;

public class SRT
{

    public void findWaitingTime(List<Process> processes, int n, int wt[])
    {
        int rt[] = new int[n];

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = processes.get(i).getRunTime();

        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        // Process until all processes gets
        // completed
        while (complete != n) {

            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++)
            {
                if ((processes.get(j).getArrivalTime() <= t) && (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }

            // Reduce remaining time by one
            rt[shortest]--;

            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;

                // Calculate waiting time
                wt[shortest] = finish_time -
                        processes.get(shortest).getRunTime() -
                        processes.get(shortest).getArrivalTime();

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    public void findTurnAroundTime(List<Process> processes, int n,
                                   int wt[], int tat[])
    {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = processes.get(i).getRunTime() + wt[i];
    }

    // Method to calculate average time
    public Stat findavgTime(List<Process> processes, int n)
    {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all
        // processes
        findWaitingTime(processes, n, wt);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(processes, n, wt, tat);



        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
        }
        for (Process p : processes) {
        	System.out.println(p.toString());
    }
        System.out.println("\nAverage Turnaround Time: " + ((float) total_tat / (float) processes.size()));
        System.out.println("Average Waiting Time: " + ((float) total_wt / (float) processes.size()));
        System.out.println("Average Response Time: " + ((float) total_wt / (float) processes.size()));

        return new Stat(total_tat/n,
                total_wt /n,
                total_wt/n);
    }
} 
