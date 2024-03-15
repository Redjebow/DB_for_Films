package com.example.validatingforminput.film;

import com.example.validatingforminput.user.User;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {
    public Film toEntity(FilmDTO filmDTO) {
        Film film = new Film();
        film.setActors(filmDTO.getActors());
        film.setGenre(filmDTO.getGenre());
        film.setYear(filmDTO.getYear());
        film.setTitle(filmDTO.getTitle());
        return film;
    }
}
