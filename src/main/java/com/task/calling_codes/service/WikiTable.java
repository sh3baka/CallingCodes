package com.task.calling_codes.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class WikiTable {

    private static final String URL = "https://en.wikipedia.org/wiki/List_of_country_calling_codes";
    private final Logger logger = LoggerFactory.getLogger(WikiTable.class);
    private Document document = null;

    private Map<String, String> mapOfCodes = createMapOfCodes();

    /**
     * Helper method to create map of country codes by parsing wikitable for data
     *
     * @return map of country codes where Key = code , Value = country
     */
    private Map<String, String> createMapOfCodes() {
        try {
            document = Jsoup.connect(URL).get();
            logger.info("Successful open of " + URL);
        } catch (Exception e) {
            logger.error("Failed to open " + URL + " please try again");
        }

        StringBuilder stringBuilder = new StringBuilder();
        Element table = document.select("table.wikitable").first();
        Map<String, String> map = new HashMap<>();

        // Get all data cells of table , and for each cell get <a> updating stringBuilder in format (*+371:Latvia,)
        table.select("td").stream().filter(element -> !element.text().contains("below") && !element.text().isEmpty()).forEach(element -> {
            Elements anchor = element.select("a[href]");
            anchor.stream().filter(anc -> !anc.text().isEmpty()).forEach(a -> {
                if (a.text().startsWith("+")) {
                    stringBuilder.append("*" + a.text() + ":");
                } else {
                    String string = a.attributes().get("title") + ",";
                    stringBuilder.append(string);
                }
            });
        });

        // Split stringBuilder to Array removing "*" separator
        String[] stringArr = stringBuilder.toString().split("[*]", 0);

        // Split each element of Array on ":" and creating map of values
        for (String string : stringArr) {
            if (string != null && !string.isEmpty()) {
                String[] tempArr = string.split(":", 0);
                if (tempArr.length == 1) {
                    map.put(tempArr[0].replace(" ", ""), "Dominican Republic");
                } else {
                    map.put(tempArr[0].replace(" ", ""), removeLastComma(tempArr[1]));
                }
            }
        }
        return map;
    }

    /**
     * Helper method to remove "," sign at the end of string
     *
     * @param str - Input String
     * @return String without "," sign in the end
     */
    private String removeLastComma(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * Getter of mapOfCodes
     *
     * @return mapOfCodes
     */
    public Map<String, String> getMapOfCodes() {
        return mapOfCodes;
    }
}
