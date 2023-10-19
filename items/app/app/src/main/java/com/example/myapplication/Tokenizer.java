package com.example.myapplication;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author u7544341 Zehua Liu.
 * Tokenizer.
 */

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

    /**
     * Extract the operator of the user input.
     * @param token
     */
    private void parseToken(String token) {
        // Delete all extra spaces;
        token = token.replaceAll(" ", "");
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

    /**
     * Game matches iff relevant condition satisfied.
     * @param game
     * @return
     */
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

