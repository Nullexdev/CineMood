package tech.nullexdev.cinemood.core.data.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver

class WebDriverFactory : DriverFactory {
    override fun createDriver(): SqlDriver {
        return WebWorkerDriver(
            Worker("sqldelight-worker.js")
        )
    }
}
