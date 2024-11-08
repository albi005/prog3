package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaesarFrameTest {
    @Test
    public void TestA() {
        String input = "AZ";
        String output = CaesarFrame.caesarCode(input, 'A');
        assertEquals("AZ", output);
    }

    @Test
    public void TestB() {
        String input = "AZ";
        String output = CaesarFrame.caesarCode(input, 'B');
        assertEquals("BA", output);
    }
}