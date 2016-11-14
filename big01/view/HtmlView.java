package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by root on 11/14/2016.
 */
public class HtmlView implements View {
    private final String filePath = "./src/"+this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";



    private Controller controller;
    @Override
    public void update(List<Vacancy> vacancies) {
        System.out.println(filePath);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }
    private String getUpdatedFileContent(List<Vacancy> vacancies){
        return "";
    }
    private void updateFile(String name){

    }
}
