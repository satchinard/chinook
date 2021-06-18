/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.service;

import com.adn.chinook.entity.Album;
import com.adn.chinook.entity.Artist;
import com.adn.chinook.repository.AlbumRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cagecfi
 */
@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public AlbumService(AlbumRepository repository) {
        this.albumRepository = repository;
    }

    Album findByTitle(String title) {
        return this.albumRepository.findByTitle(title);
    }

    List<Album> findByArtist(Artist artist) {
        return this.albumRepository.findByArtist(artist);
    }

}
