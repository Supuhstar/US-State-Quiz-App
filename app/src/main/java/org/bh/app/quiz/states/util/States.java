package org.bh.app.quiz.states.util;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Made for U.S. State Quiz App by and copyrighted to Blue Husky Programming, ©2014 GPLv3.<hr/>
 *
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.1.0
 * @since 2014-07-29
 */
public enum States {
    ALABAMA, ALASKA, ARIZONA, ARKANSAS, CALIFORNIA, COLORADO, CONNECTICUT,
    DELAWARE, FLORIDA, GEORGIA, HAWAII, IDAHO, ILLINOIS, INDIANA, IOWA,
    KANSAS, KENTUCKY, LOUISIANA, MAINE, MARYLAND, MASSACHUSETTS, MICHIGAN,
    MINNESOTA, MISSISSIPPI, MISSOURI, MONTANA, NEBRASKA, NEVADA,
    NEW_HAMPSHIRE, NEW_JERSEY, NEW_MEXICO, NEW_YORK, NORTH_CAROLINA,
    NORTH_DAKOTA, OHIO, OKLAHOMA, OREGON, PENNSYLVANIA, RHODE_ISLAND,
    SOUTH_CAROLINA, SOUTH_DAKOTA, TENNESSEE, TEXAS, UTAH, VERMONT, VIRGINIA,
    WASHINGTON, WEST_VIRGINIA, WISCONSIN, WYOMING
    ;

    public static final String IMAGE_DIR =
        "http://bhstudios.org/http/bhstudios/v2/_shared/_img/states/";
    public static final String IMAGE_EXT = ".svg";
    public static final String BUNDLE_KEY = "STATE";

    public String getImageURL()
    {
        return IMAGE_DIR + name().toLowerCase() + IMAGE_EXT;
    }

    private CharSequence title;
    public CharSequence toTitle() {
        if (title != null)
            return title;
        String name = name().toLowerCase();
        String[] words = Pattern.compile("_").split(name);
        StringBuilder title = new StringBuilder();
        for (int i = 0; i < words.length; i++)
            title
                .append(Character.toUpperCase(words[i].charAt(0)))
                .append(words[i].substring(1))
                .append(' ');
        title.deleteCharAt(title.length() - 1);
        return this.title = title;
    }

    @Override
    public String toString() {
        return toTitle()+"";
    }

    public CharSequence getCapital() {
        switch (this) {
            case ALABAMA: return "Montgomery";
            case ALASKA: return "Juneau";
            case ARIZONA: return "Phoenix";
            case ARKANSAS: return "Little Rock";
            case CALIFORNIA: return "Sacramento";
            case COLORADO: return "Denver";
            case CONNECTICUT: return "Hartford";
            case DELAWARE: return "Dover";
            case FLORIDA: return "Tallahassee";
            case GEORGIA: return "Atlanta";
            case HAWAII: return "Honolulu";
            case IDAHO: return "Boise";
            case ILLINOIS: return "Springfield";
            case INDIANA: return "Indianapolis";
            case IOWA: return "Des Moines";
            case KANSAS: return "Topeka";
            case KENTUCKY: return "Frankfort";
            case LOUISIANA: return "Baton Rouge";
            case MAINE: return "Augusta";
            case MARYLAND: return "Annapolis";
            case MASSACHUSETTS: return "Boston";
            case MICHIGAN: return "Lansing";
            case MINNESOTA: return "Saint Paul";
            case MISSISSIPPI: return "Jackson";
            case MISSOURI: return "Jefferson City";
            case MONTANA: return "Helena";
            case NEBRASKA: return "Lincoln";
            case NEVADA: return "Carson City";
            case NEW_HAMPSHIRE: return "Concord";
            case NEW_JERSEY: return "Trenton";
            case NEW_MEXICO: return "Santa Fe";
            case NEW_YORK: return "Albany";
            case NORTH_CAROLINA: return "Raleigh";
            case NORTH_DAKOTA: return "Bismarck";
            case OHIO: return "Columbus";
            case OKLAHOMA: return "Oklahoma City";
            case OREGON: return "Salem";
            case PENNSYLVANIA: return "Harrisburg";
            case RHODE_ISLAND: return "Providence";
            case SOUTH_CAROLINA: return "Columbia";
            case SOUTH_DAKOTA: return "Pierre";
            case TENNESSEE: return "Nashville";
            case TEXAS: return "Austin";
            case UTAH: return "Salt Lake City";
            case VERMONT: return "Montpelier";
            case VIRGINIA: return "Richmond";
            case WASHINGTON: return "Olympia";
            case WEST_VIRGINIA: return "Charleston";
            case WISCONSIN: return "Madison";
            case WYOMING: return "Cheyenne";
        }
        return "promblem";
    }

    private static CharSequence[] titlesCache;

    public static CharSequence[] titles() {
        if (titlesCache != null)
            return titlesCache;

        States[] states = values();
        CharSequence[] titles = new CharSequence[states.length];
        for(int i = 0; i < titles.length; i++)
            titles[i] = states[i].toTitle();
        return titlesCache = titles;
    }
}
