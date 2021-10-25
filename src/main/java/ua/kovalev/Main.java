package ua.kovalev;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Не верное кол-во аргументов");
            return;
        }
        File folder = new File(args[1]);
        if (!folder.isDirectory()) {
            System.out.println("Указанный каталог не найден");
            return;
        }

        ServiceSearchFile serviceSearchFile = new ServiceSearchFile(args[0], folder);
        serviceSearchFile.go();
    }
}
