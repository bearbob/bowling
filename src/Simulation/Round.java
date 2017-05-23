package Simulation;

import java.util.Arrays;

public class Round {
    private Game game;
    private RoundType type = RoundType.NORMAL;
    // Holds the total score up to this round
    private int score = -1;
    // Holds the number of point scored in this round
    private int points = 0;
    private int[] toss;

    /**
     * @param isLastRound True, if this round is the last round of the game
     */
    public Round(Game game, boolean isLastRound){
        this.game = game;
        if(isLastRound) toss = new int[3];
        else toss = new int[2];
        Arrays.fill(toss, -1);
    }

    /**
     * Returns the total score up to this round
     * @return
     */
    public int getScore(){
        return this.score;
    }

    /**
     *
     * @return True, if another toss is possible in this round
     */
    public boolean toss(){

        return false;
    }

    public void addToss(int pins){
        //TODO: If round is strike, call previous toss
    }

    public void twoTosses(int pins){
        //nothing special to do, 2 tosses suffice a strike
    }

}
