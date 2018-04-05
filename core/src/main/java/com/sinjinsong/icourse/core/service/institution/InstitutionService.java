package com.sinjinsong.icourse.core.service.institution;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.domain.entity.institution.InstitutionDO;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public interface InstitutionService {
    void save(InstitutionDO institutionDO);
    void update(InstitutionDO institutionDO);
    PageInfo<InstitutionDO> findAll(int pageNum, int pageSize);
    InstitutionDO findById(Long id);
    InstitutionDO findByUsername(String username);
}
