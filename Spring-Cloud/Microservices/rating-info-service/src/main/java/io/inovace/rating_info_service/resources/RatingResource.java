package io.inovace.rating_info_service.resources;

import io.inovace.rating_info_service.models.MovieRating;
import io.inovace.rating_info_service.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public MovieRating getRating(@PathVariable("movieId") String movieId){
        return new MovieRating(movieId,"4");
    }


    @RequestMapping("/users/{userId}")
    public UserRating getRatingByUserId(@PathVariable("userId") String userId){
        List<MovieRating> movieRatingList = Arrays.asList(new MovieRating("1234","3"),new MovieRating("9999","9"));

    UserRating userRating = new UserRating();
    userRating.setMovieRatingList(movieRatingList);
    return userRating;
    }



}
