package org.humber.project.services.impl;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.humber.project.domain.Movie;
import org.humber.project.domain.User;
import org.humber.project.services.UserService;
import org.humber.project.services.WatchlistJPAService;
import org.humber.project.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService
{
    private WatchlistJPAService watchlistJPAService;

    @Autowired
    public WatchlistServiceImpl(WatchlistJPAService watchlistJPAService)
    {
        this.watchlistJPAService = watchlistJPAService;
    }

    @Override
    public List<Movie> getWatchlistFromUser(Long user_id) {
        return this.watchlistJPAService.getWatchlistFromUser(user_id);
    }
}
