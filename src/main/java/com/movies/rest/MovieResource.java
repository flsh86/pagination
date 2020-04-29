package com.movies.rest;

import com.movies.movie.MovieDTO;
import com.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin
public class MovieResource {
    private MovieService movieService;

    @Autowired
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    /*
        Most basic pagination using page and size as request parameters to divide resources
        It's using default sorting => SORT BY id ASC
    */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/basic")
    public ResponseEntity<Page<MovieDTO>> findAllBasic(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size) {
        Page<MovieDTO> movies = this.movieService.findAllBasic(page, size);
        return ResponseEntity.ok(movies);
    }

    /*
           IMPORTANT NOTE PARAMETER OF THE METHOD DOESN'T USE @RequestParam ANNOTATION!
        This method is using parameter of type Pageable that is send in request with 3 optional parameters (names of parameters can be changed in properties file):
            1. page - indexing starts from 0 to (n-1) of total pages
            2. size - how many elements can be present in single page (max capacity of content on page)
            3. sort - sorting by on of the entity parameter
        If any of this parameter is empty or the request to the resource is empty (without params) default values will be put.
        Those values can be set up in properties file or in the request method with proper annotation (example below)
        Direction of sorting can be changed by adding DESC or ASC after "," that should be put at the end of the request
    */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/pageable/basic")
    public ResponseEntity<Page<MovieDTO>> findAllPageableBasic(
            Pageable pageable) {
        Page<MovieDTO> movies = this.movieService.findAllPageableBasic(pageable);
        return ResponseEntity.ok(movies);
    }

    /*
        This method is using annotation that provides default response for no param request
        Still user can provide params for request to change sorting or pagination
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/pageable/default")
    public ResponseEntity<Page<MovieDTO>> findAllPageableMethod(
            @PageableDefault(size = 3, page = 0, sort = "title", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<MovieDTO> movies = this.movieService.findAllPageableDefault(pageable);
        return ResponseEntity.ok(movies);
    }

    /*
        In this approach the result is exactly the same as in the pageable/basic
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/basic/default")
    public ResponseEntity<Page<MovieDTO>> findAllBasicDefault(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sort,
            @RequestParam(name = "direction", required = false, defaultValue = "ASC") Sort.Direction direction
            ) {
            Page<MovieDTO> movies = this.movieService.findAllBasicDefault(page, size, sort, direction);
            return ResponseEntity.ok(movies);
    }

}
