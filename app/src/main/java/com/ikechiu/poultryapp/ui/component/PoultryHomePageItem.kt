package com.ikechiu.poultryapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ikechiu.poultryapp.R
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.domain.model.PoultryInventoryModel
import com.ikechiu.poultryapp.ui.theme.PoultryAppThemeSettings
import com.ikechiu.poultryapp.util.dateCatalog
import java.util.*

@ExperimentalMaterialApi
@Composable
fun PoultryHomePageItem(
    poultryAccount: PoultryAccountModel,
    poultryInventory: PoultryInventoryModel,
) {
    val background = if(PoultryAppThemeSettings.isDarkThemeEnabled)
        colorResource(id = R.color.surface_night) else colorResource(id = R.color.white)

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        //Bird Count
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .background(background)
                    .fillMaxSize()
                    .border(
                        1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                DateTimeInventory(poultryInventory = poultryInventory)
                BirdCount(Modifier, poultryInventory)
                Spacer(modifier = Modifier.size(10.dp))
            }

        }

        //Mortality
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .background(background)
                    .fillMaxSize()
                    .border(
                        1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                DateTimeInventory(poultryInventory = poultryInventory)
                Mortality(Modifier, poultryInventory)
                Spacer(modifier = Modifier.size(10.dp))
            }

        }


        //DailyFeed
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .background(background)
                    .fillMaxSize()
                    .border(
                        1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                DateTimeInventory(poultryInventory = poultryInventory)
                DailyFeed(Modifier, poultryInventory)
                Spacer(modifier = Modifier.size(10.dp))
            }

        }

        //Egg
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .background(background)
                    .fillMaxSize()
                    .border(
                        1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                DateTimeInventory(poultryInventory = poultryInventory)
                Egg(Modifier, poultryInventory)
                Spacer(modifier = Modifier.size(10.dp))
            }

        }

        //Water
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .background(background)
                    .fillMaxSize()
                    .border(
                        1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                DateTimeInventory(poultryInventory = poultryInventory)
                Water(Modifier, poultryInventory)
                Spacer(modifier = Modifier.size(10.dp))
            }

        }

        //Drug
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .background(background)
                    .fillMaxSize()
                    .border(
                        1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                DateTimeInventory(poultryInventory = poultryInventory)
                Drug(Modifier, poultryInventory)
                Spacer(modifier = Modifier.size(20.dp))
            }

        }

        //Sales
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                DateTimeAccount(poultryAccount)
                Sales(poultryAccount)
            }

        }

        //Expenses
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                DateTimeAccount(poultryAccount)
                Expenses(poultryAccount)
            }

        }

        //ProfitLoss
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                DateTimeAccount(poultryAccount)
                ProfitLoss(poultryAccount)
            }

        }

        Spacer(modifier = Modifier.size(10.dp))
    }


}
