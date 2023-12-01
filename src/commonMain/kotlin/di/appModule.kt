package di

import org.koin.dsl.module
import viewmodel.FilesViewModel

fun appModule() = module {
    factory {
        FilesViewModel()
    }
}