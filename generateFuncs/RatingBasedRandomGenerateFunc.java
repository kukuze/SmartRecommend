package org.algotithmcontestdatacollect.displaybackend.service.recommend.generateFuncs;

import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CodeforcesProblemsWithTags;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesAccountWitheUsernameRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesProblemsRepository;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.RecommendationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("ratingBasedRandomGenerateFunc")
public class RatingBasedRandomGenerateFunc implements IGenerateFunc {
    @Autowired
    CodeforcesAccountWitheUsernameRepository codeforcesAccountWitheUsernameRepository;
    @Autowired
    CodeforcesProblemsRepository codeforcesProblemsRepository;

    @Override
    public Map<String, String> generate(int n, int countAboveScore, RecommendationContext context) {
        Predicate<CodeforcesProblemsWithTags> predicate = a -> a.getDifficulty() > (context.getRating() - 100) && a.getDifficulty() <= (context.getRating() + 100);
        Stream<CodeforcesProblemsWithTags> aboveScoreStream = context.getCoarseFiltrationProblemsAboveScore().stream()
                .filter(predicate)
                .sorted(Comparator.comparing(CodeforcesProblemsWithTags::getCid).reversed())
                .limit(countAboveScore);
        Stream<CodeforcesProblemsWithTags> belowScoreStream = context.getCoarseFiltrationProblemsBelowScore().stream()
                .filter(predicate)
                .sorted(Comparator.comparing(CodeforcesProblemsWithTags::getCid).reversed())
                .limit(n - countAboveScore);
        return Stream.concat(aboveScoreStream, belowScoreStream)
                .collect(Collectors.toMap(
                        problem -> problem.getCid() + problem.getQindex(), // Key
                        problem -> getClass().getSimpleName(), // Value
                        (existing, replacement) -> existing));
    }


}
