package org.example;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final List<Movie> AVAILABLE_MOVIES = new ArrayList<>(List.of(
            new Movie("Inception", "Christopher Nolan", 2010),
            new Movie("The Dark Knight", "Christopher Nolan", 2008),
            new Movie("Interstellar", "Christopher Nolan", 2014),
            new Movie("Pulp Fiction", "Quentin Tarantino", 1994),
            new Movie("Django Unchained", "Quentin Tarantino", 2012),
            new Movie("The Godfather", "Francis Ford Coppola", 1972),
            new Movie("The Matrix", "Lana Wachowski", 1999)
    ));

    @Override
    public List<Movie> getAllMovies() {
        return new ArrayList<>(AVAILABLE_MOVIES);
    }

    @Override
    public List<Movie> findByDirector(String director) {
        return AVAILABLE_MOVIES.stream()
                .filter(movie -> movie.getDirector().equalsIgnoreCase(director))
                .collect(Collectors.toList());
    }

    @Override
    public void addMovie(Movie movie) {
        AVAILABLE_MOVIES.add(movie);
    }
}

