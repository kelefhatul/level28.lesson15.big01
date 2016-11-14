package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/14/2016.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";



    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        try {
            Document doc;
            int pageNumber = 0;
            while (true) {

                doc = getDocument(searchString, pageNumber++);
                if (doc == null) break;

                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) break;

                for (Element curElement : elements
                        ) {
                    Vacancy vacancy = new Vacancy();
                    //title
                    String title = curElement.select("[data-qa=vacancy-serp__vacancy-title]").first().text();
                    vacancy.setTitle(title);
                    //salary
                    if (curElement.select("[data-qa=vacancy-serp__vacancy-compensation]").first() != null) {
                        String salary = curElement.select("[data-qa=vacancy-serp__vacancy-compensation]").first().text();
                        vacancy.setSalary(salary);
                    } else vacancy.setSalary("");
                    //city
                    String city = curElement.select("[data-qa=vacancy-serp__vacancy-address]").first().text();
                    vacancy.setCity(city);
                    //companyName
                    String companyName = curElement.select("[data-qa=vacancy-serp__vacancy-employer]").first().text();
                    vacancy.setCompanyName(companyName);
                    //siteName
                    vacancy.setSiteName("http://hh.ua");
                    //url
                    String URL = curElement.select("[data-qa=vacancy-serp__vacancy-title]").first().attr("href");
                    vacancy.setUrl(URL);

                    result.add(vacancy);
                }
            }
        } catch (Exception e){
            /*NOP*/
        }
        return result;
    }


    protected Document getDocument(String searchString, int page) throws IOException{
        String url = String.format(URL_FORMAT, searchString, page);
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                " Chrome/54.0.2840.87 Safari/537.36")
                .referrer("")
                .timeout(5 * 1000)
                .get();

        return doc;
    }
}
