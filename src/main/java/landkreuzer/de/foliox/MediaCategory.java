package landkreuzer.de.foliox;

public enum MediaCategory {
    KRIMI("Krimi"),
    FANTASY("Fantasy"),
    ROMANE("Romane"),
    ROMANTIK("Romantik"),
    ABENTEUER("Abenteuer"),
    ALLGEMEIN("Allgemein"),
    MANGA("Manga");

    private final String category;

    MediaCategory(String kat) {
        this.category = kat;
    }

    public String getLabel() {
        return category;
    }



}
