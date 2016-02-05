package com.ru.usty.threads;
import java.util.concurrent.*;

/** *
 * Reykjavik University
 * T-444-USTY
 * Lab Project 3: Thread Assignment
 *
 * This file was provided with the assignment and edited by the students.
 *
 * EDITED by    Asa Bjork Valdimarsdottir
 *              Vedis Erna Eyjolfsdottir
 * @since       05-Feb-16.
 *
 * To change running configuratation, change the RUN_SETTINGS parameter to 1, 2, or 3,
 * to run the solution to part 1, 2, or 3 of the project.
 */
public class ThreadAssignment01Main {

    private static final int NUMBER_OF_PROBLEMS = 30;
    private static final int POOL_SIZE = 8;

    private static final int RUN_SETTINGS = 2; // Set to 1, 2 or 3
    //private static final int RUN_SETTINGS = 2;
    //private static final int RUN_SETTINGS = 3;

    public static void main(String[] args) throws InterruptedException {
        long startTime = 0, runTime = 0;

        /* Setting up the problems, this way we will always be running
        * the same set of problems for each part */
        Problem[] problems = new Problem[NUMBER_OF_PROBLEMS];
        for(int i = 0; i < NUMBER_OF_PROBLEMS; i ++){
            problems[i] = Problematic.nextProblem();
        }

        if(RUN_SETTINGS == 1) {
            /* Part 1:
            * Sequentially: Don’t run the next instance until the one before has returned
            * */
            System.out.println("Starting part 1. Running sequentially.");

            for (int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
                Solver.findAndPrintSolution(problems[i], i);
            }
        }

        else if(RUN_SETTINGS == 2) {
        /* Part 2:
         * All at once in separate threads. A new thread is created for each instance.
         * */
            System.out.println("Starting part 2. Running all at once in separate threads.");

            for (int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
                new Thread(new ProblemRunner(problems[i], i)).start();
            }
        }

        else if(RUN_SETTINGS == 3) {
        /* Part 3:
         * A certain number at a time. Threads are run through a thread pool of a
         * certain size (students can try different sizes). New instances aren’t run
         * until a thread is free in the thread pool.
         * */
            System.out.println("Starting part 3. Pooling.");

            ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);

            for (int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
                threadPool.execute(new ProblemRunner(problems[i], i));
            }

            threadPool.shutdown();
        }
        else{
            System.out.println("Set the configure paramter RUN_SETTING to either 1, 2 " +
                    "or 3");
        }
    }
}
