package com.sinjinsong.icourse.core.service.settlement.impl;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.dao.settlement.InstitutionSettlementDOMapper;
import com.sinjinsong.icourse.core.domain.dto.institution.InstitutionStatistics;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.domain.entity.settlement.InstitutionSettlementDO;
import com.sinjinsong.icourse.core.service.institution.InstitutionService;
import com.sinjinsong.icourse.core.service.order.OrderService;
import com.sinjinsong.icourse.core.service.pay.AccountService;
import com.sinjinsong.icourse.core.service.settlement.InstitutionSettlementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@Service
@Slf4j
public class InstitutionSettlementServiceImpl implements InstitutionSettlementService {
    @Autowired
    private InstitutionSettlementDOMapper institutionSettlementDOMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private AccountService accountService;


    @Transactional(readOnly = true)
    @Override
    public PageInfo<OrderDO> findUnSettledOrders(int pageNum, int pageSize) {
        return orderService.findUnSettledOrders(pageNum, pageSize);
    }

    @Transactional
    @Override
    public void settle(List<InstitutionSettlementDO> institutionSettlements) {
        List<Long> orderIds = institutionSettlements.stream().map(settlement -> settlement.getOrder().getId()).collect(Collectors.toList());
        institutionSettlements.stream().forEach(settlementDO -> {
            settlementDO.setOrder(orderService.findById(settlementDO.getOrder().getId()));
        });

        double total = institutionSettlements.stream().map(settlementDO -> settlementDO.getOrder()).mapToDouble(OrderDO::getPrice).sum();
        double institutionSum = institutionSettlements.stream().mapToDouble(settlement -> settlement.getOrder().getPrice() * settlement.getPercent()).sum();
        orderService.setOrdersSettledBatch(orderIds);

        institutionSettlements.forEach((settlement -> {
            settlement.setInstitution(institutionService.findInstitutionByOrderId(settlement.getOrder().getId()));
            settlement.setCreateTime(LocalDateTime.now());
            institutionSettlementDOMapper.insert(settlement);
            // 划给机构
            accountService.incrementInstitutionValue(settlement.getInstitution().getId(), settlement.getOrder().getPrice() * settlement.getPercent());
        }));
        // 把钱划给经理
        accountService.incrementManagerValue(total - institutionSum);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<InstitutionSettlementDO> findAllByInstitutionId(Long institutionId, int pageNum, int pageSize) {
        return institutionSettlementDOMapper.findAllByInstitutionId(institutionId, pageNum, pageSize).toPageInfo();
    }

    @Transactional(readOnly = true)
    @Override
    public InstitutionStatistics findStatisticsByInstitutionId(Long institutionId) {
        Double income = institutionSettlementDOMapper.sumByInstitutionId(institutionId);
        return InstitutionStatistics.builder().income(income != null ? income : 0).build();
    }

    @Override
    public PageInfo<InstitutionSettlementDO> findAll(int pageNum, int pageSize) {
        return institutionSettlementDOMapper.findAll(pageNum, pageSize).toPageInfo();
    }
}
