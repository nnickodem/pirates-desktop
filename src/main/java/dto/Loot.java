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


    public Integer alter(final Integer change, final Rarity rarity) {
        Integer original;
        if(rarity == null) {
            original = killCount;
        } else {
            switch (rarity) {
                case CRUDE:
                    original = crudeCount;
                    break;
                case COMMON:
                    original = commonCount;
                    break;
                case RARE:
                    original = rareCount;
                    break;
                case FAMED:
                    original = famedCount;
                    break;
                case LEGENDARY:
                    original = legendaryCount;
                    break;
                default:
                    //TODO: wont happen, but should still handle better
                    original = 0;
            }
        }

        if(original + change >= 0) {
            return original + change;

        }

        return original;
    }
}
