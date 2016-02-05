package com.ru.usty.threads;
import java.util.*;
import static com.ru.usty.threads.Problematic.PROBLEM_WIDTH;

/**
 * Provided with the assignment,
 *
 * EDITED by    Asa Bjork Valdimarsdottir
 *              Vedis Erna Eyjolfsdottir
 * @since       05-Feb-16.
 *
 * Added the parameter 'int nr' to the findAndPrintSolution method, so it would be easier
 * to show which problem was being run each time.
 */
public class Solver {

    public static void findAndPrintSolution(Problem problem, int nr) {
        Queue<Tile> queue = new ArrayDeque<Tile>();
        Map<Integer, Tile> closedList = new HashMap<Integer, Tile>(10 * Problematic.PROBLEM_WIDTH);
        queue.add(problem.getStart());
        int expandedTiles = 0;
        while(true) {
            expandedTiles += 1;
            Tile expanded = queue.remove();
            while(closedList.containsKey(expanded.hashCode())) {
                expanded = queue.remove();
                if(expanded == null) {
                    System.out.println("No solution exists!");
                    return;
                }
            }
            closedList.put(expanded.hashCode(), expanded);
            if(expanded.equals(problem.getEnd())) {
            	System.out.println("Problem#" + nr + ": "
            			+ problem + " - length: " + expanded.travel + " - iterations: " + expandedTiles);
            	return;
            }
            else if((expandedTiles > 1000000)) {
            	System.out.println("No solution found yet - Nodes expanded: " + expandedTiles);
            	return;
            }
            else if(expandedTiles % 25000 == 0) {
                try {
                    Thread.sleep(100);  // fake I/O
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(expanded.getX() > 0) {
                queue.add(new Tile(expanded.getX() - 1, expanded.getY(), expanded.travel + 1));
            }
            if(expanded.getX() < PROBLEM_WIDTH - 1) {
                queue.add(new Tile(expanded.getX() + 1, expanded.getY(), expanded.travel + 1));
            }
            if(expanded.getY() > 0) {
                queue.add(new Tile(expanded.getX(), expanded.getY() - 1, expanded.travel + 1));
            }
            if(expanded.getY() < PROBLEM_WIDTH - 1) {
                queue.add(new Tile(expanded.getX(), expanded.getY() + 1, expanded.travel + 1));
            }
        }
    }
}
