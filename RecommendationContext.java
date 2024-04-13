package org.algotithmcontestdatacollect.displaybackend.service.recommend;

import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CfAccountWithUsername;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CodeforcesProblemsWithTags;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.intelligentTraining.IntelligentTrainingStrategy;

import java.util.List;
import java.util.Map;

/**
 * @author yjz
 * @date 2024/2/23$ 20:57$
 * @description:
 */
public class RecommendationContext {

    private Long tid;
    private Long uid;
    private List<CfAccountWithUsername>cfAccountWithUsernameList;
    private Integer rating;
    private String response;
    private Map<String,String> finalProblems;
    private IntelligentTrainingStrategy strategy;
    private List<CodeforcesProblemsWithTags> coarseFiltrationProblemsAboveScore;
    private List<CodeforcesProblemsWithTags> coarseFiltrationProblemsBelowScore;


    public List<CfAccountWithUsername> getCfAccountWithUsernameList() {
        return cfAccountWithUsernameList;
    }

    public void setCfAccountWithUsernameList(List<CfAccountWithUsername> cfAccountWithUsernameList) {
        this.cfAccountWithUsernameList = cfAccountWithUsernameList;
    }

    public Map<String, String> getFinalProblems() {
        return finalProblems;
    }

    public void setFinalProblems(Map<String, String> finalProblems) {
        this.finalProblems = finalProblems;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public IntelligentTrainingStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IntelligentTrainingStrategy strategy) {
        this.strategy = strategy;
    }

    public List<CodeforcesProblemsWithTags> getCoarseFiltrationProblemsAboveScore() {
        return coarseFiltrationProblemsAboveScore;
    }

    public void setCoarseFiltrationProblemsAboveScore(List<CodeforcesProblemsWithTags> coarseFiltrationProblemsAboveScore) {
        this.coarseFiltrationProblemsAboveScore = coarseFiltrationProblemsAboveScore;
    }

    public List<CodeforcesProblemsWithTags> getCoarseFiltrationProblemsBelowScore() {
        return coarseFiltrationProblemsBelowScore;
    }

    public void setCoarseFiltrationProblemsBelowScore(List<CodeforcesProblemsWithTags> coarseFiltrationProblemsBelowScore) {
        this.coarseFiltrationProblemsBelowScore = coarseFiltrationProblemsBelowScore;
    }
}
