/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.repository;

import com.adn.chinook.entity.Album;
import com.adn.chinook.entity.Artist;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cagecfi
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    Album findByTitle(String title);

    List<Album> findByArtist(Artist artist);
}
