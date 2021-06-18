package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Artist;
import com.adn.chinook.form.ArtistForm;
import com.adn.chinook.repository.ArtistRepository;
import static com.adn.chinook.utils.Paths.ARTIST;
import static com.adn.chinook.utils.Paths.WEB_VERSION;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author CAGECFI
 */
@Controller
@RequestMapping(WEB_VERSION + ARTIST + "s/")
public class ArtistController implements BaseWebController<Artist, Integer, ArtistForm> {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + ARTIST + "s/";
    }

    @GetMapping("/inscription")
    public ModelAndView showSignUpForm(Artist artist) {
        ModelAndView mav = new ModelAndView("add-artist");
        mav.addObject("artist", artist);
        return mav;
    }

    @PostMapping("/addartist")
    public String addArtist(@Valid Artist artist, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-artist";
        }

        artistRepository.save(artist);
        return "redirect:" + getWebControllerPath() + "artist-index";
    }

    @GetMapping("/artist-index")
    public String showArtistList(Model model) {
        model.addAttribute("artists", artistRepository.findAll());
        return "artist-index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist Id:" + id));

        model.addAttribute("artist", artist);
        return "update-artist";
    }

    @GetMapping("/sec")
    public String secPage() {
        return "sec";
    }

    @PostMapping("/update/{id}")
    public String updateArtist(@PathVariable("id") int id, @Valid Artist artist,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            artist.setArtistId(id);
            return "update-artist";
        }

        artistRepository.save(artist);
        return "redirect:" + this.getWebControllerPath() + "artist-index";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteArtist(@PathVariable("id") int id, Model model) {
//        Artist artist = artistRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid artist Id:" + id));
//        artistRepository.delete(artist);
//        return "redirect:" + this.getWebControllerPath() + "artist-index";
//    }
    @Override
    public JpaRepository<Artist, Integer> getRepository() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModelAndView addObject(Artist i, BindingResult bindingResult, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public ModelAndView getObject(Artist i, BindingResult bindingResult, HttpServletRequest request) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public ModelAndView updateObject(Artist i, BindingResult bindingResult, HttpServletRequest request) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public ModelAndView deleteObject(Artist i, BindingResult bindingResult, HttpServletRequest request) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public ModelAndView listObjects(Artist i, BindingResult bindingResult, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//    @Override
//    public Artist add(ArtistForm form) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Artist update(ArtistForm form) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Artist select(ArtistForm form) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void delete(ArtistForm form) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<Artist> list(ArtistForm form) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public ModelAndView getObject(Integer pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModelAndView defaultPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
