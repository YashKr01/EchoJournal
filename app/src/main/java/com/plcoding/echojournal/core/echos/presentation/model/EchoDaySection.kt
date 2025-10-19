package com.plcoding.echojournal.core.echos.presentation.model

import com.plcoding.echojournal.core.presentation.util.UiText

data class EchoDaySection(
    val dateHeader: UiText,
    val echos: List<EchoUi>
)