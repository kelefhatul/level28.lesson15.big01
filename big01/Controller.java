package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.Provider;

import java.util.Arrays;

/**
 * Created by root on 11/13/2016.
 */
public class Controller {
    private Provider[] providers;

    public Controller(Provider[] providers) {
        this.providers = providers;
        if (providers == null || providers.length ==0){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }
}
