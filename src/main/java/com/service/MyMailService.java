package com.service;

import com.service.dto.BestDealDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface MyMailService {
    void sendEmail(List<BestDealDto> bestDeals);
}
