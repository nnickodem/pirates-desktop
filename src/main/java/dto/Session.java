package dto;

import Enum.ChestType;
import Enum.Rarity;
import ResourceHandlers.FileHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Session {

    private final String user;
    private final Boss boss;
    private final Map<ChestType, Loot> lootMap;

    public Session(final String user, final Boss boss) {
        this.user = user;
        this.boss = boss;
        this.lootMap = new HashMap<>();
        Arrays.stream(ChestType.values()).forEach(c -> lootMap.put(c, new Loot()));
    }

    public Session(final String user, final Boss boss, final Map<ChestType, Loot> lootMap) {
        this.user = user;
        this.boss = boss;
        this.lootMap = lootMap;
    }

    public String getUser() {
        return user;
    }

    public Boss getBoss() {
        return boss;
    }

    public Map<ChestType, Loot> getLootMap() {
        return lootMap;
    }

    public Loot getLootForChestType(final ChestType chestType) {
        return lootMap.get(chestType);
    }

    public void mergeLoot(final Loot loot, final ChestType chestType) {
        Loot lootEntry = lootMap.get(chestType);
        lootEntry.alter(null, loot.getKillCount());
        Arrays.stream(Rarity.values()).forEach(r -> lootEntry.alter(r, loot.getVariable(r)));
        FileHandler.updateSave(user, boss, chestType.getName(), lootEntry);
    }

    public Integer getTotalCount(final Rarity rarity) {
        return lootMap.values().stream().map(l -> l.getVariable(rarity)).reduce(0, Integer::sum);
    }
}
