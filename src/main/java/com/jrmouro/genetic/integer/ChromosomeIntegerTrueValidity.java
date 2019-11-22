/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import java.util.List;

/**
 *
 * @author ronaldo
 */
public class ChromosomeIntegerTrueValidity extends ChromosomeAbstractValidity<Integer> {
    
    @Override
    public boolean isRepresentationValid(List<Integer> representation) {
        return true;
    }
}
