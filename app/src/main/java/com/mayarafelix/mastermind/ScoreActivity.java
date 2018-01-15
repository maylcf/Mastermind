package com.mayarafelix.mastermind;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mayarafelix.mastermind.libraries.Constants;
import com.mayarafelix.mastermind.model.Score;

import org.json.JSONObject;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity
{
    private ArrayList<TextView> scoreNames;
    private ArrayList<TextView> scoreTimes;

    private SharedPreferences sharedPreferences;
    private Score score;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        /* ******************************* */
        /* **** Get Shared Preferences *** */
        /* ******************************* */

        sharedPreferences = getSharedPreferences(Constants.PREFERENCES_TAG, MODE_PRIVATE);

        populateScore();

        // Get the View Objects

        scoreNames = new ArrayList<TextView>();
        scoreNames.add(0, (TextView) findViewById(R.id.scoreName1));
        scoreNames.add(1, (TextView) findViewById(R.id.scoreName2));
        scoreNames.add(2, (TextView) findViewById(R.id.scoreName3));
        scoreNames.add(3, (TextView) findViewById(R.id.scoreName4));
        scoreNames.add(4, (TextView) findViewById(R.id.scoreName5));
        
        scoreTimes = new ArrayList<TextView>();
        scoreTimes.add(0, (TextView) findViewById(R.id.scoreTime1));
        scoreTimes.add(1, (TextView) findViewById(R.id.scoreTime2));
        scoreTimes.add(2, (TextView) findViewById(R.id.scoreTime3));
        scoreTimes.add(3, (TextView) findViewById(R.id.scoreTime4));
        scoreTimes.add(4, (TextView) findViewById(R.id.scoreTime5));

        if (score != null)
        {
            for (int i = 0; i < score.getSize(); i++)
            {
                scoreNames.get(i).setText(score.getScoreItem(i).getPlayerName());
                scoreTimes.get(i).setText(score.getScoreItem(i).getPlayerTimeDisplay());
            }
        }
    }
    private void populateScore()
    {
        String scoreString = sharedPreferences.getString(Constants.PREFERENCES_SCORE_KEY, "");

        if (!scoreString.isEmpty())
        {
            try
            {
                JSONObject scoreJson = new JSONObject(scoreString);
                score = new Score(scoreJson);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
