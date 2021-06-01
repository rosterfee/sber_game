package ru.itis.kpfu.models;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Location {

    private Item loot;
    private Map<Direction, Location> directions;
    private boolean hasEnemy;

    public Location() {
        directions = new HashMap<>();
    }

}
