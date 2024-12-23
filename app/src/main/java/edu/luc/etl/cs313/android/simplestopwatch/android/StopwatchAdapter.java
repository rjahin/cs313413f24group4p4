package edu.luc.etl.cs313.android.simplestopwatch.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.media.MediaPlayer;

import java.util.Locale;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.common.Constants;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchModelListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.ConcreteStopwatchModelFacade;
import edu.luc.etl.cs313.android.simplestopwatch.model.StopwatchModelFacade;

/**
 * A thin adapter component for the stopwatch.
 *
 * @author laufer
 */
public class StopwatchAdapter extends Activity implements StopwatchModelListener {

    private static String TAG = "stopwatch-android-activity";

    /**
     * The state-based dynamic model.
     */
    private StopwatchModelFacade model;

    /**
     * Media player for playing a beep and alarm sounds, respectively.
     */
    private MediaPlayer beepPlayer;
    private MediaPlayer alarmPlayer;

    /**
     * Injects the stopwatch model facade into this adapter.
     *
     * @param model the initialized model facade to use
     */
    protected void setModel(final StopwatchModelFacade model) {
        this.model = model;
    }

    /**
     * Called when the activity is created.
     * Initializes the UI, model, and sound effects.
     *
     * @param savedInstanceState the saved activity state
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_main);
        //Initialize sound effects, raw files in main/res/raw
        beepPlayer = MediaPlayer.create(this, R.raw.beep);
        alarmPlayer = MediaPlayer.create(this, R.raw.alarm);
        // inject dependency on model into this so model receives UI events
        this.setModel(new ConcreteStopwatchModelFacade());
        // inject dependency on this into model to register for UI updates
        model.setModelListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * Starts the stopwatch model.
     */
    @Override
    protected void onStart() {
        super.onStart();
        model.start();
    }

    // TODO remaining lifecycle methods

    /**
     * Updates the seconds and minutes in the UI.
     * @param time
     */
    public void onTimeUpdate(final int time) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView tvS = findViewById(R.id.seconds);
            //final TextView tvM = findViewById(R.id.minutes); REMOVE!
            final var locale = Locale.getDefault();
            tvS.setText(String.format(locale,"%02d", time));
            //tvM.setText(String.format(locale,"%02d", time / Constants.SEC_PER_MIN)); REMOVE!
        });
    }

    /**
     * Updates the state name in the UI.
     * @param stateId
     */
    public void onStateUpdate(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView stateName = findViewById(R.id.stateName);
            stateName.setText(getString(stateId));
        });
    }

    // forward event listener methods to the model
    public void onIncrementReset(final View view)  {
        model.onIncrementReset();
    }

    public void onAlarm(){
        alarmPlayer.start();
    }
    public void onBeep(){
        beepPlayer.start();
    }
}
