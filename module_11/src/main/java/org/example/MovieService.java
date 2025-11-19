package org.example;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> getMoviesByDirector(String director);
    Movie addMovie(Movie movie);
}

