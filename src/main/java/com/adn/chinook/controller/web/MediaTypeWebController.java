package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.MediaTypeChinook;
import com.adn.chinook.form.MediaTypeForm;
import com.adn.chinook.repository.MediaTypeRepository;
import static com.adn.chinook.utils.Paths.MEDIA_TYPE;
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
@RequestMapping(WEB_VERSION + MEDIA_TYPE)
public class MediaTypeWebController implements BaseWebController<MediaTypeChinook, Integer, MediaTypeForm> {

    @Autowired
    private MediaTypeRepository mediaTypeRepository;

    @Override
    public JpaRepository<MediaTypeChinook, Integer> getRepository() {
        return mediaTypeRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + MEDIA_TYPE;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mediatypes", mediaTypeRepository.findAll());
        mav.setViewName("mediatype/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(MediaTypeChinook i, BindingResult bindingResult,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("mediatype/add");
            return mav;
        }
        if (i.getName() != null) {
            mediaTypeRepository.save(i);
        }
        mav.addObject("mediatypes", mediaTypeRepository.findAll());
        mav.setViewName("mediatype/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Integer pk) {
        MediaTypeChinook mediatype = null;
        try {
            mediatype = mediaTypeRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (mediatype != null && mediatype.getMediaTypeId() != null) {
            mav.addObject("mediatype", mediatype);
            mav.setViewName("mediatype/get");
            return mav;
        }
        mav.setViewName("mediatype/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        MediaTypeChinook mediatype = mediaTypeRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (mediatype != null) {
            mav.addObject("mediatype", mediatype);
            mav.setViewName("mediatype/update");
            return mav;
        }
        mav.setViewName("mediatype/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        MediaTypeChinook mediatype = mediaTypeRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (mediatype != null) {
            try {
                mediaTypeRepository.delete(mediatype);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("mediatypes", mediaTypeRepository.findAll());
        mav.setViewName("mediatype/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(MediaTypeChinook i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mediatypes", mediaTypeRepository.findAll());
        mav.setViewName("mediatype/all");
        return mav;
    }

}
