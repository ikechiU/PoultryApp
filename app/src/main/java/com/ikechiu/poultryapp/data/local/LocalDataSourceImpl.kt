package com.ikechiu.poultryapp.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ikechiu.poultryapp.data.database.dao.PoultryAccountDao
import com.ikechiu.poultryapp.data.database.dao.PoultryInventoryDao
import com.ikechiu.poultryapp.data.database.dbmapper.DbMapper
import com.ikechiu.poultryapp.data.database.model.PoultryAccountDbModel
import com.ikechiu.poultryapp.data.database.model.PoultryInventoryDbModel
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.domain.model.PoultryInventoryModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val poultryAccountDao: PoultryAccountDao,
    private val poultryInventoryDao: PoultryInventoryDao,
    private val dbMapper: DbMapper
) : LocalDataSource {

    init {
        initDatabase(this::updatePoultryAccountLiveData)
        initDatabase(this::updatePoultryInventoryLiveData)
    }

    private fun initDatabase(postInitAction: () -> Unit) {
        GlobalScope.launch {

            // Prepopulate Poultry Account
            val poultryAccount = PoultryAccountDbModel
                .DEFAULT_POULTRY_ACCOUNTS.toTypedArray()
            val dbPoultryAccount = poultryAccountDao.getAllPoultryAccounts()
            if (dbPoultryAccount.isNullOrEmpty()) {
                poultryAccountDao.insertAll(*poultryAccount)
            }

            //Prepopulate Poultry Inventory
            val poultryInventory = PoultryInventoryDbModel
                .DEFAULT_POULTRY_INVENTORY.toTypedArray()
            val dbPoultryInventory = poultryInventoryDao.getAllPoultryInventories()
            if (dbPoultryInventory.isNullOrEmpty()){
                poultryInventoryDao.insertAll(*poultryInventory)
            }

            postInitAction.invoke()

        }
    }

    private val _getAllPoultryAccounts: MutableLiveData<List<PoultryAccountModel>> by lazy {
        MutableLiveData<List<PoultryAccountModel>>()
    }

    private val _getAllPoultryInventories: MutableLiveData<List<PoultryInventoryModel>> by lazy {
        MutableLiveData<List<PoultryInventoryModel>>()
    }

    override fun insertPoultryAccount(poultryAccountModel: PoultryAccountModel) {
        poultryAccountDao.insert(dbMapper.mapDbPoultryAccount(poultryAccountModel))
        updatePoultryAccountLiveData()
    }

    override fun deletePoultryAccount(poultryAccountModelId: Long) {
        poultryAccountDao.deleteAll(poultryAccountModelId)
        updatePoultryAccountLiveData()
    }

    override fun deletePoultryAccounts(poultryAccountModelIds: List<Long>) {
        poultryAccountDao.deleteAll(poultryAccountModelIds)
        updatePoultryAccountLiveData()
    }

    override fun getAllPoultryAccounts(): LiveData<List<PoultryAccountModel>> =
        _getAllPoultryAccounts


    override fun insertPoultryInventory(poultryInventoryModel: PoultryInventoryModel) {
        poultryInventoryDao.insert(dbMapper.mapDbPoultryInventory(poultryInventoryModel))
        updatePoultryInventoryLiveData()
    }

    override fun deletePoultryInventory(poultryInventoryModelId: Long) {
        poultryInventoryDao.delete(poultryInventoryModelId)
        updatePoultryInventoryLiveData()
    }

    override fun deletePoultryInventories(poultryInventoryModelIds: List<Long>) {
        poultryInventoryDao.deleteAll(poultryInventoryModelIds)
        updatePoultryInventoryLiveData()
    }

    override fun getAllPoultryInventories(): LiveData<List<PoultryInventoryModel>> =
        _getAllPoultryInventories


    // List of PoultryAccountModel

    private fun getAllPoultryAccountsSync(): List<PoultryAccountModel> {
        val dbPoultryAccounts: List<PoultryAccountDbModel> =
            poultryAccountDao.getAllPoultryAccounts()
        return dbMapper.mapPoultryAccounts(dbPoultryAccounts)
    }

    // List of PoultryInventoryModel

    private fun getAllPoultryInventoriesSync(): List<PoultryInventoryModel> {
        val dbPoultryInventories: List<PoultryInventoryDbModel> =
            poultryInventoryDao.getAllPoultryInventories()
        return dbMapper.mapPoultryInventories(dbPoultryInventories)
    }

    private fun updatePoultryAccountLiveData() {
        _getAllPoultryAccounts.postValue(getAllPoultryAccountsSync())
    }

    private fun updatePoultryInventoryLiveData() {
        _getAllPoultryInventories.postValue(getAllPoultryInventoriesSync())
    }


    //Initial Sample List of PoultryAccountModel
   fun getSamplePoultryAccountModel(): List<PoultryAccountModel> {
        val dbPoultryAccounts: List<PoultryAccountDbModel> =
            PoultryAccountDbModel.DEFAULT_POULTRY_ACCOUNTS
        return dbMapper.mapPoultryAccounts(dbPoultryAccounts)
    }

    //Initial Sample List of PoultryInventoryModel
   fun getSamplePoultryInventoryModel(): List<PoultryInventoryModel> {
        val dbPoultryInventories: List<PoultryInventoryDbModel> =
            PoultryInventoryDbModel.DEFAULT_POULTRY_INVENTORY
        return dbMapper.mapPoultryInventories(dbPoultryInventories)
    }

    fun initAccountDb() {
        initDatabase(this::updatePoultryAccountLiveData)
    }

    fun initInventoryDb() {
        initDatabase(this::updatePoultryInventoryLiveData)
    }

}