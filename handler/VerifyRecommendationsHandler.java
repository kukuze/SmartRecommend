package org.algotithmcontestdatacollect.displaybackend.service.recommend.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.intelligentTraining.IntelligentTrainingQuestions;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.generateFuncs.RatingBasedRandomGenerateFunc;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.IntelligentTrainingQuestionsRepository;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.Chain;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.RecommendationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.IntStream;

@Service
@Order(4)
public class VerifyRecommendationsHandler implements ChainHandler<RecommendationContext> {
    @Autowired
    RatingBasedRandomGenerateFunc ratingBasedRandomGenerateFunc;
    @Autowired
    private IntelligentTrainingQuestionsRepository intelligentTrainingQuestionsRepository;
    @Override
    public void handle(RecommendationContext context, Chain<RecommendationContext> chain) {
        int size = context.getFinalProblems().size();
        JSONArray jsonArray = JSONArray.parseArray(context.getStrategy().getUseFunction());

        int needProblemNum = IntStream.range(0, jsonArray.size())
                .mapToObj(index -> jsonArray.getJSONObject(index))
                .mapToInt(obj -> obj.getIntValue("num"))
                .sum();
        if (size < needProblemNum) {
            Map<String, String> generate = ratingBasedRandomGenerateFunc.generate(needProblemNum - size,needProblemNum - size, context);
            context.getFinalProblems().putAll(generate);
        }
        //没有训练id，则认为是智能推荐，智能推荐不保存记录
        if(context.getTid()!=null){
            var newItem = new IntelligentTrainingQuestions();
            newItem.setTid(context.getTid());
            newItem.setUid(context.getUid());
            newItem.setProblems(JSON.toJSONString(context.getFinalProblems().keySet()));
            newItem.setGenerateIds(JSON.toJSONString(context.getFinalProblems().values()));
            intelligentTrainingQuestionsRepository.save(newItem);
        }

    }
}
