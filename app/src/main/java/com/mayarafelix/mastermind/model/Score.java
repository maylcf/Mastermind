package com.mayarafelix.mastermind.model;

import com.mayarafelix.mastermind.libraries.Auxiliar;
import com.mayarafelix.mastermind.libraries.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by mlcf on 2017-11-24.
 */

public class Score
{
    private int max = 5;
    private ArrayList<ScoreObject> scoreList;

    public Score(JSONObject json)
    {
        scoreList = new ArrayList<>();

        if (json != null)
        {
            try
            {
                JSONArray jsonArray = json.getJSONArray(Constants.JSON_SCORE_TAG);

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    scoreList.add(new ScoreObject(jsonArray.getJSONObject(i)));
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    public int getSize()
    {
        return scoreList.size();
    }

    public boolean checkNewTopFive(int timeInSeconds)
    {
        if (scoreList == null)
            return true;

        if (scoreList.size() < 5)
            return true;

        for (int i = 0; i < scoreList.size(); i++)
        {
            if (scoreList.get(i).getPlayerTimeInSeconds() > timeInSeconds)
            {
                return true;
            }
        }

        return false;
    }

    public JSONObject getScoreList()
    {
        JSONObject result = new JSONObject();

        try
        {
            JSONArray jsonArray = new JSONArray();

            for (int i = 0; i < scoreList.size(); i++)
            {
                JSONObject scoreItem = new JSONObject();
                scoreItem.put(Constants.JSON_PLAYER_NAME_TAG, scoreList.get(i).getPlayerName());
                scoreItem.put(Constants.JSON_TOTAL_TIME_TAG , scoreList.get(i).getPlayerTimeInSeconds());
                jsonArray.put(scoreItem);
            }

            result.put(Constants.JSON_SCORE_TAG, jsonArray);
            return result;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return result;

    }

    public void includeNewPunctuation(String username, int timeInSeconds)
    {
        ScoreObject newScore = new ScoreObject(username, timeInSeconds);

        // Add new object
        scoreList.add(newScore);

        // Put list on crescent order
        if (scoreList.size() > 1)
        {
            for (int i = scoreList.size(); i >= 1; i--) {
                for (int j = 1; j < i; j++) {
                    int timeNow = scoreList.get(j).getPlayerTimeInSeconds();
                    int timePre = scoreList.get(j - 1).getPlayerTimeInSeconds();

                    if (timePre > timeNow) {
                        ScoreObject scoreNow = new ScoreObject(scoreList.get(j).getPlayerName(), scoreList.get(j).getPlayerTimeInSeconds());
                        ScoreObject scorePre = new ScoreObject(scoreList.get(j - 1).getPlayerName(), scoreList.get(j - 1).getPlayerTimeInSeconds());

                        scoreList.set(j, scorePre);
                        scoreList.set(j - 1, scoreNow);
                    }
                }
            }
        }

        // If more than 5, remove
        if (scoreList.size() > max)
        {
            scoreList.remove(max);
        }
    }

    public ScoreObject getScoreItem(int index)
    {
        return scoreList.get(index);
    }

    public static class ScoreObject
    {

        private String playerName;
        private int playerTimeInSeconds;

        public ScoreObject(String playerName, int playerTimeInSeconds)
        {
            this.playerName = playerName;
            this.playerTimeInSeconds = playerTimeInSeconds;
        }

        public ScoreObject(JSONObject json)
        {
            playerName = json.optString(Constants.JSON_PLAYER_NAME_TAG, "");
            playerTimeInSeconds = json.optInt(Constants.JSON_TOTAL_TIME_TAG, 0);
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public int getPlayerTimeInSeconds() {
            return playerTimeInSeconds;
        }

        public void setPlayerTimeInSeconds(int playerTimeInSeconds) {
            this.playerTimeInSeconds = playerTimeInSeconds;
        }

        public String getPlayerTimeDisplay() {
            return Auxiliar.convertSecondsToString(playerTimeInSeconds);
        }
    }
}
