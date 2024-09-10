package org.example;

public class Kezdo extends Jatekos {
    private final String name;

    public Kezdo(String name) {
        this.name = name;
    }

    public void lep() {
        System.out.println(this);
        int kor = asztal.getKor();
        System.out.println("Kör: " + kor);
        boolean paratlan = kor % 2 == 1;
        if (!paratlan) {
            asztal.emel(1);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
