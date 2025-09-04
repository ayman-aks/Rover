package com.rover.model;

public interface Robot {
    void setDirection(String direction);
    void setCommands(String commands);
    Character getDirection();
    String getCommands();
    int  getX();
    int getY();
    void setX(int x);
    void setY(int y);
}
