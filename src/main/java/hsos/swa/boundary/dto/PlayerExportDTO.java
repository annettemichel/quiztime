package hsos.swa.boundary.dto;

import hsos.swa.entity.Player;

import javax.enterprise.inject.Vetoed;

@Vetoed
public class PlayerExportDTO {
    public Long id;
    public String email;
    public String username;
    public String password;
    public String role;
    public int score;

    public PlayerExportDTO(Player player){
        this.id = player.getId();
        this.email = player.getEmail();
        this.username = player.getUsername();
        this.password = player.getPassword();
        this.role = player.getRole();
        this.score = player.getScore();
    }

    @Override
    public String toString() {
        return "PlayerExportDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", score=" + score +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getScore() {
        return score;
    }
}
