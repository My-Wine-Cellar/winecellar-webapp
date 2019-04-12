package com.cellar.wine.utils;

public class Regex {

    private Regex(){}

    public static final String ALPHANUMERIC_SPACES_HYPHEN_PERIOD_PATTERN = "^[a-zA-Z0-9\\s.\\-]+$";
    public static final String ALPHANUMERIC_SPACES_HYPHEN_PERIOD_MESSAGE = "This field cannot be empty! This field can include English alphabet characters, numbers, spaces, periods, and hyphens";

    public static final String NUMERIC_PATTERN = "[0-9]+";
    public static final String NUMERIC_MESSAGE = "This field cannot be empty! Must be valid digits 0-9";

}
