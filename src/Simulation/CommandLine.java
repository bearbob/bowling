package Simulation;

import java.io.Console;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class CommandLine {

    public static void main(String[] args) throws IOException {
        //Start a new game
        Game g = new Game();

        /*
        Console c = System.console();
        if(c == null){
            System.err.println("Error while handling the console.");
            System.exit(1);
        }
        */

        while(!g.hasEnded()){
            //c.readLine("Press <Enter> to toss.");

            //use random generator to get the amount of hit pins
            int pins = ThreadLocalRandom.current().nextInt(0, Round.ALLPINS + 1);
            g.toss(pins);
            String[] template = getTemplate(g.getScores(), g.getTosses());
            System.out.println(template[0]);
            System.out.println(template[1]);
            System.out.println(template[2]);
        }
    }

    private static String[] getTemplate(int[] scores, String[][] tosses){
        StringBuilder top = new StringBuilder(" ");
        StringBuilder middle = new StringBuilder("|");
        StringBuilder bottom = new StringBuilder("|");

        String pins;
        for(int i=0; i<scores.length; i++){
            //append a box
            if(i == scores.length-1) top.append("_");
            top.append(numberFormat(i+1, 3, "_"));
            top.append("_");

            for(int j=0; j< tosses[i].length; j++) {
                pins = tosses[i][j];
                if (pins.length() > 1) pins = " ";
                middle.append(pins);
                middle.append("|");
            }

            if(i == scores.length-1) bottom.append("__");
            bottom.append(numberFormat(scores[i], 3, "_"));
            bottom.append("|");
        }

        return new String[]{ top.toString(), middle.toString(), bottom.toString() };
    }

    private static String numberFormat(int number, int places, String fillchar){
        String result = Integer.toString(number);
        if(number < 0) result = "";
        for(int i=result.length(); i<places; i++){
            result = fillchar + result;
        }

        return result;
    }

}