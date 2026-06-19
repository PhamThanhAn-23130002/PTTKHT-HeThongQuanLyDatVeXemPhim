package Model;

public class Movie {

    private String movieId;
    private String movieName;
    private String genre;
    private int duration;
    private String status;

    public Movie(String movieId, String movieName,
                 String genre, int duration,
                 String status) {

        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
        this.status = status;
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
}