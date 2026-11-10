package landkreuzer.de.foliox;

public enum MediaCondition {
    GUT("Gut"),
    MITTEL("Mittel"),
    SCHLECHT("Schlecht");

    private final String label;

    MediaCondition(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}