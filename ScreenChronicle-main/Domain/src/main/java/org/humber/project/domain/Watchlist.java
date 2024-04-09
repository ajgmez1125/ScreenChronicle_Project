package org.humber.project.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Watchlist {
    private Long watchlistId;
    private Long user_id;
    private Long movie_id;
}