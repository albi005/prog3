package chess.history;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class HistoryService {
    private static HistoryService instance;
    private final Gson gson = new Gson();

    private final TreeMap<String, User> users = new TreeMap<>();
    private final ArrayList<Match> matches = new ArrayList<>();

    private HistoryService() {
    }

    public static HistoryService getInstance() {
        if (instance == null) {
            instance = new HistoryService();
            instance.load();
        }
        return instance;
    }

    private void load() {
            try {
                FileReader reader = new FileReader("history.json");
                var entries = gson.fromJson(reader, HistoryEntry[].class);
                reader.close();
                if (entries == null)
                    return;
                for (HistoryEntry entry : entries) {
                    addMatch(entry.white(), entry.black(), entry.winner());
                }
            } catch (FileNotFoundException e) {
                // ignore
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public void addMatch(String white, String black, String winner) {
        User whiteUser = users.computeIfAbsent(white, User::new);
        User blackUser = users.computeIfAbsent(black, User::new);
        User winnerUser = winner == null ? null : users.get(winner);
        Match match = new Match(whiteUser, blackUser, winnerUser);
        whiteUser.matches().add(match);
        blackUser.matches().add(match);
        matches.add(match);
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter("history.json");
            gson.toJson(matches.stream()
                    .map(m -> new HistoryEntry(
                            m.white().name(),
                            m.black().name(),
                            m.winner() == null ? null : m.winner().name()
                    ))
                    .toList(),
                    writer
            );
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<User> getUsers() {
        return users.values();
    }

    public User getUser(String name) {
        if (name == null) return null;
        return users.get(name);
    }

    record HistoryEntry(String white, String black, String winner) {
    }
}
