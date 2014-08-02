package org.bh.app.quiz.states;

/**
 * Made for U.S. State Quiz App by and copyrighted to Blue Husky Programming, ©2014 GPLv3.<hr/>
 *
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-08-01
 */
public class DEBUG_FLAGS {
    /** Enable debug mode? */
    public static final boolean IS_DEBUG = false;
    /** Shuffle states? */
    public static final boolean SHUFFLE_STATES = !IS_DEBUG;
    /** Use "State X" instead of actual state names? */
    public static final boolean OBFUSCATE_QUESTION_NAMES = !IS_DEBUG;
}
