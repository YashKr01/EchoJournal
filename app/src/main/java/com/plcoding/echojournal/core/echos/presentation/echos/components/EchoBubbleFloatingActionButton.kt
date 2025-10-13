package com.plcoding.echojournal.core.echos.presentation.echos.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.plcoding.echojournal.R

@Composable
fun EchoBubbleFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_new_entry)
        )
    }

}