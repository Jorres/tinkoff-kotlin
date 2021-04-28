package ru.tinkoff.tarasov.dockerfirst

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    @Bean
    fun getDataSource(): DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.postgresql.Driver")
        dataSourceBuilder.url("jdbc:postgresql://${System.getProperty("dbUrl")}")
        dataSourceBuilder.username(System.getProperty("username"))
        dataSourceBuilder.password(System.getProperty("password"))
        return dataSourceBuilder.build()
    }
}