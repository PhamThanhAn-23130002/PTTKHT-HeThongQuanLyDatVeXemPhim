package Model;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String movieId;
    private String movieName;
    private String genre;
    private int duration;
    private String status;
    private List<MovieScreening> movieScreeningList;

    // UC-2.1: registry tĩnh để tra cứu Movie từ screeningId.
    private static List<Movie> movies = new ArrayList<>();

    public Movie(String movieId, String movieName, String genre, int duration, String status) {

        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
        this.status = status;
        movies.add(this);
    }

    public Movie(String movieId, String movieName, String genre, int duration, String status, List<MovieScreening> movieScreeningList) {

        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
        this.status = status;
        this.movieScreeningList = movieScreeningList;
        movies.add(this);
    }

    // UC-2.1: Controller dùng để map screeningId -> Movie (lấy movieName).
    public static Movie findByScreeningId(String screeningId) {
        if (screeningId == null) return null;
        for (Movie m : movies) {
            if (m.movieScreeningList == null) continue;
            for (MovieScreening s : m.movieScreeningList) {
                if (screeningId.equalsIgnoreCase(s.getId())) return m;
            }
        }
        return null;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MovieScreening> getMovieScreeningList() { return movieScreeningList; }
}