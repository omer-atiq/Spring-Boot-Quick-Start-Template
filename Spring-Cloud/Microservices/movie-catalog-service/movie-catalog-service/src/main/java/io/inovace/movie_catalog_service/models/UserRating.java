package io.inovace.movie_catalog_service.models;

import java.util.List;

public class UserRating {

    private List<MovieRating> movieRatingList;

    public List<MovieRating> getMovieRatingList() {
        return movieRatingList;
    }

    public void setMovieRatingList(List<MovieRating> movieRatingList) {
        this.movieRatingList = movieRatingList;
    }
}
