package me.raven.Interfaces;

import me.raven.RvInventory;
import me.raven.RvLayout;

public interface RvLayoutMethods {

    RvInventory setLayout(RvLayout layout);

    RvInventory setLayouts(RvLayout... layouts);
}
