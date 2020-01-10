import java.util.*;
import java.text.DecimalFormat;

public class InstrumentSearchTestDrive {
    public static void main(String arg[]) {
        DecimalFormat df = new DecimalFormat("#.00");
        Inventory inventory = new Inventory();
        initializeInventory(inventory);

        GuitarSpec whatErinLikes = new GuitarSpec(Builder.FENDER, 
        "Stratocastor", Type.ELECTRIC, NumStrings.SIX, Wood.ALDER, Wood.ALDER);

        MandolinSpec whatAaronLikes = new MandolinSpec(Builder.MARTIN, 
        "Ellis", Type.ACOUSTIC, Style.A, Wood.MAPLE, Wood.MAPLE);

        List<Guitar> matchingGuitars = inventory.search(whatErinLikes);
        if (!matchingGuitars.isEmpty()) {
            System.out.println("Erin, you might like these guitars:  ");
            for (Iterator<Guitar> i = matchingGuitars.iterator(); i.hasNext(); ) {
                Guitar guitar = i.next();
                GuitarSpec spec = (GuitarSpec)guitar.getSpec();
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

        List<Mandolin> matchingMandolins = inventory.search(whatAaronLikes);
        if (!matchingMandolins.isEmpty()) {
            System.out.println("Aaron, you might like these mandolins:  ");
            for (Iterator<Mandolin> i = matchingMandolins.iterator(); i.hasNext(); ) {
                Mandolin mandolin = i.next();
                MandolinSpec spec = (MandolinSpec)mandolin.getSpec();
                System.out.println("  We have a " + 
                    spec.getBuilder() + " " + spec.getStyle() + " " + 
                    spec.getModel() + " " + spec.getType() + " mandolin:\n    " +
                    spec.getBackWood() + " back and sides,\n    " +
                    spec.getTopWood() + " top.\n  You can have it for only $" +
                    df.format(mandolin.getPrice()) + "!\n  ----");
            } 
        } else {
            System.out.println("Sorry, Aaron, we have nothing for you.");
        }
    }

    private static void initializeInventory(Inventory inventory) {
        GuitarSpec guitarSpec = new GuitarSpec(Builder.FENDER, 
        "Stratocastor", Type.ELECTRIC, NumStrings.SIX, Wood.ALDER, Wood.ALDER);

        MandolinSpec mandolinSpec = new MandolinSpec(Builder.MARTIN, 
        "Ellis", Type.ACOUSTIC, Style.A, Wood.MAPLE, Wood.MAPLE);

        inventory.addInstrument("1", 500, guitarSpec);
        inventory.addInstrument("2", 550, guitarSpec);
        inventory.addInstrument("3", 450, mandolinSpec);
        inventory.addInstrument("4", 475, mandolinSpec);
    }
}

class Inventory {
    private List<Instrument> inventory;

    public Inventory() {
        inventory = new LinkedList<Instrument>();
    }

    public void addInstrument(String serialNumber, double price, 
    InstrumentSpec spec) {
        Instrument instrument = null;
        if (spec instanceof GuitarSpec) {
            instrument = new Guitar(serialNumber, price, (GuitarSpec)spec);
        } else if (spec instanceof MandolinSpec) {
            instrument = new Mandolin(serialNumber, price, (MandolinSpec)spec);
        }
        inventory.add(instrument);
    }

    public Instrument getInstrument(String serialNumber) {
        for (Iterator<Instrument> i = inventory.iterator(); i.hasNext();) {
            Instrument instrument = i.next();
            if (instrument.getSerialNumber().equals(serialNumber)) {
                return instrument;
            }
        }
        return null;
    }

    public List<Guitar> search(GuitarSpec searchSpec) {
        List<Guitar> matchingGuitars = new LinkedList<Guitar>();
        for (Iterator<Instrument> i = inventory.iterator(); i.hasNext();) {
            try {
                Guitar guitar = (Guitar)i.next();
                if (guitar.getSpec().matches(searchSpec))
                    matchingGuitars.add(guitar);
            } catch (ClassCastException e) {
                continue;
            }
        }
        return matchingGuitars;
    }

    public List<Mandolin> search(MandolinSpec searchSpec) {
        List<Mandolin> matchingMandolins = new LinkedList<Mandolin>();
        for (Iterator<Instrument> i = inventory.iterator(); i.hasNext();) {
            try {
                Mandolin mandolin = (Mandolin)i.next();
                if (mandolin.getSpec().matches(searchSpec)){
                    matchingMandolins.add(mandolin);
                }
            } catch (ClassCastException e) {
                continue;
            }
        }
        return matchingMandolins;
    }
}

abstract class Instrument {
    private String serialNumber;
    private double price;
    private InstrumentSpec spec;

    public Instrument(String serialNumber, double price, InstrumentSpec spec) {
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

    public InstrumentSpec getSpec() {
        return spec;
    }
}

class Guitar extends Instrument {
    public Guitar(String serialNumber, double price, GuitarSpec spec) {
        super(serialNumber, price, spec);
    }
}

class Mandolin extends Instrument {
    public Mandolin(String serialNumber, double price, MandolinSpec spec) {
        super(serialNumber, price, spec);
    }
}

abstract class InstrumentSpec {
    private Builder builder;
    private String model;
    private Type type;
    private Wood backWood, topWood;

    public InstrumentSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood) {
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
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

    public boolean matches(InstrumentSpec otherSpec) {
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

class GuitarSpec extends InstrumentSpec {
    private NumStrings numStrings;

    public GuitarSpec(Builder builder, String model, Type type, NumStrings numStrings, Wood backWood, Wood topWood) {
        super(builder, model, type, backWood, topWood);
        this.numStrings = numStrings;
    }

    public NumStrings getNumStrings() {
        return numStrings;
    }

    public boolean matches(InstrumentSpec otherSpec) {
        if (!super.matches(otherSpec))
            return false;
        if (!(otherSpec instanceof GuitarSpec))
            return false;
        GuitarSpec spec = (GuitarSpec)otherSpec;
        if (numStrings != spec.numStrings)
            return false;
        return true;
    }
}

class MandolinSpec extends InstrumentSpec{
    private Style style;

    public MandolinSpec(Builder builder, String model, Type type, Style style, Wood backWood, Wood topWood) {
        super(builder, model, type, backWood, topWood);
        this.style = style;
    }

    public Style getStyle() {
        return style;
    }

    public boolean matches(InstrumentSpec otherSpec) {
        if (!super.matches(otherSpec))
            return false;
        if (!(otherSpec instanceof MandolinSpec))
            return false;
        MandolinSpec spec = (MandolinSpec)otherSpec;
        if (!style.equals(spec.style))
            return false;
        return true;
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

enum Style {
    A, F;

    public String toString() {
        switch(this){
            case A: return "A";
            case F: return "F";
            default: return null;
        }
    }
}