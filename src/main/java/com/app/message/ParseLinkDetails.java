package com.app.message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by user1 on 10/06/2016.
 */
public class ParseLinkDetails {
    Document document;

    public ParseLinkDetails(String url) {
        document = parse(url);
    }

    private Document parse(String url){
        Document doc=null;
        try {
            doc = Jsoup.connect(url).get();

            System.out.println(doc.title());
            System.out.println(doc.body().text());

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return doc;
    }

    public String title(){
        if(document != null)
            return document.title();
        else return "undefined title";
    }

    public String description(){
        if(document != null)
            return document.title();
        else return "undefined description";
    }
}
