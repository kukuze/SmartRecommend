package org.algotithmcontestdatacollect.displaybackend.service.recommend.handler;

import com.alibaba.fastjson.JSONObject;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.generateFuncs.IGenerateFunc;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.IntelligentTrainingQuestionsRepository;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.Chain;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.RecommendationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Order(3)
public class RecommendationAlgorithmHandler implements ChainHandler<RecommendationContext> {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void handle(RecommendationContext context, Chain<RecommendationContext> chain) {
        var useFuncs = context.getStrategy().getUseFunction();
        List<JSONObject> funcList = JSONObject.parseArray(useFuncs, JSONObject.class);
        Map<String, String>finalProblems=context.getFinalProblems();
        for (var func : funcList) {
            var geneFunc = applicationContext.getBean(func.getString("funcName"), IGenerateFunc.class);
            int num = func.getInteger("num");
            int countAboveScore = func.getIntValue("countAboveScore");
            if (countAboveScore<=0){
                countAboveScore=num/2;
            }
            if (countAboveScore>num){
                countAboveScore=num;
            }
            Map<String, String> generated = geneFunc.generate(num,countAboveScore, context);
            finalProblems.putAll(generated);
            // 移除已选择的题目
            context.getCoarseFiltrationProblemsAboveScore().removeIf(problem -> generated.keySet().contains(problem.getCid() + problem.getQindex()));
            context.getCoarseFiltrationProblemsBelowScore().removeIf(problem -> generated.keySet().contains(problem.getCid() + problem.getQindex()));
        }
        chain.proceed(context); // 继续下一个处理器
    }
}
