package com.plcoding.echojournal.core.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.echojournal.core.echos.presentation.model.MoodUi
import com.plcoding.echojournal.core.echos.presentation.model.PlaybackState
import com.plcoding.echojournal.core.presentation.design.theme.EchoJournalTheme
import com.plcoding.echojournal.core.presentation.design.theme.Pause
import com.plcoding.echojournal.core.presentation.util.defaultShadow

@Composable
fun EchoPlaybackButton(
    modifier: Modifier = Modifier,
    playbackState: PlaybackState,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    colors: IconButtonColors,
) {

    FilledIconButton(
        onClick = when (playbackState) {
            PlaybackState.PLAYING -> onPauseClick
            PlaybackState.PAUSED,
            PlaybackState.STOPPED -> onPlayClick
        },
        colors = colors,
        modifier = Modifier.defaultShadow()
    ) {
        Icon(
            imageVector = when(playbackState) {
                PlaybackState.PLAYING -> Icons.Filled.Pause
                PlaybackState.PAUSED,
                PlaybackState.STOPPED -> Icons.Filled.PlayArrow
            },
            contentDescription = ""
        )
    }

}

@Preview
@Composable
private fun EchoPlaybackButtonPreview() {
    EchoJournalTheme {
        EchoPlaybackButton(
            playbackState = PlaybackState.PLAYING,
            onPauseClick = {},
            onPlayClick = {},
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MoodUi.SAD.colorSet.vivid
            )
        )
    }
}