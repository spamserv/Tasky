package hr.etfos.josipvojak.tasky;

import android.widget.ImageView;

/**
 * Created by jvojak on 12.4.2016..
 */
public class Task {
    private int id;
    private String title;
    private String description;
    private String priorityPicture;

    public Task(int id, String title, String description, String priorityPicture) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.priorityPicture = priorityPicture;
    }

    public Task(String title, String description, String priorityPicture) {
        this.title = title;
        this.description = description;
        this.priorityPicture = priorityPicture;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriorityPicture() {
        return priorityPicture;
    }

    @Override
    public String toString() {
        return "Task number" + id + ",\n title=" + title + ", description=" + description;
    }
}
