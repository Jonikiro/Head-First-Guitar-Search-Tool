package enums;

public enum NumStrings {
    SIX, FIVE, TWELVE;

    public String toString() {
        switch(this){
            case SIX: return "6-string";
            case FIVE: return "5-string";
            case TWELVE: return "12-string";
            default: return null;
        }
    }
}