package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Asztal {
    private final List<Jatekos> jatekosok = new ArrayList<>();
    private double tet;
    private int kor;
    private double goal;

    public Asztal() {
        ujJatek();
    }

    public void ujJatek() {
        tet = 0;
        kor = 0;
        goal = new Random().nextDouble() * 100;
    }

    public void addJatekos(Jatekos j) throws AsztalTeleException {
        if (jatekosok.size() == 10)
            throw new AsztalTeleException();
        jatekosok.add(j);
        j.setAsztal(this);
    }

    public int getKor() {
        return kor;
    }

    public void emel(double d) {
        tet += d;
    }

    public void kor() throws NincsJatekos {
        if (jatekosok.isEmpty())
            throw new NincsJatekos();
        if (tet >= goal) {
            System.out.println("Vege a jateknak.");
            return;
        }
        kor++;
        for (int i = 0; i < jatekosok.size(); i++) {
            Jatekos jatekos = jatekosok.get(i);
            if (tet >= goal) {
                System.out.println("Jatek vege. Goal: " + goal);
                if (tet - goal < 10)
                    System.out.println(i + 1 + ". jatekos nyert!");
                else
                    System.out.println("Nincs nyertes.");
                return;
            }
            jatekos.lep();
        }
        System.out.println("Tet: " + tet);
    }

    public double getTet() {
        return tet;
    }
}
