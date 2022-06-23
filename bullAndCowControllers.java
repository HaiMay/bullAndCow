/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullandcows.controllers;

import com.mycompany.bullandcows.model.Game;
import com.mycompany.bullandcows.model.Round;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.bullandcows.data.NumberGuessDao;

/**
 *
 * @author haima
 */
@RestController
@RequestMapping("/api/bullAndCows")
public class bullAndCowControllers {
    private final NumberGuessDao dao;
    public bullAndCowControllers(NumberGuessDao dao){
        this.dao = dao;
    }
    @GetMapping
    public List<Game> all() {
        return null;
    }
    @GetMapping
    public List<Round> allRounds() {
        return null;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game create(@RequestBody Game game) {
        return null;
    }
}
