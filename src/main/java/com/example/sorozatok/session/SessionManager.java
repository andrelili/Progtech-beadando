package com.example.sorozatok.session;

import com.example.sorozatok.model.User;

public class SessionManager {
    private static User activeUser;

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static boolean isLoggedIn() {
        return activeUser != null;
    }
}

