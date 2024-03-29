package org.humber.project.services;

import org.humber.project.domain.WatchList;

import java.util.List;

public interface WatchListJPAService {
    List<WatchList> getWatchListByUserId(Long userId);
    WatchList addToWatchList(WatchList watchlist);
}
