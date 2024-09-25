package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private String wd;

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        wd = System.getProperty("user.dir");
        var reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            var line = reader.readLine();
            var args = line.split(" ");
            var cmd = args[0];

            switch (cmd) {
                case "exit":
                    exit(args);
                case "pwd":
                    pwd(args);
                    break;
                case "ls":
                    ls(args);
                    break;
                case "cd":
                    cd(args);
                    break;
                case "rm":
                    rm(args);
                    break;
                case "mkdir":
                    mkdir(args);
                    break;
                case "mv":
                    mv(args);
                    break;
                case "cp":
                    cp(args);
                    break;
                case "cat":
                    cat(args);
                    break;
                case "length":
                    length(args);
                    break;
                case "grep":
                    grep(args);
                    break;
                case "tail":
                    tail(args);
                    break;
                case null, default:
                    System.out.println("Unknown command");
            }
        }
    }

    protected void exit(String[] args) {
        System.exit(0);
    }

    protected void pwd(String[] args) {
        System.out.print(wd + " ");
        var files = new File(wd).listFiles();
        if (files != null)
            System.out.println(files.length);
    }

    protected void ls(String[] args) {
        var files = new File(wd).listFiles();
        if (files == null)
            throw new IllegalStateException();
        for (var file : files) {
            if (Arrays.asList(args).contains("-l")) {
                System.out.print(file.isDirectory() ? "d " : "f ");
                System.out.print(file.length() + " ");
            }
            System.out.println(file.getName());
        }
    }

    protected void cd(String[] args) {
        var nextWd = new File(wd, args[1]);
        if (!nextWd.isDirectory())
            System.out.println("Invalid cd target");
        else
            wd = nextWd.getAbsolutePath();
    }

    protected void rm(String[] args) {
        if (!new File(wd, args[1]).delete())
            System.out.println("rm unsuccessful");
    }

    protected void mkdir(String[] args) {
        var target = new File(wd, args[1]);
        if (target.isDirectory()) {
            System.out.println(args[1] + " exists already");
            return;
        }
        boolean suc = target.mkdir();
        if (!suc)
            System.out.println("mkdir unsuccessful");
    }

    protected void mv(String[] args) {
        var src = new File(args[1]);
        var dst = new File(args[2]);
        boolean suc = src.renameTo(dst);
        if (!suc)
            System.out.println("mv unsuccessful");
    }

    protected void cp(String[] args) {
        var src = new File(args[1]);
        var dst = new File(args[2]);
        try {
            try (var srcStream = new FileInputStream(src)) {
                try (var dstStream = new FileOutputStream(dst)) {
                    while (true) {
                        int b = srcStream.read();
                        if (b == -1)
                            return;
                        dstStream.write(b);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void cat(String[] args) throws IOException {
        var target = new File(args[1]);
        if (!target.isFile()) System.out.println("not a file");
        else {
            var reader = new BufferedReader(new FileReader(target));
            while (true) {
                int b = reader.read();
                if (b == -1) return;
                System.out.print((char) b);
            }
        }
    }

    protected void length(String[] args) {
        var target = new File(args[1]);
        if (!target.exists()) System.out.println("file does not exist");
        System.out.println(target.length());
    }

    protected void grep(String[] args) {
        var file = new File(args[2]);
        var pattern = ".*" + args[1] + ".*";

        try {
            var scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.matches(pattern))
                    System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    protected void tail(String[] args) {
        int n = 10;
        boolean nSet = args[1].equals("-n");
        if (nSet)
            n = Integer.parseInt(args[2]);
        var file = new File(args[nSet ? 3 : 1]);

        var list = new LinkedList<String>();
        try {
            var scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.addLast(line);
                if (list.size() > n)
                    list.removeFirst();
            }
            for (var line : list) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
}