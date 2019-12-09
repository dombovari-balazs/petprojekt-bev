package com.dombi.krakn2.service;

import com.dombi.krakn2.model.WineFromNAV;
import com.dombi.krakn2.repository.WineFromNavRepository;
import com.dombi.krakn2.repository.WineRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dombi.krakn2.model.Wine;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class WineService {
    private WineFromNavRepository wineFromNavRepository;
    private WineRepository wineRepository;

    public Wine getExampleWine(){
        return Wine.builder().termekcsoport("peldabor").build();
    }

    public List<List<String>> htmlParseExample() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //org.jsoup.nodes.Document doc = Jsoup.connect("https://www.javatpoint.com/html-table1").get();
        File input = new File("/home/dombovari/codecool/PetProjects/Krakn/pelda.html");
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        org.jsoup.select.Elements tables = doc.select("table");
        Elements thList = tables.select("th");
        List<String> headers = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();


        org.jsoup.select.Elements rows = doc.select("tr");
        for(org.jsoup.nodes.Element row :rows) {
            org.jsoup.select.Elements tdColumns = row.select("td");
            ArrayList<String> columns  = new ArrayList<>();
            if(!tdColumns.isEmpty()) {
                for (org.jsoup.nodes.Element column:tdColumns) {
                    columns.add(column.text());
                }
            result.add(columns);}
        }
        return result;
    }
    public List<List<String>> htmlParse(String html) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Document doc = Jsoup.parse(html);
        org.jsoup.select.Elements tables = doc.select("table");

        List<String> headers = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();

        Elements thList = tables.select("th");
        org.jsoup.select.Elements rows = doc.select("tr");
        for(org.jsoup.nodes.Element row :rows) {
            org.jsoup.select.Elements tdColumns = row.select("td");
            ArrayList<String> columns  = new ArrayList<>();
            if(!tdColumns.isEmpty()) {
                for (org.jsoup.nodes.Element column:tdColumns) {
                    columns.add(column.text());
                }
                result.add(columns);}
        }
        return result;
    }


    public List<WineFromNAV> convertToWineFromNavList(List<List<String>> htmlParse) throws IOException {
        ArrayList<WineFromNAV> wineFromNAVArrayList = new ArrayList<>();
        for (List<String> wine: htmlParse){
            WineFromNAV wineFromNAV = convertToWineFromNav(wine);
            wineFromNavRepository.save(wineFromNAV);
            wineFromNAVArrayList.add(wineFromNAV);
        }
        return wineFromNAVArrayList;
    }

    private WineFromNAV convertToWineFromNav(List<String> wine) {
        if (wine.size() < 8){
            //log.warn("Couldn't create wine from: " + wine.toString());
            return WineFromNAV.builder().build();
        }
        return WineFromNAV.builder()
                .megnevezes(wine.get(1))
                .nettoEgysegar(convertWineStringToInt(wine.get(2)))
                .bruttoEgysegar(convertWineStringToInt(wine.get(3)))
                .mennyiseg(convertWineStringToInt(wine.get(4)))
                .fizetendo(convertWineStringToInt(wine.get(5)))
                .eredetiBruttoEgysegar(convertWineStringToInt(wine.get(6)))
                .osszBruttoJutalek(convertWineStringToInt(wine.get(7)))
                .build();
    }

    private int convertWineStringToInt(String detail) {
        String replaced = detail.replaceAll("[^0-9]", "");
        return Integer.parseInt(replaced);
    }
}
