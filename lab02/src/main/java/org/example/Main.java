package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            Asztal asztal = new Asztal();
            Kezdo k1 = new Kezdo("Kezdo1");
            asztal.addJatekos(k1);
            Kezdo k2 = new Kezdo("Kezdo2");
            asztal.addJatekos(k2);
            Robot robot = new Robot();
            asztal.addJatekos(robot);
            for (int i = 0; i < 3; i++)
                asztal.kor();
            asztal.ujJatek();

            asztal.addJatekos(new Mester(5));
            asztal.addJatekos(new Nyuszi("lila"));
            for (int i = 0; i < 10; i++)
                asztal.kor();
            asztal.ujJatek();

            asztal.addJatekos(new Ember());
            for (int i = 0; i < 10; i++)
                asztal.kor();
            asztal.ujJatek();

//            asztal = null;
//            System.gc();
        } catch (AsztalTeleException asztalTeleException) {
            System.out.println("Megtelt az asztal.");
        } catch (NincsJatekos nincsJatekos) {
            System.out.println("Nincs jatekos.");
        }
    }
}