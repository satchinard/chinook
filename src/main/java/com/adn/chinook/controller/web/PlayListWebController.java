package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.PlayList;
import com.adn.chinook.form.PlayListForm;
import com.adn.chinook.repository.PlayListRepository;
import static com.adn.chinook.utils.Paths.PLAY_LIST;
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
@RequestMapping(WEB_VERSION + PLAY_LIST)
public class PlayListWebController implements BaseWebController<PlayList, Integer, PlayListForm> {

    @Autowired
    private PlayListRepository playListRepository;

    @Override
    public JpaRepository<PlayList, Integer> getRepository() {
        return playListRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + PLAY_LIST;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("playlists", playListRepository.findAll());
        mav.setViewName("playlist/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(PlayList i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("playlist/add");
            return mav;
        }
        if (i.getName() != null) {
            playListRepository.save(i);
        }
        mav.addObject("playlists", playListRepository.findAll());
        mav.setViewName("playlist/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Integer pk) {
        PlayList playlist = null;
        try {
            playlist = playListRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (playlist != null && playlist.getPlayListId() != null) {
            mav.addObject("playlist", playlist);
            mav.setViewName("playlist/get");
            return mav;
        }
        mav.setViewName("playlist/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        PlayList playlist = playListRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (playlist != null) {
            mav.addObject("playlist", playlist);
            mav.setViewName("playlist/update");
            return mav;
        }
        mav.setViewName("playlist/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        PlayList playlist = playListRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (playlist != null) {
            try {
                playListRepository.delete(playlist);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("playlists", playListRepository.findAll());
        mav.setViewName("playlist/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(PlayList i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("playlists", playListRepository.findAll());
        mav.setViewName("playlist/all");
        return mav;
    }

}
