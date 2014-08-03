package org.bh.app.quiz.states.util;

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
    ALABAMA        ("Montgomery"),
    ALASKA         ("Juneau"),
    ARIZONA        ("Phoenix"),
    ARKANSAS       ("Little Rock"),
    CALIFORNIA     ("Sacramento"),
    COLORADO       ("Denver"),
    CONNECTICUT    ("Hartford"),
    DELAWARE       ("Dover"),
    FLORIDA        ("Tallahassee"),
    GEORGIA        ("Atlanta"),
    HAWAII         ("Honolulu"),
    IDAHO          ("Boise"),
    ILLINOIS       ("Springfield"),
    INDIANA        ("Indianapolis"),
    IOWA           ("Des Moines"),
    KANSAS         ("Topeka"),
    KENTUCKY       ("Frankfort"),
    LOUISIANA      ("Baton Rouge"),
    MAINE          ("Augusta"),
    MARYLAND       ("Annapolis"),
    MASSACHUSETTS  ("Boston"),
    MICHIGAN       ("Lansing"),
    MINNESOTA      ("Saint Paul"),
    MISSISSIPPI    ("Jackson"),
    MISSOURI       ("Jefferson City"),
    MONTANA        ("Helena"),
    NEBRASKA       ("Lincoln"),
    NEVADA         ("Carson City"),
    NEW_HAMPSHIRE  ("Concord"),
    NEW_JERSEY     ("Trenton"),
    NEW_MEXICO     ("Santa Fe"),
    NEW_YORK       ("Albany"),
    NORTH_CAROLINA ("Raleigh"),
    NORTH_DAKOTA   ("Bismarck"),
    OHIO           ("Columbus"),
    OKLAHOMA       ("Oklahoma City"),
    OREGON         ("Salem"),
    PENNSYLVANIA   ("Harrisburg"),
    RHODE_ISLAND   ("Providence"),
    SOUTH_CAROLINA ("Columbia"),
    SOUTH_DAKOTA   ("Pierre"),
    TENNESSEE      ("Nashville"),
    TEXAS          ("Austin"),
    UTAH           ("Salt Lake City"),
    VERMONT        ("Montpelier"),
    VIRGINIA       ("Richmond"),
    WASHINGTON     ("Olympia"),
    WEST_VIRGINIA  ("Charleston"),
    WISCONSIN      ("Madison"),
    WYOMING        ("Cheyenne")
    ;

    public static final String IMAGE_DIR =
        "http://bhstudios.org/http/bhstudios/v2/_shared/_img/states/";
    public static final String IMAGE_EXT = ".svg";
    public static final String BUNDLE_KEY = "STATE";
    private String capital;

    private States(String capital)
    {
        this.capital = capital;
    }

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
        return capital;
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
