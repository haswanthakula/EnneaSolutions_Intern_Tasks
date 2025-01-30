package com.movie.controller;

import com.movie.entity.Actor;
import com.movie.entity.BoxOffice;
import com.movie.entity.Director;
import com.movie.entity.Movie;
import com.movie.repository.ActorRepository;
import com.movie.repository.BoxOfficeRepository;
import com.movie.repository.DirectorRepository;
import com.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DatabaseViewController {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final BoxOfficeRepository boxOfficeRepository;

    @GetMapping("/")
    public String showDatabaseHome() {
        log.info("Accessing home page");
        return "/home";
    }

    @GetMapping("/movies")
    public String showMovies(Model model) {
        List<Movie> movies = movieRepository.findAll();
        log.info("Found {} movies", movies.size());
        model.addAttribute("movies", movies);
        return "/movies";
    }

    @GetMapping("/actors")
    public String showActors(Model model) {
        List<Actor> actors = actorRepository.findAll();
        log.info("Found {} actors", actors.size());
        model.addAttribute("actors", actors);
        return "/actors";
    }

    @GetMapping("/directors")
    public String showDirectors(Model model) {
        List<Director> directors = directorRepository.findAll();
        log.info("Found {} directors", directors.size());
        model.addAttribute("directors", directors);
        return "/directors";
    }

    @GetMapping("/box-office")
    public String showBoxOffice(Model model) {
        List<BoxOffice> boxOffices = boxOfficeRepository.findAll();
        log.info("Found {} box office records", boxOffices.size());
        model.addAttribute("boxOffices", boxOffices);
        return "/box-office";
    }
}
