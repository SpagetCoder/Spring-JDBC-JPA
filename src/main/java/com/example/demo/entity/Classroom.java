package com.example.demo.entity;

public class Classroom
{
    private int roomId;
    private int roomSize;
    private String  screen;

    public Classroom(int roomId, int roomSize, String screen) {
        this.roomId = roomId;
        this.roomSize = roomSize;
        this.screen = screen;
    }
    public Classroom()
    {

    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "roomId=" + roomId +
                ", roomSize=" + roomSize +
                ", screen=" + screen +
                '}';
    }
}
