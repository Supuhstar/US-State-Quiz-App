package org.bh.app.quiz.states.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Made for U.S. State Quiz App by and copyrighted to Blue Husky Programming, ©2014 GPLv3.<hr/>
 *
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-08-01
 */
public class MoreArrays {

    public static <T> T[] shuffle(T[] array) {
        return shuffle(array, new Random());
    }

    public static <T> T[] shuffle(T[] array, Random random) {
        // I know this looks dirty, but since it uses System.arraycopy, and some
        // special, package-private constructors, it's fast enough.
        ArrayList<T> ret = new ArrayList<T>(Arrays.asList(array));
        Collections.shuffle(ret, random);
        return ret.toArray(array);
    }
}
