package com.plcoding.echojournal.core.echos.presentation.echos

import com.plcoding.echojournal.R
import com.plcoding.echojournal.core.echos.presentation.echos.model.AudioCaptureMethod
import com.plcoding.echojournal.core.echos.presentation.echos.model.EchoFilterChip
import com.plcoding.echojournal.core.echos.presentation.echos.model.MoodChipContent
import com.plcoding.echojournal.core.echos.presentation.echos.model.RecordingState
import com.plcoding.echojournal.core.echos.presentation.model.EchoDaySection
import com.plcoding.echojournal.core.echos.presentation.model.EchoUi
import com.plcoding.echojournal.core.echos.presentation.model.MoodUi
import com.plcoding.echojournal.core.presentation.design.dropdowns.Selectable
import com.plcoding.echojournal.core.presentation.util.UiText
import java.util.Locale
import kotlin.math.roundToInt
import kotlin.time.Duration

data class EchosState(
    val echos: Map<UiText, List<EchoUi>> = emptyMap(),
    val hasEchosRecorded: Boolean = false,
    val hasActiveTopicFilters: Boolean = false,
    val hasActiveMoodFilters: Boolean = false,
    val isLoadingData: Boolean = false,
    val moods: List<Selectable<MoodUi>> = emptyList(),
    val topics: List<Selectable<String>> = emptyList(),
    val moodChipContent: MoodChipContent = MoodChipContent(),
    val selectedEchoFilterChip: EchoFilterChip? = null,
    val topicChipTitle: UiText = UiText.StringResource(R.string.all_topics),
    val currentCaptureMethod: AudioCaptureMethod? = null,
    val recordingElapsedDuration: Duration = Duration.ZERO,
    val recordingState: RecordingState = RecordingState.NOT_RECORDING,
) {

    val echoDaySections = echos
        .toList()
        .map { (dateHeader, echos) ->
            EchoDaySection(dateHeader, echos)
        }

    val formattedRecordDuration: String
        get() {
            val minutes = (recordingElapsedDuration.inWholeMinutes % 60).toInt()
            val seconds = (recordingElapsedDuration.inWholeSeconds % 60).toInt()
            val centiSeconds = ((recordingElapsedDuration.inWholeMilliseconds % 1000) / 10.0).roundToInt()

            return String.format(
                locale = Locale.US,
                format = "%02d:%02d:%02d",
                minutes, seconds, centiSeconds
            )
        }

}