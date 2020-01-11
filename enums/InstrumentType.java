package enums;

public enum InstrumentType {
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