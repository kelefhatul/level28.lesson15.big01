package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.MoikrugStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;

/**
 * Created by root on 11/13/2016.
 */
public class Aggregator {
    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        Provider HHProvider = new Provider(new HHStrategy());
        Provider moiKrugProvider = new Provider(new MoikrugStrategy());
        Model model = new Model(view, new Provider[]{HHProvider, moiKrugProvider});
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();






    }
}
