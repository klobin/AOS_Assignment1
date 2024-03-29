import java.util.*;

public class SJF {

    public Stat runSJF(List<Process> processes) {
        int n = processes.size();
        int pid[] = new int[n];
        int at[] = new int[n]; // at means arrival time
        int bt[] = new int[n]; // bt means burst time
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int st=0, tot=0;
        float avgwt=0, avgta=0;

        for(int i=0;i<n;i++)
        {
            at[i] = processes.get(i).getArrivalTime();
            bt[i] = processes.get(i).getRunTime();
            pid[i] = i+1;
            f[i] = 0;
        }

        boolean a = true;
        while(true)
        {
            int c=n, min=999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;

            for (int i=0; i<n; i++)
            {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min
                 * That process will be executed first
                 */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
                {
                    min=bt[i];
                    c=i;
                }
            }

            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n)
                st++;
            else
            {
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                tot++;
            }
        }


        for(int i=0;i<n;i++)
        {
            avgwt+= wt[i];
            avgta+= ta[i];
            processes.get(i).setWaitingTime(wt[i]);
            processes.get(i).setTurnAroundTime(ta[i]);
        }


        for (Process p : processes) {
            System.out.println(p.toString());
        }
//
//        System.out.println("Process: " + processes.toString());
        System.out.println("\nAverage Turnaround Time: " +avgta/10 );
        System.out.println("Average Waiting Time: " +  avgwt/10 );
        System.out.println("Average Response Time: " + avgwt/10);

       return new Stat(avgta/n, avgwt/n, avgwt/n);
    }
}