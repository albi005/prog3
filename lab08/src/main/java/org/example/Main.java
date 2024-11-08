package org.example;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        CaesarFrame frame = new CaesarFrame();
        frame.setVisible(true);
    }
}

