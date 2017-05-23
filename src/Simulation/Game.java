package Simulation;

public class Game {
    private final int MAXROUNDS = 10;
    private Round[] rounds;
    private int currentRound = 0;

    public Game(){
        rounds = new Round[MAXROUNDS];
        for(int i=0; i<MAXROUNDS; i++){
            // Create the rounds and if it is the last round, set the flag
            rounds[i] = new Round(this, (i==MAXROUNDS-1)?true:false);
        }
    }

    /**
     *
     * @return An array containing the scores up to the round given by the position of the score
     */
    public int[] getScores(){
        int[] scores = new int[MAXROUNDS];
        for(int i=0; i<MAXROUNDS; i++){
            scores[i] = rounds[i].getScore();
        }

        return scores;
    }

    /**
     * @return True, if the game has ended
     */
    public boolean toss(){
        //Toss and if no other toss is possible in this round, proceed
        if(!rounds[currentRound].toss()) currentRound++;
        if(currentRound >= MAXROUNDS) return true;
        return false;
    }

}
