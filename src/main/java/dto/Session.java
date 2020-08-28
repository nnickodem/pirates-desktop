package dto;

import Enum.ChestType;
import Enum.Rarity;
import ResourceHandlers.FileHandler;

import java.net.Inet4Address;
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

    public Integer alter(final Integer change, final ChestType chestType, final Rarity rarity) {
        Integer result = lootMap.get(chestType).alter(change, rarity);
        String name = rarity == null ? "kills" : rarity.getName();
        FileHandler.updateSave(user, boss, chestType.getName(), name, String.valueOf(result));
        return result;
    }

    public Integer getTotalCount(final Rarity rarity) {
        return lootMap.values().stream().map(l -> l.getVariable(rarity)).reduce(0, Integer::sum);
    }
}
