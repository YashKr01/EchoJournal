package com.plcoding.echojournal.core.echos.di

import com.plcoding.echojournal.core.echos.presentation.echos.EchosViewModel
import com.plcoding.echojournal.core.echos.presentation.echos.data.recording.AndroidVoiceRecorder
import com.plcoding.echojournal.core.echos.presentation.echos.domain.recording.VoiceRecorder
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val echoModule = module {

    single {
        AndroidVoiceRecorder(
            context = androidApplication(),
            applicationScope = get()
        )
    } bind VoiceRecorder::class

    viewModelOf(::EchosViewModel)

}