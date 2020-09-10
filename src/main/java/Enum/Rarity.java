package Enum;

public enum Rarity {
    CRUDE("Crude"),
    COMMON("Common"),
    RARE("Rare"),
    FAMED("Famed"),
    LEGENDARY("Legendary");

    private final String name;

    Rarity(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
