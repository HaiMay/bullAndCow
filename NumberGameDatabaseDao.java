/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullandcows.data;

import com.mycompany.bullandcows.data.numberGuessDatabaseDao.RoundMapper;
import com.mycompany.bullandcows.model.Game;
import com.mycompany.bullandcows.model.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author haima
 */
@Repository
public class NumberGameDatabaseDao implements NumberGameDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    private static final class GameMapper implements RowMapper<Game> {
        
        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setNumber(rs.getString("number"));
            game.setFinished(rs.getBoolean("isFinished"));
            
            return game;
        }
        
    }
    
    @Override
    @Transactional
    public Game add(Game game) {
        String insertGame = "Insert into game (number, isFinished) values (?,?)";
        jdbc.update(insertGame, game.getNumber(), game.isFinished());
        int id = jdbc.queryForObject("SELECT LAST INSERT ID()", Integer.class);
        game.setGameId(id);
        
        return game;
    }
    
    @Override
    public Game getGameById(int id) {
        String getGame = "select * from game where gameId = ?";
        Game game = jdbc.queryForObject(getGame, new GameMapper(), id);
        insertRounds(game);
        return game;
    }
    
    @Override
    public List<Game> gettAllGames() {
        String getAllGames = "select * from game";
        List<Game> games = jdbc.query(getAllGames, new GameMapper());
        for(Game game : games){
            insertRounds(game);
        }
        return games;
    }
    
    @Override
    @Transactional
    public void deleteGame(int id) {
        String deleteGuesses = "delete from round where gameId = ?";
        String deleteGame = "delete from game where gameId = ?";
        jdbc.update(deleteGuesses, id);
        jdbc.update(deleteGame, id);
    }
    
    private void insertRounds(Game game) {
        int id = game.getGameId();
        String getRounds = "select * from round where gameId = ?";
        List<Round> rounds = jdbc.query(getRounds, new RoundMapper(), id);
        game.setRounds(rounds);
       
    }
}
