package org.algotithmcontestdatacollect.displaybackend.service.recommend.handler;

import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CfAccountWithUsername;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CodeforcesProblemsWithTags;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.intelligentTraining.CfProblemNoTag;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesAccountWitheUsernameRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesOkSubmitWithTagsRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesProblemsWithTagsRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.CfProblemNoTagRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.NoRecommendContestRepository;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.Chain;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.RecommendationContext;
import org.algotithmcontestdatacollect.displaybackend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Order(2)
public class FilterProblemsHandler implements ChainHandler<RecommendationContext> {

    @Autowired
    NoRecommendContestRepository noRecommendContestRepository;
    @Autowired
    CfProblemNoTagRepository cfProblemNoTagRepository;
    @Autowired
    CodeforcesAccountWitheUsernameRepository codeforcesAccountWitheUsernameRepository;
    @Autowired
    CodeforcesProblemsWithTagsRepository codeforcesProblemsWithTagsRepository;
    @Autowired
    CodeforcesOkSubmitWithTagsRepository codeforcesOkSubmitWithTagsRepository;
    @Override
    public void handle(RecommendationContext context, Chain<RecommendationContext> chain) {
        List<CfAccountWithUsername> user = codeforcesAccountWitheUsernameRepository.getCfAccountWithUsernameEntitiesByUid(context.getUid());
        if(user.size() == 0) {
            context.setResponse(ResponseUtil.JSONReturn(404, "请添加cf账号"));
            return;
        }
        context.setCfAccountWithUsernameList(user);
        Set<String> doneProblems=codeforcesOkSubmitWithTagsRepository.getCodeforcesOkSubmitWithTagsEntitiesByUid(context.getUid()).stream().map(a->a.getCid()+a.getqIndex()).collect(Collectors.toSet());
        List<String> selectedProblems = new ArrayList<>();
        Set<Long> noRecommendContests = noRecommendContestRepository.findAll().stream().map(a -> a.getCid()).collect(Collectors.toSet());
        Map<String, Long> noTagHashMap = cfProblemNoTagRepository.findAll().stream().collect(Collectors.toMap(CfProblemNoTag::getCidQindex, CfProblemNoTag::getId));
        //没有标签的题目还有不推荐的比赛都不推荐给学生
        int maxRating = 800;
        for(var u : user) {
            int userRating= Optional.ofNullable(u.getRating()).orElse(800);
            maxRating = Math.max(maxRating, userRating);
        }
        context.setRating(maxRating);
        List<CodeforcesProblemsWithTags> problems = codeforcesProblemsWithTagsRepository.getCodeforcesProblemsWithTagsEntitiesByDifficultyBetween(maxRating - 200, maxRating+200);
        List<CodeforcesProblemsWithTags> filteredAbove = new ArrayList<>();
        List<CodeforcesProblemsWithTags> filteredBelow = new ArrayList<>();
        for (int i = 0; i < problems.size(); i++) {
            CodeforcesProblemsWithTags problem = problems.get(i);
            String problemDetail = problems.get(i).getCid() + problems.get(i).getQindex();
            if (noRecommendContests.contains(problems.get(i).getCid()) || noTagHashMap.containsValue(problems.get(i).getId()) || doneProblems.contains(problemDetail) || selectedProblems.contains(problemDetail)||problems.get(i).getTags()==null) {
                continue;
            }
            if (problem.getDifficulty() >= context.getRating()) {
                filteredAbove.add(problem);
            } else {
                filteredBelow.add(problem);
            }
        }
        context.setFinalProblems(new HashMap<>());
        context.setCoarseFiltrationProblemsAboveScore(filteredAbove);
        context.setCoarseFiltrationProblemsBelowScore(filteredBelow);
        chain.proceed(context); // 继续下一个处理器
    }
}
