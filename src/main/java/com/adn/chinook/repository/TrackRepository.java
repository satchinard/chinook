/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.repository;

import com.adn.chinook.entity.Album;
import com.adn.chinook.entity.Genre;
import com.adn.chinook.entity.MediaTypeChinook;
import com.adn.chinook.entity.Track;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cagecfi
 */
@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    List<Track> findByName(String name);

    List<Track> findByComposer(String composer);

    List<Track> findByGenre(Genre genre);

    List<Track> findByMediaType(MediaTypeChinook mediaType);

    List<Track> findByAlbum(Album album);

    List<Track> findByUnitPrice(Double unitPrice);
}
