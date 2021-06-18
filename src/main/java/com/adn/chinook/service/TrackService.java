/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.service;

import com.adn.chinook.entity.Album;
import com.adn.chinook.entity.Genre;
import com.adn.chinook.entity.MediaTypeChinook;
import com.adn.chinook.entity.Track;
import com.adn.chinook.repository.TrackRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cagecfi
 */
@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public TrackService(TrackRepository repository) {
        this.trackRepository = repository;
    }

    List<Track> findByName(String name) {
        return this.trackRepository.findByName(name);
    }

    List<Track> findByComposer(String composer) {
        return this.trackRepository.findByComposer(composer);
    }

    List<Track> findByGenre(Genre genre) {
        return this.trackRepository.findByGenre(genre);
    }

    List<Track> findByAlbum(Album album) {
        return this.trackRepository.findByAlbum(album);
    }

    List<Track> findByUnitPrice(Double unitPrice) {
        return this.trackRepository.findByUnitPrice(unitPrice);
    }

    List<Track> findByMediaType(MediaTypeChinook mediaType) {
        return this.trackRepository.findByMediaType(mediaType);
    }

}
