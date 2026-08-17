package de.landkreuzer.libary.inventory;

import java.util.UUID;

public record Item(
        UUID uid,
        UUID article,
        Item.Condition condition
) {
    public enum Condition { //enumaration
        MINT("Mint"),
        VERY_GOOD("Very Good"),
        GOOD("Good"),
        ACCEPTABLE("Acceptable"),
        POOR("Poor");

        private final String identifier;
        private final String title;

        Condition(String title) {
            this.identifier = name().toLowerCase();
            this.title = title;
        }

        @Deprecated
        Condition() {
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