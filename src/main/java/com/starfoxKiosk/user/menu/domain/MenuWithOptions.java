package com.starfoxKiosk.user.menu.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuWithOptions extends Menu {

    private int count = 0;
    private String size = "";
    private List<Option> options;
    private Set<String> optionSet;

    public MenuWithOptions(int id, String name, String categoryName, int categoryId,
        List<Option> options) {
        super(id, name, categoryName, categoryId);
        this.options = options;
        optionSet = new HashSet<>();
        for (Option option : options) {
            optionSet.add(option.getName());
        }
    }

    public MenuWithOptions(int id, String name, String categoryName, int categoryId) {
        super(id, name, categoryName, categoryId);
        options = new ArrayList<>();
        optionSet = new HashSet<>();
    }

    public void addOption(String name, String type) {
        if (optionSet.contains(name)) {
            for (Option option : options) {
                option.addType(type);
                return;
            }
        } else {
            optionSet.add(name);
            options.add(new Option(name, type));
        }
    }

    public int getCount() {
        return count;
    }

    public List<Option> getOptions() {
        return options;
    }

    public Set<String> getOptionSet() {
        return optionSet;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        String s = "";

        s += this.getName() + ", ";
        s += getCount() + "개";

        s += "\n";
        for (Option option : options) {
            s += option.getName() + " : " + option.getTypes() + "\n";
        }
        return s;
    }
}
