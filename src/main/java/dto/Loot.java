package dto;

import Enum.Rarity;

public class Loot {

    private Integer killCount = 0;
    private Integer crudeCount = 0;
    private Integer commonCount = 0;
    private Integer rareCount = 0;
    private Integer famedCount = 0;
    private Integer legendaryCount = 0;

    public Loot() {}

    public Loot(final int killCount, final int crudeCount,
                final int commonCount, final int rareCount, final int famedCount, final int legendaryCount) {
        this.killCount = killCount;
        this.crudeCount = crudeCount;
        this.commonCount = commonCount;
        this.rareCount = rareCount;
        this.famedCount = famedCount;
        this.legendaryCount = legendaryCount;
    }

    public Integer getKillCount() {
        return killCount;
    }


    public Integer getCrudeCount() {
        return crudeCount;
    }


    public Integer getCommonCount() {
        return commonCount;
    }


    public Integer getRareCount() {
        return rareCount;
    }


    public Integer getFamedCount() {
        return famedCount;
    }


    public Integer getLegendaryCount() {
        return legendaryCount;
    }


    public Integer alter(final Rarity rarity, final Integer change) {
        Integer original = getVariable(rarity);

        if(original + change >= 0) {
            return alterVariable(rarity, change);
        }

        return original;
    }

    private Integer alterVariable(final Rarity rarity, final Integer alteration){
        int original;
        if(rarity == null) {
            killCount += alteration;
            original = killCount;
        } else {
            switch (rarity) {
                case CRUDE:
                    crudeCount += alteration;
                    original = crudeCount;
                    break;
                case COMMON:
                    commonCount += alteration;
                    original = commonCount;
                    break;
                case RARE:
                    rareCount += alteration;
                    original = rareCount;
                    break;
                case FAMED:
                    famedCount += alteration;
                    original = famedCount;
                    break;
                case LEGENDARY:
                    legendaryCount += alteration;
                    original = legendaryCount;
                    break;
                default:
                    //TODO: wont happen, but should still handle better
                    original = 0;

            }
        }
        return original;
    }

    public Integer getVariable(final Rarity rarity) {
        return alterVariable(rarity, 0);
    }
}
