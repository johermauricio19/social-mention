package com.appgate.service;


import com.appgate.dto.SocialMention;
import com.appgate.model.AnalysisResult;
import com.appgate.model.SocialPlatform;
import jakarta.inject.Singleton;

@Singleton
public class AnalysisService {

    private final FacebookService facebookService;
    private final TweeterService tweeterService;

    public AnalysisService(FacebookService facebookService, TweeterService tweeterService) {
        this.facebookService = facebookService;
        this.tweeterService = tweeterService;
    }

    public AnalysisResult analyzeMention(SocialMention mention) {
        SocialPlatform platform = resolvePlatform(mention);

        switch (platform) {
            case FACEBOOK:
                return facebookService.analyze(mention);
            case TWEETER:
                return tweeterService.analyze(mention);
            default:
                throw new IllegalArgumentException("Unsupported platform");
        }
    }

    private SocialPlatform resolvePlatform(SocialMention mention) {
        if (mention.getFacebookAccount() != null) return SocialPlatform.FACEBOOK;
        if (mention.getTweeterAccount() != null) return SocialPlatform.TWEETER;
        throw new IllegalArgumentException("A Facebook or Tweeter account must be present");
    }
}