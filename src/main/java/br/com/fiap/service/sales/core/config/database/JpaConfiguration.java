package br.com.fiap.service.sales.core.config.database;

import br.com.fiap.service.sales.gateway.database.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class, basePackages = "br.com.fiap")
public class JpaConfiguration {}
