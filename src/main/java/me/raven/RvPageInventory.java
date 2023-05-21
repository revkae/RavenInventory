package me.raven;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class RvPageInventory {

    private Map<Integer, RvPage> pages = Maps.newLinkedHashMap();

    public RvPageInventory() {}

    public RvPageInventory(RvInventory... rvInventories) {
        for (int i = 0; i < rvInventories.length; i++) {
            this.pages.put(i, RvPage.with(rvInventories[i], i));
        }
    }

    public RvPageInventory(RvInventory rvInventory, int amount) {
        for (int i = 0; i < amount; i++) {
            this.pages.put(i, RvPage.with(rvInventory.clone(), i));
        }
    }

    public RvPage getPage(int index) {
        return pages.get(index);
    }

    public RvPage getPage(RvInventory rvInventory) {
        return pages.values().stream()
                .filter(page -> page.getPage().equals(rvInventory))
                .findFirst().orElse(null);
    }

    public List<RvPage> getPages() {
        return (List<RvPage>) pages.values();
    }

    public RvPage getFirstPage() {
        return pages.get(0);
    }

    public RvPage getLastPage() {
        return pages.get(pages.size() - 1);
    }

    public List<RvPage> getMiddlePages() {
        return pages.values().stream()
                .filter(page -> !page.equals(getFirstPage()))
                .filter(page -> !page.equals(getLastPage()))
                .collect(Collectors.toList());
    }

    public int getPageCount() {
        return pages.size();
    }

    public RvPageInventory addPages(RvInventory... rvInventories) {
        for (RvInventory rvInventory : rvInventories) {
            addPage(rvInventory);
        }
        return this;
    }

    public RvPageInventory addPages(RvInventory rvInventory, int amount) {
        for (int i = 0; i < amount; i++) {
            addPage(rvInventory.clone());
        }
        return this;
    }

    public RvPageInventory addPage(RvInventory rvInventory) {
        pages.put(pages.size(), RvPage.with(rvInventory, pages.size()));
        return this;
    }

    public RvPageInventory removePage(int index) {
        pages.remove(index);
        Map<Integer, RvPage> newPages = new LinkedHashMap<>();
        int count = 0;
        for (Integer key : pages.keySet()) {
            RvPage page = pages.get(key);
            page.setIndex(count);
            newPages.put(count, page);
            ++count;
        }
        pages = newPages;
        return this;
    }

    public RvPageInventory removePages(int... indexes) {
        for (int index : indexes) {
            pages.remove(index);
        }
        Map<Integer, RvPage> newPages = new LinkedHashMap<>();
        int count = 0;
        for (Integer key : pages.keySet()) {
            RvPage page = pages.get(key);
            page.setIndex(count);
            newPages.put(count, page);
            ++count;
        }
        pages = newPages;
        return this;
    }

    public boolean isPageEmpty(int index) {
        return pages.get(index).getPage().getExtra().isEmpty();
    }

    public boolean isPageFull(int index) {
        return pages.get(index).getPage().getExtra().isFull();
    }

    public boolean hasPage(RvInventory rvInventory) {
        return getPages().stream().anyMatch(page -> page.getPage().equals(rvInventory));
    }

    public Optional<RvPage> nextPage(RvInventory rvInventory) {
        Map.Entry<Integer, RvPage> entry = pages.entrySet().stream()
                .filter((page) -> page.getValue().getPage().equals(rvInventory))
                .findFirst().orElse(null);

        if (entry == null) return Optional.empty();

        if (!pages.containsKey(entry.getKey() + 1)) return Optional.empty();

        return Optional.of(pages.get(entry.getKey() + 1));
    }

    public Optional<RvPage> previousPage(RvInventory rvInventory) {
        Map.Entry<Integer, RvPage> entry = pages.entrySet().stream()
                .filter((page) -> page.getValue().getPage().equals(rvInventory))
                .findFirst().orElse(null);

        if (entry == null) return Optional.empty();

        if (!pages.containsKey(entry.getKey() - 1)) return Optional.empty();

        return Optional.of(pages.get(entry.getKey() - 1));
    }
}
