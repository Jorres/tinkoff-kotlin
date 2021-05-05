package ru.tinkoff.tarasov

import com.typesafe.config.ConfigFactory
import io.github.config4k.extract
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton
import ru.tinkoff.tarasov.astro.astroComponents
import ru.tinkoff.tarasov.astro.astroModule

fun main(args: Array<String>) {
    val config = ConfigFactory.load().extract<AppConfig>()
    val dbConfig = config.database

    migrate(dbConfig)

    val engine = embeddedServer(Netty, port = config.http.port) {
        di {
            mainComponents(config)
            astroComponents()
        }
        configureSerialization()
        astroModule()
    }

    engine.start(wait = true)
}

fun migrate(dbConfig: DatabaseConfig) {
    val flyway = Flyway
        .configure()
        .dataSource(dbConfig.url, dbConfig.user, dbConfig.password)
        .load()
        .migrate()
}

fun DI.Builder.mainComponents(config: AppConfig) {
    val dbConfig = config.database
    bind<AppConfig>() with singleton {
        config
    }
    bind<Database>() with singleton {
        Database.connect(
            url = dbConfig.url,
            user = dbConfig.user,
            password = dbConfig.password
        )
    }
}
