package com.blackmamba.model;

import java.util.Objects;

public class Sold {
    private int id;
    private String category;
    private int count;

    public Sold(int id, String category, int count) {
        this.id = id;
        this.category = category;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setCount(int sold) {
        this.count = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sold sold1 = (Sold) o;
        return id == sold1.id &&
                count == sold1.count &&
                Objects.equals(category, sold1.category);
    }

    @Override
    public String toString() {
        return "Sold{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", count=" + count +
                '}';
    }
}
