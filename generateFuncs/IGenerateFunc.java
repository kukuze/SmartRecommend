package org.algotithmcontestdatacollect.displaybackend.service.recommend.generateFuncs;

import org.algotithmcontestdatacollect.displaybackend.service.recommend.RecommendationContext;

import java.util.*;

public interface IGenerateFunc {
    /**
     * description:
     * author:yjz
     *
     * @param n                生成n道题目
     * @param context          环境变量
     * @return java.util.HashMap<java.lang.String, java.lang.String>
     */
    Map<String, String> generate(int n,int countAboveScore, RecommendationContext context);

}