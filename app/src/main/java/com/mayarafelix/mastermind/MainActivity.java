package com.mayarafelix.mastermind;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mayarafelix.mastermind.libraries.Auxiliar;
import com.mayarafelix.mastermind.libraries.Constants;
import com.mayarafelix.mastermind.model.Score;
import com.mayarafelix.mastermind.model.Game;
import com.mayarafelix.mastermind.model.Timer;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    private Score score;
    private Game game;
    private int numberOfPlays = 8;
    private int numberOfItems = 4;
    private int numberOfPickingOptions = 6;
    private int pickingOptionThemeId = 0;
    private Timer timer;
    private static TextView timerLabel;
    private SharedPreferences sharedPreferences;

    /****************************************/
    /***** Activity Methods *****************/
    /****************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* ******************************* */
        /* **** Menu ********************* */
        /* ******************************* */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* ******************************* */
        /* **** Get Shared Preferences *** */
        /* ******************************* */

        sharedPreferences = getSharedPreferences(Constants.PREFERENCES_TAG, MODE_PRIVATE);
        populateScore();

        /* ******************************* */
        /* **** Create Game ************** */
        /* ******************************* */

        game = new Game(this, numberOfPlays, numberOfItems, numberOfPickingOptions, pickingOptionThemeId);
        game.startGame();

        /* ******************************* */
        /* **** Create Timer ************* */
        /* ******************************* */

        timerLabel = (TextView) findViewById(R.id.timer);
        timer = new Timer(timerLabel);
        timer.StartTimer();

        /* ******************************* */
        /* **** Confirm Button *********** */
        /* ******************************* */

        ImageButton checkRowButton = (ImageButton) findViewById(R.id.btnCheckRow);
        checkRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                game.confirmPlaySequence();

                if (game.isGameOver() )
                {
                    if (game.isVictory())
                    {
                        onVictory();
                    }
                    else
                    {
                        onDefeat();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if (!game.isGameOver())
        {
            timer.ResumeTimer();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //timer.destroyTimer();

        if (timer != null) {
            timer.destroy();
        }
    }

    /****************************************/
    /***** Menu Methods *********************/
    /****************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

//        if (id == R.id.action_menu_settings)
//        {
//            return true;
//        }

        if (id == R.id.action_menu_new_game)
        {
            displayRestartDialog();
        }

        if (id == R.id.action_menu_score)
        {
            timer.PauseTimer();
            //PauseTimer();
            Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
            startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    /****************************************/
    /***** OnClickListener Methods **********/
    /****************************************/

    public void onColorOptionClick(View view)
    {
        game.setPickingOptionToSequenceItem(view);
    }

    public void onRowButtonClick(View view)
    {
        game.setSelectedImageButton(view);
    }

    /****************************************/
    /***** Custom Methods *******************/
    /****************************************/

    private void populateScore()
    {
        String scoreString = sharedPreferences.getString(Constants.PREFERENCES_SCORE_KEY, "");

        JSONObject scoreJson;

        // Parse to JsonObject
        try
        {
            scoreJson = new JSONObject(scoreString);
        }
        catch (Exception e)
        {
            scoreJson = null;
        }

        // Populate the Score object with the content
        score = new Score(scoreJson);
    }

    public void updateSystemPreferences()
    {
        JSONObject scoreList = score.getScoreList();
        sharedPreferences.edit().putString(Constants.PREFERENCES_SCORE_KEY, scoreList.toString()).apply();
    }

    private void onVictory()
    {
        // Stop Timer
        timer.PauseTimer();

        // Get Current Score information
        int timeInSeconds = Auxiliar.convertTimerToSeconds(timerLabel.getText().toString());

        if (score.checkNewTopFive(timeInSeconds))
        {
            dialogVictoryTopFive();
        }
        else
        {
            dialogVictory();
        }
    }

    private void onDefeat()
    {
        timer.PauseTimer();
        displayGameOverLostDialog();
    }

    private void startNewGame()
    {
        timer.StartTimer();
        game.startGame();
    }

    /****************************************/
    /***** Dialog Methods *******************/
    /****************************************/

    private void displayRestartDialog()
    {
        if (!game.isGameOver()) {

            String message = getString(R.string.msg_new_game);

            Auxiliar.convertTimerToSeconds(timerLabel.getText().toString());
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(message);
            builder.setCancelable(false);


            builder.setPositiveButton(getResources().getString(R.string.button_yes),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startNewGame();
                        }
                    });

            builder.setNegativeButton(getResources().getString(R.string.button_no),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
        else
        {
            startNewGame();
        }
    }

    private void displayGameOverLostDialog()
    {
        String message = getString(R.string.game_over_message);
        String title = getString(R.string.game_over_title);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setTitle(title);

        builder.setPositiveButton( getResources().getString(R.string.button_yes),
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        startNewGame();
                    }
                });

        builder.setNegativeButton( getResources().getString(R.string.button_no),
                new  DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void dialogVictory()
    {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = (MainActivity.this).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_victory, null);

        alertDialog.setView(view);

        alertDialog.setCancelable(false);

        ImageButton confirmButton = (ImageButton) view.findViewById(R.id.okButton);
        Button scoreButton   = (Button) view.findViewById(R.id.scoreButton);

        TextView punctuation = (TextView) view.findViewById(R.id.victoryPunctuationTime);
        punctuation.setText(timerLabel.getText().toString());

        final AlertDialog dialog = alertDialog.create();

        confirmButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });

        scoreButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
                Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(myIntent);
            }
        });

        dialog.show();
    }

    private void dialogVictoryTopFive()
    {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = (MainActivity.this).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_victory_top_five, null);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        ImageButton button = (ImageButton) view.findViewById(R.id.okButton);
        final EditText username = (EditText) view.findViewById(R.id.victoryUsername);

        TextView punctuation = (TextView) view.findViewById(R.id.victoryPunctuationTime);
        punctuation.setText(timerLabel.getText().toString());

        final AlertDialog dialog = alertDialog.create();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Get Current Score information
                String scoreUsername = username.getText().toString();

                if (!scoreUsername.isEmpty())
                {
                    int timeInSeconds = Auxiliar.convertTimerToSeconds(timerLabel.getText().toString());

                    score.includeNewPunctuation(scoreUsername, timeInSeconds);
                    updateSystemPreferences();

                    Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                    startActivity(myIntent);

                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.victory_enter_name), Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

}
