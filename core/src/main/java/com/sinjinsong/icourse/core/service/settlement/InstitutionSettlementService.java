package com.sinjinsong.icourse.core.service.settlement;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.domain.dto.institution.InstitutionStatistics;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.domain.entity.settlement.InstitutionSettlementDO;

import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
public interface InstitutionSettlementService {
    
    PageInfo<OrderDO> findUnSettledOrders(int pageNum,int pageSize);
    
    void settle(List<InstitutionSettlementDO> institutionSettlements);
    
    PageInfo<InstitutionSettlementDO> findAllByInstitutionId(Long institutionId, int pageNum, int pageSize);
    
    InstitutionStatistics findStatisticsByInstitutionId(Long institutionId);

    PageInfo<InstitutionSettlementDO> findAll(int pageNum, int pageSize);
}
