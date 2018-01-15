package com.mayarafelix.mastermind.model;

import java.util.ArrayList;
import java.util.Collections;

public class Player
{
    public ArrayList<PlayInfo> plays;

    public Player(int numberOfPlays, int numberOfItems)
    {
        generateObjects(numberOfPlays, numberOfItems);
    }

    private void generateObjects(int numberOfPlays, int numberOfItems)
    {
        plays = new ArrayList<PlayInfo>();

        for (int i = 0; i < numberOfPlays; i++)
        {
            PlayInfo playInfo = new PlayInfo(numberOfItems);
            plays.add(i, playInfo);
        }
    }

    public void addToSequence(int playIndex, int sequenceIndex, String tag)
    {
        // Check if we already have this position filled
        if (plays.get(playIndex).sequence.size() > 0) {
            String currentTag;

            try {
                currentTag = plays.get(playIndex).sequence.get(sequenceIndex);
            } catch (Exception e) {
                currentTag = null;
            }

            if (currentTag != null) {
                plays.get(playIndex).sequence.remove(sequenceIndex);
            }
        }

        // Add sequence item
        plays.get(playIndex).sequence.add(sequenceIndex, tag);
    }

    public void calculatePlayerPunctuation(int playIndex, ArrayList<String> masterSequence)
    {
        int sequenceSize = plays.get(playIndex).sequence.size();

        if (sequenceSize > 0)
        {
            for (int i = 0; i < sequenceSize; i++)
             {
                String userSequenceItem = plays.get(playIndex).sequence.get(i);
                String masterSequenceItem = masterSequence.get(i);

                if (userSequenceItem.equals(masterSequenceItem))
                {
                    plays.get(playIndex).addBlackPoints();
                }
                else if (checkWhitePoint(playIndex, masterSequenceItem))
                {
                    plays.get(playIndex).addWhitePoints();
                }
            }
        }
    }

    private boolean checkWhitePoint(int playIndex, String sequenceItem)
    {
        int sequenceSize = plays.get(playIndex).sequence.size();

        for (int i = 0; i < sequenceSize; i++)
        {
            if (plays.get(playIndex).sequence.get(i).equals(sequenceItem))
            {
                return true;
            }
        }
        return false;
    }
}
