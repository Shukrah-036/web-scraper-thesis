package Results;

import lombok.*;
import org.scrapernest.Item;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScraperResult {

    @Getter
    @Setter
    private String scraperName;

    @Getter
    @Setter
    private LocalDateTime timeStamp;

    @Getter
    @Setter
    private List<String> extractedData;

    @Getter
    @Setter
    private Item associatedItem;

    @Getter
    @Setter
    private boolean success;
}
