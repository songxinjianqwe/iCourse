package com.sinjinsong.icourse.core.service.institution;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.domain.entity.institution.InstitutionDO;
import com.sinjinsong.icourse.core.enumeration.institution.InstitutionStatus;

import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public interface InstitutionService {
    /**
     * 需要审批，但是可以立刻获得机构号
     * @param institutionDO
     */
    void register(InstitutionDO institutionDO);
    void update(InstitutionDO institutionDO);
    PageInfo<InstitutionDO> findAll(int pageNum, int pageSize);
    PageInfo<InstitutionDO> findAllByStatus(InstitutionStatus status, int pageNum, int pageSize);
    InstitutionDO findById(Long id);
    InstitutionDO findByUsername(String username);
    void approveBatch(List<Long> institutionIds);
    InstitutionDO findInstitutionByClassId(Long classId);
    InstitutionDO findInstitutionByOrderId(Long orderId);
}
