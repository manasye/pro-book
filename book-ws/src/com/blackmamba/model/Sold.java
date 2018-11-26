package com.blackmamba.model;

import java.util.Objects;

public class Sold {
    private String id;
    private String category;
    private int count;

    public Sold(String id, String category, int count) {
        this.id = id;
        this.category = category;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sold sold = (Sold) o;
        return count == sold.count &&
                Objects.equals(id, sold.id) &&
                Objects.equals(category, sold.category);
    }

    @Override
    public String toString() {
        return "Sold{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", count=" + count +
                '}';
    }
}
