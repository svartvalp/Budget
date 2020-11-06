package ru.mirea.budget.exception;

public class EntityNotFoundException extends BusinessLogicException {
    public EntityNotFoundException(String entityName, String entityId) {
        super(entityName + " was not found by value " + entityId);
    }
}
