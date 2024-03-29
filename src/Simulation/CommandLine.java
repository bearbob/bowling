package Simulation;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CommandLine {

    public static void main(String[] args) throws IOException {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Press <Enter> to start a new game.");
        keyboard.nextLine();
        //Start a new game
        Game g = new Game();
        System.out.println("A new game has been started. Good luck!");

        while(!g.hasEnded()){
            System.out.println("Press <Enter> to toss the bowl.");
            keyboard.nextLine();
            //use random generator to get the amount of hit pins
            int pins = ThreadLocalRandom.current().nextInt(0, Round.ALLPINS + 1);
            g.toss(pins);
            String[] template = getTemplate(g.getScores(), g.getTosses());
            System.out.println(template[0]);
            System.out.println(template[1]);
            System.out.println(template[2]);
            System.out.println();
        }
        System.out.println("You scored a total of "+g.getScores()[g.getScores().length-1]+ " points.");
        System.out.println("Game over.");
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