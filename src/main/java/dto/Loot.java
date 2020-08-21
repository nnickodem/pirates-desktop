package dto;

import ResourceHandlers.FileHandler;

public class Loot {

    private String user;
    private Integer bossId;
    private String bossName;
    private Integer killCount = 0;
    private Integer crudeCount = 0;
    private Integer commonCount = 0;
    private Integer rareCount = 0;
    private Integer famedCount = 0;
    private Integer legendaryCount = 0;

    public Loot() {

    }

    public Loot(final int killCount, final int crudeCount, final int commonCount, final int rareCount,
                     final int famedCount, final int legendaryCount) {
        this.killCount = killCount;
        this.crudeCount = crudeCount;
        this.commonCount = commonCount;
        this.rareCount = rareCount;
        this.famedCount = famedCount;
        this.legendaryCount = legendaryCount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public Integer getBossId() {
        return bossId;
    }

    public void setBossId(final Integer bossId) {
        this.bossId = bossId;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(final String bossName) {
        this.bossName = bossName;
    }

    public Integer getKillCount() {
        return killCount;
    }

    public Integer alterKillCount(final Integer change) {
        killCount = alter(killCount, change, "kills");
        return killCount;
    }

    public Integer getCrudeCount() {
        return crudeCount;
    }

    public Integer alterCrudeCount(final Integer change) {
        crudeCount = alter(crudeCount, change, "crude");
        return crudeCount;
    }

    public Integer getCommonCount() {
        return commonCount;
    }

    public Integer alterCommonCount(final Integer change) {
        commonCount = alter(commonCount, change, "common");
        return commonCount;
    }

    public Integer getRareCount() {
        return rareCount;
    }

    public Integer alterRareCount(final Integer change) {
        rareCount = alter(rareCount, change, "rare");
        return rareCount;
    }

    public Integer getFamedCount() {
        return famedCount;
    }

    public Integer alterFamedCount(final Integer change) {
        famedCount = alter(famedCount, change, "famed");
        return famedCount;
    }

    public Integer getLegendaryCount() {
        return legendaryCount;
    }

    public Integer alterLegendaryCount(final Integer change) {
        legendaryCount = alter(legendaryCount, change, "legendary");
        return legendaryCount;
    }

    public Integer alter(final Integer original, final Integer change, final String valueName) { //TODO: update value name to be an enum
        if(original + change >= 0) {
            Integer result = original + change;
            FileHandler.updateSave(valueName, String.valueOf(result), this.user, this.bossName);
            return result;
        }

        //TODO: update file
        return original;
    }
}
