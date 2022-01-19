package com.ikechiu.poultryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.ikechiu.poultryapp.routing.PoultryAppRouter
import com.ikechiu.poultryapp.routing.Screen
import com.ikechiu.poultryapp.ui.screens.*
import com.ikechiu.poultryapp.ui.screens.PoultryInventoryScreen
import com.ikechiu.poultryapp.ui.theme.PoultryAppTheme
import com.ikechiu.poultryapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContent {
            PoultryAppTheme {
               MainActivityScreen(viewModel = viewModel)
//                PoultryAccountDesign()
//                PoultryInventoryDesign()
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
private fun MainActivityScreen(viewModel: MainViewModel) {
    Surface {
        when (PoultryAppRouter.currentScreen) {
            is Screen.HomePage -> PoultryHomePageScreen(viewModel = viewModel)
            is Screen.PoultryAccount -> PoultryAccountScreen(viewModel = viewModel)
            is Screen.PoultryInventory -> PoultryInventoryScreen(viewModel = viewModel)
            is Screen.SavePoultryAccount -> SavePoultryAccountScreen(viewModel = viewModel)
            is Screen.SavePoultryInventory -> SavePoultryInventoryScreen(viewModel = viewModel)
        }
    }
}