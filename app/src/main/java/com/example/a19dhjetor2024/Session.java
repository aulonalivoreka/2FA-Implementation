package com.example.a19dhjetor2024;

public class Session {
    private static String loggedEmail = null;

    public static synchronized String getLoggedEmail() {
        return loggedEmail;
    }

    public static synchronized void setLoggedEmail(String loggedEmail) {
        if (loggedEmail != null && loggedEmail.contains("@")) {
            Session.loggedEmail = loggedEmail;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public static synchronized void clearSession() {
        Session.loggedEmail = null;
    }
}
