package Simulation;

public class Game {
    public static final int DEFAULTROUNDS = 10;
    private int MAXROUNDS = DEFAULTROUNDS;
    private Round[] rounds;
    private int currentRound = 0;

    public Game(){
        this(DEFAULTROUNDS);
    }

    public Game(int maximumRounds){
        this.MAXROUNDS = maximumRounds;
        rounds = new Round[MAXROUNDS];
        for(int i=0; i<MAXROUNDS; i++){
            // Create the rounds and if it is the last round, set the flag
            rounds[i] = new Round(this, i, (i==MAXROUNDS-1)?true:false);
        }
    }

    /**
     *
     * @return An array containing the scores up to the round given by the position of the score
     */
    public int[] getScores(){
        int overall = 0;
        int[] scores = new int[MAXROUNDS];
        for(int i=0; i<MAXROUNDS; i++){
            if(i < currentRound){
                if(rounds[i].getPoints() <= Round.ALLPINS && (rounds[i].getType() == TossType.SPARE || rounds[i].getType() == TossType.STRIKE)){
                    scores[i] = -1;
                }else {
                    overall += rounds[i].getPoints();
                    scores[i] = overall;
                }
            }else {
                scores[i] = -1;
            }
        }

        return scores;
    }

    public String[][] getTosses(){
        String[][] result = new String[MAXROUNDS][3];
        for(int i=0; i<MAXROUNDS; i++){
            result[i] = rounds[i].getTosses();
        }

        return result;
    }

    /**
     * @param pins The number of pins that were hit
     * @return True, if the game has ended
     */
    public boolean toss(int pins){
        //Toss and if no other toss is possible in this round, proceed
        boolean next = rounds[currentRound].toss(pins);
        if(!next) currentRound++;
        if(currentRound >= MAXROUNDS) return true;
        return false;
    }

    public void notify(int targetRound, int[] pins){
        if(targetRound < 0 || targetRound >= rounds.length) return;
        rounds[targetRound].setFollowingToss(pins);
    }

    /**
     * @return True if the game was finished
     */
    public boolean hasEnded(){
        return (currentRound+1 > MAXROUNDS);
    }

}
