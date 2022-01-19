package com.ikechiu.poultryapp.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikechiu.poultryapp.R
import com.ikechiu.poultryapp.domain.model.PoultryInventoryModel
import com.ikechiu.poultryapp.ui.theme.PoultryAppThemeSettings
import com.ikechiu.poultryapp.util.dateCatalog
import java.util.*

@ExperimentalFoundationApi
@Composable
fun PoultryInventoryItem(
    poultryInventory: PoultryInventoryModel,
    onPoultryInventoryClick: (PoultryInventoryModel) -> Unit,
    onPoultryInventoryLongCLick: (Boolean) -> Unit,
    isSelected: Boolean
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colors.background)
            .combinedClickable {
                onPoultryInventoryClick(poultryInventory)
                onPoultryInventoryLongCLick(true)
            }
    ) {
        PoultryInventoryColumnItem(poultryInventory = poultryInventory)
    }

}

@Composable
private fun PoultryInventoryColumnItem(
    modifier: Modifier = Modifier,
    poultryInventory: PoultryInventoryModel
) {
    val background = if(PoultryAppThemeSettings.isDarkThemeEnabled)
        colorResource(id = R.color.surface_night) else colorResource(id = R.color.white)

    Column(
        modifier = Modifier
            .background(background)
            .fillMaxSize()
            .border(1.dp, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(8.dp))
    ) {

        DateTimeInventory(poultryInventory = poultryInventory)
        BirdCount(modifier, poultryInventory)

        Spacer(modifier = Modifier.size(10.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))

        Mortality(modifier, poultryInventory)

        Spacer(modifier = Modifier.size(10.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))

        DailyFeed(modifier, poultryInventory)

        Spacer(modifier = Modifier.size(10.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))

        Egg(modifier, poultryInventory)

        Spacer(modifier = Modifier.size(10.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))

        Water(modifier, poultryInventory)

        Spacer(modifier = Modifier.size(10.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))

        Drug(modifier, poultryInventory)


        Spacer(modifier = Modifier.size(10.dp))
    }
}

@Composable
fun DailyFeed(modifier: Modifier, poultryInventory: PoultryInventoryModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp)
    ) {
        val feed = if(PoultryAppThemeSettings.isDarkThemeEnabled) R.drawable.feed_night else R.drawable.feed
        PoultryInventoryColumnImage(painter = painterResource(id = feed))
        Column(
            modifier.padding(start = 3.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            PoultryInventoryHeader(header = "Daily Feed")
            PoultryInventoryRowText(textOne = "Combo (Bags)", textTwo = poultryInventory.comboFeedCount)
            PoultryInventoryRowText(textOne = "Cracked Corn (Bags)", textTwo = poultryInventory.crackedCornFeedCount)
        }
    }
}


@Composable
fun BirdCount(modifier: Modifier, poultryInventory: PoultryInventoryModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp)
    ) {
        val bird = if(PoultryAppThemeSettings.isDarkThemeEnabled) R.drawable.bird_night else R.drawable.bird
        PoultryInventoryColumnImage(painter = painterResource(id = bird))
        Column(
            modifier.padding(start = 3.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            PoultryInventoryHeader(header = "Bird Count")
            PoultryInventoryRowText(textOne = "Chicks", textTwo = poultryInventory.chicksCount)
            PoultryInventoryRowText(textOne = "Hen", textTwo = poultryInventory.hensCount)
            PoultryInventoryRowText(textOne = "Cockerels", textTwo = poultryInventory.cockerelsCount)
        }
    }
}

@Composable
fun Mortality(modifier: Modifier, poultryInventory: PoultryInventoryModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp)
    ) {
        Column(
            modifier
                .padding(start = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            PoultryInventoryHeader(header = "Mortality")
            PoultryInventoryRowText(textOne = "Mortality Rate", textTwo = poultryInventory.mortalityCount)
        }
    }
}


@Composable
fun Egg(modifier: Modifier, poultryInventory: PoultryInventoryModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp)
    ) {
        val egg = if(PoultryAppThemeSettings.isDarkThemeEnabled) R.drawable.egg_night else R.drawable.egg
        PoultryInventoryColumnImage(painter = painterResource(id = egg))
        Column(
            modifier.padding(start = 3.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            PoultryInventoryHeader(header = "Eggs")
            PoultryInventoryRowText(textOne = "Standard Brown", textTwo = poultryInventory.standardBrownEggCount)
            PoultryInventoryRowText(textOne = "Organic", textTwo = poultryInventory.organicEggCount)
        }
    }
}



@Composable
fun Water(modifier: Modifier, poultryInventory: PoultryInventoryModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp)
    ) {
        Column(
            modifier
                .padding(start = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            PoultryInventoryHeader(header = "Water")
            PoultryInventoryRowText(textOne = "Water Consumption (Liters)", textTwo = poultryInventory.waterConsumptionCount)
        }
    }
}




@Composable
fun Drug(modifier: Modifier, poultryInventory: PoultryInventoryModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp)
    ) {
        val drug = if(PoultryAppThemeSettings.isDarkThemeEnabled) R.drawable.drug_night else R.drawable.drug
        PoultryInventoryColumnImage(painter = painterResource(id = drug))
        Column(
            modifier.padding(start = 3.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            PoultryInventoryHeader(header = "Drugs")
            PoultryInventoryRowText(textOne = "Antibiotics", textTwo = poultryInventory.antibioticsDrugCount)
            PoultryInventoryRowText(textOne = "Multivitamins", textTwo = poultryInventory.regularDrugCount)
        }
    }
}

@Composable
fun DateTimeInventory(poultryInventory: PoultryInventoryModel) {
    val time = dateCatalog(Date(poultryInventory.time))
    if(poultryInventory.isEdited) {
        RowWithText(itemName = "$time  (edited)", itemValue = poultryInventory.sample, fontSize = 10, fontWeight = FontWeight.Light)
    } else {
        RowWithText(itemName = time, itemValue = poultryInventory.sample, fontSize = 10, fontWeight = FontWeight.Light)
    }
}

@Composable
fun PoultryInventoryColumnImage(
    modifier: Modifier = Modifier,
    painter: Painter? = null
) {
    Column(
        modifier = modifier
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        if (painter != null)
            Image(
                painter = painter,
                contentDescription = "Image description",
                alignment = Alignment.TopStart,
            )
    }
}

@Composable
private fun PoultryInventoryHeader(header: String) {
    Text(
        text = header,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
private fun PoultryInventoryRowText(modifier: Modifier = Modifier, textOne: String, textTwo: String) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            text = "$textOne: ",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        )

        Text(
            text = textTwo,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

