package di

import org.koin.dsl.module
import viewmodel.TabViewmodel
import viewmodel.FileSaveViewModel

fun appModule() = module {
    single { TabViewmodel() }
    factory {params ->
        FileSaveViewModel(params.get())
    }
}