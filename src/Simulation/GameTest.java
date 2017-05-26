package Simulation;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @org.junit.Test
    public void games() throws Exception {
        Game g = new Game(-1);
        assertTrue(g.hasEnded());

        g = new Game(0);
        assertTrue(g.hasEnded());

        g = new Game(1);
        assertFalse(g.hasEnded());
    }

    @org.junit.Test
    public void singleRound() throws Exception {
        Game g = new Game();
        Round r = new Round(g, 0, false);
        assertTrue(r.toss(0));
        assertFalse(r.toss(0));
        assertFalse(r.toss(0));
        assertEquals(0, r.getPoints());

        r = new Round(g, 0, true);
        assertTrue(r.toss(1));
        assertFalse(r.toss(1));
        assertFalse(r.toss(1));
        assertEquals(3, r.getPoints());

        r = new Round(g, 0, false);
        assertFalse(r.toss(Round.ALLPINS));
        assertFalse(r.toss(1));
        // result is -1, because the two tosses after a strike are needed for point calculation
        assertEquals(-1, r.getPoints());

        r = new Round(g, 0, true);
        assertTrue(r.toss(Round.ALLPINS));
        assertTrue(r.toss(1));
        assertFalse(r.toss(1));
        assertEquals(12, r.getPoints());
    }

    @org.junit.Test
    public void complexRounds() throws Exception {
        Game g;
        int sum;

        g = new Game(2);
        //GutterGame
        assertFalse(g.toss(0));
        assertFalse(g.toss(0));
        assertFalse(g.toss(0));
        assertTrue(g.toss(0));
        assertTrue(g.hasEnded());
        int[] scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(0, sum);

        g = new Game(2);
        //Mediocre Game
        assertFalse(g.toss(1));
        assertFalse(g.toss(1));
        assertFalse(g.toss(1));
        assertTrue(g.toss(1));
        assertTrue(g.hasEnded());
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(4, sum);

        g = new Game(2);
        //Mediocre Game
        assertFalse(g.toss(3));
        assertFalse(g.toss(3));
        assertFalse(g.toss(3));
        assertTrue(g.toss(3));
        assertTrue(g.hasEnded());
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(12, sum);

        g = new Game(2);
        //OneSpare
        assertFalse(g.toss(Round.ALLPINS-1));
        assertFalse(g.toss(1));
        assertFalse(g.toss(1));
        assertTrue(g.toss(1));
        assertTrue(g.hasEnded());
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(13, sum);

        g = new Game(2);
        //GoodGame
        assertFalse(g.toss(Round.ALLPINS-1));
        assertFalse(g.toss(1));
        assertFalse(g.toss(Round.ALLPINS-1));
        assertFalse(g.toss(1));
        assertTrue(g.toss(Round.ALLPINS));
        assertTrue(g.hasEnded());
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(39, sum);

        g = new Game(2);
        //Spare and Fail
        assertFalse(g.toss(Round.ALLPINS-1));
        assertFalse(g.toss(1));
        assertFalse(g.toss(0));
        assertTrue(g.toss(1));
        assertTrue(g.hasEnded());
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(11, sum);

        g = new Game(10);
        //GameTest Example given in instructions
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(9));
        assertFalse(g.toss(1));
        assertFalse(g.toss(5));
        assertFalse(g.toss(5));
        assertFalse(g.toss(7));
        assertFalse(g.toss(2));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(9));
        assertFalse(g.toss(0));
        assertFalse(g.toss(8));
        assertFalse(g.toss(2));
        assertFalse(g.toss(9));
        assertFalse(g.toss(1));
        assertTrue(g.toss(Round.ALLPINS));
        assertTrue(g.hasEnded());
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(187, sum);

        g = new Game(10);
        //PerfectGame
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertFalse(g.toss(Round.ALLPINS));
        assertTrue(g.toss(Round.ALLPINS));
        assertTrue(g.hasEnded());
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(300, sum);
    }

    @org.junit.Test
    public void output() throws Exception {
        Game g = new Game();
        assertEquals(10, g.getScores().length);
        String[][] tosses = g.getTosses();
        assertEquals(10, tosses.length);
        for(int i=0; i<tosses.length; i++){
            if(i == tosses.length-1){
                assertEquals(3, tosses[i].length);
            }else {
                assertEquals(2, tosses[i].length);
            }
        }
    }

}