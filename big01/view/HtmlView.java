package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 11/14/2016.
 */
public class HtmlView implements View {
    private final String filePath = "./src/"+this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    Template template = new Template();

    private Controller controller;
    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }
    private String getUpdatedFileContent(List<Vacancy> vacancies){
        String fileData = "";
        try{
            //1
            Document doc = getDocument();
            Element templateElement = doc.select(".template").first();
            Element cloneTemplateElement = templateElement.clone();
            //2
            cloneTemplateElement.removeAttr("style");
            cloneTemplateElement.removeClass("template");
            //3
            doc.select("tr[class=vacancy]").remove();
            //4
            for (Vacancy vacancy : vacancies
                 ) {
                Element cloneFinal = cloneTemplateElement.clone();
                cloneFinal.getElementsByClass("city").first().text(vacancy.getCity());
                cloneFinal.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                cloneFinal.getElementsByClass("salary").first().text(vacancy.getSalary());

                Element link = cloneFinal.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateElement.before(cloneFinal.outerHtml());
            }
            fileData = doc.html();


        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }
        return fileData;
    }
    private void updateFile(String content){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected Document getDocument() throws IOException{
        Document html = Jsoup.parse(new File(filePath), "UTF-8");
        return html;
    }
}
