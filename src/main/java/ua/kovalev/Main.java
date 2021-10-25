package ua.kovalev;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ServiceSearchFile serviceSearchFile = new ServiceSearchFile("libCryptLib.so", new File("/home/kovalev/Projects/"));
        serviceSearchFile.go();
    }
}
