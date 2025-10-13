package com.plcoding.echojournal.core.echos.presentation.echos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class EchosViewModel : ViewModel() {

    private val _state = MutableStateFlow(EchosState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = EchosState()
        )

    fun onAction(action: EchosAction) {
        when (action) {
            EchosAction.OnFabClick -> {}
            EchosAction.OnFabLongClick -> {}
            EchosAction.OnMoodChipClick -> {}
            EchosAction.OnSettingClick -> {}
            is EchosAction.OnRemoveFilters -> {}
            EchosAction.OnTopicChipClick -> {}
        }
    }

}