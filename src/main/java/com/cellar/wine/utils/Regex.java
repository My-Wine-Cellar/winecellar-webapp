package com.cellar.wine.utils;

public class Regex {

    private Regex(){}

    public static final String ALPHANUMERIC_SPACES_HYPHEN_PATTERN = "[a-zA-Z0-9/-]+";
    public static final String ALPHANUMERIC_SPACES_HYPHEN_MESSAGE = "This field cannot be empty and can include English alphabet characters, numbers, and hyphens";

    public static final String NUMERIC_PATTERN = "[0-9]+";
    public static final String NUMERIC_MESSAGE = "Must be valid digits 0-9";

    public static final String STRING_NO_SPACES_PATTERN = "[a-zA-Z]+";
    public static final String STRING_NO_SPACES_MESSAGE = "This field can only be English alphabet characters";

    public static final String STRING_SPACES_PATTERN = "[a-zA-Z\\s]+";
    public static final String STRING_SPACES_MESSAGE = "This field can only be English alphabet characters and spaces";

}
