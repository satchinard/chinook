package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Artist;
import com.adn.chinook.form.ArtistForm;
import com.adn.chinook.repository.ArtistRepository;
import static com.adn.chinook.utils.Paths.ARTIST;
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

/**
 *
 * @author cagecfi
 */
@Controller
@Slf4j
@RequestMapping(WEB_VERSION + ARTIST)
public class ArtistWebController implements BaseWebController<Artist, Integer, ArtistForm> {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public JpaRepository<Artist, Integer> getRepository() {
        return artistRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + ARTIST;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("artists", artistRepository.findAll());
        mav.setViewName("artist/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(Artist i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("artist/add");
            return mav;
        }
        if (i.getName() != null) {
            artistRepository.save(i);
        }
        mav.addObject("artists", artistRepository.findAll());
        mav.setViewName("artist/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Integer pk) {
        Artist artist = null;
        try {
            artist = artistRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (artist != null && artist.getArtistId() != null) {
            mav.addObject("artist", artist);
            mav.setViewName("artist/get");
            return mav;
        }
        mav.setViewName("artist/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        Artist artist = artistRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (artist != null) {
            mav.addObject("artist", artist);
            mav.setViewName("artist/update");
            return mav;
        }
        mav.setViewName("artist/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        Artist artist = artistRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (artist != null) {
            try {
                artistRepository.delete(artist);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("artists", artistRepository.findAll());
        mav.setViewName("artist/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(Artist i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("artists", artistRepository.findAll());
        mav.setViewName("artist/all");
        return mav;
    }

}
