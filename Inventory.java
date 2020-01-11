import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;


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