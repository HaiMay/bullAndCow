/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullandcows.data;

import com.mycompany.bullandcows.model.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author haima
 */
@Repository
public class numberGuessDatabaseDao implements NumberGuessDao {

    @Autowired
    JdbcTemplate jdbc;

    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
            Round round = new Round();
            round.setResult(rs.getString("result"));
            round.setGuessTime(rs.getTimestamp("guessTime").toLocalDateTime());
            return round;
        }

    }

    @Override
    public Round add(Round round) {
        String addRound = "Insert into round(gameId, result, guessTime) Values (?,?,?)";
        jdbc.update(addRound, round.getGameId(), round.getResult(), Timestamp.valueOf(round.getGuessTime()));
        int id = jdbc.queryForObject("SELECT Count(*) from round where gameId = ?", Integer.class, round.getGameId());
      round.setRoundNumber(id);
      return round;

    }

}
