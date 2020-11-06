package ru.mirea.budget.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.mirea.budget.entity.MonetaryOperation;

import java.util.List;

public interface MonetaryOperationDao extends MongoRepository<MonetaryOperation, String> {
    List<MonetaryOperation> findByUserId(String userId);
}
