package model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ppolak on 12.05.17.
 */
public class Category {
    protected Integer id;
    protected String name;

    private static AtomicInteger number = new AtomicInteger(0);
    public  static List<Category> categories = new ArrayList<>();
    private ArrayList<Event> eventList = new ArrayList<>();

    public Category(){

    }

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


    public void addEventToCategory(Event event) {
        this.eventList.add(event);
    }

    public static void getEventCategories() {

//        if (categories.size() > 0) {
//            for (int i = 0; i < categories.size(); i++) {
//                if (Category.categories.get(i).getClass() == new Category().getClass()) {
//                    System.out.println(categories.get(i).getCategoryId() + ". " + categories.get(i).getCategoryName() +"\n");
//                }
//            }
//        }
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

    public static void addCategoryToList(Category category) {
        categories.add(category);
    }

    public static Category getCategoryById(Integer id) {
        Iterator events = categories.iterator();
        while (events.hasNext()) {
            Category category = (Category) events.next();
            if (Objects.equals(category.getCategoryId(), id)) {
                return category;
            }
        }
        return null;
    }

    public static boolean isThereAnyCategory() {
        if (categories.size() > 0) {
            for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).getClass() == new Category().getClass()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    public static void getEventsFromCategory(Category eventCategory) {
//        if (eventCategory.eventList.size()> 0){
//            for (int i = 0; i < eventCategory.eventList.size(); i++)
//                show.text("Event name: " + eventCategory.eventList.get(i).getName());
//        } else {
//            show.text("There are no events for this category");
//        }

    }

    public static List<Category> getListOfCategories() {
        return categories;
    }

    public String toString() {
        return String.format("id: %1$d," + "name: %2$s",
                this.id, this.name);
    }
}
