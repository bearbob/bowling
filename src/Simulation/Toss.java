package Simulation;

public class Toss {
    private TossType type = TossType.NORMAL;
    private int pins = 0;

    public Toss(int pins, TossType type){
        this.pins = pins;
        this.type = type;
    }

    public int getPins(){
        return this.pins;
    }

    public TossType getType(){
        return this.type;
    }
}
