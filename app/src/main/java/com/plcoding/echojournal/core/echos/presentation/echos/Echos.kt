package com.plcoding.echojournal.core.echos.presentation.echos

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.echojournal.R
import com.plcoding.echojournal.core.echos.presentation.echos.components.EchoEvents
import com.plcoding.echojournal.core.echos.presentation.echos.components.EchoFilterRow
import com.plcoding.echojournal.core.echos.presentation.echos.components.EchoList
import com.plcoding.echojournal.core.echos.presentation.echos.components.EchoRecordingSheet
import com.plcoding.echojournal.core.echos.presentation.echos.components.EchosEmptyBackground
import com.plcoding.echojournal.core.echos.presentation.echos.components.EchosTopBar
import com.plcoding.echojournal.core.echos.presentation.echos.model.AudioCaptureMethod
import com.plcoding.echojournal.core.echos.presentation.echos.model.RecordingState
import com.plcoding.echojournal.core.presentation.design.theme.EchoJournalTheme
import com.plcoding.echojournal.core.presentation.design.theme.bgGradient
import com.plcoding.echojournal.core.presentation.util.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun EchosRoot(
    viewModel: EchosViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted && state.currentCaptureMethod == AudioCaptureMethod.STANDARD) {
            viewModel.onAction(EchosAction.OnEchoPermissionGranted)
        }
    }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            EchoEvents.RequestAudioPermission -> permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            EchoEvents.RecordingTooShortEvent -> Toast.makeText(context, context.getString(R.string.recording_too_short), Toast.LENGTH_SHORT).show()
            EchoEvents.OnDoneRecording -> Unit
        }
    }

    EchosScreen(
        state = state,
        onAction = viewModel::onAction
    )

}

@Composable
fun EchosScreen(
    state: EchosState,
    onAction: (EchosAction) -> Unit,
) {

    Scaffold(
        floatingActionButton = {
//            EchoBubbleFloatingActionButton { onAction(EchosAction.OnFabClick) }
        },
        topBar = {
            EchosTopBar(
                onSettingsClick = { onAction(EchosAction.OnSettingClick) }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = MaterialTheme.colorScheme.bgGradient
                )
                .padding(innerPadding)
        ) {

            EchoFilterRow(
                moodChipContent = state.moodChipContent,
                hasActiveMoodFilters = state.hasActiveMoodFilters,
                selectedEchoFilterChip = state.selectedEchoFilterChip,
                moods = state.moods,
                topicChipTitle = state.topicChipTitle,
                hasActiveTopicFilters = state.hasActiveTopicFilters,
                onAction = onAction,
                topics = state.topics,
                modifier = Modifier
                    .fillMaxWidth()
            )
            when {
                state.isLoadingData -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .wrapContentSize()
                    )
                }

                state.hasEchosRecorded.not() -> {
                    EchosEmptyBackground(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }

                else -> {
                    EchoList(
                        sections = state.echoDaySections,
                        onPlayClick = { onAction(EchosAction.OnPlayEchoClick(it)) },
                        onPauseClick = { onAction(EchosAction.OnPauseAudioClick) },
                        onTrackSizeAvailable = { onAction(EchosAction.OnTrackSizeAvailable) }
                    )
                }

            }

        }

        if (state.recordingState in listOf(RecordingState.NORMAL_CAPTURE, RecordingState.PAUSED)) {
            EchoRecordingSheet(
                formattedRecordingDuration = state.formattedRecordDuration,
                isRecording = state.recordingState == RecordingState.NORMAL_CAPTURE,
                onDismiss = { onAction(EchosAction.OnCancelRecording) },
                onPauseClick = { onAction(EchosAction.OnPauseRecordingClick) },
                onResumeClick = { onAction(EchosAction.OnResumeRecordingClick) },
                onCompleteRecording = { onAction(EchosAction.OnCompleteRecording) },
            )
        }

    }

}

@Preview
@Composable
private fun Preview() {
    EchoJournalTheme {
        EchosScreen(
            state = EchosState(
                isLoadingData = false,
                hasEchosRecorded = false
            ),
            onAction = {}
        )
    }
}