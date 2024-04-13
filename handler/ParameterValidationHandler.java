package org.algotithmcontestdatacollect.displaybackend.service.recommend.handler;

import org.algotithmcontestdatacollect.displaybackend.tableEntity.intelligentTraining.IntelligentTraining;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.intelligentTraining.IntelligentTrainingStrategy;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.IntelligentTrainingRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.IntelligentTrainingStrategyRepository;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.Chain;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.RecommendationContext;
import org.algotithmcontestdatacollect.displaybackend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Order(1)
public class ParameterValidationHandler implements ChainHandler<RecommendationContext> {
    @Autowired
    private IntelligentTrainingRepository intelligentTrainingRepository;
    @Autowired
    private IntelligentTrainingStrategyRepository intelligentTrainingStrategyRepository;
    @Override
    public void handle(RecommendationContext context, Chain<RecommendationContext> chain) {
        Optional<IntelligentTrainingStrategy> strategy;
        if(context.getTid()!=null){
            Optional<IntelligentTraining> training = intelligentTrainingRepository.findById(context.getTid());
            if (training.isEmpty()) {
                context.setResponse(ResponseUtil.JSONReturn(404, "不存在该比赛"));
                return;
            }
            Integer sid = training.get().getStrategyId();
            strategy = intelligentTrainingStrategyRepository.findById(sid);
            if (strategy.isEmpty()) {
                context.setResponse(ResponseUtil.JSONReturn(404, "不存在该策略"));
                return;
            }else{
                context.setStrategy(strategy.get());
            }
        }
        chain.proceed(context);
    }
}
