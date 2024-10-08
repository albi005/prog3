package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    static ArrayList<Beer> beers = new ArrayList<>();
    static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        beers.add(new Beer("sor1", "stil1", 2.2));
        beers.add(new Beer("sor2", "stil2", 4.2));

        while (true) {
            String line = r.readLine();
            String[] cmd = line.split(" ");
            switch (cmd[0]) {
                case "exit":
                    exit(cmd);
                    break;
                case "add":
                    add(cmd);
                    break;
                case "list":
                    list(cmd);
                    break;
                case "search":
                    search(cmd);
                    break;
                case "find":
                    find(cmd);
                    break;
                case "load":
                    load(cmd);
                    break;
                case "save":
                    save(cmd);
                    break;
                case "delete":
                    delete(cmd);
                    break;
                default:
                    break;
            }
        }
    }

    protected static void load(String[] cmd) {
        try {
            FileInputStream fis = new FileInputStream(cmd[1]);
            ObjectInputStream ois = new ObjectInputStream(fis);
            beers = (ArrayList<Beer>) ois.readObject();
            ois.close();
        } catch (Exception e) {
        }
    }

    protected static void save(String[] cmd) {
        try {
            FileOutputStream fos = new FileOutputStream(cmd[1]);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(beers);
            oos.close();
        } catch (Exception e) {
        }
    }

    protected static void exit(String[] cmd) {
        System.exit(0);
    }

    protected static void add(String[] cmd) {
        Beer beer = new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3]));
        beers.add(beer);
    }

    protected static void list(String[] cmd) {
        if (cmd.length >= 2) {
            Comparator<Beer> comparator = Beer.getComparator(cmd[1]);
            if (comparator != null)
                beers.sort(Beer.getComparator(cmd[1]));
        }
        for (Beer beer : beers) {
            System.out.println(beer);
        }
    }

    protected static void search(String[] cmd) {
        String target = cmd[1];
        for (Beer beer : beers) {
            if (beer.name.equals(target))
                System.out.println(beer);
        }
    }

    protected static void find(String[] cmd) {
        String target = cmd[1];
        for (Beer beer : beers) {
            if (beer.name.contains(target))
                System.out.println(beer);
        }
    }

    protected static void delete(String[] cmd) {
        String target = cmd[1];
        beers.removeIf((b) -> b.name.equals(target));
    }
}