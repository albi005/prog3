package org.example;

public abstract class Jatekos {
    protected Asztal asztal;

    public void lep() {
    }

    public void setAsztal(Asztal a) {
        asztal = a;
    }

    @SuppressWarnings("removal")
    @Override
    protected void finalize() throws Throwable {
        System.out.println(this);
    }
}
