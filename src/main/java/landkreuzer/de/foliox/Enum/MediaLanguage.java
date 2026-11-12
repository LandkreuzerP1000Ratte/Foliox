package landkreuzer.de.foliox.Enum;

public enum MediaLanguage {
    DEUTSCH("Deutsch"),
    ENGLISCH("Englisch"),
    FRANZÖSISCH("Französisch"),
    POLNISCH("Polnisch"),
    SPANISCH("Spanisch"),
    NIEDERLÄNDISCH("Niederländisch");

    private final String language;

    MediaLanguage(String spr) {
        this.language = spr;
    }

    public String getSpr() {
        return language;
    }
}