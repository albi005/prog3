package org.example;

public class Nyuszi extends Jatekos {
    public final String szin;

    public Nyuszi(String szin) {
        this.szin = szin;
    }

    @Override
    public void lep() {
        System.out.println(this);
        int kor = asztal.getKor();
        System.out.println("Kor: " + kor);

        if (asztal.getTet() < 50)
            asztal.emel(5);
        else
            System.out.println("Húha!");
    }

    @Override
    public String toString() {
        return "Nyuszi " + szin;
    }
}
