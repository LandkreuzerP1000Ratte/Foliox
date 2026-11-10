package landkreuzer.de.foliox;

public enum MediaType {
    BUCH("Buch"),
    CD("CD"),
    ZEITUNG("Zeitung");

    private final String type;

    MediaType(String label) {
        this.type = label;
    }

    public String getLabel() {
        return type;
    }
}
