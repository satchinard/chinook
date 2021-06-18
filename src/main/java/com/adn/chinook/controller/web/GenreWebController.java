package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Genre;
import com.adn.chinook.form.GenreForm;
import com.adn.chinook.repository.GenreRepository;
import static com.adn.chinook.utils.Paths.GENRE;
import static com.adn.chinook.utils.Paths.WEB_VERSION;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping(WEB_VERSION + GENRE)
public class GenreWebController implements BaseWebController<Genre, Integer, GenreForm> {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public JpaRepository<Genre, Integer> getRepository() {
        return genreRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + GENRE;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("genres", genreRepository.findAll());
        mav.setViewName("genre/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(Genre i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("genre/add");
            return mav;
        }
        if (i.getName() != null) {
            genreRepository.save(i);
        }
        mav.addObject("genres", genreRepository.findAll());
        mav.setViewName("genre/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Integer pk) {
        Genre genre = null;
        try {
            genre = genreRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (genre != null && genre.getGenreId() != null) {
            mav.addObject("genre", genre);
            mav.setViewName("genre/get");
            return mav;
        }
        mav.setViewName("genre/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        Genre genre = genreRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (genre != null) {
            mav.addObject("genre", genre);
            mav.setViewName("genre/update");
            return mav;
        }
        mav.setViewName("genre/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        Genre genre = genreRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (genre != null) {
            try {
                genreRepository.delete(genre);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("genres", genreRepository.findAll());
        mav.setViewName("genre/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(Genre i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("genres", genreRepository.findAll());
        mav.setViewName("genre/all");
        return mav;
    }

}
