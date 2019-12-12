package org.rastalion.brewery.web.mappers;

import org.mapstruct.Mapper;
import org.rastalion.brewery.domain.Beer;
import org.rastalion.brewery.web.model.BeerDto;

/*
Take a look at what this does:

target/generated-sources/annotations/org/rastalion/brewery/web/mappers/BeerMapperImpl.java

Do a vertical split screen ;)
 */

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);

}
