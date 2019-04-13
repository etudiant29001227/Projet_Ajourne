package com.example.projet_ajourn;

public class Timer extends Thread {

    private long timeLifeInMilliSeconds, intervalTick;
    private static long SECONDS_TO_ADDED = 1000;
    private boolean addTime = false, removeTime = false;

    public Timer(long timeLifeInMilliSeconds, long intervalTick){
        this.timeLifeInMilliSeconds = timeLifeInMilliSeconds;
        this.intervalTick = intervalTick;
        this.setDaemon(true);
    }

    public long getTimeLifeInMilliSeconds(){return  timeLifeInMilliSeconds;}

    public void setTimeLifeInMilliSeconds(long timeLifeInMilliSeconds) {
        this.timeLifeInMilliSeconds = timeLifeInMilliSeconds;
    }

    public void setSecondsAdded(long secondsAdded){
        SECONDS_TO_ADDED = secondsAdded;
    }

    public void addTime(){
        addTime = true;
    }

    public void removeTime(){
        removeTime = true;
    }

    public void run(){
        while (timeLifeInMilliSeconds>0) {
            try {
                sleep(intervalTick);
                timeLifeInMilliSeconds -= intervalTick;
                if (addTime) {
                    timeLifeInMilliSeconds += SECONDS_TO_ADDED;
                    addTime = false;
                } else if (removeTime) {
                    timeLifeInMilliSeconds -= SECONDS_TO_ADDED;
                    removeTime = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
