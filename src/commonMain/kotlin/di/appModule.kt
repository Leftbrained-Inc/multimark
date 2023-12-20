package di

import org.koin.dsl.module
import viewmodel.TabViewModel

fun appModule() = module {
    single { TabViewModel() }
}