package org.algotithmcontestdatacollect.displaybackend.service.recommend.generateFuncs;

import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CodeforcesProblemsWithTags;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesOkSubmitWithTagsRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesProblemsWithTagsRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.CfProblemNoTagRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.NoRecommendContestRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.ProblemTagRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.UserRepository;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.RecommendationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("lessTagSolveBasedGenerateFunc")
public class LessTagSolveBasedGenerateFunc implements IGenerateFunc{
    private static Logger logger = LoggerFactory.getLogger(LessTagSolveBasedGenerateFunc.class);
    @Autowired
    CodeforcesOkSubmitWithTagsRepository codeforcesOkSubmitWithTagsRepository;
    @Autowired
    ProblemTagRepository problemTagRepository;
    @Autowired
    CodeforcesProblemsWithTagsRepository codeforcesProblemsWithTagsRepository;
    @Autowired
    NoRecommendContestRepository noRecommendContestRepository;
    @Autowired
    CfProblemNoTagRepository cfProblemNoTagRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Map<String, String> generate(int n,int countAboveScore, RecommendationContext context){
        var problems = codeforcesOkSubmitWithTagsRepository.getCodeforcesOkSubmitWithTagsEntitiesByUid(context.getUid());
        var tags = problemTagRepository.findAll();
        var tagMap = new HashMap<String,Integer>();
        for(var tag : tags) {
            tagMap.put(tag.getTagName(),0);
        }
        for(var problem : problems) {
            if(problem.getTags() == null) continue;
            for(var tag : problem.getTags().split(",")) {
                tagMap.put(tag,tagMap.get(tag) + 1);
            }
        }
        // 排序tagMap 并选择最小的n个
        List<Map.Entry<String,Integer>> list=new ArrayList<>(tagMap.entrySet());
        Collections.shuffle(list);
        list = new ArrayList<>(list.subList(0, Math.min(n, list.size())));
        List<String> targetTagKey = list.stream().map(a -> a.getKey()).collect(Collectors.toList());

        Stream<CodeforcesProblemsWithTags> aboveScoreStream = context.getCoarseFiltrationProblemsAboveScore().stream()
                .filter(problem -> {
                    List<String> problemTags = Arrays.asList(problem.getTags().split(","));
                    return problemTags.stream().anyMatch(targetTagKey::contains);
                })
                .sorted(Comparator.comparing(CodeforcesProblemsWithTags::getCid).reversed())
                .limit(countAboveScore);
        Stream<CodeforcesProblemsWithTags> belowScoreStream = context.getCoarseFiltrationProblemsBelowScore().stream()
                .filter(problem -> {
                    List<String> problemTags = Arrays.asList(problem.getTags().split(","));
                    return problemTags.stream().anyMatch(targetTagKey::contains);
                })
                .sorted(Comparator.comparing(CodeforcesProblemsWithTags::getCid).reversed())
                .limit(n - countAboveScore);
        return Stream.concat(aboveScoreStream, belowScoreStream)
                .collect(Collectors.toMap(
                        problem -> problem.getCid() + problem.getQindex(), // Key
                        problem -> getClass().getSimpleName(), // Value
                        (existing, replacement) -> existing));
    }
}
