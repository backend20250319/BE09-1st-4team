package com.starfoxKiosk.user.menu.domain;

public class Menu {

    private int id;
    private String name;
    private String categoryName;
    private int categoryId;

    public Menu(int id, String name, String categoryName, int categoryId) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
