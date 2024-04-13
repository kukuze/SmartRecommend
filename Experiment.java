package org.algotithmcontestdatacollect.displaybackend.service.recommend;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.algotithmcontestdatacollect.displaybackend.repository.UserRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CfSolveSituationRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CfSubmitWithUserinfoRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesOkSubmitWithTagsRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.cfRepository.CodeforcesProblemsWithTagsRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.IntelligentTrainingGenerateFunctionRepository;
import org.algotithmcontestdatacollect.displaybackend.repository.intelligentTraining.IntelligentTrainingQuestionsRepository;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CfSolveSituation;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CfSubmitWithUserinfo;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CodeforcesOkSubmitWithTags;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.codeforcesEntity.CodeforcesProblemsWithTags;
import org.algotithmcontestdatacollect.displaybackend.tableEntity.intelligentTraining.IntelligentTrainingQuestions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class Experiment {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    CfSolveSituationRepository cfSolveSituationRepository;
    @Autowired
    IntelligentTrainingQuestionsRepository intelligentTrainingQuestionsRepository;
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    CfSubmitWithUserinfoRepository cfSubmitWithUserinfoRepository;
    @Autowired
    IntelligentTrainingGenerateFunctionRepository intelligentTrainingGenerateFunctionRepository;
    @Autowired
    CodeforcesProblemsWithTagsRepository codeforcesProblemsWithTagsRepository;
    @Autowired
    CodeforcesOkSubmitWithTagsRepository codeforcesOkSubmitWithTagsRepository;
    @Autowired
    UserRepository userRepository;



    /**
     * description:各个策略完成率
     * author:yjz
     *
     * @return void
     */
    @Test
    void contextLoads() {
        List<IntelligentTrainingQuestions> trainingQuestionsList = intelligentTrainingQuestionsRepository.findAll();
        List<CfSolveSituation> cfSolveSituationList = cfSolveSituationRepository.findAll();
        Map<String, CfSolveSituation> solveSituationMap = cfSolveSituationList.stream().collect(Collectors.toMap(a -> a.getUid() + "-" + a.getPid(), a -> a));
        //过滤掉只参加了智能训练但没做过的人的那次记录
        List<IntelligentTrainingQuestions> collect = trainingQuestionsList.stream().filter(new Predicate<IntelligentTrainingQuestions>() {
            @Override
            public boolean test(IntelligentTrainingQuestions intelligentTrainingQuestions) {
                JSONArray jsonArray = JSONArray.parseArray(intelligentTrainingQuestions.getProblems());
                for (Object problem : jsonArray) {
                    String pidKey = problem.toString();
                    // 查找solveSituationMap，如果找不到相应记录 则过滤掉
                    CfSolveSituation solveSituation = solveSituationMap.get(intelligentTrainingQuestions.getUid() + "-" + pidKey);
                    if (solveSituation != null) {
                        return true;
                    }
                }
                return false;
            }
        }).collect(Collectors.toList());
        Map<String, Integer> strategyDoingNumMap = new HashMap<>();//做了的记录
        HashMap<String, Integer> recommendNumMap = new HashMap<>();//推荐了多少
        HashMap<String, Integer> strategyDoingAndOkNumMap = new HashMap<>();//解题成功的记录
        for (int i = 0; i < collect.size(); i++) {
            System.out.println("处理第" + (i + 1) + "个训练" + "共" + collect.size() + "个");
            IntelligentTrainingQuestions cur = collect.get(i);
            Long uid = cur.getUid();
            List<CfSubmitWithUserinfo> cfSubmitWithUserinfosByUid = cfSubmitWithUserinfoRepository.getCfSubmitWithUserinfosByUid(uid);
            List<String> stuDoingList = cfSubmitWithUserinfosByUid.stream().map(a -> a.getCid() + a.getqIndex()).collect(Collectors.toList());
            Set<String> stuOkList = cfSubmitWithUserinfosByUid.stream().filter(a -> a.getStatus().equals("OK")).map(a -> a.getCid() + a.getqIndex()).collect(Collectors.toSet());
            JSONArray problemArray = JSONArray.parseArray(cur.getProblems());
            JSONArray strategy = JSONArray.parseArray(cur.getGenerateIds());
            for (int j = 0; j < problemArray.size(); j++) {
                if (stuDoingList.contains(problemArray.get(j))) {
                    if (!strategyDoingNumMap.containsKey(strategy.getString(j))) {
                        strategyDoingNumMap.put(strategy.getString(j), 0);
                    }
                    strategyDoingNumMap.put(strategy.getString(j), strategyDoingNumMap.get(strategy.getString(j)) + 1);
                }
                if (stuOkList.contains(problemArray.get(j))) {
                    if (!strategyDoingAndOkNumMap.containsKey(strategy.getString(j))) {
                        strategyDoingAndOkNumMap.put(strategy.getString(j), 0);
                    }
                    strategyDoingAndOkNumMap.put(strategy.getString(j), strategyDoingAndOkNumMap.get(strategy.getString(j)) + 1);
                }
                if (!recommendNumMap.containsKey(strategy.getString(j))) {
                    recommendNumMap.put(strategy.getString(j), 0);
                }
                recommendNumMap.put(strategy.getString(j), recommendNumMap.get(strategy.getString(j)) + 1);
            }
        }
        Set<Map.Entry<String, Integer>> entries = strategyDoingAndOkNumMap.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String strategyName = entry.getKey();
            Integer okNum = entry.getValue();
            Integer doingNum = strategyDoingNumMap.get(strategyName);
            Integer recommendNum = recommendNumMap.get(strategyName);
            System.out.println(strategyName + "解题正确率" + (double) okNum / doingNum);
            System.out.println(strategyName + "吸引率" + (double) doingNum / recommendNum);
        }
    }

    /**
     * description:每个类型数量
     * author:yjz
     */
    @Test
    void tagNum() {
        List<CodeforcesProblemsWithTags> all = codeforcesProblemsWithTagsRepository.findAll();
        Map<String, Long> tagCount = all.stream()
                .flatMap(problem -> {
                    String tags = problem.getTags();
                    return Optional.ofNullable(tags).map(t -> Arrays.stream(t.split(","))).orElseGet(Stream::empty);
                })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

// 将map转换为stream，然后排序
        List<Map.Entry<String, Long>> sortedTagCount = tagCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()) // 按值升序排序
                .collect(Collectors.toList());

// 遍历并打印排序后的结果
        sortedTagCount.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    /**
     * description:每年做题平均分
     * author:yjz
     */
    @Test
    void avgRatingPerYear() {
        List<CodeforcesOkSubmitWithTags> all = codeforcesOkSubmitWithTagsRepository.findAll();
        Map<String, Optional<CodeforcesOkSubmitWithTags>> collect = all.stream()
                .filter(a -> a.getDifficulty() != null && a.getDifficulty() != 0)
                .collect(Collectors.groupingBy(
                        a -> a.getUid() + "-" + a.getCid() + "-" + a.getqIndex(),
                        Collectors.minBy(Comparator.comparing(a -> a.getFirstSubmitTime()))
                ));

        HashMap<String, Long> yearSubmitSum = new HashMap<>();
        HashMap<String, Long> yearDiffSum = new HashMap<>();
        HashMap<String, Double> yearAvgDiff = new HashMap<>();

        Set<Map.Entry<String, Optional<CodeforcesOkSubmitWithTags>>> entries = collect.entrySet();
        for (Map.Entry<String, Optional<CodeforcesOkSubmitWithTags>> entry : entries) {
            Optional<CodeforcesOkSubmitWithTags> value = entry.getValue();
            CodeforcesOkSubmitWithTags codeforcesOkSubmitWithTags = value.get();
            Long firstSubmitTime = codeforcesOkSubmitWithTags.getFirstSubmitTime();
            int year = getYearFromTimestamp(firstSubmitTime);
            Integer difficulty = codeforcesOkSubmitWithTags.getDifficulty();
            String yearKey = String.valueOf(year);
            yearSubmitSum.put(yearKey, yearSubmitSum.getOrDefault(yearKey, 0L) + 1);

            // Update yearDiffSum
            yearDiffSum.put(yearKey, yearDiffSum.getOrDefault(yearKey, 0L) + difficulty);
        }

        for (String yearKey : yearSubmitSum.keySet()) {
            Long submitCount = yearSubmitSum.getOrDefault(yearKey, 0L);
            Long diffSum = yearDiffSum.getOrDefault(yearKey, 0L);

            // Avoid division by zero
            if (submitCount != 0) {
                double avgDiff = (double) diffSum / submitCount;
                yearAvgDiff.put(yearKey, avgDiff);
            } else {
                yearAvgDiff.put(yearKey, 0.0); // If no submissions, average difficulty is 0
            }
        }

        // Sorting yearAvgDiff entries by year
        List<Map.Entry<String, Double>> sortedEntries = new ArrayList<>(yearAvgDiff.entrySet());
        sortedEntries.sort(Comparator.comparing(Map.Entry::getKey));

        // Printing out the results in ascending order of year
        for (Map.Entry<String, Double> entry : sortedEntries) {
            System.out.println("Year: " + entry.getKey() +"submitNum: "+yearSubmitSum.get(entry.getKey())+", Average Difficulty: " + entry.getValue());
        }

    }

    private static int getYearFromTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp); // Convert timestamp to Instant
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); // Convert Instant to LocalDateTime
        return dateTime.getYear(); // Extract year from LocalDateTime
    }

    /**
     * description:做了我的应用，分数提升。
     * author:yjz
     */
    @Test
    void doRecommand(){
        List<Long> stuList = userRepository.findAll().stream().filter(a->a.getYear()>=2021).map(a->a.getId()).collect(Collectors.toList());
        List<IntelligentTrainingQuestions> all = intelligentTrainingQuestionsRepository.findAll().stream().filter(a->stuList.contains(a.getUid())).collect(Collectors.toList());
        Map<Long, Set<String>> recommendMap = new HashMap<>();
        System.out.println("获取推荐过的题目");
        for (int i = 0; i < all.size(); i++) {
            IntelligentTrainingQuestions temp = all.get(i);
            Long uid = temp.getUid();
            String problems = temp.getProblems();
            List<String> problemList = JSONObject.parseArray(problems, String.class);
            recommendMap.computeIfAbsent(uid,k->new HashSet<>()).addAll(problemList);
        }
        System.out.println("获取做过的题目");
        Map<Long, Set<String>> doMap = new HashMap<>();
        List<CfSubmitWithUserinfo> submitOkList = cfSubmitWithUserinfoRepository.findAll().stream().filter(a-> a.getStatus().equals("OK") && stuList.contains(a.getUid())).collect(Collectors.toList());
        for (int i = 0; i < submitOkList.size(); i++) {
            CfSubmitWithUserinfo temp = submitOkList.get(i);
            Long uid = temp.getUid();
            String problemId = temp.getCid()+temp.getqIndex();
            doMap.computeIfAbsent(uid, k -> new HashSet<>()).add(problemId);
        }
        //recommendMap现在都是真实做的题目了
        System.out.println("保留出来推荐了且做了的题目");
        for (Map.Entry<Long, Set<String>> longSetEntry : recommendMap.entrySet()) {
            Long key = longSetEntry.getKey();
            Set<String> value = longSetEntry.getValue();
            Set<String> strings = doMap.getOrDefault(key,new HashSet<>());
            value.retainAll(strings);
        }
        doMap.keySet().removeIf(key -> recommendMap.containsKey(key));

        Iterator<Map.Entry<Long, Set<String>>> iterator = recommendMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Set<String>> entry = iterator.next();
            Long uid = entry.getKey();
            Set<String> recommendedProblems = entry.getValue();

            // 如果推荐做的题目少于10道，则进行移动操作
            if (recommendedProblems.size() < 10) {
                // 将数据添加到doMap中
                doMap.put(uid, recommendedProblems);
                // 从recommendMap中移除当前用户的数据
                iterator.remove();
            }
        }


        String startSql = "SELECT sc.rating, c.start_time_stamp ,ca.uid " +
                     "FROM cf_stucontest sc " +
                     "JOIN cf_contest c ON sc.cid = c.cid " +
                     "join cf_account ca on sc.cfid = ca.id " +
                     "AND c.start_time_stamp > UNIX_TIMESTAMP('2023-07-01 00:00:00') " +
                     "ORDER BY c.start_time_stamp ASC";

        List<Object[]> startResults = entityManager.createNativeQuery(startSql).getResultList();
        Map<Long, Object[]> startCollect = startResults.stream()
                .collect(Collectors.toMap(
                        a -> ((Number) a[2]).longValue(), // Key extractor
                        a -> a, // Value mapper
                        (a, b) -> a)); // Merge function, keeping the first encountered in case of a duplicate key

        String endSql = "SELECT sc.rating, c.start_time_stamp ,ca.uid " +
                     "FROM cf_stucontest sc " +
                     "JOIN cf_contest c ON sc.cid = c.cid " +
                     "join cf_account ca on sc.cfid = ca.id " +
                     "AND c.start_time_stamp < UNIX_TIMESTAMP('2024-03-24 00:00:00') " +
                     "ORDER BY c.start_time_stamp DESC";

        List<Object[]> endResults = entityManager.createNativeQuery(endSql).getResultList();
        Map<Long, Object[]> endCollect = endResults.stream().collect(Collectors.toMap(a -> ((Number) a[2]).longValue(),
                a -> a,
                (a, b) -> a));
        Set<Long> recommendUidSet = recommendMap.keySet();
        Set<Long> doUidSet = doMap.keySet();
        HashMap<Long, Integer> recommendDiff = new HashMap<>();
        HashMap<Long, Integer> doDiff = new HashMap<>();

        for (Long uid : recommendUidSet) {
            if(startCollect.get(uid)==null){
                System.out.println("uid:"+uid+"用户没有比赛记录,忽略");
                continue;
            }
            Integer start = (Integer) startCollect.get(uid)[0];
            Integer end = (Integer) endCollect.get(uid)[0];
            /**
             * description:忽略那些做过智能推荐，但好久没比赛的人。
             * author:yjz
             */
            if(end-start==0){
                continue;
            }
            start=Math.max(start,800);
            if(end-start<-400){
                continue;
            }
            recommendDiff.put(uid,end-start);
        }
        for (Long uid : doUidSet) {
            if(startCollect.get(uid)==null){
                System.out.println("uid:"+uid+"用户没有比赛记录,忽略");
                continue;
            }
            Integer start = (Integer) startCollect.get(uid)[0];
            Integer end = (Integer) endCollect.get(uid)[0];
            /**
             * description:忽略好久没比赛的人。
             * author:yjz
             */
            if(end-start==0){
                continue;
            }
            start=Math.max(start,800);
            if(end-start<-400){
                continue;
            }
            doDiff.put(uid,end-start);
        }

        double recommendSum = 0;
        for (Integer valueDiff : recommendDiff.values()) {
            recommendSum += valueDiff;
        }
        double recommendAverage = recommendSum / recommendDiff.size();

        double doSum = 0;
        for (Integer valueDiff : doDiff.values()) {
            doSum += valueDiff;
        }
        double doAverage = doSum / doDiff.size();
        System.out.println("recommend平均涨分：" + recommendAverage);
        System.out.println("noUse平均涨分：" + doAverage);
    }
    

}
