package ru.itis.kpfu.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Inventory {

    List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public void show() {
        if (!items.isEmpty()) {
            items.forEach(Item::showInfo);
        } else System.out.println("Ваш инвентарь пуст!");
    }

}
