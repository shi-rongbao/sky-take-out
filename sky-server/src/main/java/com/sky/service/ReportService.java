package com.sky.service;

import com.sky.vo.TurnoverReportVO;

import java.time.LocalDate;

/**
 * @author ShiRongbao
 * @create 2024/3/8 11:04
 * @description:
 */
public interface ReportService {

    /**
     * 统计指定时间区间的营业额
     * @param begin 开始日期
     * @param end 结束日期
     * @return 返回VO对象
     */
    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);
}
