package com.ikechiu.poultryapp.routing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class Screen {
  object HomePage : Screen()
  object PoultryAccount : Screen()
  object PoultryInventory : Screen()
  object SavePoultryAccount : Screen()
  object SavePoultryInventory : Screen()
}

object PoultryAppRouter {
  var currentScreen: Screen by mutableStateOf(Screen.HomePage)

  fun navigateTo(destination: Screen) {
    currentScreen = destination
  }
}
