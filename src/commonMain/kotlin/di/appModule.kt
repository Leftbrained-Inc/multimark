package di

import org.koin.dsl.module
import viewmodel.FileSaveViewModel
import viewmodel.TabViewmodel

fun appModule() = module {
    single { TabViewmodel() }
    factory { params ->
        FileSaveViewModel(params.get())
    }
}