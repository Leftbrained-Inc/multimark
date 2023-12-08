package di

import org.koin.dsl.module
import viewmodel.TabViewmodel

fun appModule() = module {
    single { TabViewmodel() }
}