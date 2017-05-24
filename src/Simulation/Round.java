package Simulation;

import java.util.Arrays;

public class Round {
    public static final int ALLPINS = 10;

    private Game game;
    private int id;
    private TossType type = TossType.NORMAL;
    private int points = -1;
    private int[] toss;
    private boolean isLastRound = false;
    private boolean updated = false;

    /**
     * @param game
     * @param id Unique id of the round in the game
     * @param isLastRound True, if this round is the last round of the game
     */
    public Round(Game game, int id, boolean isLastRound){
        this.game = game;
        this.id = id;
        if(isLastRound) {
            toss = new int[3];
            this.isLastRound = isLastRound;
        }
        else toss = new int[2];
        Arrays.fill(toss, -1);
    }

    /**
     * @return The Points of this round, or -1 if no score should be shown
     */
    public int getPoints(){
        //don't return the points, if the round was a strike or spare and is still awaiting the next results
        if(points == ALLPINS && !updated && (this.type == TossType.STRIKE || this.type == TossType.SPARE)){
            return -1;
        }
        return this.points;
    }

    public TossType getType(){
        return this.type;
    }

    /**
     * @return A string array containing the string value of the pins hit with each toss. Special values are used for tosses that resulted in a strike, spare or gutter.
     */
    public String[] getTosses(){
        if(this.type == TossType.STRIKE && !isLastRound){
            return new String[]{ " ", "X"};
        }
        String[] result = new String[toss.length];
        for(int i=0; i<toss.length; i++){
            result[i] = Integer.toString(toss[i]);
            if(toss[i] == 0){
                result[i] = "-";
            }
            if(toss[i] == ALLPINS){
                if(i > 0 && this.type == TossType.SPARE) result[i] = "/";
                else result[i] = "X";
            }
            if(i > 0 && toss[i-1] + toss[i] == ALLPINS) result[i] = "/";
        }

        return result;
    }

    /**
     *
     * @return True, if another toss is possible in this round
     */
    public boolean toss(int pins){
        //get the current toss
        int current = 0;
        for(; current < toss.length; current++){
            if(toss[current] < 0) break;
        }
        //no new tosses in this round allowed
        if(current == toss.length) return false;

        //modify points to fit ranges
        if(current == 0) points = 0;
        if(pins <= 0) pins = 0;
        if(current > 0 && !isLastRound && pins > (ALLPINS - toss[current-1])) pins = (ALLPINS - toss[current-1]);
        if(pins >= ALLPINS) pins = ALLPINS;

        toss[current] = pins;
        points += pins;
        if(points >= ALLPINS){
            //if this was not the first toss and the previous was no strike: spare
            if(current > 0 && this.type == TossType.NORMAL){
                this.type = TossType.SPARE;
            }else{
                this.type = TossType.STRIKE;
            }
        }

        //notify previous round about result
        int[] tosses = new int[current+1];
        for(int i = 0; i<=current; i++){
            tosses[i] = toss[i];
        }
        game.notify(id - 1, tosses);

        //only throw a third time in the last round, if a strike or spare occurred
        if(isLastRound && current == toss.length - 2){
            return (this.type == TossType.STRIKE || this.type == TossType.SPARE);
        }
        //if this was not the last toss and neither a strike occurred or this was the last round, another
        // toss is possible
        if(current < toss.length - 1 && (this.type != TossType.STRIKE || isLastRound)) return true;
        return false;
    }

    /**
     * Used to receive information about the tosses that followed this round.
     * This information is necessary to calculate the points for spares and
     * strikes.
     * @param pins The pins hit in each toss
     */
    protected void setFollowingToss(int[] pins){
        //ignore if points have been updated before
        if(updated) return;

        //use only if type not spare or strike
        if(this.type == TossType.SPARE){
            this.points += pins[0];
            updated = true;
        }
        if(this.type == TossType.STRIKE){
            //call previous toss if only one toss
            if(pins.length == 1){
                game.notify(id - 1, new int[]{this.points, pins[0]});
            }else{
                this.points += pins[0] + pins[1];
                updated = true;
            }
        }
    }

    public boolean isUpdated(){
        return this.updated;
    }

}
