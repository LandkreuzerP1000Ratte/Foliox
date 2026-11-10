package landkreuzer.de.foliox;

import java.util.UUID;

public record Media(
        UUID uuid,
        String barcode,
        String title,
        String author,
        String publisher,
        MediaType type,
        MediaLanguage language,
        String borrowed,
        MediaCategory category,
        MediaCondition condition,
        Boolean available
) {

    public UUID getUuid() {
        return uuid;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public MediaType getType() {
        return type;
    }

    public MediaLanguage getLanguage() {
        return language;
    }

    public String getBorrowed() {return borrowed;}

    public String getBorrowedText() {return borrowed == null || borrowed.isBlank() ? "n/d" : borrowed;}

    public MediaCategory getCategory() {
        return category;
    }

    public MediaCondition getCondition() {
        return condition;
    }

    public Boolean getAvailable() {
        return available;
    }
}