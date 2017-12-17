package com.saranyamoellers.guessingnumbergame.model;

import com.saranyamoellers.guessingnumbergame.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by saranya on 12/9/2017.
 */

public class Game {
    private List gameLevelList = new ArrayList();
    private boolean lastPage = false;

    //constructor to set value of fields or variable
    public Game() {
        gameLevelList.add( new GamePage( 2,
                                         R.string.question_level_0,
                                        R.string.hint_level_0 ));
        gameLevelList.add( new GamePage( new Random().nextInt(5)+1,
                                        R.string.question_level_1,
                                        R.string.hint_level_1 ));
        gameLevelList.add( new GamePage( 13,
                                        R.string.question_level_2,
                                        R.string.hint_level_2) );
    }
    //this method return GamePage object which has current index or page
    public GamePage getGamePage(int pageNumber) {
        if(pageNumber >= gameLevelList.size()){
            pageNumber = 0;
        }
        return (GamePage) gameLevelList.get(pageNumber);
    }

    //pass in GamePage object and will return index of the object passes in
    public int getIndex(GamePage currentPage) {

        return  gameLevelList.indexOf(currentPage);
    }
    public int getGamePageTotalList(){
        return gameLevelList.size();
    }
    public  int getLastIndex (){
        return gameLevelList.size() -1;
    }

}
