package ru.itis.kpfu.models;

import lombok.Data;

@Data
public class Item {

    private String name;
    private String description;
    private boolean movable;
    private int count;

    public void showInfo() {
        System.out.println("Название предмета: " + name);
        System.out.println("Описание предмета: " + description);
        System.out.print("Предмет используется на: ");
        if (movable) {
            System.out.println("враге");
        }
        else System.out.println("себе");
    }

}
