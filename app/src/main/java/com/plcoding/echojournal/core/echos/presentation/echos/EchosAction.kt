package com.plcoding.echojournal.core.echos.presentation.echos

import com.plcoding.echojournal.core.echos.presentation.echos.model.EchoFilterChip
import com.plcoding.echojournal.core.echos.presentation.model.MoodUi

sealed interface EchosAction {
    data object OnRecordFabClick : EchosAction
    data object OnSettingClick : EchosAction
    data object OnRequestPermissionQuickRecording : EchosAction
    data object OnRecordButtonLongClick : EchosAction
    data object OnMoodChipClick : EchosAction
    data object OnTopicChipClick : EchosAction
    data object OnDismissMoodDropdown : EchosAction
    data object OnDismissTopicDropDown : EchosAction
    data class OnFilterByMoodClick(val moodUi: MoodUi) : EchosAction
    data class OnFilterByTopicClick(val topic: String) : EchosAction
    data class OnRemoveFilters(val filterType: EchoFilterChip) : EchosAction
    data class OnPlayEchoClick(val echoId: Int) : EchosAction
    data object OnPauseAudioClick : EchosAction
    data object OnTrackSizeAvailable : EchosAction
    data object OnEchoPermissionGranted : EchosAction
    data object OnCancelRecording : EchosAction
    data object OnPauseRecordingClick : EchosAction
    data object OnResumeRecordingClick : EchosAction
    data object OnCompleteRecording : EchosAction
}