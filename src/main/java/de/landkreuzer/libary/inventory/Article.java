package de.landkreuzer.libary.inventory;

import java.util.UUID;

public record Article(
        UUID uid,
        String title,
        String description,
        UUID author,
        UUID publisher,
        int barcode,
        Article.Medium medium
) {
    public enum Medium {
        BOOK("Book"),
        GAME("Game"),
        SONG("Song"),
        MOVIE("Movie"),
        MAGAZINE("Magazine"),
        PAPERBACK("Paperback"),
        AUDIOBOOK("Audiobook"),
        BLU_RAY("Blu-ray"),
        VINYL("Vinyl"),
        NEWSPAPER("Newspaper"),
        COMIC("Comic"),
        JOURNAL("Journal"),
        MANUSCRIPT("Manuscript");

        private final String identifier;
        private final String title;

        Medium(String title) {
            this.identifier = name().toLowerCase();
            this.title = title;
        }

        @Deprecated
        Medium() {
            this.identifier = name().toLowerCase();
            this.title = name().charAt(0) + name().substring(1).toLowerCase();
        }

        public String getIdentifier() {
            return identifier;
        }

        public String getTitle() {
            return title;
        }
    }
}
