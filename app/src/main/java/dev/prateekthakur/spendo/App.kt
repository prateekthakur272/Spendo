package dev.prateekthakur.spendo

import android.app.Application
import dev.prateekthakur.spendo.di.AppModule
import dev.prateekthakur.spendo.di.RoomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@App)
            modules(RoomModule, AppModule)
        }
    }
}