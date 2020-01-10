import java.util.*;
import java.text.DecimalFormat;

public class InstrumentSearchTestDrive {
    public static void main(String arg[]) {
        DecimalFormat df = new DecimalFormat("#.00");
        Inventory inventory = new Inventory();
        initializeInventory(inventory);

        Map<String, Object> properties = new HashMap<>();
        properties.put("builder", Builder.GIBSON);
        properties.put("backWood", Wood.MAPLE);
        InstrumentSpec clientSpec = new InstrumentSpec(properties);

        List<Instrument> matchingInstruments = inventory.search(clientSpec);
        if (!matchingInstruments.isEmpty()) {
            System.out.println("You might like these instruments:  ");
            for (Iterator<Instrument> i = matchingInstruments.iterator(); i.hasNext(); ) {
                Instrument instrument = i.next();
                InstrumentSpec spec = instrument.getSpec();
                System.out.println("  We have a " + spec.getProperty("instrumentType") +
                    " with the following properties:");
                for (Iterator<String> j = spec.getProperties().keySet().iterator(); j.hasNext(); ) {
                    String propertyName = j.next();
                    if (propertyName.equals("instrumentType"))
                        continue;
                    System.out.println("   " + propertyName + ": " +
                        spec.getProperty(propertyName));
                }
                System.out.println("You can have this " + spec.getProperty("instrumentType") + 
                " for only $" + df.format(instrument.getPrice()) + "!\n  ----");
            } 
        } else {
            System.out.println("Sorry, we have nothing for you.");
        }
    }

    private static void initializeInventory(Inventory inventory) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("instrumentType", InstrumentType.GUITAR);
        properties.put("builder", Builder.GIBSON);
        properties.put("model", "CJ");
        properties.put("type", Type.ACOUSTIC);
        properties.put("numStrings", NumStrings.SIX);
        properties.put("topWood", Wood.MAPLE);
        properties.put("backWood", Wood.MAPLE);
        inventory.addInstrument("11277", 3999.95, new InstrumentSpec(properties));
    }
}

class Inventory {
    private List<Instrument> inventory;

    public Inventory() {
        inventory = new LinkedList<Instrument>();
    }

    public void addInstrument(String serialNumber, double price, 
    InstrumentSpec spec) {
        Instrument instrument = new Instrument(serialNumber, price, spec);
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

    public List<Instrument> search(InstrumentSpec searchSpec) {
        List<Instrument> matchingInstruments = new LinkedList<Instrument>();
        for (Iterator<Instrument> i = inventory.iterator(); i.hasNext();) {
            Instrument instrument = i.next();
            if (instrument.getSpec().matches(searchSpec))
                matchingInstruments.add(instrument);
        }
        return matchingInstruments;
    }
}

class Instrument {
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

class InstrumentSpec {
    private Map<String, Object> properties;

    public InstrumentSpec(Map<String, Object> properties) {
        if (properties == null) {
            this.properties = new HashMap<>();
        } else {
            this.properties = new HashMap<>(properties);
        }
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public boolean matches(InstrumentSpec otherSpec) {
        for (Iterator<String> i = otherSpec.getProperties().keySet().iterator(); i.hasNext(); ) {
            String propertyName = i.next();
            if (!properties.get(propertyName).equals(otherSpec.getProperty(propertyName))) {
                return false;
            }
        }
        return true;
    }
}

enum InstrumentType {
    GUITAR, MANDOLIN, BANJO, FIDDLE, BASS;

    public String toString() {
        switch(this) {
            case GUITAR: return "guitar";
            case MANDOLIN: return "mandolin";
            case BANJO: return "banjo";
            case FIDDLE: return "fiddle";
            case BASS: return "bass";
            default: return null;
        }
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
    FENDER, MARTIN, GIBSON;

    public String toString() {
        switch(this) {
            case FENDER: return "Fender";
            case MARTIN: return "Martin";
            case GIBSON: return "Gibson";
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