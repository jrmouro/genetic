/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import com.jrmouro.genetic.chromosome.ChromosomeValidity;
import com.jrmouro.genetic.chromosome.ValidityRepresentation;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ListPopulation;
import org.apache.commons.math3.genetics.Population;

/**
 *
 * @author ronaldo
 */
public class IntegerPopulation extends ListPopulation {

    final int reuse;
    //Chromosome best = null;

    public IntegerPopulation(int reuse, int populationLimit) throws NotPositiveException {
        super(populationLimit);
        this.reuse = reuse;
    }

    public IntegerPopulation(int reuse, List<Chromosome> chromosomes, int populationLimit) throws NullArgumentException, NotPositiveException, NumberIsTooLargeException {
        super(chromosomes, populationLimit);
        this.reuse = reuse;
    }

    @Override
    public Population nextGeneration() {

        Population ret = new IntegerPopulation(this.reuse, this.getPopulationLimit());

        Collections.sort(this.getChromosomeList());

        Collections.reverse(this.getChromosomeList());

        int n = this.getChromosomeList().size();

        int size = n / 2 * (n + 1);
        if (n % 2 == 1) {
            size += ((n + 1) / 2);
        }

        Random rnd = new Random();

        for (int i = 0; i < this.reuse && i < this.getPopulationLimit(); i++) {

            int r = rnd.nextInt(size);
            int u = 0;
            int x = n + u;

            while (x <= r) {
                x += (n - (++u));
            }

            ret.addChromosome(this.getChromosomeList().get(u));

        }
        
        

        if (ret.getFittestChromosome().getFitness() < this.getFittestChromosome().getFitness()) {
            ret.addChromosome(this.getFittestChromosome());
        }

        return ret;
    }

    public static IntegerPopulation getRandom(
            int size, 
            int reuse, 
            int populationLimit, 
            FitnessFunction<Integer> fitnessFunction, 
            int sizeChromosome, 
            int leftBound, 
            int rightBound,
            ValidityRepresentation<Integer> validityRepresentation) {
        
        List<Chromosome> ret = new ArrayList();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            ret.add(IntegerChromosome.getRandom(fitnessFunction, sizeChromosome, leftBound, rightBound, validityRepresentation));
        }
        return new IntegerPopulation(reuse, ret, populationLimit);
    }

    @Override
    public String toString() {

        String ret = "Fitness: " + this.getFittestChromosome().getFitness() + "\n";

        for (Chromosome chromosome : this.getChromosomes()) {
            ret += "\t" + chromosome.toString() + "\n";
        }

        return ret; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addChromosome(Chromosome chromosome) throws NumberIsTooLargeException {

        if (!(chromosome instanceof IntegerChromosome)) {
            try {
                throw new Exception("IntegerPopulation works only with IntegerChromosome");
            } catch (Exception ex) {
                Logger.getLogger(IntegerPopulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (((IntegerChromosome) chromosome).isValid())
            super.addChromosome(chromosome);
        

    }

}
