package io.inovace.movie_info_service.resources;


import io.inovace.movie_info_service.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieResource {

    @RequestMapping("/{movieId}")
    public List<Movie> getCatalog(@PathVariable("movieId") String movieId){
        return Collections.singletonList(new Movie("1","Gladiator"));
    }


}
