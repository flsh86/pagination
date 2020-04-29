package com.movies.service;

import com.movies.mapper.MovieMapper;
import com.movies.movie.MovieDTO;
import com.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<MovieDTO> findAllBasic(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.movieRepository.findAll(pageable)
                .map(MovieMapper::toDTO);
    }


    public Page<MovieDTO> findAllPageableBasic(Pageable pageable) {
        return this.movieRepository.findAll(pageable)
                .map(MovieMapper::toDTO);
    }

    public Page<MovieDTO> findAllPageableDefault(Pageable pageable) {
        return this.movieRepository.findAll(pageable)
                .map(MovieMapper::toDTO);
    }

    public Page<MovieDTO> findAllBasicDefault(Integer page, Integer size, String sort, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        return this.movieRepository.findAll(pageable)
                .map(MovieMapper::toDTO);
    }
}
