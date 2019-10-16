public class ScheduleAlgo {
    public static void main(String[] args) {
        FCFS fcfc = new FCFS();
        SJF sjf = new SJF();
        Roundrobin rb = new Roundrobin();
        float turnaroundTime = 0;
        float responseTime = 0;
        float waitingTime = 0;

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

        for (int i = 0; i < 5; i++) {
            Process.setCh('A');
            Stat s = sjf.run(Process.generateProcesses(10));
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

        for (int i = 0; i < 5; i++) {
            Process.setCh('A');
            Stat s = rb.run(Process.generateProcesses(10));
            turnaroundTime = turnaroundTime + s.getTurnAroudTime();
            responseTime = responseTime + s.getResponseTime();
            waitingTime = waitingTime + s.getWaitingTIme();
        }

        System.out.println("\n\nCombined Stats for FCFS over 5 sets of input:");
        System.out.println("Turnaround Time: " + turnaroundTime / 5);
        System.out.println("Response Time: " + responseTime / 5);
        System.out.println("Waiting Time: " + waitingTime / 5);

    }
}
