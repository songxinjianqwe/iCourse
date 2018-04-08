package com.sinjinsong.icourse.core.service.manager;

import com.sinjinsong.icourse.core.domain.entity.manager.ManagerDO;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public interface ManagerService {
    ManagerDO findManager();

    ManagerDO findById(Long id);

    ManagerDO findByUsername(String username);
}
