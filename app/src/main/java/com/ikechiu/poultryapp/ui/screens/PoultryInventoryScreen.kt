package com.ikechiu.poultryapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ikechiu.poultryapp.R
import com.ikechiu.poultryapp.domain.model.PoultryInventoryModel
import com.ikechiu.poultryapp.routing.PoultryAppRouter
import com.ikechiu.poultryapp.routing.Screen
import com.ikechiu.poultryapp.ui.component.PoultryAppDrawer
import com.ikechiu.poultryapp.ui.component.PoultryInventoryItem
import com.ikechiu.poultryapp.ui.theme.PoultryAppThemeSettings
import com.ikechiu.poultryapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun  PoultryInventoryScreen(viewModel: MainViewModel) {

    val poultryInventories by viewModel
        .poultryInventories
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
                    Text(text = "Inventories", color = MaterialTheme.colors.onPrimary)
                },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                        Icon(imageVector = Icons.Filled.List, contentDescription = "Drawer Icon")
                    }
                }
            )
        },
//        backgroundColor = backgroundColor,
        scaffoldState = scaffoldState,
        drawerContent = {
            PoultryAppDrawer(currentScreen = Screen.PoultryInventory) {
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(5.dp),
                onClick = { viewModel.onCreateNewPoultryInventoryClick() },
                contentColor = MaterialTheme.colors.background,
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Poultry Inventory Button" )
                }
            )
        }
    ) {
        if (poultryInventories.isNotEmpty()) {
            PoultryInventoryList(
                poultryInventories = poultryInventories,
                onPoultryInventoryClick = {
                    viewModel.onPoultryInventoryClick(it)
                },
                onPoultryInventoryLongClick = {
                    showDeleteIcon(it)
                }
            )
        }

    }

}

@ExperimentalFoundationApi
@Composable
fun PoultryInventoryList(
    poultryInventories: List<PoultryInventoryModel>,
    onPoultryInventoryClick: (PoultryInventoryModel) -> Unit,
    onPoultryInventoryLongClick: (Boolean) -> Unit,
    isSelected: Boolean = false
) {
    LazyColumn {
        items(poultryInventories.size) { poultryInventoryIndex ->
            val poultryInventoryModel = poultryInventories[poultryInventoryIndex]
            PoultryInventoryItem(
                poultryInventory = poultryInventoryModel,
                onPoultryInventoryClick = { onPoultryInventoryClick(poultryInventoryModel) },
                onPoultryInventoryLongCLick = { onPoultryInventoryLongClick(true) },
                isSelected = isSelected
            )
        }
    }
}

fun showDeleteIcon(action: Boolean) {

}
