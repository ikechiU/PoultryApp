package com.ikechiu.poultryapp.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.domain.model.PoultryInventoryModel
import com.ikechiu.poultryapp.routing.Screen
import com.ikechiu.poultryapp.ui.component.PoultryAppDrawer
import com.ikechiu.poultryapp.ui.component.PoultryHomePageItem
import com.ikechiu.poultryapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterialApi
fun PoultryHomePageScreen(viewModel: MainViewModel) {

    val poultryAccounts: List<PoultryAccountModel> by viewModel
        .poultryAccounts
        .observeAsState(initial = viewModel.getSamplePoultryAccountModel())

    val poultryInventories by viewModel
        .poultryInventories
        .observeAsState(initial = viewModel.getSamplePoultryInventoryModel())

    val poultryAccountModel: PoultryAccountModel = poultryAccounts.first()
    val poultryInventoryModel: PoultryInventoryModel = poultryInventories.first()

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "HomePage", color = MaterialTheme.colors.onPrimary)
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                            Icon(imageVector = Icons.Filled.List, contentDescription = "Drawer Icon")
                        }
                    }
                )
            },
            scaffoldState = scaffoldState,
            drawerContent = {
                PoultryAppDrawer(currentScreen = Screen.HomePage) {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                }
            },
        ) {
            if (poultryInventories.isNotEmpty() && poultryAccounts.isNotEmpty()) {

                PoultryHomePageItem(
                    poultryAccount = poultryAccountModel,
                    poultryInventory = poultryInventoryModel
                )

            }
        }
}