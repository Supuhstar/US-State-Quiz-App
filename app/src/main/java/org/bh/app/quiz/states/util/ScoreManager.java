package org.bh.app.quiz.states.util;

import android.content.SharedPreferences;

/**
 * Made for U.S. State Quiz App by and copyrighted to Blue Husky Programming, ©2014 GPLv3.<hr/>
 *
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-08-02
 */
public class ScoreManager {
    private SharedPreferences preferences;
    private static final String SCORE_ID_PREFIX = "org.bh.app.quiz.states.SCORE.";

    public ScoreManager(SharedPreferences initPreferences)
    {
        preferences = initPreferences;
        States[] states = States.values();
        for(States state : states)
            setScore(state, 0);
    }

    public int calculateOverallScore()
    {
        int finalScore = 0;
        States[] states = States.values();
        for(States state : states)
            finalScore += getScore(state);
        return finalScore;
    }

    public int getScore(States state)
    {
        return loadSavedPreference(SCORE_ID_PREFIX + state);
    }

    public void setScore(States state, int newScore)
    {
        savePreference(SCORE_ID_PREFIX + state, newScore);
    }

    private int loadSavedPreference(String preferenceID)
    {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getInt(preferenceID,0);

    }

    private void savePreference(String key, int value)
    {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,value);
        editor.apply(); // apply is lazier and thus smoother than commit
    }
}
