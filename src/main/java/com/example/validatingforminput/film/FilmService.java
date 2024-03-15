package com.example.validatingforminput.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
    @Autowired
    FilmRepository filmRepository;
    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }
}
