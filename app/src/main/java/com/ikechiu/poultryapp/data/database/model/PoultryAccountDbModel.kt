package com.ikechiu.poultryapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PoultryAccountDbModel")
data class PoultryAccountDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "accountTime") var time: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "accountSample") val sample: String = "",
    @ColumnInfo(name = "isEditedAccount") var isEdited: Boolean = false,
    @ColumnInfo(name = "standardBrownEggSales") val standardBrownEggSales: String = "",
    @ColumnInfo(name = "organicEggSales") val organicEggSales: String = "",
    @ColumnInfo(name = "chickSales") val chickSales: String = "",
    @ColumnInfo(name = "henSales") val henSales: String = "",
    @ColumnInfo(name = "cockerelSales") val cockerelSales: String = "",
    @ColumnInfo(name = "dungSales") val dungSales: String = "",
    @ColumnInfo(name = "totalSales") var totalSales: String = "",
    @ColumnInfo(name = "crackedCornFeedExpenses") val crackedCornFeedExpenses: String = "",
    @ColumnInfo(name = "comboFeedExpenses") val comboFeedExpenses: String = "",
    @ColumnInfo(name = "waterExpenses") val waterExpenses: String = "",
    @ColumnInfo(name = "antibioticsDrugExpenses") val antibioticsDrugExpenses: String = "",
    @ColumnInfo(name = "regularDrugExpenses") val regularDrugExpenses: String = "",
    @ColumnInfo(name = "utilityExpenses") val utilityExpenses: String = "",
    @ColumnInfo(name = "totalExpenses") var totalExpenses: String = "",
    @ColumnInfo(name = "profitLoss") var profitLoss: String = ""
) {
    companion object {
        val DEFAULT_POULTRY_ACCOUNTS = listOf(
            PoultryAccountDbModel(
                1, System.currentTimeMillis(), "Sample",false, "100",
                "100", "100", "100", "100",
                "100", "600", "100",
                "100", "100", "100",
                "100", "100", "600", "0"
            )
        )
    }
}
