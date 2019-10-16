public class Stat {
    private float turnAroundTime;
    private float responseTime;
    private float waitingTIme;

    public Stat(float turnAroundTime, float responseTime, float waitingTIme) {
        this.turnAroundTime = turnAroundTime;
        this.responseTime = responseTime;
        this.waitingTIme = waitingTIme;
    }

    public float getTurnAroudTime() {
        return turnAroundTime;
    }

    public void setTurnAroudTime(float turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public float getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(float responseTime) {
        this.responseTime = responseTime;
    }

    public float getWaitingTIme() {
        return waitingTIme;
    }

    public void setWaitingTIme(float waitingTIme) {
        this.waitingTIme = waitingTIme;
    }
}
