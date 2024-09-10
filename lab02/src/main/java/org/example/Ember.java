package org.example;

import java.util.Scanner;

public class Ember extends Jatekos {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void lep() {
        System.out.println(this);
        int kor = asztal.getKor();
        System.out.println("Kör: " + kor);
        int tet = scanner.nextInt();
        asztal.emel(tet);
    }

    @Override
    public String toString() {
        return "Ember";
    }
}
