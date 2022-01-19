package com.ikechiu.poultryapp.ui.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikechiu.poultryapp.routing.PoultryAppRouter
import com.ikechiu.poultryapp.routing.Screen
import com.ikechiu.poultryapp.ui.theme.PoultryAppThemeSettings
import com.ikechiu.poultryapp.viewmodel.MainViewModel
import java.time.format.TextStyle

@Composable
fun PoultryAppDrawer(
    currentScreen: Screen,
    closeDrawerAction: () -> Unit
) {
//    val backgroundColor = if (PoultryAppThemeSettings.isDarkThemeEnabled)
//        MaterialTheme.colors.primary.copy(alpha = 0.12f) else MaterialTheme.colors.surface

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PoultryAppDrawerHeader()

        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))

        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = "Homepage",
            isSelected = currentScreen == Screen.HomePage,
            onClick = {
                PoultryAppRouter.navigateTo(Screen.HomePage)
                closeDrawerAction()
            }
        )

        ScreenNavigationButton(
            icon = Icons.Filled.AccountBalance,
            label = "Accounts",
            isSelected = currentScreen == Screen.PoultryAccount,
            onClick = {
                PoultryAppRouter.navigateTo(Screen.PoultryAccount)
                closeDrawerAction()
            }
        )

        ScreenNavigationButton(
            icon = Icons.Filled.Inventory,
            label = "Inventories",
            isSelected = currentScreen == Screen.PoultryInventory,
            onClick = {
                PoultryAppRouter.navigateTo(Screen.PoultryInventory)
                closeDrawerAction()
            }
        )

        LightDarkTheme()
    }
}

@Composable
fun PoultryAppDrawerHeader() {
    Row(modifier = Modifier.fillMaxWidth()) {

        val color = MaterialTheme.colors.primary

        Image(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Drawer Header Icon",
            colorFilter = ColorFilter
                .tint(color = color),
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "PoultryApp",
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            fontSize = 20.sp,
            color = color,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
fun ScreenNavigationButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val colors = MaterialTheme.colors

    val imageAlpha = if (isSelected) 1f else 0.6f

    val textColor = if (isSelected) colors.primaryVariant else colors.primary

    val textFontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal

    val backgroundColor = if (isSelected) colors.primary.copy(alpha = 0.12f) else colors.surface

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Image(
                imageVector = icon,
                contentDescription = "Screen Navigation Button",
                colorFilter = ColorFilter.tint(textColor),
                alpha = imageAlpha
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
                color = textColor,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = textFontWeight
            )
        }
    }
}

@Composable
fun LightDarkTheme() {
    Row(Modifier.padding(8.dp)) {
        Text(
            text = "Turn on dark theme",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
        Switch(
            checked = PoultryAppThemeSettings.isDarkThemeEnabled,
            onCheckedChange = { PoultryAppThemeSettings.isDarkThemeEnabled = it },
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}