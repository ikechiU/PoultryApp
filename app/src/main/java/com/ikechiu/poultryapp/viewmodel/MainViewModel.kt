package com.ikechiu.poultryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ikechiu.poultryapp.data.database.model.PoultryAccountDbModel
import com.ikechiu.poultryapp.data.database.model.PoultryInventoryDbModel
import com.ikechiu.poultryapp.data.repository.Repository
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.domain.model.PoultryInventoryModel
import com.ikechiu.poultryapp.routing.PoultryAppRouter
import com.ikechiu.poultryapp.routing.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val poultryAccounts: LiveData<List<PoultryAccountModel>> =
        repository.localDatasource.getAllPoultryAccounts()

    val poultryInventories: LiveData<List<PoultryInventoryModel>> = repository.localDatasource
        .getAllPoultryInventories()

    private val _poultryAccountEntry = MutableLiveData(PoultryAccountModel())
    val poultryAccountEntry: LiveData<PoultryAccountModel> = _poultryAccountEntry

    private val _poultryInventoryEntry = MutableLiveData(PoultryInventoryModel())
    val poultryInventoryEntry: LiveData<PoultryInventoryModel> = _poultryInventoryEntry


    fun onCreateNewPoultryAccountClick() {
        _poultryAccountEntry.value = PoultryAccountModel()
        PoultryAppRouter.navigateTo(Screen.SavePoultryAccount)
    }

    fun onPoultryAccountClick(poultryAccountModel: PoultryAccountModel) {
        _poultryAccountEntry.value = poultryAccountModel
        PoultryAppRouter.navigateTo(Screen.SavePoultryAccount)
    }

    fun onNewPoultryAccountEntryChange(poultryAccountModel: PoultryAccountModel) {
        _poultryAccountEntry.value = poultryAccountModel
    }

    fun onCreateNewPoultryInventoryClick() {
        _poultryInventoryEntry.value = PoultryInventoryModel()
        PoultryAppRouter.navigateTo(Screen.SavePoultryInventory)
    }

    fun onPoultryInventoryClick(poultryInventoryModel: PoultryInventoryModel) {
        _poultryInventoryEntry.value = poultryInventoryModel
        PoultryAppRouter.navigateTo(Screen.SavePoultryInventory)
    }

    fun onNewPoultryInventoryEntryChange(poultryInventoryModel: PoultryInventoryModel) {
        _poultryInventoryEntry.value = poultryInventoryModel
    }

    fun onSavePoultryAccount(poultryAccountModel: PoultryAccountModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.localDatasource.insertPoultryAccount(poultryAccountModel)

            withContext(Dispatchers.Main) {
                PoultryAppRouter.navigateTo(Screen.PoultryAccount)
                _poultryAccountEntry.value = PoultryAccountModel()
            }
        }
    }

    fun onSavePoultryInventory(poultryInventoryModel: PoultryInventoryModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.localDatasource.insertPoultryInventory(poultryInventoryModel)

            withContext(Dispatchers.Main) {
                PoultryAppRouter.navigateTo(Screen.PoultryInventory)
                _poultryInventoryEntry.value = PoultryInventoryModel()
            }
        }
    }

    fun onDeletePoultryAccount(poultryAccountModel: PoultryAccountModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.localDatasource.deletePoultryAccount(poultryAccountModel.id)
            repository.localDatasource.initAccountDb()

            withContext(Dispatchers.Main) {
                PoultryAppRouter.navigateTo(Screen.PoultryAccount)
            }
        }
    }

    fun onDeletePoultryInventory(poultryInventoryModel: PoultryInventoryModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.localDatasource.deletePoultryInventory(poultryInventoryModel.id)
            repository.localDatasource.initInventoryDb()

            withContext(Dispatchers.Main) {
                PoultryAppRouter.navigateTo(Screen.PoultryInventory)
            }
        }
    }

    //Initial Sample List of PoultryAccountModel
    fun getSamplePoultryAccountModel(): List<PoultryAccountModel> =
        repository.localDatasource.getSamplePoultryAccountModel()

    //Initial Sample List of PoultryInventoryModel
    fun getSamplePoultryInventoryModel(): List<PoultryInventoryModel> =
        repository.localDatasource.getSamplePoultryInventoryModel()

}