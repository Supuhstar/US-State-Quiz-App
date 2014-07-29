package org.bh.app.quiz.states.util;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Made for U.S. State Quiz App by and copyrighted to Blue Husky Programming, ©2014 GPLv3.<hr/>
 *
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
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

    public CharSequence toTitle() {
        String name = name().toLowerCase();
        String[] words = Pattern.compile("_").split(name);
        StringBuilder title = new StringBuilder();
        for (int i = 0; i < words.length; i++)
            title
                .append(Character.toUpperCase(words[i].charAt(0)))
                .append(words[i].substring(1))
                .append(' ');
        title.deleteCharAt(title.length() - 1);
        return title;
    }
}
