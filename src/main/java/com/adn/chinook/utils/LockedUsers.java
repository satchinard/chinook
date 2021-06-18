/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.utils;

import java.util.Set;
import org.assertj.core.util.Sets;

/**
 *
 * @author cagecfi
 */
public class LockedUsers {

    private static final Set<String> lockedUsersSets = Sets.newHashSet();

    private LockedUsers() {

    }

    public static boolean isLocked(final String username) {
        return lockedUsersSets.contains(username);
    }

    public static void lock(final String username) {
        lockedUsersSets.add(username);
    }
}
