package org.humber.project.services;

import java.util.List;

import org.humber.project.domain.Movie;

public interface WatchlistJPAService
{
    List<Movie> getWatchlistFromUser(Long user_id);
}
