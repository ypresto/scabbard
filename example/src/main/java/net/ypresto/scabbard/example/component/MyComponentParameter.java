package net.ypresto.scabbard.example.component;

import net.ypresto.scabbard.component.ComponentParameter;

public class MyComponentParameter implements ComponentParameter {
    private final int userId;

    public MyComponentParameter(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
