package extractor;

import title.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenreExtractor implements Extractor {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenreExtractor.class);

    private static final String RATING_START_STR = "<a href=\"/genre/";
    private static final String RATING_END_STR = "?ref";
    private static final String REGEX_STRING = Pattern.quote(RATING_START_STR) + "(.*?)" + Pattern.quote(RATING_END_STR);
    private static final Pattern PATTERN = Pattern.compile(REGEX_STRING);

    @Override
    public void extract(String pageSource, Title title) {
        Matcher matcher = PATTERN.matcher(pageSource);

        while (matcher.find()) {
            String genre = matcher.group(1);
            if (!genre.equals("")) {
                title.addGenre(genre.toLowerCase());
            }
        }

        if (title.getGenres().size() == 0) {
            LOGGER.warn("No genres found for title {}", title);
        }
    }
}
