package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
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


    /**
     * 统计指定时间区间的用户数据
     * @param begin 开始日期
     * @param end 结束日期
     * @return 返回VO对象
     */
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

    /**
     * 统计指定时间区间内的订单数据
     * @param begin 开始日期
     * @param end 结束日期
     * @return 返回VO对象
     */
    OrderReportVO getOrdersStatistics(LocalDate begin, LocalDate end);

    /**
     * 统计指定时间区间内的销量前10
     * @param begin 开始日期
     * @param end 结束日期
     * @return 返回VO对象
     */
    SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end);

    /**
     * 导出最近三十天的运营数据报表
     * @param response HttpServletResponse
     */
    void exportBusinessData(HttpServletResponse response);
}
