package me.raven.Interfaces;

import com.google.common.collect.Sets;
import me.raven.RvInventory;

import java.util.Set;

public interface IRvLayout {

    default void Init(RvInventory rvInventory) {
        if (!Title().isEmpty())
            rvInventory.getExtra().setTitle(Title());

        BlockedKeys().forEach(rvInventory.getKey()::addBlockedKey);
        AllowedKeys().forEach(rvInventory.getKey()::addAllowedKey);

        Setup(rvInventory);
    }

    void Setup(RvInventory rvInventory);

    default String Title() {
        return "";
    }

    default Set<String> BlockedKeys() {
        return Sets.newHashSet();
    }

    default Set<String> AllowedKeys() {
        return Sets.newHashSet();
    }
}