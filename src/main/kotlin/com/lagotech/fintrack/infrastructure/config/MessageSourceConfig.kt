package com.lagotech.fintrack.infrastructure.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@Configuration
class MessageSourceConfig {

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:i18n/messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    @Bean
    fun validator(): LocalValidatorFactoryBean {
        val factoryBean = LocalValidatorFactoryBean()
        factoryBean.setValidationMessageSource(messageSource())
        return factoryBean
    }
}