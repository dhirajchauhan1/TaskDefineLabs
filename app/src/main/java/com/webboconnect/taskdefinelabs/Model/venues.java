package com.webboconnect.taskdefinelabs.Model;

import java.util.List;

public class venues {
    private String id;
    private String name;
    private location location;
    private List<categories> categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.webboconnect.taskdefinelabs.Model.location getLocation() {
        return location;
    }

    public void setLocation(com.webboconnect.taskdefinelabs.Model.location location) {
        this.location = location;
    }

    public List<com.webboconnect.taskdefinelabs.Model.categories> getCategories() {
        return categories;
    }

    public void setCategories(List<com.webboconnect.taskdefinelabs.Model.categories> categories) {
        this.categories = categories;
    }
}
