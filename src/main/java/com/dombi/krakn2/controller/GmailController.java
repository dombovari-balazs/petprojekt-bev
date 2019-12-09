package com.dombi.krakn2.controller;

import com.dombi.krakn2.service.GmailQuickstart;
import com.google.api.services.gmail.model.Message;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@AllArgsConstructor
@RestController
public class GmailController {
    public GmailQuickstart gmailQuickstart;

    @PostMapping("/oauth")
    public void setupServiceOauth() throws IOException, GeneralSecurityException {
        //gmailQuickstart.init();
    }
    @GetMapping("/list-messages/{query}")
    public List<List<String>> messageList(@PathVariable String query) throws IOException {
        List<Message> messages = gmailQuickstart.listMessagesMatchingQuery("me", query);
        List<String> collect = messages.stream()
                .map(Message::getThreadId)
                .collect(Collectors.toList());
        List<List<String>> me = collect.stream()
                .map(s -> {
                    try {
                        return gmailQuickstart.getThreadMessages("me", s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
        return me;
    }

    @GetMapping("/thread/{id}")
    public List<String> getThreadById(@PathVariable String id) throws IOException {
        return gmailQuickstart.getThreadMessages("me", id);
    }


}
