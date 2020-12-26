package kg.study.mlkit.usertest

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import kg.study.mlkit.usertest.di.databaseModule
import kg.study.mlkit.usertest.di.userModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        startKoin {
            androidContext(baseContext)
            modules( databaseModule, userModules)
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}