package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Movie;

public class MovieController {

    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public boolean deleteMovie(String movieId) {

        return movies.removeIf(
                movie -> movie.getMovieId().equals(movieId));
    }

    public void updateMovie(String id,
                            String name,
                            String genre,
                            int duration) {

        for(Movie m : movies) {

            if(m.getMovieId().equals(id)) {

                m.setMovieName(name);
                m.setGenre(genre);
                m.setDuration(duration);
            }
        }
    }

    public void updateStatus(String id,
                             String status) {

        for(Movie m : movies) {

            if(m.getMovieId().equals(id)) {

                m.setStatus(status);
            }
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }
}