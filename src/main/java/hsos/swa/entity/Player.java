package hsos.swa.entity;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;


import javax.enterprise.inject.Vetoed;
import javax.persistence.*;
@Vetoed
@Entity
@NamedQuery(name = "Player.findAll", query = "SELECT players FROM Player players ORDER BY players.id")
@NamedQuery(name = "Player.findBestPlayers", query = "SELECT players FROM Player players ORDER BY players.score DESC")
@UserDefinition
public class Player{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name="player_seq", sequenceName = "player_seq",allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    @Username
    private String username;
    @Password
    private String password;
    private int score;
    @Roles
    private String role;

    public Player() {
    }

    public Player(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = BcryptUtil.bcryptHash(password);
        this.role = "user";
        this.score = 0;
    }

    public Player(String email, String username, String password, String role) {
        this.email = email;
        this.username = username;
        this.password = BcryptUtil.bcryptHash(password);
        this.role = role;
        this.score = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.password = BcryptUtil.bcryptHash(password);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}