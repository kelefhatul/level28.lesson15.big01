package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 11/13/2016.
 */
public class Controller {
    private Provider[] providers;

    public Controller(Provider... providers) {
        if (providers == null || providers.length ==0){
            throw new IllegalArgumentException();
        }
        this.providers = providers;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }

    public void scan() {
        List<Vacancy> result = new ArrayList<>();

            for (Provider curProvider: providers
                 ) {
                for (Vacancy curVacancy : curProvider.getJavaVacancies("TO DO")
                     ) {
                    result.add(curVacancy);
                }
            }

        System.out.println(result.size());
    }
}
