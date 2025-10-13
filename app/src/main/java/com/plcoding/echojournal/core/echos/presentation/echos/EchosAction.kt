package com.plcoding.echojournal.core.echos.presentation.echos

import com.plcoding.echojournal.core.echos.presentation.echos.model.EchoFilterChip

sealed interface EchosAction {
    data object OnFabClick : EchosAction
    data object OnSettingClick : EchosAction
    data object OnFabLongClick : EchosAction
    data object OnMoodChipClick : EchosAction
    data object OnTopicChipClick : EchosAction
    data class OnRemoveFilters(val filterType: EchoFilterChip) : EchosAction
}