package com.plcoding.echojournal.core.echos.presentation.echos

import android.adservices.topics.Topic
import com.plcoding.echojournal.core.echos.presentation.echos.model.EchoFilterChip
import com.plcoding.echojournal.core.echos.presentation.model.MoodUi

sealed interface EchosAction {
    data object OnFabClick : EchosAction
    data object OnSettingClick : EchosAction
    data object OnFabLongClick : EchosAction
    data object OnMoodChipClick : EchosAction
    data object OnTopicChipClick : EchosAction
    data object OnDismissMoodDropdown : EchosAction
    data object OnDismissTopicDropDown : EchosAction
    data class OnFilterByMoodClick(val moodUi: MoodUi): EchosAction
    data class OnFilterByTopicClick(val topic: String): EchosAction
    data class OnRemoveFilters(val filterType: EchoFilterChip) : EchosAction
}