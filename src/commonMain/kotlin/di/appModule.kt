package di

import org.koin.dsl.module
import viewmodel.FileSaveViewModel

fun appModule() = module {
    factory {params ->
        FileSaveViewModel(params.get())
    }
}