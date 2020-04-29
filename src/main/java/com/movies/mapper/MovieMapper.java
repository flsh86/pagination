package com.movies.mapper;

import com.movies.movie.Movie;
import com.movies.movie.MovieDTO;

public class MovieMapper {
    public static Movie toEntity(MovieDTO dto) {
        return new Movie(dto.getId(), dto.getTitle());
    }

    public static MovieDTO toDTO(Movie entity) {
        return new MovieDTO(entity.getId(), entity.getTitle());
    }
}
