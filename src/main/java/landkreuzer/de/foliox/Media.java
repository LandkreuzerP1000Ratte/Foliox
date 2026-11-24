package landkreuzer.de.foliox;

import landkreuzer.de.foliox.Enum.MediaCategory;
import landkreuzer.de.foliox.Enum.MediaType;
import landkreuzer.de.foliox.Enum.MediaLanguage;
import landkreuzer.de.foliox.Enum.MediaCondition;

import java.util.List;
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


    public Media(List<String> item) {
        this(
                UUID.fromString(item.get(0)),
                item.get(1),
                item.get(2),
                item.get(3),
                item.get(4),
                MediaType.valueOf(item.get(5)),
                MediaLanguage.valueOf(item.get(6)),
                item.get(7),
                MediaCategory.valueOf(item.get(8)),
                MediaCondition.valueOf(item.get(9)),
                Boolean.parseBoolean(item.get(10))
        );
    }

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

    public String getBorrowed() {
        return borrowed;
    }

    public String getBorrowedText() {
        return borrowed == null || borrowed.isBlank() ? "n/d" : borrowed;
    }

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