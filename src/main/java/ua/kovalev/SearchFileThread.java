package ua.kovalev;

import java.io.File;

public class SearchFileThread extends Thread {
    private String fileName;
    private File folder;
    private ThreadController threadController;

    public SearchFileThread() {
        super();
    }

    public SearchFileThread(String fileName, File folder, ThreadController threadController) {
        super();
        this.fileName = fileName;
        this.threadController = threadController;
        this.folder = folder;
    }

    @Override
    public void run() {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    if (file.getName().equals(fileName)){
                        System.out.println("Файл найден: " + file.getAbsolutePath());
                        break;
                    }
                }
            }
        }
        threadController.finishThread();
    }
}
