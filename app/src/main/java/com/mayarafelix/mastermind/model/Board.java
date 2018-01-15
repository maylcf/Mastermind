package com.mayarafelix.mastermind.model;

import android.app.Activity;
import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.mayarafelix.mastermind.R;
import com.mayarafelix.mastermind.libraries.Auxiliar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board
{
    private Context context;

    public ArrayList<Play> plays;
    public ArrayList<Item> masterSequence;
    public ArrayList<Item> pickingOptions;

    private ImageButton confirmButton;

    // Constructor

    public Board(Context context)
    {
        this.context = context;
    }

    /* ******************************* */
    // *** Populate Board ************ */
    /* ******************************* */

    public void populateBoardElementsEasy()
    {
        Activity activity = (Activity) context;

        /* *************************************** */
        /* ** Get view objects ******************* */
        /* *************************************** */

        // Play Sequence

        ImageButton play0Item0 = (ImageButton) activity.findViewById(R.id.lin_0_btn_0);
        ImageButton play0Item1 = (ImageButton) activity.findViewById(R.id.lin_0_btn_1);
        ImageButton play0Item2 = (ImageButton) activity.findViewById(R.id.lin_0_btn_2);
        ImageButton play0Item3 = (ImageButton) activity.findViewById(R.id.lin_0_btn_3);

        ImageButton play1Item0 = (ImageButton) activity.findViewById(R.id.lin_1_btn_0);
        ImageButton play1Item1 = (ImageButton) activity.findViewById(R.id.lin_1_btn_1);
        ImageButton play1Item2 = (ImageButton) activity.findViewById(R.id.lin_1_btn_2);
        ImageButton play1Item3 = (ImageButton) activity.findViewById(R.id.lin_1_btn_3);

        ImageButton play2Item0 = (ImageButton) activity.findViewById(R.id.lin_2_btn_0);
        ImageButton play2Item1 = (ImageButton) activity.findViewById(R.id.lin_2_btn_1);
        ImageButton play2Item2 = (ImageButton) activity.findViewById(R.id.lin_2_btn_2);
        ImageButton play2Item3 = (ImageButton) activity.findViewById(R.id.lin_2_btn_3);

        ImageButton play3Item0 = (ImageButton) activity.findViewById(R.id.lin_3_btn_0);
        ImageButton play3Item1 = (ImageButton) activity.findViewById(R.id.lin_3_btn_1);
        ImageButton play3Item2 = (ImageButton) activity.findViewById(R.id.lin_3_btn_2);
        ImageButton play3Item3 = (ImageButton) activity.findViewById(R.id.lin_3_btn_3);

        ImageButton play4Item0 = (ImageButton) activity.findViewById(R.id.lin_4_btn_0);
        ImageButton play4Item1 = (ImageButton) activity.findViewById(R.id.lin_4_btn_1);
        ImageButton play4Item2 = (ImageButton) activity.findViewById(R.id.lin_4_btn_2);
        ImageButton play4Item3 = (ImageButton) activity.findViewById(R.id.lin_4_btn_3);

        ImageButton play5Item0 = (ImageButton) activity.findViewById(R.id.lin_5_btn_0);
        ImageButton play5Item1 = (ImageButton) activity.findViewById(R.id.lin_5_btn_1);
        ImageButton play5Item2 = (ImageButton) activity.findViewById(R.id.lin_5_btn_2);
        ImageButton play5Item3 = (ImageButton) activity.findViewById(R.id.lin_5_btn_3);

        ImageButton play6Item0 = (ImageButton) activity.findViewById(R.id.lin_6_btn_0);
        ImageButton play6Item1 = (ImageButton) activity.findViewById(R.id.lin_6_btn_1);
        ImageButton play6Item2 = (ImageButton) activity.findViewById(R.id.lin_6_btn_2);
        ImageButton play6Item3 = (ImageButton) activity.findViewById(R.id.lin_6_btn_3);

        ImageButton play7Item0 = (ImageButton) activity.findViewById(R.id.lin_7_btn_0);
        ImageButton play7Item1 = (ImageButton) activity.findViewById(R.id.lin_7_btn_1);
        ImageButton play7Item2 = (ImageButton) activity.findViewById(R.id.lin_7_btn_2);
        ImageButton play7Item3 = (ImageButton) activity.findViewById(R.id.lin_7_btn_3);

        // Play Result

        ImageView resultL0I0 = (ImageView) activity.findViewById(R.id.result_l0_i0);
        ImageView resultL0I1 = (ImageView) activity.findViewById(R.id.result_l0_i1);
        ImageView resultL0I2 = (ImageView) activity.findViewById(R.id.result_l0_i2);
        ImageView resultL0I3 = (ImageView) activity.findViewById(R.id.result_l0_i3);

        ImageView resultL1I0 = (ImageView) activity.findViewById(R.id.result_l1_i0);
        ImageView resultL1I1 = (ImageView) activity.findViewById(R.id.result_l1_i1);
        ImageView resultL1I2 = (ImageView) activity.findViewById(R.id.result_l1_i2);
        ImageView resultL1I3 = (ImageView) activity.findViewById(R.id.result_l1_i3);

        ImageView resultL2I0 = (ImageView) activity.findViewById(R.id.result_l2_i0);
        ImageView resultL2I1 = (ImageView) activity.findViewById(R.id.result_l2_i1);
        ImageView resultL2I2 = (ImageView) activity.findViewById(R.id.result_l2_i2);
        ImageView resultL2I3 = (ImageView) activity.findViewById(R.id.result_l2_i3);

        ImageView resultL3I0 = (ImageView) activity.findViewById(R.id.result_l3_i0);
        ImageView resultL3I1 = (ImageView) activity.findViewById(R.id.result_l3_i1);
        ImageView resultL3I2 = (ImageView) activity.findViewById(R.id.result_l3_i2);
        ImageView resultL3I3 = (ImageView) activity.findViewById(R.id.result_l3_i3);

        ImageView resultL4I0 = (ImageView) activity.findViewById(R.id.result_l4_i0);
        ImageView resultL4I1 = (ImageView) activity.findViewById(R.id.result_l4_i1);
        ImageView resultL4I2 = (ImageView) activity.findViewById(R.id.result_l4_i2);
        ImageView resultL4I3 = (ImageView) activity.findViewById(R.id.result_l4_i3);

        ImageView resultL5I0 = (ImageView) activity.findViewById(R.id.result_l5_i0);
        ImageView resultL5I1 = (ImageView) activity.findViewById(R.id.result_l5_i1);
        ImageView resultL5I2 = (ImageView) activity.findViewById(R.id.result_l5_i2);
        ImageView resultL5I3 = (ImageView) activity.findViewById(R.id.result_l5_i3);

        ImageView resultL6I0 = (ImageView) activity.findViewById(R.id.result_l6_i0);
        ImageView resultL6I1 = (ImageView) activity.findViewById(R.id.result_l6_i1);
        ImageView resultL6I2 = (ImageView) activity.findViewById(R.id.result_l6_i2);
        ImageView resultL6I3 = (ImageView) activity.findViewById(R.id.result_l6_i3);

        ImageView resultL7I0 = (ImageView) activity.findViewById(R.id.result_l7_i0);
        ImageView resultL7I1 = (ImageView) activity.findViewById(R.id.result_l7_i1);
        ImageView resultL7I2 = (ImageView) activity.findViewById(R.id.result_l7_i2);
        ImageView resultL7I3 = (ImageView) activity.findViewById(R.id.result_l7_i3);

        // Master Sequence

        ImageButton masterSeq0 = (ImageButton) activity.findViewById(R.id.master_seq_0);
        ImageButton masterSeq1 = (ImageButton) activity.findViewById(R.id.master_seq_1);
        ImageButton masterSeq2 = (ImageButton) activity.findViewById(R.id.master_seq_2);
        ImageButton masterSeq3 = (ImageButton) activity.findViewById(R.id.master_seq_3);

        // Picking Options

        ImageButton pickingOp0 = (ImageButton) activity.findViewById(R.id.pick_opt_0);
        ImageButton pickingOp1 = (ImageButton) activity.findViewById(R.id.pick_opt_1);
        ImageButton pickingOp2 = (ImageButton) activity.findViewById(R.id.pick_opt_2);
        ImageButton pickingOp3 = (ImageButton) activity.findViewById(R.id.pick_opt_3);
        ImageButton pickingOp4 = (ImageButton) activity.findViewById(R.id.pick_opt_4);
        ImageButton pickingOp5 = (ImageButton) activity.findViewById(R.id.pick_opt_5);

        // Other Buttons

        confirmButton = (ImageButton) activity.findViewById(R.id.btnCheckRow);

        /* *************************************** */
        /* ** Populate the Array of Plays ******** */
        /* *************************************** */

        plays = new ArrayList<Play>();

        // Play 0

        Play play0 = new Play();
        play0.result = new ArrayList<ImageView>();
        play0.sequence = new ArrayList<ImageButton>();

        play0.sequence.add(0, play0Item0);
        play0.sequence.add(1, play0Item1);
        play0.sequence.add(2, play0Item2);
        play0.sequence.add(3, play0Item3);
        
        play0.result.add(0,resultL0I0);
        play0.result.add(1,resultL0I1);
        play0.result.add(2,resultL0I2);
        play0.result.add(3,resultL0I3);

        plays.add(0, play0);

        // Play 1

        Play play1 = new Play();
        play1.result = new ArrayList<ImageView>();
        play1.sequence = new ArrayList<ImageButton>();

        play1.sequence.add(0, play1Item0);
        play1.sequence.add(1, play1Item1);
        play1.sequence.add(2, play1Item2);
        play1.sequence.add(3, play1Item3);

        play1.result.add(0,resultL1I0);
        play1.result.add(1,resultL1I1);
        play1.result.add(2,resultL1I2);
        play1.result.add(3,resultL1I3);

        plays.add(1, play1);

        // Play 2

        Play play2 = new Play();
        play2.result = new ArrayList<ImageView>();
        play2.sequence = new ArrayList<ImageButton>();

        play2.sequence.add(0, play2Item0);
        play2.sequence.add(1, play2Item1);
        play2.sequence.add(2, play2Item2);
        play2.sequence.add(3, play2Item3);

        play2.result.add(0,resultL2I0);
        play2.result.add(1,resultL2I1);
        play2.result.add(2,resultL2I2);
        play2.result.add(3,resultL2I3);

        plays.add(2, play2);

        // Play 3

        Play play3 = new Play();
        play3.result = new ArrayList<ImageView>();
        play3.sequence = new ArrayList<ImageButton>();

        play3.sequence.add(0, play3Item0);
        play3.sequence.add(1, play3Item1);
        play3.sequence.add(2, play3Item2);
        play3.sequence.add(3, play3Item3);

        play3.result.add(0,resultL3I0);
        play3.result.add(1,resultL3I1);
        play3.result.add(2,resultL3I2);
        play3.result.add(3,resultL3I3);

        plays.add(3, play3);

        // Play 4

        Play play4 = new Play();
        play4.result = new ArrayList<ImageView>();
        play4.sequence = new ArrayList<ImageButton>();

        play4.sequence.add(0, play4Item0);
        play4.sequence.add(1, play4Item1);
        play4.sequence.add(2, play4Item2);
        play4.sequence.add(3, play4Item3);

        play4.result.add(0,resultL4I0);
        play4.result.add(1,resultL4I1);
        play4.result.add(2,resultL4I2);
        play4.result.add(3,resultL4I3);

        plays.add(4, play4);

        // Play 5

        Play play5 = new Play();
        play5.result = new ArrayList<ImageView>();
        play5.sequence = new ArrayList<ImageButton>();

        play5.sequence.add(0, play5Item0);
        play5.sequence.add(1, play5Item1);
        play5.sequence.add(2, play5Item2);
        play5.sequence.add(3, play5Item3);

        play5.result.add(0,resultL5I0);
        play5.result.add(1,resultL5I1);
        play5.result.add(2,resultL5I2);
        play5.result.add(3,resultL5I3);

        plays.add(5, play5);

        // Play 6

        Play play6 = new Play();
        play6.result = new ArrayList<ImageView>();
        play6.sequence = new ArrayList<ImageButton>();

        play6.sequence.add(0, play6Item0);
        play6.sequence.add(1, play6Item1);
        play6.sequence.add(2, play6Item2);
        play6.sequence.add(3, play6Item3);

        play6.result.add(0,resultL6I0);
        play6.result.add(1,resultL6I1);
        play6.result.add(2,resultL6I2);
        play6.result.add(3,resultL6I3);

        plays.add(6, play6);

        // Play 7

        Play play7 = new Play();
        play7.result = new ArrayList<ImageView>();
        play7.sequence = new ArrayList<ImageButton>();

        play7.sequence.add(0, play7Item0);
        play7.sequence.add(1, play7Item1);
        play7.sequence.add(2, play7Item2);
        play7.sequence.add(3, play7Item3);

        play7.result.add(0,resultL7I0);
        play7.result.add(1,resultL7I1);
        play7.result.add(2,resultL7I2);
        play7.result.add(3,resultL7I3);

        plays.add(7, play7);

        /* *************************************** */
        /* ** Populate the Master Sequence ******* */
        /* *************************************** */

        masterSequence = new ArrayList<Item>();

        masterSequence.add(0, new Item(masterSeq0));
        masterSequence.add(1, new Item(masterSeq1));
        masterSequence.add(2, new Item(masterSeq2));
        masterSequence.add(3, new Item(masterSeq3));

        /* *************************************** */
        /* ** Populate the Picking Options ******* */
        /* *************************************** */

        pickingOptions = new ArrayList<Item>();

        pickingOptions.add(0, new Item(pickingOp0));
        pickingOptions.add(1, new Item(pickingOp1));
        pickingOptions.add(2, new Item(pickingOp2));
        pickingOptions.add(3, new Item(pickingOp3));
        pickingOptions.add(4, new Item(pickingOp4));
        pickingOptions.add(5, new Item(pickingOp5));
    }

    /* ******************************* */
    /* *** Erase Board *************** */
    /* ******************************* */

    public void eraseBoardElements()
    {
        // Plays

        for (int i = 0; i < plays.size(); i++)
        {
            for (int j = 0; j < plays.get(i).sequence.size(); j++)
            {
                ImageButton img = plays.get(i).sequence.get(j);
                img.setImageResource(0);
                img.setTag(null);
                img.setBackgroundResource(R.drawable.background_circle);

                ImageView result = plays.get(i).result.get(j);
                result.setImageResource(R.drawable.ic_circle_24dp);
                result.setTag(null);
            }
        }

        // Master Sequence

        for (int k = 0; k < masterSequence.size(); k++)
        {
            masterSequence.get(k).setImageResourceId(0);
            masterSequence.get(k).getImageButtonObject().setTag("");
        }
    }

    /* ******************************* */
    /* *** Getters ******************* */
    /* ******************************* */

    public int getImageButtonIndex(int playIndex, int imageButtonId)
    {
        for (int i = 0; i < plays.get(playIndex).sequence.size(); i++)
        {
            if (plays.get(playIndex).sequence.get(i).getId() == imageButtonId)
            {
                return i;
            }
        }

        return -1;
    }

    public int getPickingOptionIndex(int playIndex, int pickingOptionId)
    {
        for (int i = 0; i < pickingOptions.size(); i++)
        {
            if (pickingOptions.get(i).getImageButtonObject().getId() == pickingOptionId)
            {
                return i;
            }
        }
        return -1;
    }

    public int getIndexOfItemInMasterSequence(String tag)
    {
        for (int i = 0; i < masterSequence.size(); i++)
        {
            String masterSequenceTag = masterSequence.get(i).getImageButtonObject().getTag().toString();

            if (masterSequenceTag.equals(tag))
            {
                return i;
            }
        }

        return -1;
    }

    public ImageButton getImageButton(int playIndex, int imageButtonIndex)
    {
        return plays.get(playIndex).sequence.get(imageButtonIndex);
    }

    public ArrayList<String> getMasterSequenceStrings()
    {
        ArrayList<String> masterSequenceArray = new ArrayList<>();

        for (int i = 0; i < masterSequence.size(); i++)
        {
            if (masterSequence.get(i).getImageButtonObject().getTag() != null)
            {
                masterSequenceArray.add(i, masterSequence.get(i).getImageButtonObject().getTag().toString());
            }
        }

        return  masterSequenceArray;
    }

    /* ******************************* */
    /* *** Custom Methods ************ */
    /* ******************************* */

    public void setEnablePropertyToPickingOptions(boolean isEnabled)
    {
        for (int i = 0; i < pickingOptions.size(); i++)
        {
            pickingOptions.get(i).getImageButtonObject().setEnabled(isEnabled);
        }
    }

    public void setEnablePropertyToSequence(int playIndex, boolean isEnabled)
    {
        if (playIndex != -1) // set enable to only one row
        {
            for (int k = 0; k < plays.get(playIndex).sequence.size(); k++)
            {
                plays.get(playIndex).sequence.get(k).setEnabled(isEnabled);
            }
        }
        else // do it for all rows
        {
            for (int i = 0; i < plays.size(); i++)
            {
                for (int j = 0; j < plays.get(j).sequence.size(); j++)
                {
                    plays.get(i).sequence.get(j).setEnabled(isEnabled);
                }
            }
        }
    }

    public void setEnablePropertyToConfirmButton(boolean isEnabled)
    {
        confirmButton.setEnabled(isEnabled);
    }

    public void setAnimationToImageButton(int playIndex, int imageButtonIndex, boolean isAnimating)
    {
        ImageButton imageButton = plays.get(playIndex).sequence.get(imageButtonIndex);

        if (isAnimating)
        {
            Animation mAnimation = new AlphaAnimation(1, 0.5f);
            mAnimation.setDuration(800);
            mAnimation.setInterpolator(new LinearInterpolator());
            mAnimation.setRepeatCount(Animation.INFINITE);
            mAnimation.setRepeatMode(Animation.REVERSE);

            imageButton.startAnimation(mAnimation);
        }
        else
        {
            imageButton.clearAnimation();
        }
    }

    public void fillResultImages(int currentPlayIndex, int blackPoints, int whitePoints, int totalOfItems)
    {
        // Populate an auxiliary array to help randomize the result
        List<Integer> resultIndices = Auxiliar.getListOfIndices(totalOfItems);

        // Populate Result Images for Black Points
        if ( blackPoints > 0)
        {
            for (int j = 0; j < blackPoints; j++)
            {
                int randomIndex = new Random().nextInt(resultIndices.size());
                plays.get(currentPlayIndex).result.get(resultIndices.get(randomIndex)).setImageResource(R.drawable.ic_circle_black_24dp);
                resultIndices.remove(randomIndex);
            }
        }

        // Populate Result Images for White Points
        if (whitePoints > 0)
        {
            for (int j = 0; j < whitePoints; j++)
            {
                int randomIndex = new Random().nextInt(resultIndices.size());
                plays.get(currentPlayIndex).result.get(resultIndices.get(randomIndex)).setImageResource(R.drawable.ic_circle_white_24dp);
                resultIndices.remove(randomIndex);
            }
        }
    }

    public void setImageButtonBackground(int playIndex, int imageButtonIndex, boolean isBackgroundDark )
    {
        ImageButton imageButton = plays.get(playIndex).sequence.get(imageButtonIndex);

        if (isBackgroundDark)
        {
            imageButton.setBackgroundResource(R.drawable.background_circle_darker);
        }
        else
        {
            imageButton.setBackgroundResource(R.drawable.background_circle);
        }
    }

}
