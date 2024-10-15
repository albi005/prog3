package lambeer;

import java.io.Serializable;
import java.util.Comparator;

public class Beer implements Serializable {
    String name;
    public String style;
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
}
