package di

import core.db.Db
import org.koin.dsl.module
import viewmodel.FilesViewModel

fun appModule() = module {
    factory {
        FilesViewModel()
    }
    single {
        Db.connect
    }
}