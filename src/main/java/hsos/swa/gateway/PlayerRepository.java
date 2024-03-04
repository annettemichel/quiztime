package hsos.swa.gateway;

import hsos.swa.entity.Player;
import hsos.swa.boundary.dto.PlayerAdminImportDTO;
import hsos.swa.boundary.dto.PlayerUserImportDTO;
import hsos.swa.service.PlayerService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class PlayerRepository implements PlayerService, Serializable {

    @Inject
    EntityManager em;

    /*------------------------------------------- GET --------------------------------------------*/

    @Override
    public Collection<Player> getAllPlayers() {
        TypedQuery<Player> query = this.em.createNamedQuery("Player.findAll", Player.class);
        List<Player> resultList = query.getResultList();
        return resultList.stream().collect(Collectors.toList());
    }

    @Override
    public Player getPlayer(Long id) {
        return this.em.find(Player.class, id);
    }

    @Override
    public Player getPlayer(String username) {
        TypedQuery<Player> query = em.createQuery("SELECT player FROM Player player WHERE player.username = :username", Player.class);
        Player player = query.setParameter("username", username).getSingleResult();
        return player;
    }

    @Override
    public List<Player> getBestPlayers() {
        TypedQuery<Player> query = this.em.createNamedQuery("Player.findBestPlayers", Player.class);
        List<Player> resultList = query.getResultList();
        return resultList.stream().collect(Collectors.toList());
    }

    /*------------------------------------------- PUT --------------------------------------------*/

    @Override
    public Player updatePlayer(Long id, String mail, String password) {
        TypedQuery<Player> query = em.createQuery("SELECT player FROM Player player WHERE player.id = :id", Player.class);
        Player player = query.setParameter("id", id).getSingleResult();
        player.setEmail(mail);
        player.setPassword(password);
        return player;
    }

    @Override
    public Player updatePlayerAdmin(Long id, String mail, String password, String role) {
        TypedQuery<Player> query = em.createQuery("SELECT player FROM Player player WHERE player.id = :id", Player.class);
        Player player = query.setParameter("id", id).getSingleResult();
        player.setEmail(mail);
        player.setPassword(password);
        player.setRole(role);
        return player;
    }

    @Override
    public void increaseScore(Long playerId, int quizPoints) {
        TypedQuery<Player> query = em.createQuery("SELECT player FROM Player player WHERE player.id = :id", Player.class);
        Player player = query.setParameter("id", playerId).getSingleResult();
        int oldScore = player.getScore();
        int newScore = oldScore + quizPoints;
        player.setScore(newScore);
    }

    @Override
    public void subtractScore(Long playerId, int quizPoints) {
        TypedQuery<Player> query = em.createQuery("SELECT player FROM Player player WHERE player.id = :id", Player.class);
        Player player = query.setParameter("id", playerId).getSingleResult();
        int oldScore = player.getScore();
        int newScore = oldScore - quizPoints;
        player.setScore(newScore);
    }

    /*------------------------------------------ POST -------------------------------------------*/

    @Override
    public Player createPlayer(PlayerAdminImportDTO playerAdminImportDTO) {
        Player newPlayer = new Player(playerAdminImportDTO.getEmail(), playerAdminImportDTO.getUsername(), playerAdminImportDTO.getPassword(), playerAdminImportDTO.getRole());
        try {
            em.persist(newPlayer);
            return newPlayer;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Player createPlayer(PlayerUserImportDTO playerUserImportDTO) {
        Player newPlayer = new Player(playerUserImportDTO.getEmail(), playerUserImportDTO.getUsername(), playerUserImportDTO.getPassword());
        try {
            em.persist(newPlayer);
            return newPlayer;
        } catch (Exception e) {
            return null;
        }
    }

    /*----------------------------------------- DELETE ------------------------------------------*/

    @Override
    public void deletePlayer(Long playerId) {
        Player toBeDeletedPlayer = this.em.find(Player.class, playerId);
        this.em.remove(toBeDeletedPlayer);
    }

}
