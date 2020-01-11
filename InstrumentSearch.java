import enums.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.text.DecimalFormat;

public class InstrumentSearch {
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