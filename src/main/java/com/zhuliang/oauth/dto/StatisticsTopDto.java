package com.zhuliang.oauth.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @author v_liuwen
 * @date 2019/11/5 19:08
 */
public class StatisticsTopDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排名
     */
    private Integer ranking;
    /**
     * 部门id
     */
    private Integer departmentId;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 销售业绩
     */
    private Long totalResults;
    /**
     * 销售单数
     */
    private Integer totalOrderCount;

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }
    public static void main(String[] args) {
    	List<StatisticsTopDto> statisticsTopDtos = new ArrayList<>();
		StatisticsTopDto statisticsTopDto = new StatisticsTopDto();
		statisticsTopDto.setTotalResults(1000L);
		StatisticsTopDto statisticsTopDto2 = new StatisticsTopDto();
		statisticsTopDto2.setTotalResults(2000L);
		statisticsTopDtos.add(statisticsTopDto2);
		statisticsTopDtos.add(statisticsTopDto);
		Collections.sort(statisticsTopDtos,  (c1, c2) -> c2.getTotalResults()
                .compareTo(c1.getTotalResults()));
		System.out.println(JSON.toJSONString(statisticsTopDtos));
	}
}
