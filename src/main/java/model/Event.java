package model;

import java.util.concurrent.atomic.AtomicInteger;


public class Event {
    private Integer id;
    private String name;
    private Category category;
    private String description;
    private String startDate;
    private static AtomicInteger number = new AtomicInteger(5);

    public Event(String name, Category category, String description, String startDate ) {
        this.id = number.getAndIncrement();
        this.name = name;
        this.category = category;
        this.description = description;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getCategoryName() {
        return this.category.getCategoryName();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "category: %3$s, " +
                        "description: %4$s, " +
                        "startDate: %5$s",
                this.id,
                this.name,
                this.category.getCategoryName(),
                this.description,
                this.startDate);
    }
}

