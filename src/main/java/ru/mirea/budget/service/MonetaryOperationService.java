package ru.mirea.budget.service;

import ru.mirea.budget.dto.AccountBudgetInfoDto;
import ru.mirea.budget.entity.MonetaryOperation;

import java.util.List;

public interface MonetaryOperationService {
    MonetaryOperation createMonetaryOp(MonetaryOperation operation);
    List<MonetaryOperation> getAllByUserId(String userId);
    AccountBudgetInfoDto getBudgetInfoByUserId(String userId);
}
