package com.ru.usty.threads;

/**
 * A simple class to facilitate the running of specific problems on threads
 *
 * @author      Asa Bjork Valdimarsdottir
 * @author      Vedis Erna Eyjolfsdottir
 * @since       05-Feb-16.
 */
public class ProblemRunner implements Runnable {

    Problem problem;
    int nr;

    public ProblemRunner(Problem p, int n){
        this.problem = p;
        this.nr = n;
    }

    @Override
    public void run() {
        Solver.findAndPrintSolution(this.problem, nr);
    }
}
