package com.sinjinsong.icourse.core.controller.settlement;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.common.exception.RestValidationException;
import com.sinjinsong.icourse.common.properties.PageProperties;
import com.sinjinsong.icourse.core.domain.dto.institution.InstitutionStatistics;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.domain.entity.settlement.InstitutionSettlementDO;
import com.sinjinsong.icourse.core.service.settlement.InstitutionSettlementService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@RestController
@RequestMapping("/settlement")
public class InstitutionSettlementController {
    @Autowired
    private InstitutionSettlementService institutionSettlementService;
    
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/unsettled_orders")
    public PageInfo<OrderDO> findUnSettledOrders(@RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return institutionSettlementService.findUnSettledOrders(pageNum,pageSize);
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public void settle(@RequestBody @Valid List<InstitutionSettlementDO> institutionSettlements,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new RestValidationException(bindingResult.getFieldErrors());
        }
        institutionSettlementService.settle(institutionSettlements);
    }
    
    
    @GetMapping
    public PageInfo<InstitutionSettlementDO> findAll(@RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return institutionSettlementService.findAll( pageNum, pageSize);
    }
    
    @GetMapping("/institutions/{institutionId}")
    public PageInfo<InstitutionSettlementDO> findAllByInstitutionId(@PathVariable("institutionId") Long institutionId, @RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return institutionSettlementService.findAllByInstitutionId(institutionId, pageNum, pageSize);
    }
    
    @GetMapping("/institutions/{institutionId}/statistics")
    public InstitutionStatistics findStatisticByInstitutionId(@PathVariable("institutionId") Long institutionId) {
        return institutionSettlementService.findStatisticsByInstitutionId(institutionId);
    }
}
