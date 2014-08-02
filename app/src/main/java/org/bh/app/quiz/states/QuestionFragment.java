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
import android.widget.TextView;
import android.widget.Toast;

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

    private WebView webView;
    private States state;
    private Spinner guessSpinner;
    private Button guessButton;
    private Toast correctionToast;
    private TextView correctionToastView;

    private boolean mIsCorrect;

    public QuestionFragment() {}
    @SuppressLint("ValidFragment")
    public QuestionFragment(States topic) {
        this();
        state = topic;

    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param stateIndex the state to display
     * @return A new instance of fragment QuestionFragment.
     */
    public static QuestionFragment newInstance(int stateIndex) {
        System.out.println("Creating new instance for state #" + stateIndex);
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
        initGuesser();
        initToast();
        return rootView;
    }

    private void initViews(View rootView) {
        webView = (WebView)rootView.findViewById(R.id.webView);
        guessSpinner = (Spinner)rootView.findViewById(R.id.guess_spinner);
        guessButton = (Button)rootView.findViewById(R.id.guess_button);
    }

    private void initState() {
        Bundle args = getArguments();
        if (args == null) {
            throw new IllegalArgumentException("The arguments should be valid!");
        }
        System.out.println("Bundle is now: " + args);
        int stateIndex = args.getInt(States.BUNDLE_KEY);
        System.out.println("Gonna be state #" + stateIndex);
        state = States.values()[stateIndex];
        System.out.println("Gonna be " + state);
        String path = state.getImageURL();
        System.out.println("Opening image at " + path);

        webView.loadUrl(path);
        webView.setBackgroundColor(getResources().getColor(R.color.transparent));
    }

//removed the onCreate() method

    private void initGuesser() {
        if (guessSpinner != null)
            guessSpinner.setAdapter(
                new ArrayAdapter<States>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    States.values()));
        guessSpinner.setOnItemSelectedListener(this);

        if (guessButton != null)
            guessButton.setOnClickListener(this);
    }

    @SuppressLint("ShowToast") // this only sets it up to be created later, but does not show it
    private void initToast() {
        if (correctionToastView == null)
        {
            LayoutInflater layoutInflater = getActivity().getLayoutInflater();
            correctionToastView = (TextView)layoutInflater.inflate(R.layout.correction_toast_layout, null);
        }

        correctionToast = Toast.makeText(getActivity(), "welp", Toast.LENGTH_SHORT);
        correctionToast.setView(correctionToastView);
        correctionToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.FILL_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View view) {
        if (mIsCorrect) {
            correctionToastView.setText(R.string.guess_correct);
            correctionToastView.setBackgroundColor(
                getResources().getColor(R.color.correct_background));
        }
        else {
            correctionToastView.setText(R.string.guess_incorrect);
            correctionToastView.setBackgroundColor(
                getResources().getColor(R.color.incorrect_background));
        }

        switch (view.getId())
        {
            case R.id.guess_button:
                correctionToast.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.guess_spinner:
                mIsCorrect = position == state.ordinal();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
