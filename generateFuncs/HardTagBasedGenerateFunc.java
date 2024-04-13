package org.algotithmcontestdatacollect.displaybackend.service.recommend.generateFuncs;

import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CodeforcesProblemsWithTags;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.ProblemTag;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesOkSubmitWithTagsRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesProblemsWithTagsRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.CfProblemNoTagRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.NoRecommendContestRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.ProblemTagRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.UserRepository;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.RecommendationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("hardTagBasedFunc")
public class HardTagBasedGenerateFunc implements IGenerateFunc {
    @Autowired
    CodeforcesOkSubmitWithTagsRepository codeforcesOkSubmitWithTagsRepository;
    @Autowired
    CodeforcesProblemsWithTagsRepository codeforcesProblemsWithTagsRepository;
    @Autowired
    NoRecommendContestRepository noRecommendContestRepository;
    @Autowired
    ProblemTagRepository problemTagRepository;
    @Autowired
    CfProblemNoTagRepository cfProblemNoTagRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Map<String, String> generate(int n,int countAboveScore, RecommendationContext context){
        var problems = codeforcesOkSubmitWithTagsRepository.getCodeforcesOkSubmitWithTagsEntitiesByUid(context.getUid());
        var tagMap = new HashMap<String, Integer>();
        for (var problem : problems) {
            if (problem.getTags() == null) continue;
            for (var tag : problem.getTags().split(",")) {
                if (!tagMap.containsKey(tag)) {
                    tagMap.put(tag, 600);
                }
                tagMap.put(tag, Math.max(tagMap.get(tag), problem.getDifficulty()));
            }
        }
        //假设只做过3种专题题目，则无法生成5道题，补充2个专题！
        if (tagMap.size() < n) {
            List<ProblemTag> all = problemTagRepository.findAll();
            int length = all.size();
            for (int j = 0; j < length && tagMap.size() < n; j++) {
                int randomElementIndex
                        = new Random().nextInt(all.size()) % all.size();
                ProblemTag remove = all.remove(randomElementIndex);
                if (tagMap.containsKey(remove.getTagName())) {
                    continue;
                }
                tagMap.put(remove.getTagName(), 0);
            }
        }

        List<String> targetTagKey = new ArrayList<>(tagMap.keySet());
        Collections.shuffle(targetTagKey);
        targetTagKey = new ArrayList<>(targetTagKey.subList(0, Math.min(n, targetTagKey.size())));
        List<String> finalTargetTagKey = targetTagKey;
        Stream<CodeforcesProblemsWithTags> aboveScoreStream = context.getCoarseFiltrationProblemsAboveScore().stream()
                .filter(problem -> {
                    List<String> problemTags = Arrays.asList(problem.getTags().split(","));
                    return problemTags.stream().anyMatch(finalTargetTagKey::contains);
                })
                .sorted(Comparator.comparing(CodeforcesProblemsWithTags::getCid).reversed())
                .limit(countAboveScore);
        Stream<CodeforcesProblemsWithTags> belowScoreStream = context.getCoarseFiltrationProblemsBelowScore().stream()
                .filter(problem -> {
                    List<String> problemTags = Arrays.asList(problem.getTags().split(","));
                    return problemTags.stream().anyMatch(finalTargetTagKey::contains);
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
