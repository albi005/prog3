package org.example;

import java.io.Serializable;
import java.util.Comparator;

public class Beer implements Serializable {
    String name;
    String style;
    double strength;

    public Beer(String name, String style, double strength) {
        this.name = name;
        this.style = style;
        this.strength = strength;
    }

    String getName() {
        return name;
    };

    String getStyle() {
        return style;
    };

    double getStrength() {
        return strength;
    };

    public String toString() {
        return "name: " + name + ", style: " + style + " strength: " + strength;
    }

    public static Comparator<Beer> getComparator(String sort) {
        switch (sort) {
            case "name":
                return new NameComparator();
            case "strength":
                return new StrengthComparator();
            case "style":
                return new StyleComparator();
            default:
                return null;
        }
    }

    public static class NameComparator implements Comparator<Beer> {
        @Override
        public int compare(Beer o1, Beer o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    public static class StyleComparator implements Comparator<Beer> {
        @Override
        public int compare(Beer o1, Beer o2) {
            return o1.style.compareTo(o2.style);
        }
    }

    public static class StrengthComparator implements Comparator<Beer> {
        @Override
        public int compare(Beer o1, Beer o2) {
            return (int) (o1.strength - o2.strength);
        }
    }
}
