package com.mobinyousefi.cs.examseating.model;

public class Room {

    private int id;
    private String code;
    private int capacity;

    public Room() {
    }

    public Room(int id, String code, int capacity) {
        this.id = id;
        this.code = code;
        this.capacity = capacity;
    }

    public Room(String code, int capacity) {
        this(0, code, capacity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
