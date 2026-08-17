package de.landkreuzer.libary.inventory;

import java.util.UUID;

public record Author(
        UUID uid,
        String name
) {
}
