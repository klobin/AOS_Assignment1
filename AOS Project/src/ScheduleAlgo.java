import java.util.List;

public class ScheduleAlgo {
    public static void main(String[] args) {
        FCFS fcfc = new FCFS();
        SJF sjf = new SJF();
        HPF hpf = new HPF();
        SRT srt = new SRT();
        HPFP hpfp = new HPFP();
        Roundrobin rb = new Roundrobin();
        float turnaroundTime = 0;
        float responseTime = 0;
        float waitingTime = 0;

       List<Process> processes = Process.generateProcesses(10);

        for (int i = 0; i < 5; i++) {
            Process.setCh('A');
            Stat s = fcfc.run(Process.generateProcesses(10));
            turnaroundTime = turnaroundTime + s.getTurnAroudTime();
            responseTime = responseTime + s.getResponseTime();
            waitingTime = waitingTime + s.getWaitingTIme();
        }

        System.out.println("\n\nCombined Stats for FCFS over 5 sets of input:");
        System.out.println("Turnaround Time: " + turnaroundTime / 5);
        System.out.println("Response Time: " + responseTime / 5);
        System.out.println("Waiting Time: " + waitingTime / 5);

        turnaroundTime = 0;
        responseTime = 0;
        waitingTime = 0;

        System.out.println("\nShortest Job First Results");

        for (int i = 0; i < 5; i++) {
            Process.setCh('A');
            Stat s = sjf.runSJF(Process.generateProcesses(10));
            turnaroundTime = turnaroundTime + s.getTurnAroudTime();
            responseTime = responseTime + s.getResponseTime();
            waitingTime = waitingTime + s.getWaitingTIme();
        }

        System.out.println("\n\nCombined Stats for Shortest Job First over 5 sets of input:");
        System.out.println("Turnaround Time: " + turnaroundTime / 5);
        System.out.println("Response Time: " + responseTime / 5);
        System.out.println("Waiting Time: " + waitingTime / 5);

        turnaroundTime = 0;
        responseTime = 0;
        waitingTime = 0;

        System.out.println("\nShortest remaining time First Results");

        for (int i = 0; i < 5; i++) {
            Process.setCh('A');
            Stat s = srt.findavgTime(Process.generateProcesses(10), 2);
            turnaroundTime = turnaroundTime + s.getTurnAroudTime();
            responseTime = responseTime + s.getResponseTime();
            waitingTime = waitingTime + s.getWaitingTIme();
        }

        System.out.println("\n\nCombined Stats for SRT over 5 sets of input:");
        System.out.println("Turnaround Time: " + turnaroundTime / 5);
        System.out.println("Response Time: " + responseTime / 5);
        System.out.println("Waiting Time: " + waitingTime / 5);


        turnaroundTime = 0;
        responseTime = 0;
        waitingTime = 0;

        System.out.println("\nRound Robin Results");

        for (int i = 0; i < 5; i++) {
            Process.setCh('A');
            Stat s = rb.run(Process.generateProcesses(10));
            turnaroundTime = turnaroundTime + s.getTurnAroudTime();
            responseTime = responseTime + s.getResponseTime();
            waitingTime = waitingTime + s.getWaitingTIme();
        }

        System.out.println("\n\nCombined Stats for Round robin over 5 sets of input:");
        System.out.println("Turnaround Time: " + turnaroundTime / Process.processLen);
        System.out.println("Response Time: " + responseTime / Process.processLen);
        System.out.println("Waiting Time: " + waitingTime / Process.processlen);

        turnaroundTime = 0;
        responseTime = 0;
        waitingTime = 0;

        System.out.println("\nHighest priority First non preamptive Results");

        for (int i = 0; i < 5; i++) {
            Process.setCh('A');
            Stat s = hpf.run(Process.generateProcesses(10));
            turnaroundTime = turnaroundTime + s.getTurnAroudTime();
            responseTime = responseTime + s.getResponseTime();
            waitingTime = waitingTime + s.getWaitingTIme();
        }

        System.out.println("\n\nCombined Stats for Highest priority first non pre over 5 sets of input:");
        System.out.println("Turnaround Time: " + turnaroundTime / 5);
        System.out.println("Response Time: " + responseTime / 5);
        System.out.println("Waiting Time: " + waitingTime / 5);

        turnaroundTime = 0;
        responseTime = 0;
        waitingTime = 0;

        System.out.println("\nHighest priority First preamptive Results");

        for (int i = 0; i < 5; i++) {
            Process.setCh('A');
            Stat s = hpfp.run(Process.generateProcesses(10));
            turnaroundTime = turnaroundTime + s.getTurnAroudTime();
            responseTime = responseTime + s.getResponseTime();
            waitingTime = waitingTime + s.getWaitingTIme();
        }

        System.out.println("\n\nCombined Stats for Highest priority first preamptive over 5 sets of input:");
        System.out.println("Turnaround Time: " + turnaroundTime / 5);
        System.out.println("Response Time: " + responseTime / 5);
        System.out.println("Waiting Time: " + waitingTime / 5);
    }
}
