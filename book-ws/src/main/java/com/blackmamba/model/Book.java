package com.blackmamba.model;

//import java.util.Objects;

import java.util.Objects;

public class Book{
    private String id;
    private int price;
    private String category;
    private int sold;

    public Book(String id, int price, String category, int sold) {
        this.id = id;
        this.price = price;
        this.category = category;
        this.sold = sold;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return price == book.price &&
                sold == book.sold &&
                Objects.equals(id, book.id) &&
                Objects.equals(category, book.category);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", sold=" + sold +
                '}';
    }
}
