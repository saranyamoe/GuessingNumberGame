package com.saranyamoellers.guessingnumbergame.model;

/**
 * Created by saranya on 12/9/2017.
 */

public class Choice {

    private int textId;
    private int nextPage;

    //constructor
    public Choice(int textId, int nextPage) {
        this.textId = textId;
        this.nextPage = nextPage;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
