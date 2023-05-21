package me.raven;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RvPage implements Cloneable{

    private RvInventory page;
    private int index;

    public RvPage(RvInventory page, int index) {
        this.page = page;
        this.index = index;
    }

    public static RvPage with(RvInventory page, int index) {
        return new RvPage(page, index);
    }

    public RvPage clone() {
        RvPage clone;
        try {
            clone = (RvPage) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }
}
