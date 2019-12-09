package com.dombi.krakn2.controller;

import com.dombi.krakn2.model.Wine;
import com.dombi.krakn2.model.WineFromNAV;
import com.dombi.krakn2.service.GmailQuickstart;
import com.dombi.krakn2.service.WineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
public class WineController {

    private WineService service;
    private GmailQuickstart gmail;

    @GetMapping("")
    public String hello(){
        return "Hello Wine";
    }

    @GetMapping("/example")
    public Wine exampleWine(){
        return service.getExampleWine();
    }

    @GetMapping("do-html-parse-list")
    public List<List<String>> doHtmlParseList() throws IOException {
        return service.htmlParseExample();
    }
    @GetMapping("do-html-parse-example")
    public List<WineFromNAV> doHtmlParseExample() throws IOException {
        return service.convertToWineFromNavList(service.htmlParseExample());
    }

    @GetMapping("do-html-parse")
    public List<WineFromNAV> doHtmlParse() throws IOException {
        return service.convertToWineFromNavList(service.htmlParse(gmail.getThreadMessages("me","16d96a79dfbee636").get(0)));
    }



}
