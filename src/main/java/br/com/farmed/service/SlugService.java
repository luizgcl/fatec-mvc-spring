package br.com.farmed.service;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class SlugService {

    private static final Pattern NON_ALPHANUMERIC = Pattern.compile("[^a-z0-9\\s-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]+");
    private static final Pattern MULTIPLE_HYPHENS = Pattern.compile("[-]+");

    public String slugify(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }

        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        String lowerCaseText = normalizedText.toLowerCase(Locale.ROOT);
        String cleanedText = NON_ALPHANUMERIC.matcher(lowerCaseText).replaceAll("");
        String hyphenatedText = WHITESPACE.matcher(cleanedText).replaceAll("-");
        String singleHyphenText = MULTIPLE_HYPHENS.matcher(hyphenatedText).replaceAll("-");

        String finalSlug = singleHyphenText.replaceAll("^-|-$", "");

        return finalSlug;
    }

}
