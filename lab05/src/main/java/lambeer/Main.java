package lambeer;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Beer> beers = new ArrayList<>();
    static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    static Map<String, Comparator<Beer>> comps = new HashMap<>();
    static List<String> lparams = new LinkedList<>();

    static {
        comps.put("name", Comparator.comparing(a -> a.name));
        comps.put("style", Comparator.comparing(a -> a.style));
        comps.put("strength", Comparator.comparing(b -> b.strength));
        lparams.addAll(comps.keySet());
    }

    public static void main(String[] args) throws IOException {
        beers.add(new Beer("sor1", "stilus2", 4.2));
        beers.add(new Beer("sor2", "stilus1", 2.2));
        beers.add(new Beer("sor2", "stilus2", 3.2));
        beers.add(new Beer("sor1", "stilus1", 2.2));

        HashMap<String, Command> commands = new HashMap<>();
        commands.put("exit", Main::exit);
        commands.put("add", Main::add);
        commands.put("list", Main::list);
        commands.put("search", Main::search);
        commands.put("find", Main::find);
        commands.put("load", Main::load);
        commands.put("save", Main::save);
        commands.put("delete", Main::delete);

        while (true) {
            String line = r.readLine();
            String[] cmd = line.split(" ");
            Command command = commands.get(cmd[0]);
            if (command == null) {
                System.out.println("unknown command.");
                continue;
            }
            command.execute(cmd);
        }
    }

    protected static void load(String[] cmd) {
        try {
            FileInputStream fis = new FileInputStream(cmd[1]);
            ObjectInputStream ois = new ObjectInputStream(fis);
            beers = (ArrayList<Beer>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            //
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
            if (!comps.containsKey(cmd[1]))
                System.out.println("unknown comparator.");
            else {
                lparams.remove(cmd[1]);
                lparams.addFirst(cmd[1]);
            }
        }
        var it = lparams.iterator();
        Comparator<Beer> comparator =
                comps.get(it.next())
                        .thenComparing(comps.get(it.next()))
                        .thenComparing(comps.get(it.next()));
        beers.sort(comparator);
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