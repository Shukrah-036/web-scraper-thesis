package Results;

import lombok.NonNull;
import util.JacksonHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class JsonResultRepository implements ResultRepository{

    private Path filePath;

    public JsonResultRepository(@NonNull Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<ScraperResult> add(@NonNull ScraperResult result) throws IOException {
        var results = getAll();
        results.add(result);
        try (var out = Files.newOutputStream(filePath)) {
            JacksonHelper.writeList(out, results);
        }
        return results;
    }

    public List<ScraperResult> getAll() throws IOException {
        if (!Files.exists(filePath)) {
            return new ArrayList<ScraperResult>();
        }
        try (var in = Files.newInputStream(filePath)) {
            return JacksonHelper.readList(in, ScraperResult.class);
        }
    }
}
