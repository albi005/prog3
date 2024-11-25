import chess.history.HistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HistoryServiceTests {
    @Test
    public void testHistoryService() {
        TestHistoryService historyService = new TestHistoryService("history.test.json");
        historyService.clear();
        historyService.addMatch("white", "black", "white");
        historyService.addMatch("white", "black", "black");
        historyService.addMatch("white", "black", null);
        historyService.save();
        historyService.clear();
        historyService.load();
        Assertions.assertEquals(
                3,
                historyService
                        .getUsers()
                        .stream()
                        .flatMap(u -> u.matches().stream())
                        .distinct()
                        .count());

    }

    public static class TestHistoryService extends HistoryService {
        public TestHistoryService(String filename) {
            super(filename);
        }

        @Override
        public void load() {
            clear();
            super.load();
        }
    }
}
