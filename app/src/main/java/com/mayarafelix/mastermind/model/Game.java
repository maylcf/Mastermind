package com.mayarafelix.mastermind.model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.mayarafelix.mastermind.R;
import com.mayarafelix.mastermind.libraries.Auxiliar;
import com.mayarafelix.mastermind.libraries.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{
    private Context context;
    private Player player;
    private Board  board;
    private PickingOptionCollection pickingOptionTheme;

    private int numberOfPlays;
    private int numberOfItems;
    private int numberOfPickingOptions;
    private int currentPlayIndex;
    private int currentItemIndex;
    private int pickingOptionThemeId;

    private boolean gameOver;
    private boolean playerVictory;

    public Game(Context context, int numberOfPlays, int numberOfItems, int numberOfPickingOptions, int pickingOptionThemeId)
    {
        this.context = context;
        this.numberOfPlays = numberOfPlays;
        this.numberOfItems = numberOfItems;
        this.numberOfPickingOptions = numberOfPickingOptions;
        this.pickingOptionThemeId = pickingOptionThemeId;

        this.currentPlayIndex = 0;
        this.currentItemIndex = 0;
    }

    public void startGame()
    {
        // Set GameOver
        gameOver = false;
        playerVictory = false;

        // Create Player Objets
        player = new Player(numberOfPlays, numberOfItems);

        // Get the current theme
        pickingOptionTheme = Themes.getPickingOptionTheme(pickingOptionThemeId, numberOfPickingOptions);

        // Populate The board Objects
        board = new Board(context);
        board.populateBoardElementsEasy();
        board.eraseBoardElements();

        // Set the current theme to the Picking Options
        for (int i = 0; i < numberOfPickingOptions; i++)
        {
            String tag = pickingOptionTheme.getTag(i);
            int imageResourceId = pickingOptionTheme.getImageResourceId(i);

            board.pickingOptions.get(i).getImageButtonObject().setImageResource(imageResourceId);
            board.pickingOptions.get(i).getImageButtonObject().setTag(tag);
            board.pickingOptions.get(i).setImageResourceId(imageResourceId);
        }

        // Remove Animation -> If any
        board.setAnimationToImageButton(currentPlayIndex, currentItemIndex, false);
        board.setImageButtonBackground(currentPlayIndex, currentItemIndex, false);

        // Set the current row
        currentPlayIndex = 0;
        currentItemIndex = 0;

        // Generate Master Sequence
        generateMasterSequence();

        // Disable All Plays
        board.setEnablePropertyToSequence(-1, false);

        // Enable First Play
        board.setEnablePropertyToSequence(currentPlayIndex, true);

        // Enable Picking Options
        board.setEnablePropertyToPickingOptions(true);

        // Enable Confirm Button
        board.setEnablePropertyToConfirmButton(true);

        // Set the focus to the first button
        setSelectedImageButton(currentItemIndex);
    }

    private void generateMasterSequence()
    {
        List<Integer> tempIndices = Auxiliar.getListOfIndices(pickingOptionTheme.items.size());

        // Generate Sequence
        for (int i = 0; i < numberOfItems; i++)
        {
            // Get a random index of the list of PickingOptions
            int randomIndex = new Random().nextInt(tempIndices.size());
            int randomIndexValue = tempIndices.get(randomIndex);

            // Get Information of the correspondent Item (ie, ColorBlue)
            int imageResourceId = pickingOptionTheme.getImageResourceId(randomIndexValue);
            String tag = pickingOptionTheme.getTag(randomIndexValue);

            // Populate the masterSequence with the PickingOption information (ie, ColorBlue)
            board.masterSequence.get(i).setImageResourceId(imageResourceId);
            board.masterSequence.get(i).getImageButtonObject().setTag(tag);
            board.masterSequence.get(i).getImageButtonObject().setEnabled(false);
            board.masterSequence.get(i).getImageButtonObject().setImageResource(R.drawable.ic_star);

            // Remove this PickingOption from the list so the MasterSequence will not have repeated items
            tempIndices.remove(randomIndex);
        }

        debugPrintMasterSequence();
    }

    private int getNextIndexInPlay()
    {
        // if 0,1,2,3 => Will return the same number
        // if 4 => will return 0 to go back to the first row element
        return (currentItemIndex+1)%numberOfItems;
    }

    private void goToNextPlay()
    {
        // Disable last row
        board.setEnablePropertyToSequence(currentPlayIndex, false);

        // Remove animation from the last selected button
        board.setAnimationToImageButton(currentPlayIndex, currentItemIndex, false);
        board.setImageButtonBackground(currentPlayIndex, currentItemIndex, false);

        // Increment the current row
        currentPlayIndex++;

        // define current element
        currentItemIndex = 0;

        // Enable Current Row
        board.setEnablePropertyToSequence(currentPlayIndex, true);

        // Set Focus to First ImageButton of the Sequence
        setSelectedImageButton(currentItemIndex);
    }

    private void setSelectedImageButton(int newImageButtonIndex)
    {
        // Remove Animation from Previously Button
        board.setAnimationToImageButton(currentPlayIndex, currentItemIndex, false);
        board.setImageButtonBackground(currentPlayIndex, currentItemIndex, false);

        // Update the current id
        this.currentItemIndex = newImageButtonIndex;

        // Set Animation
        board.setAnimationToImageButton(currentPlayIndex, currentItemIndex, true);
        board.setImageButtonBackground(currentPlayIndex, currentItemIndex, true);
    }

    private void gameOver()
    {
        gameOver = true;

        board.setAnimationToImageButton(currentPlayIndex, currentItemIndex, false);
        board.setImageButtonBackground(currentPlayIndex, currentItemIndex, false);

        board.setEnablePropertyToSequence(-1, false);
        board.setEnablePropertyToPickingOptions(false);
        board.setEnablePropertyToConfirmButton(false);

        // Show sequence
        for (int i = 0; i < board.masterSequence.size(); i++)
        {
            int imageResourceId = board.masterSequence.get(i).getImageResourceId();
            board.masterSequence.get(i).getImageButtonObject().setImageResource(imageResourceId);
        }
    }

    private void checkGameOver(int blackPoints, int whitePoints)
    {
        if (blackPoints == numberOfItems)
        {
            gameOver();
            playerVictory = true;
        }
        else
        {
            // Check if the user lost the game
            if ((currentPlayIndex+1) >= numberOfPlays)
            {
                gameOver();
                playerVictory = false;
            }
        }
    }

    public boolean isGameOver()
    {
        return this.gameOver;
    }

    public boolean isVictory()
    {
        if (gameOver)
        {
            return playerVictory;
        }

        return false;
    }

    /* ******************************** */
    // *** Methods Called by the UI *** */
    /* ******************************** */

    public void setPickingOptionToSequenceItem(View pickingOptionButton)
    {
        if (gameOver) {
            return;
        }

        if (currentItemIndex > -1)
        {
            if (pickingOptionButton.getTag() != null)
            {
                // Get Picking Option Information
                int pickingOptionIndex  = board.getPickingOptionIndex(currentPlayIndex, pickingOptionButton.getId());
                int pickingOptionImage  = board.pickingOptions.get(pickingOptionIndex).getImageResourceId();
                String pickingOptionTag = pickingOptionButton.getTag().toString();

                // Get Selected Sequence Item
                ImageButton selectedImageButton = board.getImageButton(currentPlayIndex, currentItemIndex);
                selectedImageButton.setImageResource(pickingOptionImage);
                selectedImageButton.setTag(pickingOptionTag);

                //Set the player Information
                player.plays.get(currentPlayIndex).addSequenceItem(currentItemIndex, pickingOptionTag);

                // Set Focus to next Image Button in the current Play
                int nextIndex = getNextIndexInPlay();
                setSelectedImageButton(nextIndex);
            }
        }
    }

    public void setSelectedImageButton(View imageButton)
    {
        if (gameOver) return;

        int newImageButtonIndex = board.getImageButtonIndex(currentPlayIndex, imageButton.getId());
        setSelectedImageButton(newImageButtonIndex);
    }

    public void confirmPlaySequence()
    {
        // End of the game
        if (gameOver)
            return;

        if (currentPlayIndex >= 0 && currentItemIndex >= 0 && currentPlayIndex < numberOfPlays )
        {
            // Get Current Play (Board)
            Play currentBoardPlay = board.plays.get(currentPlayIndex);

            // Populate Player Punctuation
            ArrayList<String> masterSequence = board.getMasterSequenceStrings();
            player.calculatePlayerPunctuation(currentPlayIndex, masterSequence);

            // Get Punctuation Information
            int numberOfBlackPoints = player.plays.get(currentPlayIndex).getQtyBlackPoints();
            int numberOfWhitePoints = player.plays.get(currentPlayIndex).getQtyWhitePoints();

            // Populate Result Images
            board.fillResultImages(currentPlayIndex, numberOfBlackPoints, numberOfWhitePoints, numberOfItems);

            // Check if player won the game
            checkGameOver(numberOfBlackPoints, numberOfWhitePoints);

            // If it is not GameOver => enable next play
            if (!gameOver)
            {
                goToNextPlay();
            }
        }
    }

    /* ******************************* */
    // *** Debug Methods ************* */
    /* ******************************* */

    private void debugPrintMasterSequence()
    {
        Log.i(Constants.GAME_TAG, "####################");
        Log.i(Constants.GAME_TAG, "Master Sequence:");

        for (int i = 0; i < board.masterSequence.size(); i++)
        {
            Log.i(Constants.GAME_TAG, board.masterSequence.get(i).getImageButtonObject().getTag().toString());
        }
    }
}
