package org.bh.app.quiz.states;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.bh.app.quiz.states.evt.OnQuestionAnswerListener;
import org.bh.app.quiz.states.util.MoreArrays;
import org.bh.app.quiz.states.util.ScoreManager;
import org.bh.app.quiz.states.util.States;

import static org.bh.app.quiz.states.DEBUG_FLAGS.OBFUSCATE_QUESTION_NAMES;
import static org.bh.app.quiz.states.DEBUG_FLAGS.SHUFFLE_STATES;


public class MainQuizActivity extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    StatePagerAdapter mStatePagerAdapter;

    /** The {@link ViewPager} that will host the section contents. */
    private ViewPager mViewPager;
    private MenuItem mScoreMenuItem;

    private ScoreManager mScoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);

        mScoreManager = new ScoreManager(PreferenceManager.getDefaultSharedPreferences(this));
        initTabs();
    }

    private void initTabs() {
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mStatePagerAdapter = new StatePagerAdapter(getFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mStatePagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mStatePagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                actionBar.newTab()
                    .setText(mStatePagerAdapter.getPageTitle(i))
                    .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz_menu, menu);
        mScoreMenuItem = (MenuItem)menu.findItem(R.id.score_menu_item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId())
        {
            case R.id.score_menu_item:
                Toast
                    .makeText(this, getString(R.string.refresh_clicked_toast), Toast.LENGTH_SHORT)
                    .show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class StatePagerAdapter extends FragmentPagerAdapter {

        private Byte[] shuffled;

        public StatePagerAdapter(FragmentManager fm) {
            super(fm);
            States[] vals = States.values();
            shuffled = new Byte[vals.length];
            for(byte i = 0, l = (byte)Math.min(vals.length, Byte.MAX_VALUE); i < l; i++) {
                shuffled[i] = i;
            }

            if (SHUFFLE_STATES) shuffled = MoreArrays.shuffle(shuffled);
        }

        QuestionFragment[] questionFragments = new QuestionFragment[States.values().length];

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            position = shuffled[position];
            return
                questionFragments[position] == null
                    ? questionFragments[position] = makeNewQuestionFragment(position)
                    : questionFragments[position];
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return questionFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (OBFUSCATE_QUESTION_NAMES)
                return "State  " + (position + 1);

            States[] states = States.values();
            if (position >= states.length || position < 0)
                return "air or";
            return states[position].toTitle();
        }
    }

    private QuestionFragment makeNewQuestionFragment(int position) {
        final QuestionFragment ret = QuestionFragment.newInstance(position);
        ret.setOnQuestionAnswerListener(new OnQuestionAnswerListener() {
            @Override
            public void onQuestionAnswer(boolean correctAnswer, boolean secondAnswer) {
                mScoreManager.setScore(
                    ret.getState(),
                    correctAnswer
                        ? (secondAnswer
                            ? 11
                            : 0)
                        : 0
                );
                if (!correctAnswer || secondAnswer)
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                else
                    ret.showSecondQuestion();
                updateScore();
            }
        });
        return ret;
    }

    private void updateScore() {
        int score = mScoreManager.calculateOverallScore();
        mScoreMenuItem.setTitle("Score: " + score);
        mScoreMenuItem.setTitleCondensed(score + " pts");
    }
}