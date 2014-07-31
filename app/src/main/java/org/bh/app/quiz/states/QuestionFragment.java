package org.bh.app.quiz.states;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


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
public class QuestionFragment extends Fragment {

    private WebView webView;
    private States state;
    private Spinner guessSpinner;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param stateIndex the state to display
     * @return A new instance of fragment QuestionFragment.
     */
    public static QuestionFragment newInstance(int stateIndex) {
        System.out.println("Creating new instance for state #" + stateIndex);
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(States.BUNDLE_KEY, stateIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState) {
        System.out.println("~onCreateView");
        View rootView = inflater.inflate(
            R.layout.fragment_main_quiz, container, false);

        webView = (WebView)rootView.findViewById(R.id.webView);
        guessSpinner = (Spinner)getActivity().findViewById(R.id.guess_spinner);
        initState(savedInstanceState);
        initGuesser();

        return rootView;
    }

    private void initState(Bundle args) {
        if (webView == null)
            return;

        if (state == null) {
            System.out.println("Bundle: " + args);
            if (args == null)
                args = getArguments();
            if (args == null)
                return;
            System.out.println("Bundle is now: " + args);
            int stateIndex = args.getInt(States.BUNDLE_KEY);
            System.out.println("Gonna be state #" + stateIndex);
            state = States.values()[stateIndex];
            System.out.println("Gonna be " + state);
        }
        else
            System.out.println("State already exists! (yay!)");

        String path = state.getImageURL();
        System.out.println("Opening image at " + path);

        webView.loadUrl(path);
        webView.setBackgroundColor(0x00000000);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("~onResume");
        super.onCreate(savedInstanceState);
        if (webView == null)
            webView = (WebView)(getActivity().findViewById(R.id.webView));
        if (guessSpinner == null)
            guessSpinner = (Spinner)getActivity().findViewById(R.id.guess_spinner);
        System.out.println("onCreate: webView == " + webView);
        System.out.println("onCreate: bundle == " + savedInstanceState);

        initState(savedInstanceState);
        initGuesser();
    }

    private void initGuesser() {
        if (guessSpinner != null)
            guessSpinner.setAdapter(
                new ArrayAdapter<States>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    States.values()));
    }

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
}
