package kg.study.mlkit.usertest.di


import android.content.Context
import androidx.room.Room
import kg.study.mlkit.usertest.db.model.AppDatabase
import kg.study.mlkit.usertest.db.model.MainRepository
import kg.study.mlkit.usertest.ui.main.MainViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.factory


val databaseModule = module {
    single { createDatabase(get()) }
    single { createAppDao(get()) }
}

private fun createDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "user")
        .fallbackToDestructiveMigration()
        .build()
}

private fun createAppDao(appDatabase: AppDatabase) = appDatabase.userDao()


val userModules = module {
    factory<MainRepository>()
    viewModel<MainViewModel>()
}