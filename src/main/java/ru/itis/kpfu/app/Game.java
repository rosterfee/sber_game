package ru.itis.kpfu.app;

import ru.itis.kpfu.models.GameMap;
import ru.itis.kpfu.models.Location;
import ru.itis.kpfu.models.Player;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размерность игрового поля(от 5 до 20)");
        int mapSize = scanner.nextInt();
        while (mapSize < 5 || mapSize > 20) {
            System.out.println("Недопустимая размерность поля!");
            mapSize = scanner.nextInt();
        }

        GameMap gameMap = new GameMap(mapSize);

        Player player = new Player();
        Location location = gameMap.getRandomLocation();
        player.setLocation(location);

        System.out.println("Игра начата!");

        while (player.getHealth() > 0 && gameMap.getEnemiesLeft() > 0) {

            System.out.println("Введите одну из следующих команд");
            showActions();
            scanner.nextLine();

            String action = scanner.nextLine();
            System.out.println(action);
            if (action.equals("Осмотреться")) {
                player.lookAround();
            }
            else if (action.startsWith("Идти ")) {
                String[] split = action.split(" ");
                try {
                    player.go(split[1]);
                } catch (IllegalArgumentException e) {
                    //ignore
                }
            }
            else if (action.startsWith("Подобрать ")) {
                String[] split = action.split(" ");
                player.take(split[1]);
            }
            else if (action.startsWith("Использовать ")) {
                String[] split = action.split(" ");
                player.use(split[1]);
            }
            else if (action.equals("Просмотреть инвентарь")) {
                player.viewInventory();
            }
            else {
                System.out.println("Вы неверно ввели команду!");
            }
        }

        if (player.getHealth() == 0) {
            System.out.println("Вас убили! Игра закончена.");
        }
        else if (gameMap.getEnemiesLeft() == 0) {
            System.out.println("Вы убили всех врагов на карте. Победа!");
        }

    }

    public static void showActions() {
        System.out.println("1.Осмотреться");
        System.out.println("2.Идти <направление> (влево, вправо, вверх, вниз)");
        System.out.println("3.Подобрать <название предмета>");
        System.out.println("4.Использовать <название предмета>");
        System.out.println("5.Просмотреть инвентарь");
    }
}
