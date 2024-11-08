package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class CaesarFrame extends JFrame {
    public static String caesarCode(String input, char offset) {
        char[] chars = input.toUpperCase(Locale.ROOT).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'A' && c <= 'Z') {
                c -= 'A';
                c += (char) (offset - 'A');
                c %= 26;
                c += 'A';
            }
            chars[i] = c;
        }
        return String.valueOf(chars);
    }

    JTextField textFieldTop;
    JTextField textFieldBottom;
    JButton button;
    JComboBox<Character> comboBox;
    public CaesarFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SwingLab");
        setSize(400, 110);
        setResizable(true);

        JPanel panelTop = new JPanel();
        add(panelTop, BorderLayout.NORTH);
        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new Character[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'}));
        panelTop.add(comboBox);

        textFieldTop = new JTextField();
        textFieldTop.setColumns(20);
        panelTop.add(textFieldTop);

        button = new JButton("Code!");
        panelTop.add(button);

        JPanel panelBottom = new JPanel();
        add(panelBottom, BorderLayout.SOUTH);

        panelBottom.add(new JLabel("Output:"));

        textFieldBottom = new JTextField();
        textFieldBottom.setEnabled(false);
        textFieldBottom.setColumns(20);
        panelBottom.add(textFieldBottom);

        OkButtonActionListener listener = new OkButtonActionListener();
        button.addActionListener(listener);
    }

    public class OkButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            textFieldBottom.setText(caesarCode(textFieldTop.getText(), (Character) comboBox.getSelectedItem()));
        }
    }
}
