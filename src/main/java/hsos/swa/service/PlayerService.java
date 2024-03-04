package hsos.swa.service;

import hsos.swa.entity.Player;
import hsos.swa.boundary.dto.PlayerAdminImportDTO;
import hsos.swa.boundary.dto.PlayerUserImportDTO;

import java.util.Collection;
import java.util.List;

public interface PlayerService {

    Player createPlayer(PlayerAdminImportDTO playerAdminImportDTO);

    Player createPlayer(PlayerUserImportDTO playerUserImportDTO);

    Player getPlayer(Long id);

    Player getPlayer(String username);

    Collection<Player> getAllPlayers();

    void deletePlayer(Long id);

    Player updatePlayer(Long id, String mail, String password);

    Player updatePlayerAdmin(Long id, String mail, String password, String role);

    void increaseScore(Long playerId, int quizPoints);

    void subtractScore(Long playerId, int quizPoints);

    List<Player> getBestPlayers();



}