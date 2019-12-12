package org.rastalion.brewery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rastalion.brewery.web.model.v2.BeerStyle;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {
    private UUID beerId;
    private String beerName;
    private BeerStyle beerStyle;
    private Long upc;

    /*
    Good Type of date for sql data store
     */
    private Timestamp createdDate;
    private Timestamp lastUpdatedDate;
}
