package chess.history;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

/**
 * Service for persisting and managing game history.
 */
public class HistoryService {
    private static HistoryService instance;
    private final Gson gson = new Gson();

    private final TreeMap<String, Player> users = new TreeMap<>();
    private final ArrayList<Match> matches = new ArrayList<>();
    private final String filename;

    protected HistoryService(String filename) {
        this.filename = filename;
    }

    public static HistoryService getInstance() {
        if (instance == null) {
            instance = new HistoryService("history.json");
            instance.load();
        }
        return instance;
    }


    /**
     * Loads history from a file.
     */
    protected void load() {
        try {
            FileReader reader = new FileReader(filename);
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

    /**
     * Adds a match to the history.
     *
     * @param white  white player name
     * @param black  black player name
     * @param winner winner player name
     */
    public void addMatch(String white, String black, String winner) {
        Player whitePlayer = users.computeIfAbsent(white, Player::new);
        Player blackPlayer = users.computeIfAbsent(black, Player::new);
        Player winnerPlayer = winner == null ? null : users.get(winner);
        Match match = new Match(whitePlayer, blackPlayer, winnerPlayer);
        whitePlayer.matches().add(match);
        blackPlayer.matches().add(match);
        matches.add(match);
    }

    public void clear() {
        users.clear();
        matches.clear();
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(filename);
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

    public Collection<Player> getUsers() {
        return users.values();
    }

    public Player getUser(String name) {
        if (name == null) return null;
        return users.get(name);
    }

    record HistoryEntry(String white, String black, String winner) {
    }
}
