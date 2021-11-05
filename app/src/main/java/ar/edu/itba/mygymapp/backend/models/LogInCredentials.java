package ar.edu.itba.mygymapp.backend.models;

public class LogInCredentials {

    private String username;
    private String password;

    public LogInCredentials(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}