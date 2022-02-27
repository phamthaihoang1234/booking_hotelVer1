package com.example.bookinghotel.entities;

import java.util.List;

public class RoomGroup {
    String type ;
    int length_of_room_group;
    List<Room> empty_rooms;
    double price;

    public RoomGroup(String type, int length_of_room_group, List<Room> empty_rooms, double price) {
        this.type = type;
        this.length_of_room_group = length_of_room_group;
        this.empty_rooms = empty_rooms;
        this.price = price;
    }

    public RoomGroup() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength_of_room_group() {
        return length_of_room_group;
    }

    public void setLength_of_room_group(int length_of_room_group) {
        this.length_of_room_group = length_of_room_group;
    }

    public List<Room> getEmpty_rooms() {
        return empty_rooms;
    }

    public void setEmpty_rooms(List<Room> empty_rooms) {
        this.empty_rooms = empty_rooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
