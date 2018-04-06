package com.sinjinsong.icourse.core.controller.order;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.common.exception.RestValidationException;
import com.sinjinsong.icourse.common.properties.PageProperties;
import com.sinjinsong.icourse.common.security.domain.JwtUser;
import com.sinjinsong.icourse.core.domain.dto.order.OrderQueryConditionDTO;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.domain.entity.student.StudentDO;
import com.sinjinsong.icourse.core.enumeration.order.OrderStatus;
import com.sinjinsong.icourse.core.exception.order.OrderNotFoundException;
import com.sinjinsong.icourse.core.exception.order.OrderStateIllegalException;
import com.sinjinsong.icourse.core.service.order.OrderService;
import com.sinjinsong.icourse.core.service.pay.AccountService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping
    public OrderDO placeOrder(@RequestBody @Valid @ApiParam(value = "订单对象") OrderDO order, BindingResult bindingResult, @AuthenticationPrincipal JwtUser student) {
        if (bindingResult.hasErrors()) {
            throw new RestValidationException(bindingResult.getFieldErrors());
        }
        order.setStudent(StudentDO.builder().id(student.getId()).build());
        return orderService.placeOrder(order);
    }

    @GetMapping
    public PageInfo<OrderDO> findAll(@RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return orderService.findAll(pageNum, pageSize);
    }

    @PostMapping("/condition")
    public PageInfo<OrderDO> findAllByCondition(@RequestBody @Valid @ApiParam(value = "查询对象，包含了查询的各种条件", required = true) OrderQueryConditionDTO queryDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RestValidationException(result.getFieldErrors());
        }
        if (queryDTO.getPageNum() == null || queryDTO.getPageNum() <= 0) {
            queryDTO.setPageNum(Integer.valueOf(PageProperties.DEFAULT_PAGE_NUM));
        }
        if (queryDTO.getPageSize() == null || queryDTO.getPageSize() <= 0) {
            queryDTO.setPageSize(Integer.valueOf(PageProperties.DEFAULT_PAGE_SIZE));
        }
        return orderService.findAllByCondition(queryDTO, queryDTO.getPageNum(), queryDTO.getPageSize());
    }

    @GetMapping("/{id}")
    public OrderDO findById(@PathVariable("id") Long orderId) {
        return orderService.findById(orderId);
    }

    @PutMapping("/cancel/{id}")
    public OrderDO cancel(@PathVariable("id") Long orderId) {
        OrderDO order = orderService.findById(orderId);
        log.info("{}", order);
        if (order == null) {
            throw new OrderNotFoundException(String.valueOf(orderId));
        }
        // 如果是未付款，则直接修改订单状态即可
        // 如果是已付款，则根据情况修改订单状态
        if (order.getStatus() == OrderStatus.UNPAID) {
            orderService.cancelOrder(order);
        } else if (order.getStatus() == OrderStatus.PAID) {
            accountService.refund(order);
        } else {
            throw new OrderStateIllegalException(order.getStatus().toString());
        }
        return order;
    }
}
