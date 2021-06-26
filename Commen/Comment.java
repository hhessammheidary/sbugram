package Commen;

import java.io.Serializable;

public class Comment implements Serializable {
    private final User user;
    private final String comment;

    public Comment(User user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public String getUserUsername() {
        return user.getUsername();
    }

    public String getComment() {
        return comment;
    }

    public byte[] getProfileImage() {
        return user.getProfileImage();
    }
}
