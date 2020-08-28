package Enum;

public enum Rarity {
    CRUDE("crude"),
    COMMON("common"),
    RARE("rare"),
    FAMED("famed"),
    LEGENDARY("legendary");

    private final String name;

    Rarity(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
