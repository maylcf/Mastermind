package com.mayarafelix.mastermind.model;

import java.util.ArrayList;

public class PickingOptionCollection
{
    public ArrayList<PickingOption> items;

    public PickingOptionCollection()
    {
        items = new ArrayList<PickingOption>();
    }

    public void addPickingOption(int index, String tag, int imageResourceId)
    {
        items.add(index, new PickingOption(tag, imageResourceId));
    }

    public String getTag(int pickingOptionIndex)
    {
        return items.get(pickingOptionIndex).getTag();
    }

    public int getImageResourceId(int pickingOptionIndex)
    {
        return items.get(pickingOptionIndex).getImageResourceId();
    }

    /* ****************************** */
    /* *** Item Class *************** */
    /* ****************************** */

    public static class PickingOption
    {
        private String tag;
        private int imageResourceId;

        public PickingOption(String tag, int imageResourceId)
        {
            this.tag = tag;
            this.imageResourceId = imageResourceId;
        }

        // Getters & Setters

        public String getTag()
        {
            return tag;
        }

        public void setTag(String tag)
        {
            this.tag = tag;
        }

        public int getImageResourceId()
        {
            return imageResourceId;
        }

        public void setImageResourceId(int imageResourceId)
        {
            this.imageResourceId = imageResourceId;
        }
    }
}

