/*
 * Opre HF1 kétszintű ütemező kódvázlat
 * v20241018
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Task> tasks = new ArrayList<>();

        String s;
        while ((s = in.readLine()) != null && !s.isEmpty()) {
            String[] parts = s.split(",");
            if (parts.length < 4) System.exit(-1);
            Task task = new Task();
            task.name = parts[0];
            task.priority = Byte.parseByte(parts[1]);
            task.canStartAt = Long.parseLong(parts[2]);
            task.remainingWorkLength = Integer.parseInt(parts[3]);
            tasks.add(task);
        }

        // globálisan preemptív, statikus prioritásos
        //    1: Shortest Job First
        //    0: RR, időszelet: 2 egység

        // waiting for I/O, not ready to run
        tasks.sort(Comparator.comparingLong(t -> t.canStartAt));
        Queue<Task> waiting = new LinkedList<>(tasks);
        int currentTime = 0;
        Task current = null;
        int currentRunningSince = 0;
        PriorityQueue<Task> highPrioReady = new PriorityQueue<>(Comparator.comparingLong((Task t) -> t.remainingWorkLength).reversed());
        Queue<Task> lowPrioReady = new LinkedList<>();
        ArrayList<Task> readinessHistory = new ArrayList<>();

        while (current != null || waiting.peek() != null) {
            while (waiting.peek() != null && waiting.peek().canStartAt >= currentTime) {
                Task task = waiting.remove();
                if (task.priority == 0)
                    lowPrioReady.add(task);
                else
                    highPrioReady.add(task);
                readinessHistory.add(task);
            }

            int currentRanFor = currentTime - currentRunningSince;
            if (current != null && currentRanFor == current.remainingWorkLength) {
                current = null;
            }

            Task nextLowPrio = lowPrioReady.peek();
            Task nextHighPrio = highPrioReady.peek();
            boolean switchToNextHighPrio =
                    nextHighPrio != null && (current == null || current.priority == 0);
            boolean switchToNextLowPrio =
                    !switchToNextHighPrio && nextLowPrio != null && (current == null || (current.priority == 0 && currentRanFor >= 2));
            boolean shouldSwitch = switchToNextLowPrio || switchToNextHighPrio;
            if (shouldSwitch && current != null) {
                current.remainingWorkLength -= currentRanFor;
                current.canStartAt = currentTime;
                if (current.remainingWorkLength > 0) {
                    assert current.priority == 0;
                    lowPrioReady.add(current);
                }
            }
            if (switchToNextHighPrio) {
                current = highPrioReady.remove();
            }
            if (switchToNextLowPrio) {
                current = lowPrioReady.remove();
            }
            if (shouldSwitch) {
                currentRunningSince = currentTime;
                current.waitingTime += currentTime - current.canStartAt;
                System.out.print(current.name);
            }

            // TODO: optimize
            currentTime++;
        }

        System.out.println();
        System.out.println(String.join(",",
                readinessHistory.stream().map(t -> t.name + ":" + t.waitingTime).collect(Collectors.toCollection(ArrayList::new))));
    }
}

class Task {
    public String name;
    public byte priority;
    public long canStartAt;
    public long remainingWorkLength;
    public long waitingTime = 0;
}