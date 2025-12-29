package br.com.fiap.service.sales.core.config.bean;

import br.com.fiap.service.sales.core.domain.shared.exception.SalesExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "br.com.fiap",
    excludeFilters =
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            value = SalesExceptionHandler.class))
public class ComponentScanConfiguration {}
