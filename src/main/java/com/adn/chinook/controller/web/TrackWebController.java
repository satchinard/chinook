package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Track;
import com.adn.chinook.form.TrackForm;
import com.adn.chinook.repository.AlbumRepository;
import com.adn.chinook.repository.GenreRepository;
import com.adn.chinook.repository.MediaTypeRepository;
import com.adn.chinook.repository.TrackRepository;
import static com.adn.chinook.utils.Paths.TRACK;
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
@RequestMapping(WEB_VERSION + TRACK)
public class TrackWebController implements BaseWebController<Track, Integer, TrackForm> {

    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MediaTypeRepository mediaTypeRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public JpaRepository<Track, Integer> getRepository() {
        return trackRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + TRACK;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("tracks", trackRepository.findAll());
        mav.setViewName("track/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(Track i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.addObject("allGenres", genreRepository.findAll());
            mav.addObject("allMediaTypes", mediaTypeRepository.findAll());
            mav.addObject("allAlbums", albumRepository.findAll());
            mav.setViewName("track/add");
            return mav;
        }
        if (i.getName() != null) {
            trackRepository.save(i);
        }
        mav.addObject("tracks", trackRepository.findAll());
        mav.setViewName("track/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Integer pk) {
        Track track = null;
        try {
            track = trackRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (track != null && track.getTrackId() != null) {
            mav.addObject("allGenres", genreRepository.findAll());
            mav.addObject("allMediaTypes", mediaTypeRepository.findAll());
            mav.addObject("allAlbums", albumRepository.findAll());
            mav.addObject("track", track);
            mav.setViewName("track/get");
            return mav;
        }
        mav.setViewName("track/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        Track track = trackRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (track != null) {
            mav.addObject("allGenres", genreRepository.findAll());
            mav.addObject("allMediaTypes", mediaTypeRepository.findAll());
            mav.addObject("allAlbums", albumRepository.findAll());
            mav.addObject("track", track);
            mav.setViewName("track/update");
            return mav;
        }
        mav.setViewName("track/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        Track track = trackRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (track != null) {
            try {
                trackRepository.delete(track);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("tracks", trackRepository.findAll());
        mav.setViewName("track/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(Track i, BindingResult bindingResult,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("tracks", trackRepository.findAll());
        mav.setViewName("track/all");
        return mav;
    }

}
