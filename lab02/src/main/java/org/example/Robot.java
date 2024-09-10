package org.example;

public class Robot extends Jatekos {
    private static int lastId = 0;

    private static int getNextId() {
        lastId++;
        return lastId;
    }

    private int id;

    public Robot() {
        id = getNextId();
    }

    @Override
    public void lep(){
        System.out.println(this);
        int kor = asztal.getKor();
        System.out.println("Kör: " + kor);
    }

    @Override
    public String toString() {
        return "Robot" + id;
    }
}
