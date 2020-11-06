package ru.mirea.budget.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.mirea.budget.common.MonetaryOpType;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class MonetaryOperation {
    @Id
    private String id;
    @NotNull
    private String userId;
    @NotNull
    private Long value;
    @NotNull
    private MonetaryOpType type;
    @NotNull
    private LocalDateTime operationTime;

    private String comment;
}
