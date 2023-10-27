package Results;

import java.io.IOException;
import java.util.List;

public interface ResultRepository {

    List<ScraperResult> add(ScraperResult result) throws IOException;

    List<ScraperResult> getAll() throws IOException;
}
