package com.plcoding.echojournal.core.echos.presentation.echos

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.echojournal.core.echos.presentation.echos.components.EchoBubbleFloatingActionButton
import com.plcoding.echojournal.core.echos.presentation.echos.components.EchosEmptyBackground
import com.plcoding.echojournal.core.echos.presentation.echos.components.EchosTopBar
import com.plcoding.echojournal.core.presentation.design.theme.EchoJournalTheme
import com.plcoding.echojournal.core.presentation.design.theme.bgGradient

@Composable
fun EchosRoot(
    viewModel: EchosViewModel = viewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

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
            EchoBubbleFloatingActionButton { onAction(EchosAction.OnFabClick) }
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
            }
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