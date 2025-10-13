package com.plcoding.echojournal.core.echos.presentation.echos

import com.plcoding.echojournal.R
import com.plcoding.echojournal.core.echos.presentation.echos.model.EchoFilterChip
import com.plcoding.echojournal.core.echos.presentation.echos.model.MoodChipContent
import com.plcoding.echojournal.core.echos.presentation.model.MoodUi
import com.plcoding.echojournal.core.presentation.design.dropdowns.Selectable
import com.plcoding.echojournal.core.presentation.util.UiText

data class EchosState(
    val hasEchosRecorded: Boolean = false,
    val hasActiveTopicFilters: Boolean = false,
    val hasActiveMoodFilters: Boolean = false,
    val isLoadingData: Boolean = false,
    val moods: List<Selectable<MoodUi>> = emptyList(),
    val topics: List<Selectable<String>> = emptyList(),
    val moodChipContent: MoodChipContent = MoodChipContent(),
    val selectedEchoFilterChip: EchoFilterChip? = null,
    val topicChipTitle: UiText = UiText.StringResource(R.string.all_topics)
)