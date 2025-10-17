package com.plcoding.echojournal.core.presentation.util

import com.plcoding.echojournal.core.echos.presentation.model.EchoUi
import com.plcoding.echojournal.core.echos.presentation.model.MoodUi
import com.plcoding.echojournal.core.echos.presentation.model.PlaybackState
import java.time.Instant
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

data object PreviewModels {

    val echoUi = EchoUi(
        id = 0,
        title = "My audio memo",
        mood = MoodUi.PEACEFUL,
        recordedAt = Instant.now(),
        note = (1..50).map { "Hello" }.joinToString(" "),
        topics = listOf("Love", "Work"),
        amplitudes = (1..30).map { Random.nextFloat() },
        playbackTotalDuration = 250.seconds,
        playbackCurrentDuration = 120.seconds,
        playbackState = PlaybackState.PAUSED,
        audioFilePath = ""
    )

}