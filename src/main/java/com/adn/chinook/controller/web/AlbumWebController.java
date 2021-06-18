package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Album;
import com.adn.chinook.form.AlbumForm;
import com.adn.chinook.repository.AlbumRepository;
import com.adn.chinook.repository.ArtistRepository;
import static com.adn.chinook.utils.Paths.ALBUM;
import static com.adn.chinook.utils.Paths.WEB_VERSION;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author cagecfi
 */
@Controller
@Slf4j
@RequestMapping(WEB_VERSION + ALBUM)
public class AlbumWebController
        implements BaseWebController<Album, Integer, AlbumForm> {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public JpaRepository<Album, Integer> getRepository() {
        return albumRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + ALBUM;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("albums", albumRepository.findAll());
        mav.setViewName("album/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(Album i, BindingResult bindingResult,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.addObject("allArtists", artistRepository.findAll());
            mav.setViewName("album/add");
            return mav;
        }
        if (i.getTitle() != null) {
            albumRepository.save(i);
        }
        mav.addObject("albums", albumRepository.findAll());
        mav.setViewName("album/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(Album i, BindingResult bindingResult,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("albums", albumRepository.findAll());
        mav.setViewName("album/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(@PathVariable("id") Integer pk) {
        Album album = null;
        try {
            album = albumRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("allArtists", artistRepository.findAll());
        if (album != null && album.getAlbumId() != null) {
            mav.addObject("album", album);
            mav.setViewName("album/get");
            return mav;
        }
        mav.setViewName("album/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        Album album = albumRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        mav.addObject("allArtists", artistRepository.findAll());
        if (album != null) {
            mav.addObject("album", album);
            mav.setViewName("album/update");
            return mav;
        }
        mav.setViewName("album/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        Album album = albumRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (album != null) {
            albumRepository.delete(album);
        }
        mav.addObject("albums", albumRepository.findAll());
        mav.setViewName("album/all");
        return mav;
    }
}
