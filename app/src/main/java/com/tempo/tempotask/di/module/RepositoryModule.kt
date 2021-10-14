package com.tempo.tempotask.di.module

import com.tempo.tempotask.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}