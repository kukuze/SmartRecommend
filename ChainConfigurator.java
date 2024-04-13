package org.algotithmcontestdatacollect.displaybackend.service.recommend;

import org.algotithmcontestdatacollect.displaybackend.service.recommend.handler.ChainHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChainConfigurator {

    @Autowired
    private List<ChainHandler<RecommendationContext>> handlers;

    public Chain<RecommendationContext> configureChain() {
        Chain<RecommendationContext> chain = new Chain<>();
        handlers.forEach(chain::addHandler);
        return chain;
    }
}
