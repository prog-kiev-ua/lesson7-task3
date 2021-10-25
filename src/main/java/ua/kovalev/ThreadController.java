package ua.kovalev;

import java.util.ArrayList;
import java.util.List;

public class ThreadController extends Thread{
    private int maxCounts;
    private int curCounts;
    private List<Thread> threads;
    private boolean stop;

    public ThreadController() {
        super();
        maxCounts = Runtime.getRuntime().availableProcessors();
        threads = new ArrayList<>();
    }

    @Override
    public void run() {
        synchronized (this){
            while (!stop) {
                if(curCounts>=maxCounts) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(!threads.isEmpty()){
                    Thread thread = threads.get(0);
                    threads.remove(0);
                    curCounts++;
                    thread.start();
                }else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    synchronized public void setThread(Thread thread){
        threads.add(thread);
        notifyAll();
    }

    synchronized public void finishThread(){
        curCounts--;
        notifyAll();
    }

    synchronized public void setStop(boolean stop){
//        System.out.println("ThreadController.setStop.stop: " + stop);
        this.stop = stop;
        notifyAll();
    }
}
