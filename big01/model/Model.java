package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/14/2016.
 */
public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (view == null || providers == null || providers.length == 0){
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }
    public void selectCity(String city){
        List<Vacancy> listByCity = new ArrayList<>();
        for (Provider curProvider : providers
             ) {
            try {
                listByCity.addAll(curProvider.getJavaVacancies(city));
            } catch (NullPointerException e) {
                /*e.printStackTrace();*/
            }
        }
        view.update(listByCity);
    }
}
