package com.mayarafelix.mastermind.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mlcf on 2017-11-20.
 */

public class PlayInfo
{
    public ArrayList<String> sequence;
    private int qtyBlackPoints;
    private int qtyWhitePoints;

    public PlayInfo(int numberOfItems)
    {
        qtyBlackPoints = 0;
        qtyWhitePoints = 0;
        sequence = new ArrayList<String>(Collections.nCopies(numberOfItems, ""));
    }

    public void addWhitePoints()
    {
        qtyWhitePoints++;
    }

    public void addBlackPoints()
    {
        qtyBlackPoints++;
    }

    public void addSequenceItem(int sequenceIndex, String tag)
    {
        // Check if we already have this position filled
        if (sequence.size() > 0)
        {
            String currentTag;

            try
            {
                currentTag = sequence.get(sequenceIndex);
            }
            catch (Exception e)
            {
                currentTag = null;
            }

            if (currentTag != null)
            {
                sequence.remove(sequenceIndex);
            }
        }

        sequence.add(sequenceIndex, tag);
    }

    public int getQtyBlackPoints()
    {
        return qtyBlackPoints;
    }

    public int getQtyWhitePoints()
    {
        return qtyWhitePoints;
    }
}
