import java.util.*;
import java.text.DecimalFormat;

public class test {
    public static void main(String arg[]) {
        DecimalFormat df = new DecimalFormat("#.00");
        Inventory inventory = new Inventory();
        initializeInventory(inventory);

        GuitarSpec whatErinLikes = new GuitarSpec(Builder.FENDER, 
        "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER, NumStrings.SIX);

        List<Guitar> matchingGuitars = inventory.search(whatErinLikes);
        if (!matchingGuitars.isEmpty()) {
            System.out.println("Erin, you might like these guitars:  ");
            for (Iterator<Guitar> i = matchingGuitars.iterator(); i.hasNext(); ) {
                Guitar guitar = (Guitar)i.next();
                GuitarSpec spec = guitar.getSpec();
                System.out.println("  We have a " + 
                    spec.getBuilder() + " " + spec.getNumStrings() + " " + 
                    spec.getModel() + " " + spec.getType() + " guitar:\n    " +
                    spec.getBackWood() + " back and sides,\n    " +
                    spec.getTopWood() + " top.\n  You can have it for only $" +
                    df.format(guitar.getPrice()) + "!\n  ----");
            } 
        } else {
            System.out.println("Sorry, Erin, we have nothing for you.");
        }
    }

    private static void initializeInventory(Inventory inventory) {
        GuitarSpec spec = new GuitarSpec(Builder.FENDER, "Stratocastor", 
        Type.ELECTRIC, Wood.ALDER, Wood.ALDER, NumStrings.SIX);
        inventory.addGuitar("1", 500, spec);
        inventory.addGuitar("2", 550, spec);
    }
}

class Guitar {
    private String serialNumber;
    private double price;
    private GuitarSpec spec;

    public Guitar(String serialNumber, double price, GuitarSpec spec) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.spec = spec;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float newPrice) {
        this.price = newPrice;
    }

    public GuitarSpec getSpec() {
        return spec;
    }
}

class GuitarSpec {
    private String model;
    private Type type;
    private Wood backWood, topWood;
    private Builder builder;
    private NumStrings numStrings;

    public GuitarSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood, NumStrings numStrings) {
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
        this.numStrings = numStrings;
    }

    public Builder getBuilder() {
        return builder;
    }

    public String getModel() {
        return model;
    }

    public Type getType() {
        return type;
    }

    public Wood getBackWood() {
        return backWood;
    }

    public Wood getTopWood() {
        return topWood;
    }

    public NumStrings getNumStrings() {
        return numStrings;
    }

    public boolean matches(GuitarSpec otherSpec) {
        if (builder != otherSpec.builder)
            return false;
        if ((model != null) && (!model.equals("")) &&
            (!model.equals(otherSpec.model)))
            return false;
        if (type != otherSpec.type)
            return false;
        if (backWood != otherSpec.backWood)
            return false;
        if (topWood != otherSpec.topWood)
            return false;
        return true;
    }
}

class Inventory {
    private List<Guitar> guitars;

    public Inventory() {
        guitars = new LinkedList<Guitar>();
    }

    public void addGuitar(String serialNumber, double price, 
    GuitarSpec spec) {
        Guitar guitar = new Guitar(serialNumber, price, spec);
        guitars.add(guitar);
    }

    public Guitar getGuitar(String serialNumber) {
        for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
            Guitar guitar = (Guitar)i.next();
            if (guitar.getSerialNumber().equals(serialNumber)) {
                return guitar;
            }
        }
        return null;
    }

    public List<Guitar> search(GuitarSpec searchSpec) {
        List<Guitar> matchingGuitars = new LinkedList<Guitar>();
        for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
            Guitar guitar = (Guitar)i.next();
            if (guitar.getSpec().matches(searchSpec))
                matchingGuitars.add(guitar);
        }
        return matchingGuitars;
    }
}

enum Type {
    ACOUSTIC, ELECTRIC;

    public String toString() {
        switch(this) {
            case ACOUSTIC: return "acoustic";
            case ELECTRIC: return "electric";
            default: return null;
        }
    }
}

enum Builder {
    FENDER, MARTIN;

    public String toString() {
        switch(this) {
            case FENDER: return "Fender";
            case MARTIN: return "Martin";
            default: return null;
        }
    }
}

enum Wood {
    MAPLE, ALDER;

    public String toString() {
        switch(this) {
            case MAPLE: return "Maple";
            case ALDER: return "Alder";
            default: return null;
        }
    }
}

enum NumStrings {
    SIX, TWELVE;

    public String toString() {
        switch(this){
            case SIX: return "6-string";
            case TWELVE: return "12-string";
            default: return null;
        }
    }
}