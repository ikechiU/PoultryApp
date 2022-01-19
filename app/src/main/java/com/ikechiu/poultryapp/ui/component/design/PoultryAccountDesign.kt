package com.ikechiu.poultryapp.ui.component.design

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PoultryAccountDesign() {
    val organicEgg = remember { mutableStateOf("") }
    val organicEggInt = remember { mutableStateOf(0) }
    val regularEgg = remember { mutableStateOf("") }
    val regularEggInt = remember { mutableStateOf(0) }
    val chicks = remember { mutableStateOf("") }
    val chicksInt = remember { mutableStateOf(0) }
    val hen = remember { mutableStateOf("") }
    val henInt = remember { mutableStateOf(0) }
    val cockerel = remember { mutableStateOf("") }
    val cockerelInt = remember { mutableStateOf(0) }
    val poultryDung = remember { mutableStateOf("") }
    val poultryDungInt = remember { mutableStateOf(0) }

    val totalSales = organicEggInt.value + regularEggInt.value + chicksInt.value +
            henInt.value + cockerelInt.value + poultryDungInt.value


    val crackedCornFeed = remember { mutableStateOf("") }
    val crackedCornFeedInt = remember { mutableStateOf(0) }
    val comboFeed = remember { mutableStateOf("") }
    val comboFeedInt = remember { mutableStateOf(0) }
    val water = remember { mutableStateOf("") }
    val waterInt = remember { mutableStateOf(0) }
    val antibioticsDrug = remember { mutableStateOf("") }
    val antibioticsDrugInt = remember { mutableStateOf(0) }
    val regularDrug = remember { mutableStateOf("") }
    val regularDrugInt = remember { mutableStateOf(0) }
    val utility = remember { mutableStateOf("") }
    val utilityInt = remember { mutableStateOf(0) }

    val totalExpenses = crackedCornFeedInt.value + comboFeedInt.value + waterInt.value +
            antibioticsDrugInt.value + regularDrugInt.value + utilityInt.value

    val profitLoss = totalSales - totalExpenses

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        RowWithText(itemName = "Friday, December 17,2021 00:15:05  (edited)",
            itemValue = "", fontSize = 10, fontWeight = FontWeight.Light)

        RowWithText(itemName = "Sales", itemValue = "Amount ₦", fontSize = 24, fontWeight = FontWeight.Bold)

        Sales(
            organicEgg,
            organicEggInt,
            regularEgg,
            regularEggInt,
            chicks,
            chicksInt,
            hen,
            henInt,
            cockerel,
            cockerelInt,
            poultryDung,
            poultryDungInt,
            totalSales
        )

        Spacer(modifier = Modifier.size(20.dp))

        RowWithText(itemName = "Expenses", itemValue = "Amount ₦", fontSize = 24, fontWeight = FontWeight.Bold)

        Expenses(
            crackedCornFeed,
            crackedCornFeedInt,
            comboFeed,
            comboFeedInt,
            water,
            waterInt,
            antibioticsDrug,
            antibioticsDrugInt,
            regularDrug,
            regularDrugInt,
            utility,
            utilityInt,
            totalExpenses
        )

        Spacer(modifier = Modifier.size(20.dp))

        RowWithText(itemName = "ProfitLoss", itemValue = "Amount ₦", fontSize = 24, fontWeight = FontWeight.Bold)

        ProfitLoss(totalSales, totalExpenses, profitLoss)
    }
}

@Composable
private fun Sales(
    organicEgg: MutableState<String>,
    organicEggInt: MutableState<Int>,
    regularEgg: MutableState<String>,
    regularEggInt: MutableState<Int>,
    chicks: MutableState<String>,
    chicksInt: MutableState<Int>,
    hen: MutableState<String>,
    henInt: MutableState<Int>,
    cockerel: MutableState<String>,
    cockerelInt: MutableState<Int>,
    poultryDung: MutableState<String>,
    poultryDungInt: MutableState<Int>,
    totalSales: Int
) {
    RowWithTextField(itemName = "Organic Egg (Crates)", itemQty = organicEgg, organicEggInt)
    RowWithTextField(itemName = "Regular Egg (Crates)", itemQty = regularEgg, regularEggInt)
    RowWithTextField(itemName = "Chicks", itemQty = chicks, chicksInt)
    RowWithTextField(itemName = "Hens", itemQty = hen, henInt)
    RowWithTextField(itemName = "Cockerels", itemQty = cockerel, cockerelInt)
    RowWithTextField(itemName = "Poultry Dung (Bags)", itemQty = poultryDung, poultryDungInt)
    RowWithText(modifier = Modifier.padding(16.dp), itemName = "Total Sales", itemValue = totalSales.toString())
}

@Composable
private fun Expenses(
    crackedCornFeed: MutableState<String>,
    crackedCornFeedInt: MutableState<Int>,
    comboFeed: MutableState<String>,
    comboFeedInt: MutableState<Int>,
    water: MutableState<String>,
    waterInt: MutableState<Int>,
    antibioticsDrug: MutableState<String>,
    antibioticsDrugInt: MutableState<Int>,
    regularDrug: MutableState<String>,
    regularDrugInt: MutableState<Int>,
    utility: MutableState<String>,
    utilityInt: MutableState<Int>,
    totalExpenses: Int
) {

    RowWithTextField(itemName = "CrackedCornFeed (Bags)", itemQty = crackedCornFeed, crackedCornFeedInt)
    RowWithTextField(itemName = "ComboFeed (Bags)", itemQty = comboFeed, comboFeedInt)
    RowWithTextField(itemName = "Water (Liters)", itemQty = water, waterInt)
    RowWithTextField(itemName = "Antibiotics Drug", itemQty = antibioticsDrug, antibioticsDrugInt)
    RowWithTextField(itemName = "Regular Drug", itemQty = regularDrug, regularDrugInt)
    RowWithTextField(itemName = "Utility", itemQty = utility, utilityInt)
    RowWithText(modifier = Modifier.padding(16.dp), itemName = "Total Expenses", itemValue = totalExpenses.toString())

}

@Composable
private fun ProfitLoss(totalSales: Int, totalExpenses: Int, profitLoss: Int) {
    Spacer(modifier = Modifier.size(5.dp))
    RowWithText(itemName = "Total Sales", itemValue = totalSales.toString())
    Spacer(modifier = Modifier.size(5.dp))
    RowWithText(itemName = "Total Expenses", itemValue = totalExpenses.toString())
    Divider(modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.size(10.dp))
    RowWithText(itemName = "Profit(+)/Loss(-)", itemValue = profitLoss.toString())
}

@Composable
private fun RowWithTextField(
    itemName: String,
    itemQty: MutableState<String>,
    itemQtyInt: MutableState<Int>,
    isEnabled: Boolean = true
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.weight(1f),
            text = itemName
        )

        TextField(
            modifier = Modifier.weight(0.4f),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.onPrimary,
                cursorColor = MaterialTheme.colors.onSecondary,
                focusedIndicatorColor = MaterialTheme.colors.onSecondary
            ),
            enabled = isEnabled,
            singleLine = true,
            value = itemQty.value,
            onValueChange = { stringValue ->
                if (stringValue == "") {
                    itemQtyInt.value = 0
                } else if (stringValue != "") {
                    itemQtyInt.value = stringValue.toInt()
                }
                itemQty.value = stringValue
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
private fun RowWithText(
    modifier: Modifier = Modifier,
    itemName: String,
    itemValue: String,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            modifier = modifier
                .weight(1f),
            text = itemName,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = fontWeight
            )
        )

        Text(
            textAlign = TextAlign.End,
            text = itemValue,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = fontWeight,
            )
        )
    }
}

@Preview
@Composable
private fun PoultryAccountCardPreview() {
    PoultryAccountDesign()
}
