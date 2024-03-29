package org.humber.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private long movie_id;
    private String title;
    private String date;
    private Integer length; //In minutes
    private String director;
    private String description;
}
