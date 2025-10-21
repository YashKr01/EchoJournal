package com.plcoding.echojournal.core.echos.presentation.echos.components

sealed interface EchoEvents {
    data object RequestAudioPermission : EchoEvents
}