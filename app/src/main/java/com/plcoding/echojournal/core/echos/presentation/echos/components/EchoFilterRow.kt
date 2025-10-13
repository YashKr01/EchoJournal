package com.plcoding.echojournal.core.echos.presentation.echos.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.plcoding.echojournal.R
import com.plcoding.echojournal.core.echos.presentation.echos.EchosAction
import com.plcoding.echojournal.core.echos.presentation.echos.model.EchoFilterChip
import com.plcoding.echojournal.core.echos.presentation.echos.model.MoodChipContent
import com.plcoding.echojournal.core.echos.presentation.model.MoodUi
import com.plcoding.echojournal.core.presentation.design.chips.MultiChoiceChip
import com.plcoding.echojournal.core.presentation.design.dropdowns.Selectable
import com.plcoding.echojournal.core.presentation.design.dropdowns.SelectableDropDownOptionsMenu
import com.plcoding.echojournal.core.presentation.util.UiText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EchoFilterRow(
    modifier: Modifier = Modifier,
    moodChipContent: MoodChipContent,
    topicChipTitle: UiText,
    hasActiveMoodFilters: Boolean,
    selectedEchoFilterChip: EchoFilterChip?,
    moods: List<Selectable<MoodUi>>,
    hasActiveTopicFilters: Boolean,
    topics: List<Selectable<String>>,
    onAction: (EchosAction) -> Unit,
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val dropDownMaxHeight = (configuration.screenHeightDp * 0.3f).dp

    var dropDownOffset by remember {
        mutableStateOf(IntOffset.Zero)
    }


    FlowRow(
        modifier = modifier
            .padding(16.dp)
            .onGloballyPositioned {
                dropDownOffset = IntOffset(
                    x = 0,
                    y = it.size.height
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        MultiChoiceChip(
            displayText = topicChipTitle.asString(),
            onClick = {
                onAction(EchosAction.OnTopicChipClick)
            },
            leadingContent = {
                if (moodChipContent.iconsRes.isNotEmpty()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy((-4).dp),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        moodChipContent.iconsRes.forEach { iconRes ->
                            Image(
                                imageVector = ImageVector.vectorResource(iconRes),
                                contentDescription = moodChipContent.title.asString(),
                                modifier = Modifier.height(16.dp)
                            )
                        }
                    }
                }
            },
            isClearVisible = hasActiveMoodFilters,
            isDropDownVisible = selectedEchoFilterChip == EchoFilterChip.MOODS,
            isHighlighted = hasActiveMoodFilters || selectedEchoFilterChip == EchoFilterChip.MOODS,
            onClearButtonClick = { onAction(EchosAction.OnRemoveFilters(EchoFilterChip.MOODS)) },
            dropDownMenu = {
                SelectableDropDownOptionsMenu(
                    items = moods,
                    itemDisplayText = { moodUi -> moodUi.title.asString(context) },
                    onDismiss = { onAction(EchosAction.OnDismissMoodDropdown) },
                    key = { it.title.asString(context) },
                    onItemClick = { moodUi -> onAction(EchosAction.OnFilterByMoodClick(moodUi.item)) },
                    maxDropDownHeight = dropDownMaxHeight,
                    dropDownOffset = dropDownOffset,
                    leadingIcon = {
                        Image(
                            imageVector = ImageVector.vectorResource(it.iconSet.fill),
                            contentDescription = it.title.asString(),
                        )
                    }
                )
            }
        )

        MultiChoiceChip(
            displayText = moodChipContent.title.asString(),
            onClick = {
                onAction(EchosAction.OnMoodChipClick)
            },
            isClearVisible = hasActiveTopicFilters,
            isDropDownVisible = selectedEchoFilterChip == EchoFilterChip.TOPICS,
            isHighlighted = hasActiveTopicFilters || selectedEchoFilterChip == EchoFilterChip.MOODS,
            onClearButtonClick = { onAction(EchosAction.OnRemoveFilters(EchoFilterChip.TOPICS)) },
            dropDownMenu = {
                if (topics.isEmpty()) {
                    SelectableDropDownOptionsMenu(
                        items = listOf(
                            Selectable(
                                stringResource(R.string.you_don_t_have_any_topics_yet),
                                selected = false
                            )
                        ),
                        itemDisplayText = { it },
                        onDismiss = { onAction(EchosAction.OnDismissTopicDropDown) },
                        onItemClick = { },
                        key = { it },
                        dropDownOffset = dropDownOffset,
                        maxDropDownHeight = dropDownMaxHeight
                    )
                } else {
                    SelectableDropDownOptionsMenu(
                        items = topics,
                        itemDisplayText = { topic -> topic },
                        onDismiss = { onAction(EchosAction.OnDismissTopicDropDown) },
                        key = { it },
                        onItemClick = { topic -> onAction(EchosAction.OnFilterByTopicClick(topic.item)) },
                        maxDropDownHeight = dropDownMaxHeight,
                        dropDownOffset = dropDownOffset,
                        leadingIcon = {
                            Image(
                                imageVector = ImageVector.vectorResource(R.drawable.hashtag),
                                contentDescription = it,
                            )
                        }
                    )
                }
            }
        )

    }


}