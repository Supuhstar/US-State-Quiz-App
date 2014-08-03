package org.bh.app.quiz.states;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.bh.app.quiz.states.evt.OnQuestionAnswerListener;
import org.bh.app.quiz.states.util.States;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class QuestionFragment
    extends Fragment
    implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private WebView mStateImageView;
    private Spinner mStateSpinner, mCapitalSpinner;
    private Button mGuessStateButton, mGuessCapitalButton;
    private Toast mCorrectionToast;
    private TextView mCorrectionToastView;
    private TableRow mSecondQuestion;

    private boolean mIsStateCorrect, mIsCapitalCorrect;
    private States mState;
    private byte mScore = 1;
    private OnQuestionAnswerListener mOnQuestionAnswerListener;

    public QuestionFragment() {}
    @SuppressLint("ValidFragment")
    public QuestionFragment(States topic) {
        this();
        mState = topic;
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param stateIndex the mState to display
     * @return A new instance of fragment QuestionFragment.
     */
    public static QuestionFragment newInstance(int stateIndex) {
        System.out.println("Creating new instance for mState #" + stateIndex);
        QuestionFragment fragment = new QuestionFragment(States.values()[stateIndex]);
        Bundle args = new Bundle();
        args.putInt(States.BUNDLE_KEY, stateIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("~onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_main_quiz, container, false);
        initViews(rootView);
        initState();
        initGuessers();
        initToast();
        return rootView;
    }

    private void initViews(View rootView) {
        mStateImageView     = (WebView)rootView.findViewById(R.id.webView);
        mStateSpinner = (Spinner)rootView.findViewById(R.id.state_spinner);
        mCapitalSpinner = (Spinner)rootView.findViewById(R.id.capital_spinner);
        mGuessStateButton   = (Button)rootView.findViewById(R.id.guess_state_button);
        mGuessCapitalButton = (Button)rootView.findViewById(R.id.guess_capital_button);
        mSecondQuestion     = (TableRow)rootView.findViewById(R.id.capital_guesser);
    }

    private void initState() {
        Bundle args = getArguments();
        if (args == null) {
            throw new IllegalArgumentException("The arguments should be valid!");
        }
        System.out.println("Bundle is now: " + args);
        int stateIndex = args.getInt(States.BUNDLE_KEY);
        System.out.println("Gonna be mState #" + stateIndex);
        mState = States.values()[stateIndex];
        System.out.println("Gonna be " + mState);
        String path = mState.getImageURL();
        System.out.println("Opening image at " + path);

        mStateImageView.loadUrl(path);
        mStateImageView.setBackgroundColor(getResources().getColor(R.color.transparent));
    }

//removed the onCreate() method

    private void initGuessers() {
        if (mStateSpinner != null) {
            mStateSpinner.setAdapter(
                new ArrayAdapter<States>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    States.values())
            );
            mStateSpinner.setOnItemSelectedListener(this);
        }
        if (mCapitalSpinner != null) {
            mCapitalSpinner.setOnItemSelectedListener(this);
        }

        if (mGuessStateButton != null)
            mGuessStateButton.setOnClickListener(this);
        if (mGuessCapitalButton != null)
            mGuessCapitalButton.setOnClickListener(this);
    }

    @SuppressLint("ShowToast") // this only sets it up to be created later, but does not show it
    private void initToast() {
        if (mCorrectionToastView == null)
        {
            LayoutInflater layoutInflater = getActivity().getLayoutInflater();
            mCorrectionToastView = (TextView)layoutInflater.inflate(R.layout.correction_toast_layout, null);
        }

        mCorrectionToast = Toast.makeText(getActivity(), "welp", Toast.LENGTH_SHORT);
        mCorrectionToast.setView(mCorrectionToastView);
        mCorrectionToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.FILL_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.guess_state_button:
                if (mIsStateCorrect) {
                    mCorrectionToastView.setText(R.string.guess_correct);
                    mCorrectionToastView.setBackgroundColor(
                        getResources().getColor(R.color.correct_background));
                }
                else {
                    mCorrectionToastView.setText(R.string.guess_incorrect);
                    mCorrectionToastView.setBackgroundColor(
                        getResources().getColor(R.color.incorrect_background));
                }
                mCorrectionToast.show();
                if (mOnQuestionAnswerListener != null)
                    mOnQuestionAnswerListener.onQuestionAnswer(mIsStateCorrect, false);
                break;
            case R.id.guess_capital_button:
                if (mIsCapitalCorrect) {
                    mCorrectionToastView.setText(R.string.guess_correct);
                    mCorrectionToastView.setBackgroundColor(
                        getResources().getColor(R.color.correct_background));
                }
                else {
                    mCorrectionToastView.setText(mState.getCapital());
                    mCorrectionToastView.setBackgroundColor(
                        getResources().getColor(R.color.incorrect_background));
                }
                mCorrectionToast.show();
                if (mOnQuestionAnswerListener != null)
                    mOnQuestionAnswerListener.onQuestionAnswer(mIsCapitalCorrect, true);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.state_spinner:
                mIsStateCorrect = position == mState.ordinal();
                break;
            case R.id.capital_spinner:
                /*System.out.println("Resource capital id (" + id + "): " +
                    getResources()
                        .getStringArray(R.array.state_capitals)
                        [position]);
                System.out.println("This capital (" + mState.ordinal() + "): " + mState.getCapital());*/
                mIsCapitalCorrect =
                    getResources()
                        .getStringArray(R.array.state_capitals)
                        [position]
                    .equalsIgnoreCase(
                        mState.getCapital()+""
                    )
                ;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){}

    public void setOnQuestionAnswerListener(OnQuestionAnswerListener newListener) {
        mOnQuestionAnswerListener = newListener;
    }

    public States getState() {
        return mState;
    }

    public void showSecondQuestion() {
        mSecondQuestion.setVisibility(View.VISIBLE);
    }

    /*
    @Override public void onResume(){
        System.out.println("~onResume");
        super.onResume();}
    @Override public void onAttach(Activity activity){
        System.out.println("~onAttach");
        super.onAttach(activity);}
    @Override public void onDestroy(){
        System.out.println("~onDestroy");
        super.onDestroy();}
    @Override public void onDestroyView(){
        System.out.println("~onDestroyView");
        super.onDestroyView();}
    @Override public void onDetach(){
        System.out.println("~onDetach");
        super.onDetach();}
    @Override public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState){
        System.out.println("~onInflate");
        super.onInflate(activity, attrs, savedInstanceState);}
    @Override public void onPause(){
        System.out.println("~onPause");
        super.onPause();}
    @Override public void onStart(){
        System.out.println("~onStart");
        super.onStart();}
    @Override public void onStop(){
        System.out.println("~onStop");
        super.onStop();}
    @Override public void onInflate(AttributeSet attrs, Bundle savedInstanceState){
        System.out.println("~onInflate (old)");
        super.onInflate(attrs, savedInstanceState);}
    */
}
