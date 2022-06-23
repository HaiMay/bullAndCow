/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullandcows.data;

import com.mycompany.bullandcows.model.Game;
import java.util.List;

/**
 *
 * @author haima
 */
public interface NumberGameDao {
    Game add(Game game);
    Game getGameById(int id);
    List<Game> gettAllGames();
    void deleteGame(int id);
    
}
