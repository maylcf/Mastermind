package com.mayarafelix.mastermind.model;

import com.mayarafelix.mastermind.R;

import java.util.ArrayList;

/**
 * Created by mlcf on 2017-11-21.
 */

public class Themes
{
    private static ArrayList<PickingOptionCollection> pickingOptionsThemes;

    public static PickingOptionCollection getPickingOptionTheme(int pickingOptionThemeIndex, int pickingOptionsQuantity)
    {
        if (pickingOptionsThemes == null)
        {
            populatePickingOptionsThemes();
        }

        PickingOptionCollection pickingOptionTheme = new PickingOptionCollection();

        for (int i = 0; i < pickingOptionsQuantity; i++)
        {
            pickingOptionTheme.items.add(pickingOptionsThemes.get(pickingOptionThemeIndex).items.get(i));
        }

        return pickingOptionTheme;
    }

    private static void populatePickingOptionsThemes()
    {
        pickingOptionsThemes = new ArrayList<PickingOptionCollection>();

        // Season

        PickingOptionCollection seasonTheme= new PickingOptionCollection();

        seasonTheme.addPickingOption(0, "SUN"   , R.drawable.ic_s1_sun);
        seasonTheme.addPickingOption(1, "CLOUD" , R.drawable.ic_s1_cloud);
        seasonTheme.addPickingOption(2, "ROSE"  , R.drawable.ic_s1_flower);
        seasonTheme.addPickingOption(3, "MOON"  , R.drawable.ic_s1_moon);
        seasonTheme.addPickingOption(4, "STAR"  , R.drawable.ic_s1_star);
        seasonTheme.addPickingOption(5, "SNOW"  , R.drawable.ic_s1_snow);

        // Color

        PickingOptionCollection colorTheme = new PickingOptionCollection();

        colorTheme.addPickingOption(0, "BLUE"   , R.drawable.ic_s0_circle_blue);
        colorTheme.addPickingOption(1, "GREEN"  , R.drawable.ic_s0_circle_green);
        colorTheme.addPickingOption(2, "ORANGE" , R.drawable.ic_s0_circle_orange);
        colorTheme.addPickingOption(3, "PINK"   , R.drawable.ic_s0_circle_pink);
        colorTheme.addPickingOption(4, "RED"    , R.drawable.ic_s0_circle_red);
        colorTheme.addPickingOption(5, "YELLOW" , R.drawable.ic_s0_circle_yellow);

        // Add to list of Themes

        pickingOptionsThemes.add(0, colorTheme);
        pickingOptionsThemes.add(1, seasonTheme);
    }

}
