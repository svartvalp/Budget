package ru.mirea.budget.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "ru.mirea.budget.dao")
@Configuration
public class AppConfig {
}
