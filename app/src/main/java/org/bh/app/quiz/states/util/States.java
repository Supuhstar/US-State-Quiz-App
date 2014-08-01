package org.bh.app.quiz.states.util;

import android.content.res.Resources;

import org.bh.app.quiz.states.R;

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
    // THE ORDER OF THESE IS TIED TO arrays.xml!
    // DO NOT CHANGE THIS WITHOUT CHANGING THAT!
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

        /*return title =
            Resources
                .getSystem()
                .getStringArray(R.array.state_names)
                [ordinal()];
        */
        String name = name().toLowerCase();
        String[] words = Pattern.compile("_").split(name);
        StringBuilder title = new StringBuilder();
        for (int i = 0; i < words.length; i++)
            title
                .append(Character.toUpperCase(words[i].charAt(0)))
                .append(words[i].substring(1))
                .append(' ');
        title.deleteCharAt(title.length() - 1);
        return this.title = title;/**/
    }

    @Override
    public String toString() {
        return toTitle()+"";
    }

    public CharSequence getCapital() {
        return
            Resources
            .getSystem()
            .getStringArray(R.array.state_capitals)
            [ordinal()];
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
