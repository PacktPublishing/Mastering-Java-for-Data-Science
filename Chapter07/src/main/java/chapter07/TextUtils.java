package chapter07;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;

public class TextUtils {

    public static final Set<String> EN_STOPWORDS = ImmutableSet.of("a", "an", "and", "are", "as", "at", "be",
            "but", "by", "for", "if", "in", "into", "is", "it", "no", "not", "of", "on", "or", "such", "that", "the",
            "their", "then", "there", "these", "they", "this", "to", "was", "will", "with", "what", "which", "s", "m", "t");

    public static List<String> tokenize(String line) {
        Pattern pattern = Pattern.compile("\\W+");
        String[] split = pattern.split(line.toLowerCase());
        return Arrays.stream(split)
                .map(String::trim)
                .filter(s -> s.length() > 2)
                .collect(Collectors.toList());
    }

    public static List<String> tokenizeFilter(String line) {
        Pattern pattern = Pattern.compile("\\W+");
        String[] split = pattern.split(line.toLowerCase());
        return Arrays.stream(split)
                .map(String::trim)
                .filter(s -> s.length() > 2)
                .filter(s -> !isStopword(s))
                .collect(Collectors.toList());
    }

    public static boolean isStopword(String token) {
        return EN_STOPWORDS.contains(token);
    }

    public static List<String> removeStopwords(List<String> line) {
        return removeStopwords(line, EN_STOPWORDS);
    }

    public static List<String> removeStopwords(List<String> line, Set<String> stopwords) {
        return line.stream().filter(token -> !stopwords.contains(token)).collect(Collectors.toList());
    }
}