package model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Category {
    protected Integer id;
    protected String name;
    private static AtomicInteger number = new AtomicInteger(0);
    public  static List<Category> categories = new ArrayList<>();
    private ArrayList<Event> eventList = new ArrayList<>();

    public Category(String name) {
        this.id = number.getAndIncrement();
        this.name = name;
        this.eventList = new ArrayList<>();
        categories.add(this);
    }

    public Integer getCategoryId() {
        return id;
    }

    public String getCategoryName() {
        return name;
    }

    public static Category getCategoryByName(String name) {
        Iterator<Category> itr = categories.iterator();
        while (itr.hasNext()) {
            Category category = itr.next();
            if (category.getCategoryName().toLowerCase().equals(name.toLowerCase())) {
                return category;
            }
        }
        return null;
    }

    public String toString() {
        return String.format("id: %1$d," + "name: %2$s",
                this.id, this.name);
    }
}
