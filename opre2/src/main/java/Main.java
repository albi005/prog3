/*
 * Opre HF2 lapcsere algoritmus - kódvázlat
 * v20241028
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

class Frame {
    public Frame(String id) {
        this.id = id;
    }
    public String id;
    public int address = 0;
    public int loadedAt = Integer.MIN_VALUE;
    public boolean accessed;

    @Override
    public String toString() {
        return id;
    }
}

class Main {


    public static void main(String[] args) throws IOException {
        ArrayList<Integer> pageSequence = new ArrayList<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null && !s.isEmpty()) {
            String[] pageRequests = s.split(",");
            for (String pageId : pageRequests) {
                pageSequence.add(Integer.parseInt(pageId));
            }
        }

        int misses = 0;

        Queue<Frame> fifo = new LinkedList<>();
        fifo.add(new Frame("A"));
        fifo.add(new Frame("B"));
        fifo.add(new Frame("C"));

        HashMap<Integer, Frame> addrToFrame = new HashMap<>();

        int step = 0;
        for (var addr : pageSequence) {
            step++;
            int tries = 0;
            addr = Math.abs(addr);
            if (addrToFrame.containsKey(addr)) {
                Frame frame = addrToFrame.get(addr);
                frame.accessed = true;
                frame.loadedAt = Integer.MIN_VALUE;
                System.out.print("-");
                continue;
            }
            while (tries < 6) {
                Frame frame = fifo.remove();
                fifo.add(frame);
                if (frame.loadedAt < step - 3 && !frame.accessed) {
                    System.out.print(frame);
                    addrToFrame.remove(frame.address);
                    frame.address = addr;
                    addrToFrame.put(addr, frame);
                    frame.accessed = false;
                    frame.loadedAt = step;
                    break;
                }
                frame.accessed = false;
                tries++;
            }
            if (!addrToFrame.containsKey(addr))
                System.out.print("*");
            misses++;
        }
        System.out.println();
        System.out.println(misses);
    }
}
