package enums;

public enum Wood {
    MAPLE, ALDER, INDIAN_ROSEWOOD, SITKA, MAHOGANY, ADIRONDACK;

    public String toString() {
        switch(this) {
            case MAPLE: return "Maple";
            case ALDER: return "Alder";
            case INDIAN_ROSEWOOD: return "Indian Rosewood";
            case SITKA: return "Sitka";
            case MAHOGANY: return "Mahogany";
            case ADIRONDACK: return "Adirondack";
            default: return null;
        }
    }
}