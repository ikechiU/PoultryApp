package com.ikechiu.poultryapp.ui.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.routing.PoultryAppRouter
import com.ikechiu.poultryapp.routing.Screen
import com.ikechiu.poultryapp.ui.component.ContentText
import com.ikechiu.poultryapp.ui.component.ContentTextField
import com.ikechiu.poultryapp.ui.theme.PoultryAppThemeSettings
import com.ikechiu.poultryapp.util.*
import com.ikechiu.poultryapp.util.avoidEmptyString
import com.ikechiu.poultryapp.viewmodel.MainViewModel

var standardBrownEggSales = ""
var organicEggSales = ""
var chickSales = ""
var henSales = ""
var cockerelSales = ""
var dungSales = ""
var crackedCornFeedExpenses = ""
var comboFeedExpenses = ""
var waterExpenses = ""
var antibioticsDrugExpenses = ""
var regularDrugExpenses = ""
var utilityExpenses = ""

@Composable
fun SavePoultryAccountScreen(viewModel: MainViewModel) {

    val poultryAccountEntry: PoultryAccountModel by viewModel
        .poultryAccountEntry
        .observeAsState(PoultryAccountModel())

    val isCurrentValueState = remember { mutableStateOf(true) }

    val nonStatePoultryAccountEntry = viewModel.poultryAccountEntry.value!!

    val isEditedState = remember { mutableStateOf(nonStatePoultryAccountEntry.isEdited) }

    BackHandler(onBack = {
        PoultryAppRouter.navigateTo(Screen.PoultryAccount)
    })

    val deletePoultryAccountDialogShownState: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }

    val isEditingMode: Boolean = poultryAccountEntry.id != NEW_ID

    val background = if(PoultryAppThemeSettings.isDarkThemeEnabled)
        MaterialTheme.colors.surface else MaterialTheme.colors.background

    Scaffold(
        topBar = {
            val shouldSave = shouldSave(poultryAccountEntry)
            SavePoultryAccountTopAppBar(
                shouldSave = shouldSave,
                isEditingMode = isEditingMode,
                onBackClick = { PoultryAppRouter.navigateTo(Screen.PoultryAccount) },
                onSavePoultryAccountClick = {
                    viewModel.onSavePoultryAccount(poultryAccountEntry)
                },
                onDeletePoultryAccountClick = { deletePoultryAccountDialogShownState.value = true }
            )
        },
        backgroundColor = background,
        content = {
            if (deletePoultryAccountDialogShownState.value) {
                AlertDialog(
                    onDismissRequest = {
                        deletePoultryAccountDialogShownState.value = false
                    },
                    title = {
                        Text("Delete this poultry account?")
                    },
                    text = {
                        Text("Are you sure you want to delete this poultry account?")
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.onDeletePoultryAccount(poultryAccountEntry)
                        }) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            deletePoultryAccountDialogShownState.value = false
                        }) {
                            Text("Dismiss")
                        }
                    }
                )
            }

            poultryAccountEntry.totalSales = sales(poultryAccountEntry).toString()
            poultryAccountEntry.totalExpenses = expense(poultryAccountEntry).toString()
            poultryAccountEntry.profitLoss = (sales(poultryAccountEntry) - expense(poultryAccountEntry)).toString()
            poultryAccountEntry.time = System.currentTimeMillis()

            if(isEditingMode) {
                if(!(isEditedState.value))
                    poultryAccountEntry.isEdited = shouldEdit(nonStatePoultryAccountEntry, isCurrentValueState, isEditedState)
            }

            SavePoultryContent(
                poultryAccountModel = poultryAccountEntry,
                onPoultryAccountModelChange = { updatePoultryAccountEntry ->
                    viewModel.onNewPoultryAccountEntryChange(updatePoultryAccountEntry)
                }
            )

        }
    )
}


@Composable
private fun SavePoultryAccountTopAppBar(
    shouldSave: Boolean,
    isEditingMode: Boolean,
    onBackClick: () -> Unit,
    onSavePoultryAccountClick: () -> Unit,
    onDeletePoultryAccountClick: () -> Unit,
) {

    TopAppBar(
        title = {
            Text(
                text = "Save Account",
                color = MaterialTheme.colors.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Save Poultry Account Button",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            if (shouldSave) {
                IconButton(onClick = onSavePoultryAccountClick) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Save Poultry Account",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }

            if (isEditingMode) {
                IconButton(onClick = onDeletePoultryAccountClick) {
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
private fun SavePoultryContent(
    poultryAccountModel: PoultryAccountModel,
    onPoultryAccountModelChange: (PoultryAccountModel) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        //Sales
        ContentText(
            itemName = "Sales",
            itemValue = "Amount ₦",
            fontSize = 24,
            fontWeight = FontWeight.Bold
        )
        ContentTextField(
            itemName = "Regular Egg (Crates)",
            itemQty = poultryAccountModel.standardBrownEggSales,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(standardBrownEggSales = stringValue))
            },
        )
        ContentTextField(
            itemName = "Organic Egg (Crates)",
            itemQty = poultryAccountModel.organicEggSales,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(organicEggSales = stringValue))
            },
        )
        ContentTextField(
            itemName = "Chicks",
            itemQty = poultryAccountModel.chickSales,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(chickSales = stringValue))
            },
        )
        ContentTextField(
            itemName = "Hens",
            itemQty = poultryAccountModel.henSales,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(henSales = stringValue))
            },
        )
        ContentTextField(
            itemName = "Cockerels",
            itemQty = poultryAccountModel.cockerelSales,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(cockerelSales = stringValue))
            },
        )
        ContentTextField(
            itemName = "Poultry Dung (Bags)",
            itemQty = poultryAccountModel.dungSales,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(dungSales = stringValue))
            },
        )
        ContentText(
            paddingSize = 16,
            itemName = "Total Sales",
            itemValue = sales(poultryAccountModel = poultryAccountModel).toString()
        )

        //Expenses
        Spacer(modifier = Modifier.size(20.dp))
        ContentText(
            itemName = "Expenses",
            itemValue = "Amount ₦",
            fontSize = 24,
            fontWeight = FontWeight.Bold
        )
        ContentTextField(
            itemName = "CrackedCornFeed (Bags)",
            itemQty = poultryAccountModel.crackedCornFeedExpenses,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(crackedCornFeedExpenses = stringValue))
            },
        )
        ContentTextField(
            itemName = "ComboFeed (Bags)",
            itemQty = poultryAccountModel.comboFeedExpenses,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(comboFeedExpenses = stringValue))
            },
        )
        ContentTextField(
            itemName = "Water (Liters)",
            itemQty = poultryAccountModel.waterExpenses,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(waterExpenses = stringValue))
            },
        )
        ContentTextField(
            itemName = "Antibiotics Drug",
            itemQty = poultryAccountModel.antibioticsDrugExpenses,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(antibioticsDrugExpenses = stringValue))
            },
        )
        ContentTextField(
            itemName = "Multivitamins Drug",
            itemQty = poultryAccountModel.regularDrugExpenses,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(regularDrugExpenses = stringValue))
            },
        )
        ContentTextField(
            itemName = "Utility",
            itemQty = poultryAccountModel.utilityExpenses,
            onTextChange = { stringValue ->
                onPoultryAccountModelChange.invoke(poultryAccountModel.copy(utilityExpenses = stringValue))
            },
        )
        ContentText(
            paddingSize = 16,
            itemName = "Total Expenses",
            itemValue = expense(poultryAccountModel = poultryAccountModel).toString()
        )

        //Profit/Loss
        Spacer(modifier = Modifier.size(10.dp))
        ContentText(
            itemName = "Profit/Loss",
            itemValue = "Amount ₦",
            fontSize = 24,
            fontWeight = FontWeight.Bold
        )
        ContentText( paddingSize = 16, itemName = "Total Sales", itemValue = sales(poultryAccountModel).toString())
        ContentText( paddingSize = 16, itemName = "Total Expenses", itemValue = expense(poultryAccountModel).toString())
        Divider(modifier = Modifier.fillMaxWidth())

        val profitLoss = sales(poultryAccountModel) - expense(poultryAccountModel)
        ContentText( paddingSize = 16, itemName = "Profit(+)/Loss(-)", itemValue = profitLoss.toString())

    }

}

private fun sales(poultryAccountModel: PoultryAccountModel): Int {
    return returnInt(poultryAccountModel.standardBrownEggSales) +
            returnInt(poultryAccountModel.organicEggSales) +
            returnInt(poultryAccountModel.chickSales) +
            returnInt(poultryAccountModel.henSales) +
            returnInt(poultryAccountModel.cockerelSales) +
            returnInt(poultryAccountModel.dungSales)
}

private fun expense(poultryAccountModel: PoultryAccountModel): Int {
    return returnInt(poultryAccountModel.crackedCornFeedExpenses) +
            returnInt(poultryAccountModel.comboFeedExpenses) +
            returnInt(poultryAccountModel.waterExpenses).toInt() +
            returnInt(poultryAccountModel.antibioticsDrugExpenses) +
            returnInt(poultryAccountModel.regularDrugExpenses) +
            returnInt(poultryAccountModel.utilityExpenses)
}

private fun shouldSave(poultryAccountModel: PoultryAccountModel): Boolean {
    return (sales(poultryAccountModel) != 0 || expense(poultryAccountModel) != 0)
}

private fun shouldEdit(
    nonStatePoultryAccountEntry: PoultryAccountModel,
    isCurrentValueState: MutableState<Boolean>,
    isEditedState: MutableState<Boolean>
): Boolean {
    var returnValue = true

    if(isCurrentValueState.value) {
        standardBrownEggSales = nonStatePoultryAccountEntry.standardBrownEggSales
        organicEggSales = nonStatePoultryAccountEntry.organicEggSales
        chickSales = nonStatePoultryAccountEntry.chickSales
        henSales = nonStatePoultryAccountEntry.henSales
        cockerelSales = nonStatePoultryAccountEntry.cockerelSales
        dungSales = nonStatePoultryAccountEntry.dungSales
        crackedCornFeedExpenses = nonStatePoultryAccountEntry.crackedCornFeedExpenses
        comboFeedExpenses = nonStatePoultryAccountEntry.comboFeedExpenses
        waterExpenses = nonStatePoultryAccountEntry.waterExpenses
        antibioticsDrugExpenses = nonStatePoultryAccountEntry.antibioticsDrugExpenses
        regularDrugExpenses = nonStatePoultryAccountEntry.regularDrugExpenses
        utilityExpenses = nonStatePoultryAccountEntry.utilityExpenses

        isCurrentValueState.value = false
    }

    if (isEqual(standardBrownEggSales,nonStatePoultryAccountEntry.standardBrownEggSales ) &&
        isEqual(organicEggSales, nonStatePoultryAccountEntry.organicEggSales)  &&
        isEqual(chickSales, nonStatePoultryAccountEntry.chickSales)  &&
        isEqual(henSales, nonStatePoultryAccountEntry.henSales)  &&
        isEqual(cockerelSales, nonStatePoultryAccountEntry.cockerelSales)  &&
        isEqual(dungSales, nonStatePoultryAccountEntry.dungSales)  &&
        isEqual(crackedCornFeedExpenses, nonStatePoultryAccountEntry.crackedCornFeedExpenses)  &&
        isEqual(comboFeedExpenses, nonStatePoultryAccountEntry.comboFeedExpenses)  &&
        isEqual(waterExpenses, nonStatePoultryAccountEntry.waterExpenses)  &&
        isEqual(antibioticsDrugExpenses, nonStatePoultryAccountEntry.antibioticsDrugExpenses)  &&
        isEqual(regularDrugExpenses, nonStatePoultryAccountEntry.regularDrugExpenses)  &&
        isEqual(utilityExpenses, nonStatePoultryAccountEntry.utilityExpenses)
    ) {
        returnValue = false
    }

    isEditedState.value = false

    Log.d("TAG", "isEdited: is $returnValue")

    return returnValue
}


