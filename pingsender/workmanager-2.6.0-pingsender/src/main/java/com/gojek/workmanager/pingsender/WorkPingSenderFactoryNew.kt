package com.gojek.workmanager.pingsender

import android.content.Context
import androidx.work.WorkManager
import com.gojek.mqtt.pingsender.AdaptiveGojekMqttPingSender
import com.gojek.mqtt.pingsender.GojekMqttPingSender

class WorkPingSenderFactoryNew private constructor() {
    companion object {
        fun createMqttPingSender(context: Context, pingSenderConfig: WorkManagerPingSenderConfig): GojekMqttPingSender {
            val workManager = WorkManager.getInstance(context.applicationContext)
            return WorkManagerPingSenderGojek(
                pingWorkScheduler = NonAdaptivePingWorkScheduler(workManager),
                pingSenderConfig = pingSenderConfig
            )
        }

        fun createAdaptiveMqttPingSender(context: Context, pingSenderConfig: WorkManagerPingSenderConfig): AdaptiveGojekMqttPingSender {
            val workManager = WorkManager.getInstance(context.applicationContext)
            return WorkManagerPingSenderAdaptiveGojek(
                pingWorkScheduler = AdaptivePingWorkScheduler(workManager),
                pingSenderConfig = pingSenderConfig
            )
        }
    }
}
