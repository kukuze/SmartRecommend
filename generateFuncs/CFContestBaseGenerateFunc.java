package org.algotithmcontestdatacollect.displaybackend.service.recommend.generateFuncs;

import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CfContestMaxsolve;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CodeforcesProblemsWithTags;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.*;
import org.algotithmcontestdatacollect.displaybackend.repository.UserRepository;
import org.algotithmcontestdatacollect.displaybackend.service.recommend.RecommendationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("cfContestBaseGenerateFunc")
public class CFContestBaseGenerateFunc implements IGenerateFunc {

    @Autowired
    CfContestMaxsolveRepository cfContestMaxsolveRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RatingBasedRandomGenerateFunc ratingBasedRandomGenerateFunc;
    @Autowired
    CodeforcesProblemsRepository codeforcesProblemsRepository;

    @Override
    public Map<String, String> generate(int n,int countAboveScore, RecommendationContext context){
        List<String> usernames = context.getCfAccountWithUsernameList().stream().map(a -> a.getUsername()).collect(Collectors.toList());
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "startTimeStamp"));
        Page<CfContestMaxsolve> page = cfContestMaxsolveRepository.findAll((root, query, cb) -> root.get("username").in(usernames), pageable);
        CfContestMaxsolve firstEntry = page.getContent().isEmpty() ? null : page.getContent().get(0);
        String lastMax;
        if (firstEntry==null) {
            //如果没参加过比赛则认为他上次参加的A
            lastMax="A";
        }else{
            lastMax=firstEntry.getMaxSolve();
        }
        Stream<CodeforcesProblemsWithTags> aboveScoreStream = context.getCoarseFiltrationProblemsAboveScore().stream()
                .filter(a -> a.getQindex().equals(lastMax))
                .sorted(Comparator.comparing(CodeforcesProblemsWithTags::getCid).reversed())
                .limit(countAboveScore);
        Stream<CodeforcesProblemsWithTags> belowScoreStream = context.getCoarseFiltrationProblemsBelowScore().stream()
                .filter(a -> a.getQindex().equals(lastMax))
                .sorted(Comparator.comparing(CodeforcesProblemsWithTags::getCid).reversed())
                .limit(n - countAboveScore);
        return Stream.concat(aboveScoreStream, belowScoreStream)
                .collect(Collectors.toMap(
                        problem -> problem.getCid() + problem.getQindex(), // Key
                        problem -> getClass().getSimpleName(), // Value
                        (existing, replacement) -> existing));
    }


}
