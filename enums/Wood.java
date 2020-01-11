package enums;

public enum Wood {
    MAPLE, ALDER;

    public String toString() {
        switch(this) {
            case MAPLE: return "Maple";
            case ALDER: return "Alder";
            default: return null;
        }
    }
}