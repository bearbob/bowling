package Simulation;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoundTest {
    @Test
    public void toss() throws Exception {
        singleRound();
        doubleRound();
    }

    private void singleRound(){
        Game g = new Game();
        Round r = new Round(g, 0, false);
        assertTrue(r.toss(0));
        assertFalse(r.toss(0));
        assertFalse(r.toss(0));

        r = new Round(g, 0, true);
        assertTrue(r.toss(1));
        assertFalse(r.toss(1));
        assertFalse(r.toss(1));

        r = new Round(g, 0, false);
        assertFalse(r.toss(Round.ALLPINS));
        assertFalse(r.toss(1));

        r = new Round(g, 0, false);
        assertFalse(r.toss(Round.ALLPINS));
        assertFalse(r.toss(1));

        r = new Round(g, 0, true);
        assertTrue(r.toss(Round.ALLPINS));
        assertTrue(r.toss(1));
        assertFalse(r.toss(1));
    }

    private void doubleRound(){
        Game g;
        int sum;

        g = new Game(2);
        //GutterGame
        assertFalse(g.toss(0));
        assertFalse(g.toss(0));
        assertFalse(g.toss(0));
        assertTrue(g.toss(0));
        int[] scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(0, sum);

        g = new Game(2);
        //Mediocre Game
        assertFalse(g.toss(1));
        assertFalse(g.toss(1));
        assertFalse(g.toss(1));
        assertTrue(g.toss(1));
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(4, sum);

        g = new Game(2);
        //Mediocre Game
        assertFalse(g.toss(3));
        assertFalse(g.toss(3));
        assertFalse(g.toss(3));
        assertTrue(g.toss(3));
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(12, sum);

        g = new Game(2);
        //OneSpare
        assertFalse(g.toss(Round.ALLPINS-1));
        assertFalse(g.toss(1));
        assertFalse(g.toss(1));
        assertTrue(g.toss(1));
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
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(38, sum);

        g = new Game(2);
        //Spare and Fail
        assertFalse(g.toss(Round.ALLPINS-1));
        assertFalse(g.toss(1));
        assertFalse(g.toss(0));
        assertTrue(g.toss(1));
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(12, sum);

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
        scores = g.getScores();
        sum = scores[scores.length-1];
        assertEquals(300, sum);
    }

}