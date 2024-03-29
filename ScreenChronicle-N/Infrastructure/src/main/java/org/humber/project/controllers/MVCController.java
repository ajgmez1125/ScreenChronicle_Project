package org.humber.project.controllers;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import org.humber.project.domain.Movie;
import org.humber.project.domain.WatchList;
import org.humber.project.services.WatchListJPAService;

@Controller
public class MVCController
{
    private final RestTemplate restTemplate;
    private final WatchListJPAService watchListJPAService;

    public MVCController(RestTemplateBuilder restTemplate, WatchListJPAService watchListJPAService)
    {
        this.restTemplate = restTemplate.build();
        this.watchListJPAService = watchListJPAService;
    }

    @GetMapping("/")
    public String homepage(Model model)
    {
        ResponseEntity<Movie[]> moviesResponse = restTemplate.getForEntity("http://localhost:8000/rest/movie", Movie[].class);
        Movie[] movies = moviesResponse.getBody();
        model.addAttribute("movies", movies);
        //System.out.println(model.toString());
        return "index";
    }

    @GetMapping("/watchlist")
    public String showWatchList(Model model) {
        // Assuming userId is 1 for now - dynamic implementation with spring security
        Long userId = 1L;
        List<WatchList> watchlist = watchListJPAService.getWatchListByUserId(userId);
        model.addAttribute("watchlist", watchlist);
        return "watchlist";
    }
}
