package ua.kovalev;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServiceSearchFile {
    private List<Thread> workers = new ArrayList<>();
    private String fileName;
    private File folder;
    private ThreadController threadController;

    public ServiceSearchFile() {
        super();
    }

    public ServiceSearchFile(String fileName, File folder) {
        super();
        this.fileName = fileName;
        this.folder = folder;
        threadController = new ThreadController();
        threadController.start();
    }

    public void go(){
        Thread thread = new SearchFileThread(fileName, folder, this.threadController);
        workers.add(thread);
        threadController.setThread(thread);
        createThreads(folder);
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadController.setStop(true);
    }

    private void createThreads(File folder){
        File[] files = folder.listFiles();
        if(files == null) return;
        for (File f : files) {
            if (f.isDirectory()){
                Thread th = new SearchFileThread(fileName, f, this.threadController);
                workers.add(th);
                threadController.setThread(th);
                createThreads(f);
            }
        }
    }
}
