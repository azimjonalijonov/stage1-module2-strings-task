package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        boolean begin = false;
        String temp = "";
        List<String> res = new ArrayList<>();
        for (int i = 0; i < source.length(); i++) {
            char ch = source.charAt(i);
            String stringCH = String.valueOf(ch);
            for (String delimiter : delimiters) {
                if (delimiter.equals(stringCH)) {
                    begin = true;
                    break;
                }
            }
            if (begin) {
                if (!temp.isEmpty()) {
                    res.add(temp);
                    temp = "";
                }
                begin = false;
            } else {
                temp += stringCH;
            }
        }
        if (!temp.isEmpty()) {
            res.add(temp);
        }
        return res;
    }
}
