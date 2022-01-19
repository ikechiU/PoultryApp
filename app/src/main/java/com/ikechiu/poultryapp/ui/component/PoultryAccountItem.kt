package com.ikechiu.poultryapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ikechiu.poultryapp.R
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.ui.theme.PoultryAppThemeSettings
import com.ikechiu.poultryapp.util.dateCatalog
import java.util.*

@ExperimentalMaterialApi
@Composable
fun PoultryAccountItem(
    poultryAccount: PoultryAccountModel,
    onPoultryAccountClick: (PoultryAccountModel) -> Unit,
    isSelected: Boolean
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { onPoultryAccountClick(poultryAccount) })
            .background(MaterialTheme.colors.background)
    ) {
        val background = if(PoultryAppThemeSettings.isDarkThemeEnabled)
            colorResource(id = R.color.surface_night) else colorResource(id = R.color.white)

        Column(
            modifier = Modifier
                .background(background)
                .fillMaxSize()
                .border(1.dp, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(8.dp))
        ) {
            DateTimeAccount(poultryAccount)
            Sales(poultryAccount)
            Expenses(poultryAccount)
            ProfitLoss(poultryAccount)
        }

    }
}

@Composable
fun DateTimeAccount(poultryAccount: PoultryAccountModel) {
    val time = dateCatalog(Date(poultryAccount.time))
    if(poultryAccount.isEdited) {
        RowWithText(itemName = "$time  (edited)", itemValue = poultryAccount.sample, fontSize = 10, fontWeight = FontWeight.Light)
    } else {
        RowWithText(itemName = time, itemValue = poultryAccount.sample, fontSize = 10, fontWeight = FontWeight.Light)
    }
}

@Composable
fun Sales(poultryAccount: PoultryAccountModel) {
    RowWithText(itemName = "Sales", itemValue = "Amount ₦", fontSize = 24, fontWeight = FontWeight.Bold)
    RowWithText(itemName = "Regular Egg (Crates)", itemValue = poultryAccount.standardBrownEggSales)
    RowWithText(itemName = "Organic Egg (Crates)", itemValue = poultryAccount.organicEggSales)
    RowWithText(itemName = "Chicks", itemValue = poultryAccount.chickSales)
    RowWithText(itemName = "Hens", itemValue = poultryAccount.henSales)
    RowWithText(itemName = "Cockerels", itemValue = poultryAccount.cockerelSales)
    RowWithText(itemName = "Poultry Dung (Bags)", itemValue = poultryAccount.dungSales)
    Divider(modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.size(5.dp))
    RowWithText(itemName = "Total Sales", itemValue = poultryAccount.totalSales)
}

@Composable
fun Expenses(poultryAccount: PoultryAccountModel) {
    Spacer(modifier = Modifier.size(10.dp))
    RowWithText(itemName = "Expenses", itemValue = "Amount ₦", fontSize = 24, fontWeight = FontWeight.Bold)
    RowWithText(itemName = "CrackedCornFeed (Bags)", itemValue = poultryAccount.crackedCornFeedExpenses)
    RowWithText(itemName = "ComboFeed (Bags)", itemValue = poultryAccount.comboFeedExpenses)
    RowWithText(itemName = "Water (Liters)", itemValue = poultryAccount.waterExpenses)
    RowWithText(itemName = "Antibiotics Drug", itemValue = poultryAccount.antibioticsDrugExpenses)
    RowWithText(itemName = "Multivitamins Drug", itemValue = poultryAccount.regularDrugExpenses)
    RowWithText(itemName = "Utility", itemValue = poultryAccount.utilityExpenses)
    Divider(modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.size(5.dp))
    RowWithText(itemName = "Total Expenses", itemValue = poultryAccount.totalExpenses)
}

@Composable
fun ProfitLoss(poultryAccount: PoultryAccountModel) {
    Spacer(modifier = Modifier.size(10.dp))
    RowWithText(itemName = "ProfitLoss", itemValue = "Amount ₦", fontSize = 24, fontWeight = FontWeight.Bold)
    RowWithText(itemName = "Total Sales", itemValue = poultryAccount.totalSales)
    RowWithText(itemName = "Total Expenses", itemValue = poultryAccount.totalExpenses)
    Divider(modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.size(5.dp))
    RowWithText(itemName = "Profit(+)/Loss(-)", itemValue = poultryAccount.profitLoss)
}
