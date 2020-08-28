package Enum;

public enum ChestType {
    NONE("none"),
    LOOT_POUCH("pouch"),
    LOOT_CHEST("chest"),
    SKULL_CHEST("skull");

    private final String name;

    ChestType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
