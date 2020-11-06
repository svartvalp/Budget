package ru.mirea.budget.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.budget.dto.AccountBudgetInfoDto;
import ru.mirea.budget.entity.MonetaryOperation;
import ru.mirea.budget.service.MonetaryOperationService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/monetary")
@RequiredArgsConstructor
@RestController
public class MonetaryOperationController {
    private final MonetaryOperationService operationService;

    @GetMapping("/user/{userId}")
    public List<MonetaryOperation> getAllByUserId(@PathVariable("userId") String userId) {
        return operationService.getAllByUserId(userId);
    }

    @GetMapping("/budget/total/user/{userId}")
    public AccountBudgetInfoDto getBudgetInfoByUserId(@PathVariable("userId") String userId) {
        return operationService.getBudgetInfoByUserId(userId);
    }

    @PostMapping(value = "")
    public MonetaryOperation createMonetaryOperation(@RequestBody @Valid MonetaryOperation monetaryOperation) {
        return operationService.createMonetaryOp(monetaryOperation);
    }

}
