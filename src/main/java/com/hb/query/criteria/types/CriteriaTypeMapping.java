package com.hb.query.criteria.types;

import com.hb.query.criteria.CriteriaType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CriteriaTypeMapping {

    public static Map<CriteriaType, Criteria> getCriteriaTypeMap() {
        return criteriaTypeMap;
    }

    public static void setCriteriaTypeMap(Map<CriteriaType, Criteria> criteriaTypeMap) {
        CriteriaTypeMapping.criteriaTypeMap = criteriaTypeMap;
    }

    private static Map<CriteriaType, Criteria> criteriaTypeMap;

    @PostConstruct
    public static void init() {
        criteriaTypeMap = new HashMap<>();
        criteriaTypeMap.put(CriteriaType.TimesCriteria, new TimesCriteria());
    }
}
