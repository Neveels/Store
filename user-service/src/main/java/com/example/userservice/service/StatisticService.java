package com.example.userservice.service;

import com.example.userservice.dto.response.AmountStatisticResponse;
import com.example.userservice.dto.response.PriceStatisticResponse;

import java.util.List;

public interface StatisticService {
    List<AmountStatisticResponse> getProfitByProductType();
    List<PriceStatisticResponse> getPriceStatistic();
}
