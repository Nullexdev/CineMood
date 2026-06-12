package tech.nullexdev.cinemood.core.data.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import app.cash.sqldelight.driver.worker.expected.Worker

class WebDriverFactory : DriverFactory {

    override fun createDriver(): SqlDriver {
        val worker = Worker("sqldelight-worker.js")
        return WebWorkerDriver(worker)
    }
}