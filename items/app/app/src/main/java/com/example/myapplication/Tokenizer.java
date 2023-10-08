package com.example.myapplication;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private String name;
    private Integer year;
    private Integer price;
    private String yearOperator; // can be '=', '<', '>', etc.
    private String priceOperator; // can be '=', '<', '>', etc.
    public boolean grammarRight = true;
    public boolean invalidInput = false;
    public int nameSearchedTimes = 0;

    public Tokenizer(String criteria) {
        StringTokenizer tokenizer = new StringTokenizer(criteria, ";");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            parseToken(token);
        }
    }

    private void parseToken(String token) {
        // delete all extra spaces;
        token = token.replaceAll(" ", "");

        Pattern patternSign = Pattern.compile("([<>]=?|=)");
        Pattern patternNum = Pattern.compile("\\d+");
//        Pattern combinedPattern = Pattern.compile("([<>]=?|=)\\d+");
        Pattern combinedPattern = Pattern.compile("(year|price)(<|>|=)\\d+");
        grammarRight = combinedPattern.matcher(token).matches();
        if (grammarRight) {
            if (token.startsWith("year")) {
                yearOperator = token.substring(4, 5);
                year = Integer.parseInt(token.substring(5).trim());
                System.out.println("year = " + year + "yearOperator = " + yearOperator);
            } else if (token.startsWith("price")) {

                priceOperator = token.substring(5, 6);
                price = Integer.parseInt(token.substring(6).trim());

//            Pattern pattern = Pattern.compile("([<>]=?|=)");
//            Matcher matcher = pattern.matcher(token);
//            Pattern patternPrice = Pattern.compile("\\d+");
//            Matcher matcherPrice = pattern.matcher(token);
//          Check if any match is found and return the first one
//            if (matcher.find()) {
//                priceOperator = matcher.group(1);
//            }
//            if (matcherPrice.find()) {
//                price = Integer.parseInt(matcherPrice.group(1));
//            }
            }
        }
        else {
            if (!token.startsWith("year") && !token.startsWith("price")) {
                grammarRight = true;
                name = token.trim();
                nameSearchedTimes++;
            }
            else {
                invalidInput = true;
            }
        }
    }

    public int getPrice() {
        return price;
    }

    public String getPriceOperator() {
        return priceOperator;
    }

    public boolean matches(Game game) {

        if (name != null) {
            if (!game.getName().toLowerCase().contains(name.toLowerCase())) {
                return false;
            }
        }

        if (year != null) {
            switch (yearOperator) {
                case "=":
                    if (game.getYear() != year) return false;
                    break;
                case "<":
                    if (game.getYear() >= year) return false;
                    break;
                case ">":
                    if (game.getYear() <= year) return false;
                    break;
            }
        }
        return true;
    }
}

