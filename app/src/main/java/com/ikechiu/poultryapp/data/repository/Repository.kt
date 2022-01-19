package com.ikechiu.poultryapp.data.repository

import com.ikechiu.poultryapp.data.local.LocalDataSourceImpl
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val localDataSourceImpl: LocalDataSourceImpl
){
    val localDatasource = localDataSourceImpl
}