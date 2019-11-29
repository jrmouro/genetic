/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.test;

import com.jrmouro.genetic.chromosome.ValidityRepresentation;
import com.jrmouro.genetic.evolutionstrategies.evolution.EvolutionScoutSniffer;
import com.jrmouro.genetic.similarity.SimilarityEvolution;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author ronaldo
 */
public class SimilarityEvolutionJUnitTest {

    @Test
    public void test() {

        /* 
            
            Dois alunos, Pedro e Jonas, ao longo do curso
            obtiveram notas conforme a matriz 'notas' e resultado
            final conforme o vetor 'result'. Sabendo-se que as notas
            possuem pesos diferentes no cálculo do resultado final,
            como os demais alunos poderiam saber seu resultado sem
            conhecer tais pesos uma vez que só conhecem o resultado
            final de Pedro e Jonas?
        
        
         */
        double[][] notas = {
            {5.0, 4.0, 7.0, 6.0}, //Pedro
            {8.0, 10.0, 2.0, 6.0}, //Jonas
        };

        double[] result = {
            6.0, //Pedro
            5.0 //Jonas
        };

        SimilarityEvolution se = new SimilarityEvolution(
                new EvolutionScoutSniffer(200, 0.01),
                notas,
                result,
                2000,
                0.1,
                true,
                new ValidityRepresentation<Double>() {
                    @Override
                    public boolean isValid(List<Double> representation) {
                        
                        for (Double d : representation)
                            if(d < 0.0 || d > 1.0)
                                return false;
                        
                        
                        return true;
                    }

                }
        );
        
        
        se.run();
        
        
        double[] aval1 = {5.0, 4.0, 7.0, 6.0};//Pedro
        double[] aval2 = {8.0, 10.0, 2.0, 6.0};//Jonas
        
        
        double[] aval3 = {6.0, 6.0, 6.0, 6.0};//Augusto
        double[] aval4 = {6.0, 7.0, 5.0, 6.0};//Carla
        
        
        System.out.println("Pedro: " + se.getSimilarity(aval1));
        System.out.println("Jonas: " + se.getSimilarity(aval2));
        System.out.println("Augusto: " + se.getSimilarity(aval3));
        System.out.println("Carla: " + se.getSimilarity(aval4));

    }
}
