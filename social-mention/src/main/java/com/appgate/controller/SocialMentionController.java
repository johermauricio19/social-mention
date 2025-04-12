package com.appgate.controller;

import com.appgate.dto.SocialMention;
import com.appgate.model.AnalysisResult;
import com.appgate.service.AnalysisService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import jakarta.inject.Inject;

@Controller("/social-mention")
public class SocialMentionController {

    @Inject
    private AnalysisService analysisService;

    @Post("/analyze")
    @Produces(MediaType.TEXT_PLAIN)
    public String analyze(@Body SocialMention mention) {
        try {
            AnalysisResult result = analysisService.analyzeMention(mention);
            return result.name(); // HIGH_RISK, MEDIUM_RISK, etc.
        } catch (IllegalArgumentException ex) {
            return "Error: " + ex.getMessage();
        }
    }
}
