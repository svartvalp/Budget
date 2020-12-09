package ru.mirea.budget.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.budget.common.MonetaryOpType;
import ru.mirea.budget.dao.MonetaryOperationDao;
import ru.mirea.budget.dto.AccountBudgetInfoDto;
import ru.mirea.budget.entity.MonetaryOperation;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonetaryOperationServiceImpl implements MonetaryOperationService {
    private final MonetaryOperationDao operationDao;


    @Override
    public MonetaryOperation createMonetaryOp(MonetaryOperation operation) {
        operation.setOperationTime(operation.getOperationTime().atOffset(ZoneOffset.UTC).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
        operation.setId(null);
        return operationDao.save(operation);
    }

    @Override
    public List<MonetaryOperation> getAllByUserId(String userId) {
        return operationDao.findByUserId(userId);
    }

    @Override
    public AccountBudgetInfoDto getBudgetInfoByUserId(String userId) {
        Long value;
        value = operationDao
                .findByUserId(userId)
                .stream()
                .map(op -> op.getType().equals(MonetaryOpType.INCOME) ? op.getValue() : - op.getValue())
                .reduce(Long::sum).orElse(0L);
        AccountBudgetInfoDto dto = new AccountBudgetInfoDto();
        dto.setCurrentMoney(value);
        return dto;
    }

    @Override
    public void deleteById(String id) {
        operationDao.deleteById(id);
    }
}
