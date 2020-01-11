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

        Map<String, Object> clientProperties = new HashMap<>();
        clientProperties.put("builder", Builder.GIBSON);
        clientProperties.put("backWood", Wood.MAPLE);
        InstrumentSpec clientSpec = new InstrumentSpec(clientProperties);

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
                    System.out.println("    " + propertyName + ": " +
                        spec.getProperty(propertyName));
                }
                System.out.println("  You can have this " + spec.getProperty("instrumentType") + 
                " for only $" + df.format(instrument.getPrice()) + "!\n  ----");
            } 
        } else {
            System.out.println("Sorry, we have nothing for you.");
        }
    }

    private static void initializeInventory(Inventory inventory) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("instrumentType", InstrumentType.GUITAR);
        properties.put("builder", Builder.COLLINGS);
        properties.put("model", "CJ");
        properties.put("type", Type.ACOUSTIC);
        properties.put("numStrings", NumStrings.SIX);
        properties.put("topWood", Wood.INDIAN_ROSEWOOD);
        properties.put("backWood", Wood.SITKA);
        inventory.addInstrument("11277", 3999.95, new InstrumentSpec(properties));

        properties.put("builder", Builder.MARTIN);
        properties.put("model", "D-18");
        properties.put("topWood", Wood.MAHOGANY);
        properties.put("backWood", Wood.ADIRONDACK);
        inventory.addInstrument("112784", 5495.95, new InstrumentSpec(properties));

        properties.put("builder", Builder.FENDER);
        properties.put("model", "Stratocastor");
        properties.put("type", Type.ELECTRIC);
        properties.put("topWood", Wood.ALDER);
        properties.put("backWood", Wood.ALDER);
        inventory.addInstrument("V95693", 1499.95, new InstrumentSpec(properties));
        inventory.addInstrument("V9512", 1549.95, new InstrumentSpec(properties));

        properties.put("builder", Builder.GIBSON);
        properties.put("model", "Les Paul");
        properties.put("topWood", Wood.MAPLE);
        properties.put("backWood", Wood.MAPLE);
        inventory.addInstrument("70108276", 2295.95, new InstrumentSpec(properties));

        properties.put("model", "61 Reissue");
        properties.put("topWood", Wood.MAHOGANY);
        properties.put("backWood", Wood.MAHOGANY);
        inventory.addInstrument("82765501", 1890.95, new InstrumentSpec(properties));

        properties.put("instrumentType", InstrumentType.MANDOLIN);
        properties.put("model", "F-5G");
        properties.put("type", Type.ACOUSTIC);
        properties.put("topWood", Wood.MAPLE);
        properties.put("backWood", Wood.MAPLE);
        properties.remove("numStrings");
        inventory.addInstrument("9019920", 5495.99, new InstrumentSpec(properties));

        properties.put("instrumentType", InstrumentType.BANJO);
        properties.put("model", "RB-3 Wreath");
        properties.put("numStrings", NumStrings.FIVE);
        properties.remove("topWood");
        inventory.addInstrument("8900231", 2945.95, new InstrumentSpec(properties));
    }
}