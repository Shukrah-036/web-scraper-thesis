package Results;

import lombok.Getter;
import lombok.Setter;
import org.scrapernest.Item;

import java.time.LocalDateTime;
import java.util.List;

public class ScraperResults {

    @Getter
    private LocalDateTime timeStamp;

    @Getter
    private List<String> extractedData;

    @Getter
    private String scraperName;

    private boolean success;

    @Getter
    @Setter
    private Item associatedItem;

}
