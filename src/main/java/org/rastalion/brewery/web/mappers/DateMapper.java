package org.rastalion.brewery.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/*
Conversion between Timestamp and OffsetDateTime

after mvn:compile go look at:

target/classes/org/rastalion/brewery/web/mappers/BeerMapperImpl.class
 */

@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(Timestamp ts) {
        if (ts != null) {
            return OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
                    ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(),
                    ts.toLocalDateTime().getMinute(), ts.toLocalDateTime().getSecond(), ts.toLocalDateTime().getNano(),
                    ZoneOffset.UTC);
        }
        return null;
    }

    public Timestamp asTimeStamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime != null) {
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        }
        return null;
    }
}
