package hsos.swa.boundary.dto;

import javax.enterprise.inject.Vetoed;
import java.io.Serializable;

@Vetoed
public class PlayerUserImportDTO{
    private String email;
    private String username;
    private String password;

    public PlayerUserImportDTO(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public PlayerUserImportDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PlayerUserImportDTO{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
