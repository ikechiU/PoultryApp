package com.ikechiu.poultryapp.ui.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.domain.model.PoultryInventoryModel
import com.ikechiu.poultryapp.routing.PoultryAppRouter
import com.ikechiu.poultryapp.routing.Screen
import com.ikechiu.poultryapp.ui.component.ContentText
import com.ikechiu.poultryapp.ui.component.ContentTextField
import com.ikechiu.poultryapp.ui.theme.PoultryAppThemeSettings
import com.ikechiu.poultryapp.util.NEW_ID
import com.ikechiu.poultryapp.util.avoidEmptyString
import com.ikechiu.poultryapp.util.isEqual
import com.ikechiu.poultryapp.util.returnInt
import com.ikechiu.poultryapp.viewmodel.MainViewModel

var chicksCount: String = ""
var hensCount: String = ""
var cockerelsCount: String = ""
var mortalityCount: String = ""
var crackedCornFeedCount: String = ""
var comboFeedCount: String = ""
var standardBrownEggCount: String = ""
var organicEggCount: String = ""
var waterConsumptionCount: String = ""
var antibioticsDrugCount: String = ""
var regularDrugCount: String = ""

@Composable
fun SavePoultryInventoryScreen(viewModel: MainViewModel) {

    val poultryInventoryEntry by viewModel
        .poultryInventoryEntry
        .observeAsState(PoultryInventoryModel())

    val isCurrentValueState = remember { mutableStateOf(true) }

    val nonStatePoultryInventoryEntry = viewModel.poultryInventoryEntry.value!!

    val isEditedState = remember { mutableStateOf(nonStatePoultryInventoryEntry.isEdited) }

    BackHandler() {
        PoultryAppRouter.navigateTo(Screen.PoultryInventory)
    }

    val deletePoultryInventoryDialogShownState = rememberSaveable {
        mutableStateOf(false)
    }

    val isEditingMode = poultryInventoryEntry.id != NEW_ID

    val background = if(PoultryAppThemeSettings.isDarkThemeEnabled)
        MaterialTheme.colors.surface else MaterialTheme.colors.background

    Scaffold(
        topBar = {
            val shouldSave = shouldSave(poultryInventoryEntry)
            SavePoultryInventoryTopAppBar(
                shouldSave = shouldSave,
                isEditingMode = isEditingMode,
                onBackClick = { PoultryAppRouter.navigateTo(Screen.PoultryInventory) },
                onSavePoultryInventoryClick = {
                    viewModel.onSavePoultryInventory(poultryInventoryEntry)
                },
                onDeletePoultryInventoryClick = { deletePoultryInventoryDialogShownState.value = true }
            )
        },
        backgroundColor = background,
        content = {
            if (deletePoultryInventoryDialogShownState.value) {
                AlertDialog(
                    onDismissRequest = {
                        deletePoultryInventoryDialogShownState.value = false
                    },
                    title = {
                        Text("Delete this poultry inventory?")
                    },
                    text = {
                        Text("Are you sure you want to delete this poultry inventory?")
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.onDeletePoultryInventory(poultryInventoryEntry)
                        }) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            deletePoultryInventoryDialogShownState.value = false
                        }) {
                            Text("Dismiss")
                        }
                    }
                )
            }

            poultryInventoryEntry.time = System.currentTimeMillis()
            if(isEditingMode) {
                if(!(isEditedState.value))
                    poultryInventoryEntry.isEdited = shouldEdit(nonStatePoultryInventoryEntry, isCurrentValueState, isEditedState)
            }

            SavePoultryContent(
                poultryInventoryModel = poultryInventoryEntry,
                onPoultryInventoryModelChange = { updatePoultryInventoryEntry ->
                    viewModel.onNewPoultryInventoryEntryChange(updatePoultryInventoryEntry)
                }
            )

        }
    )


}

@Composable
fun SavePoultryInventoryTopAppBar(
    shouldSave: Boolean,
    isEditingMode: Boolean,
    onBackClick: () -> Unit,
    onSavePoultryInventoryClick: () -> Unit,
    onDeletePoultryInventoryClick: () -> Unit
) {
    TopAppBar(
            title = {
                Text(
                    text = "Save Inventory",
                    color = MaterialTheme.colors.onPrimary
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Save Poultry Inventory Button",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.primary,
            actions = {
                if (shouldSave) {
                    IconButton(onClick = onSavePoultryInventoryClick) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Save Poultry Inventory",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }

                if (isEditingMode) {
                    IconButton(onClick = onDeletePoultryInventoryClick) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Note Button",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        )
}

@Composable
fun SavePoultryContent(
    poultryInventoryModel: PoultryInventoryModel,
    onPoultryInventoryModelChange: (PoultryInventoryModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        //Bird Count
        ContentText(
            itemName = "BirdCount",
            itemValue = "Qty",
            fontSize = 24,
            fontWeight = FontWeight.Bold
        )
        ContentTextField(
            itemName = "Chicks",
            itemQty = poultryInventoryModel.chicksCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(chicksCount = stringValue))
            },
        )
        ContentTextField(
            itemName = "Hens",
            itemQty = poultryInventoryModel.hensCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(hensCount = stringValue))
            },
        )
        ContentTextField(
            itemName = "Cockerels",
            itemQty = poultryInventoryModel.cockerelsCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(cockerelsCount = stringValue))
            },
        )

        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(20.dp))
        //Bird Count
        ContentText(
            itemName = "Mortality",
            itemValue = "Qty",
            fontSize = 24,
            fontWeight = FontWeight.Bold
        )
        ContentTextField(
            itemName = "Mortality Rate",
            itemQty = poultryInventoryModel.mortalityCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(mortalityCount = stringValue))
            },
        )

        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(20.dp))
        //Daily Feed
        ContentText(
            itemName = "Daily Feed",
            itemValue = "Qty",
            fontSize = 24,
            fontWeight = FontWeight.Bold
        )
        ContentTextField(
            itemName = "Combo Feed",
            itemQty = poultryInventoryModel.comboFeedCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(comboFeedCount = stringValue))
            },
        )
        ContentTextField(
            itemName = "Cracked Corn Feed",
            itemQty = poultryInventoryModel.crackedCornFeedCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(crackedCornFeedCount = stringValue))
            },
        )

        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(20.dp))
        //Egg
            ContentText(
                itemName = "Eggs",
                itemValue = "Qty",
                fontSize = 24,
                fontWeight = FontWeight.Bold
            )
        ContentTextField(
            itemName = "Standard Brown",
            itemQty = poultryInventoryModel.standardBrownEggCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(standardBrownEggCount = stringValue))
            },
        )
        ContentTextField(
            itemName = "Organic",
            itemQty = poultryInventoryModel.organicEggCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(organicEggCount = stringValue))
            },
        )


        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(20.dp))
        //Water
        ContentText(
            itemName = "Water",
            itemValue = "Qty",
            fontSize = 24,
            fontWeight = FontWeight.Bold
        )
        ContentTextField(
            itemName = "Water Consumption",
            itemQty = poultryInventoryModel.mortalityCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(waterConsumptionCount = stringValue))
            },
        )


        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(20.dp))
        //Drugs
        ContentText(
            itemName = "Drugs",
            itemValue = "Qty",
            fontSize = 24,
            fontWeight = FontWeight.Bold
        )
        ContentTextField(
            itemName = "Antibiotics",
            itemQty = poultryInventoryModel.antibioticsDrugCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(antibioticsDrugCount = stringValue))
            },
        )
        ContentTextField(
            itemName = "Regular",
            itemQty = poultryInventoryModel.regularDrugCount,
            onTextChange = { stringValue ->
                onPoultryInventoryModelChange.invoke(poultryInventoryModel.copy(regularDrugCount = stringValue))
            },
        )

    }
}


private fun shouldSave(poultryInventoryModel: PoultryInventoryModel): Boolean {
    return returnInt(poultryInventoryModel.chicksCount) +
            returnInt(poultryInventoryModel.hensCount) +
            returnInt(poultryInventoryModel.chicksCount) +
            returnInt(poultryInventoryModel.chicksCount) +
            returnInt(poultryInventoryModel.chicksCount) +
            returnInt(poultryInventoryModel.chicksCount) +
            returnInt(poultryInventoryModel.chicksCount) +
            returnInt(poultryInventoryModel.chicksCount) +
            returnInt(poultryInventoryModel.chicksCount) +
            returnInt(poultryInventoryModel.chicksCount) +
            returnInt(poultryInventoryModel.chicksCount) != 0
}

private fun shouldEdit(
    nonStatePoultryInventoryEntry: PoultryInventoryModel,
    isCurrentValueState: MutableState<Boolean>,
    isEditedState: MutableState<Boolean>
): Boolean {
    var returnValue = true

    if(isCurrentValueState.value) {
        chicksCount = nonStatePoultryInventoryEntry.chicksCount
        hensCount = nonStatePoultryInventoryEntry.hensCount
        cockerelsCount = nonStatePoultryInventoryEntry.cockerelsCount
        mortalityCount = nonStatePoultryInventoryEntry.mortalityCount
        comboFeedCount = nonStatePoultryInventoryEntry.comboFeedCount
        crackedCornFeedCount = nonStatePoultryInventoryEntry.crackedCornFeedCount
        standardBrownEggCount = nonStatePoultryInventoryEntry.standardBrownEggCount
        organicEggCount = nonStatePoultryInventoryEntry.organicEggCount
        waterConsumptionCount = nonStatePoultryInventoryEntry.waterConsumptionCount
        antibioticsDrugCount = nonStatePoultryInventoryEntry.antibioticsDrugCount
        regularDrugCount = nonStatePoultryInventoryEntry.regularDrugCount

        isCurrentValueState.value = false
    }

    if (isEqual(chicksCount,nonStatePoultryInventoryEntry.chicksCount ) &&
        isEqual(hensCount, nonStatePoultryInventoryEntry.hensCount)  &&
        isEqual(cockerelsCount, nonStatePoultryInventoryEntry.cockerelsCount)  &&
        isEqual(mortalityCount, nonStatePoultryInventoryEntry.mortalityCount)  &&
        isEqual(comboFeedCount, nonStatePoultryInventoryEntry.comboFeedCount)  &&
        isEqual(crackedCornFeedCount, nonStatePoultryInventoryEntry.crackedCornFeedCount)  &&
        isEqual(standardBrownEggCount, nonStatePoultryInventoryEntry.standardBrownEggCount)  &&
        isEqual(organicEggCount, nonStatePoultryInventoryEntry.organicEggCount)  &&
        isEqual(waterConsumptionCount, nonStatePoultryInventoryEntry.waterConsumptionCount)  &&
        isEqual(antibioticsDrugCount, nonStatePoultryInventoryEntry.antibioticsDrugCount)  &&
        isEqual(regularDrugCount, nonStatePoultryInventoryEntry.regularDrugCount)
    ) {
        returnValue = false
    }

    isEditedState.value = false

    Log.d("TAG", "isEdited: is $returnValue")

    return returnValue
}
