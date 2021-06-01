package ru.itis.kpfu.models;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class Player {

    Logger logger = LoggerFactory.getLogger(Player.class);

    int health;
    private Location location;
    private Inventory inventory;
    private Map<String, Direction> directionsMap;

    public Player() {

        inventory = new Inventory();

        directionsMap = new HashMap<>();
        directionsMap.put("влево", Direction.WEST);
        directionsMap.put("вправо", Direction.EAST);
        directionsMap.put("вверх", Direction.NORTH);
        directionsMap.put("вниз", Direction.SOUTH);

        health = 3;
    }

    public void lookAround() {

        logger.info("look around");

        System.out.print("1.На локации есть враг: ");
        if (location.isHasEnemy()) {
            System.out.println("да");
        } else System.out.println("нет");

        System.out.print("2.Лут: ");
        Item item = location.getLoot();
        if (item == null) {
            System.out.println("отсутствует");
        } else location.getLoot().showInfo();

        System.out.print("3.Можно пойти: ");
        Set<Direction> directionSet = location.getDirections().keySet();
        directionSet.forEach(direction -> {
            if (direction.equals(Direction.EAST)) {
                System.out.print("вправо, ");
            }
            else if (direction.equals(Direction.WEST)) {
                System.out.print("влево, ");
            }
            else if (direction.equals(Direction.NORTH)) {
                System.out.print("вверх, ");
            }
            else if (direction.equals(Direction.SOUTH)) {
                System.out.print("вниз, ");
            }
        });

    }

    public void go(String directionString) {

        if (directionString.equals("влево") || (directionString.equals("вправо")) ||
                (directionString.equals("вверх")) || (directionString.equals("вниз"))) {

            Direction direction = directionsMap.get(directionString);

            Map<Direction, Location> directions = location.getDirections();
            if (directions.containsKey(direction)) {
                if (location.isHasEnemy()) {
                    logger.info("Враг нанес урон");
                    health--;
                    System.out.println("Враг нанес по вам урон. Вы потяряли единицу здоровья!");
                    System.out.println("Единиц здоровья: " + health);
                }
                location = directions.get(direction);
                logger.info("Локацию сменена");
                System.out.println("Вы успешно сменили локацию");
            } else {
                System.out.println("В данном навправлении пойти нельзя!");
            }
        }
        else {
            logger.info("Неправильно введено навправление");
            System.out.println("Вы неправильно ввели название направления. Возможный список: " +
                    "'влево', 'вправо', 'вверх', 'вниз'.");
            throw new IllegalArgumentException();
        }
    }

    public void take(String itemName) {

        Item item = location.getLoot();
        if (item != null) {
            if (item.getName().equals(itemName)) {
                location.setLoot(null);
                inventory.items.add(item);
                System.out.println("Предмет успешно добавлен в инвентарь");
                logger.info("Предмет добавлен в иентентарь");
            }
            else {
                System.out.println("Нет такого предмета на локации!");
            }
        }
        else {
            logger.info("лут не найден");
            System.out.println("На данной локации нет лута!");
        }
    }

    public void use(String itemName) {
        Item item = null;
        List<Item> items = inventory.getItems();
        for (Item item1: items) {
            if (item1.getName().equals(itemName)) {
                item = item1;
                break;
            }
        }
        if (item == null) {
            logger.info("Предмет не найден в интентаре");
            System.out.println("Нет такого предмета в инвентаре!");
        }
        else {
            if (itemName.equals("аптечка")) {
                if (health < 3) {
                    health++;
                    logger.info("Использована аптечка");
                    System.out.println("Аптечка использована - здоровье: " + health);
                }
            }
            else if (itemName.equals("патрон")) {
                if (location.isHasEnemy()) {
                    location.setHasEnemy(false);
                    logger.info("Враг убит");
                    System.out.println("Враг убит!");
                }
                else {
                    System.out.println("Вы зря потратили патрон ((");
                }
            }
            inventory.remove(item);
        }
    }

    public void viewInventory() {
        logger.info("Просмотр инвентаря");
        inventory.show();
    }

}
