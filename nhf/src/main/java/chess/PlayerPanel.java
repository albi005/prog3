package chess;

import chess.history.HistoryService;
import chess.history.Match;
import chess.history.Player;

import javax.swing.*;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Allows selecting a single player and shows their game history.
 */
public class PlayerPanel extends JPanel {

    private final JComboBox<String> selector = new JComboBox<>();
    private final DefaultListModel<Match> matchList = new DefaultListModel<>();

    private String playerName;
    private Player player;
    private Consumer<PlayerPanel> onPlayerChanged;
    private boolean isRefreshing;

    public PlayerPanel() {
        super();
        HistoryService historyService = HistoryService.getInstance();

        selector.setEditable(true);
        selector.addItem(null);
        historyService.getUsers().forEach((player) -> selector.addItem(player.name()));
        selector.addActionListener((e) -> {
            if (isRefreshing)
                return;
            if (!Objects.equals(e.getActionCommand(), "comboBoxChanged"))
                return;
            String selected = (String) selector.getSelectedItem();
            if (Objects.equals(selected, playerName))
                return;
            playerName = selected;
            if (onPlayerChanged != null)
                onPlayerChanged.accept(this);
            refreshLists();
        });

        this.add(selector);
        JList<Match> list = new JList<>(matchList);
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Match match = (Match) value;
                boolean draw = match.winner() == null;
                boolean won = !draw && playerName.equals(match.winner().name());
                String opponent = match.white().name().equals(playerName) ? match.black().name() : match.white().name();
                String text = (draw ? "Döntetlen " : (won ? "Győzelem " : "Vereség "))
                        + opponent + " ellen";
                return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
            }
        });
        this.add(new JScrollPane(list));
    }

    public void update() {
        refreshLists();
    }

    /**
     * Gets the name of the selected player.
     */
    public String getPlayerName() {
        return playerName;
    }

    public void setOnPlayerChanged(Consumer<PlayerPanel> onPlayerChanged) {
        this.onPlayerChanged = onPlayerChanged;
    }

    private void refreshLists() {
        isRefreshing = true;
        HistoryService historyService = HistoryService.getInstance();
        matchList.clear();
        player = historyService.getUser(playerName);
        if (player != null) {
            player.matches().forEach(matchList::addElement);
        }

        selector.removeAllItems();
        selector.addItem(null);
        historyService.getUsers().forEach((player) -> selector.addItem(player.name()));
        selector.setSelectedItem(playerName);
        isRefreshing = false;
    }
}
