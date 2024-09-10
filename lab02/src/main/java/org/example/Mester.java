package org.example;

public class Mester extends Jatekos {
    private final int mesterFokozat;
    public Mester(int mesterFokozat) {
        this.mesterFokozat = mesterFokozat;
    }

    @Override
    public void lep() {
        System.out.println(this);
        int kor = asztal.getKor();
        System.out.println("Kor: " + kor);
        asztal.emel(mesterFokozat);
    }

    @Override
    public String toString() {
        return "Mester" + mesterFokozat;
    }
}
