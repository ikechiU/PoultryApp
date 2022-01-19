package com.ikechiu.poultryapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ikechiu.poultryapp.R
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.routing.PoultryAppRouter
import com.ikechiu.poultryapp.routing.Screen
import com.ikechiu.poultryapp.ui.component.PoultryAccountItem
import com.ikechiu.poultryapp.ui.component.PoultryAppDrawer
import com.ikechiu.poultryapp.ui.theme.PoultryAppThemeSettings
import com.ikechiu.poultryapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun PoultryAccountScreen(viewModel: MainViewModel) {

//    val backgroundColor = if (PoultryAppThemeSettings.isDarkThemeEnabled)
//        colors.primary.copy(alpha = 0.3f) else colors.surface

    val poultryAccounts: List<PoultryAccountModel> by viewModel
        .poultryAccounts
        .observeAsState(initial = listOf())

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BackHandler(onBack = {
        PoultryAppRouter.navigateTo(Screen.HomePage)
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Accounts",
                        color = colors.onPrimary
                    )
                },
                backgroundColor = colors.primary,
                navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                        Icon(imageVector = Icons.Filled.List, contentDescription = "Drawer Button")
                    }
                }
            )
        },
        scaffoldState = scaffoldState,
//        backgroundColor = backgroundColor,
        drawerContent = {
            PoultryAppDrawer(
                currentScreen = Screen.PoultryAccount,
                closeDrawerAction = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(5.dp),
                onClick = { viewModel.onCreateNewPoultryAccountClick() },
                contentColor = colors.background,
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Poultry Account Button",
                    )
                }
            )
        }
    ) {
        if(poultryAccounts.isNotEmpty()){
            PoultryAccountList(
                poultryAccounts = poultryAccounts,
                onPoultryAccountClick = {
                    viewModel.onPoultryAccountClick(it)
                }
            )
        }

    }

}

@ExperimentalMaterialApi
@Composable
private fun PoultryAccountList(
    poultryAccounts: List<PoultryAccountModel>,
    onPoultryAccountClick: (PoultryAccountModel) -> Unit,
    isSelected: Boolean = false
) {
    LazyColumn() {
        items(count = poultryAccounts.size) { poultryAccountIndex ->
            val poultryAccount = poultryAccounts[poultryAccountIndex]
            PoultryAccountItem(
                poultryAccount = poultryAccount,
                onPoultryAccountClick = { onPoultryAccountClick(poultryAccount) },
                isSelected = isSelected
            )
        }
    }
}