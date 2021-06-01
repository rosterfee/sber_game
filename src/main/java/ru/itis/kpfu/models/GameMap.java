package ru.itis.kpfu.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class GameMap {

    List<Location> locations;
    int enemiesLeft;

    public GameMap(int size) {

        locations = new ArrayList<>();
        enemiesLeft = 0;

        Location[][] gameField = new Location[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameField[i][j] = new Location();
            }
        }

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                Location location = gameField[i][j];

                int randomNumber = random.nextInt(5);
                if (randomNumber == 0) {
                    Item loot = new Item();
                    randomNumber = random.nextInt(2);
                    if (randomNumber == 0) {
                        loot.setName("аптечка");
                        loot.setDescription("Востаналивает 1 единицу здоровья");
                        loot.setMovable(false);
                    } else {
                        loot.setName("патрон");
                        loot.setDescription("Можно использовать для убийства противника");
                        loot.setMovable(true);
                    }
                    location.setLoot(loot);
                }

                randomNumber = random.nextInt(5);
                if (randomNumber == 0) {
                    location.setHasEnemy(true);
                    enemiesLeft++;
                }

                java.util.Map<Direction, Location> directions = location.getDirections();
                if (j > 0) {
                    directions.put(Direction.WEST, gameField[i][j - 1]);
                }
                if (j < size - 1) {
                    directions.put(Direction.EAST, gameField[i][j + 1]);
                }
                if (i > 0) {
                    directions.put(Direction.NORTH, gameField[i - 1][j]);
                }
                if (i < size - 1) {
                    directions.put(Direction.SOUTH, gameField[i + 1][j]);
                }

                locations.add(location);
            }
        }
    }

    public Location getRandomLocation() {
        int size = locations.size();
        Random random = new Random();
        int randomNumber = random.nextInt(size);
        return locations.get(randomNumber);
    }

}
