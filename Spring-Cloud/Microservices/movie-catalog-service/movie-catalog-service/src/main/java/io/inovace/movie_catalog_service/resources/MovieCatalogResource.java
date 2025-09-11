package io.inovace.movie_catalog_service.resources;

import com.netflix.discovery.DiscoveryClient;
import io.inovace.movie_catalog_service.models.CatalogItem;
import io.inovace.movie_catalog_service.models.Movie;
import io.inovace.movie_catalog_service.models.MovieRating;
import io.inovace.movie_catalog_service.models.UserRating;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("catalog")
public class MovieCatalogResource {

@Autowired
public RestTemplate restTemplate;

@Autowired
public WebClient.Builder webClient;

@Autowired
private DiscoveryClient discoveryClient;

@RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

//    RestTemplate restTemplate = new RestTemplate();


//    List<MovieRating> ratings = Arrays.asList(new MovieRating("1234",4), new MovieRating("1235",3));
//    UserRating ratings = restTemplate.getForObject("http://localhost:8082/ratingdata/users/"+userId, UserRating.class);
    UserRating ratings = restTemplate.getForObject("http:/rating-info-service:8082/ratingdata/users/"+userId, UserRating.class);
//            return ratings.stream().map(rating -> new CatalogItem("4","Glaadiator","test")).collect(Collectors.toList());

    return ratings.getMovieRatingList().stream().map(rating ->{
//        Movie[] movie = restTemplate.getForObject("http://localhost:8060/movie" + rating.getMovieId(), Movie[].class);
//        Movie[] movie =  webClient.build().get().uri("http://localhost:8060/movie/"+ rating.getMovieId()).retrieve().bodyToMono(Movie[].class).block();
        Movie[] movie =  webClient.build().get().uri("http://movie-info-servie:8060/movie/"+ rating.getMovieId()).retrieve().bodyToMono(Movie[].class).block();

        return new CatalogItem(rating.getRating()+"",movie[0].getName(),"test");
    }).collect(Collectors.toList());
//        return Collections.singletonList(new CatalogItem("Transformer","Test","4"));
    }

}