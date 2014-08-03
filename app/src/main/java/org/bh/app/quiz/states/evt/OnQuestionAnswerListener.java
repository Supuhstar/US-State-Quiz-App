package org.bh.app.quiz.states.evt;

/**
 * Made for U.S. State Quiz App by and copyrighted to Blue Husky Programming, ©2014 GPLv3.<hr/>
 *
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-08-02
 */
public interface OnQuestionAnswerListener {
    public void onQuestionAnswer(boolean correctAnswer, boolean secondAnswer);
}
