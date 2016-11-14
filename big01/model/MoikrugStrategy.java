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
public class MoikrugStrategy implements Strategy{
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        try{
            Document doc;
            int pageNumber=1;
            while (true){
                doc = getDocument(searchString, pageNumber++);
                if (doc == null) break;

                Elements elements = doc.select("[id^=job_]");
                if (elements.size() == 0) break;

                for ( Element curElement : elements
                     ) {
                    Vacancy vacancy = new Vacancy();
                    //title
                    String tittle = curElement.select("[class=title]").first().text();
                    vacancy.setTitle(tittle);
                    //salary
                    if (curElement.select("[class=salary]").first() != null){
                        String salary = curElement.select("[class=salary]").first().text();
                        vacancy.setSalary(salary);
                    } else vacancy.setSalary("");
                    //city
                    if (curElement.select("[class=location]").first() != null) {
                        String city = curElement.select("[class=location]").first().text();
                        vacancy.setCity(city);
                    } else vacancy.setCity("");
                    //companyName
                    String companyName= curElement.select("[class=company_name]").first().text();
                    vacancy.setCompanyName(companyName);
                    //siteName
                    vacancy.setSiteName("https://moikrug.ru");
                    //url
                    String URL = vacancy.getSiteName()+curElement.select("[class=job_icon]").first().attr("href");
                    vacancy.setUrl(URL);


                    result.add(vacancy);
                }

            }

        } catch (IOException e) {
            /*NOP*/
        }
        return result;
    }


    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        org.jsoup.nodes.Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                        " Chrome/54.0.2840.87 Safari/537.36")
                .referrer("")
                .get();

        return doc;
    }
}
