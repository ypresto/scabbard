package net.ypresto.scabbard.example.object;

public class UserInformationObject {
    private final int userId;

    public UserInformationObject(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString() + " (userId: " + userId + ")";
    }
}
