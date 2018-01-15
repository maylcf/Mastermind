package com.mayarafelix.mastermind.libraries;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mlcf on 2017-11-20.
 */

public class Auxiliar
{
    private static int getRandomNumber(int minNumberInclusive, int maxNumberExclusive)
    {
        Random r = new Random();
        return r.nextInt(maxNumberExclusive - minNumberInclusive) + minNumberInclusive;
    }

    private static int getValidIndexOfArray(int[] array, int minNumberInclusive, int maxNumberExclusive)
    {
        int index = getRandomNumber(0, maxNumberExclusive);

        if (array[index] != 0)
        {
            return index;
        }
        else
        {
            return getValidIndexOfArray(array, minNumberInclusive, maxNumberExclusive);
        }
    }

    public static ArrayList<Integer> getListOfIndices(int size)
    {
        ArrayList<Integer> resultIndex = new ArrayList<Integer>();

        for (int j = 0; j < size; j++)
        {
            resultIndex.add(j);
        }

        return resultIndex;
    }

    public static int convertTimerToSeconds(String timer)
    {
        try
        {
            String[] timerItems = timer.split(":");
            String minutes = timerItems[0];
            String seconds = timerItems[1];

            int minutesNumber = Integer.parseInt(minutes) * 60;
            int secondsNumber = Integer.parseInt(seconds);

            return  minutesNumber + secondsNumber;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public static String convertSecondsToString(int totalOfTimeInSeconds)
    {
        try
        {
            int minutes = totalOfTimeInSeconds / 60;
            int seconds = totalOfTimeInSeconds % 60;

            String minutesStr = String.format("%02d", minutes);
            String secondsStr = String.format("%02d", seconds);

            return minutesStr + ":" + secondsStr;
        }
        catch (Exception e)
        {
            return "";
        }
    }
}
