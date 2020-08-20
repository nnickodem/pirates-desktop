package dto;

public class Loot {

    private String user;
    private Integer bossId;
    private Integer killCount;
    private Integer crudeCount;
    private Integer commonCount;
    private Integer rareCount;
    private Integer famedCount;
    private Integer legendaryCount;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getBossId() {
        return bossId;
    }

    public void setBossId(Integer bossId) {
        this.bossId = bossId;
    }

    public Integer getKillCount() {
        return killCount;
    }

    public void setKillCount(Integer killCount) {
        this.killCount = killCount;
    }

    public Integer getCrudeCount() {
        return crudeCount;
    }

    public void setCrudeCount(Integer crudeCount) {
        this.crudeCount = crudeCount;
    }

    public Integer getCommonCount() {
        return commonCount;
    }

    public void setCommonCount(Integer commonCount) {
        this.commonCount = commonCount;
    }

    public Integer getRareCount() {
        return rareCount;
    }

    public void setRareCount(Integer rareCount) {
        this.rareCount = rareCount;
    }

    public Integer getFamedCount() {
        return famedCount;
    }

    public void setFamedCount(Integer famedCount) {
        this.famedCount = famedCount;
    }

    public Integer getLegendaryCount() {
        return legendaryCount;
    }

    public void setLegendaryCount(Integer legendaryCount) {
        this.legendaryCount = legendaryCount;
    }
}
