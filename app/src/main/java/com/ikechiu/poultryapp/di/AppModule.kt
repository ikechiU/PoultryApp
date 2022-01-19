package com.ikechiu.poultryapp.di

import android.content.Context
import androidx.room.Room
import com.ikechiu.poultryapp.data.database.AppDatabase
import com.ikechiu.poultryapp.data.database.dbmapper.DbMapper
import com.ikechiu.poultryapp.data.database.dbmapper.DbMapperImpl
import com.ikechiu.poultryapp.data.local.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun appDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "PoultryAppDatabase.db"
    ).build()

    @Provides
    @Singleton
    fun poultryAccountDao(appDatabase: AppDatabase) = appDatabase.poultryAccountDao()

    @Provides
    @Singleton
    fun poultryInventoryDao(appDatabase: AppDatabase) = appDatabase.poultryInventoryDao()

    @Provides
    @Singleton
    fun dbMapper(): DbMapper = DbMapperImpl()

}