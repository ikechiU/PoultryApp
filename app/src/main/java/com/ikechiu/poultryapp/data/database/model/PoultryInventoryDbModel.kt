package com.ikechiu.poultryapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PoultryInventoryDbModel")
data class PoultryInventoryDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "inventoryTime") var time: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "inventorySample") val sample: String = "",
    @ColumnInfo(name = "isEditedInventory") var isEdited: Boolean = false,
    @ColumnInfo(name = "chicksCount") val chicksCount: String = "",
    @ColumnInfo(name = "hensCount") val hensCount: String = "",
    @ColumnInfo(name = "cockerelsCount") val cockerelsCount: String = "",
    @ColumnInfo(name = "mortalityCount") val mortalityCount: String = "",
    @ColumnInfo(name = "crackedCornFeedCount") val crackedCornFeedCount: String = "",
    @ColumnInfo(name = "comboFeedCount") val comboFeedCount: String = "",
    @ColumnInfo(name = "standardBrownEggCount") val standardBrownEggCount: String = "",
    @ColumnInfo(name = "organicEggCount") val organicEggCount: String = "",
    @ColumnInfo(name = "waterConsumptionCount") val waterConsumptionCount: String = "",
    @ColumnInfo(name = "antibioticsDrugCount") val antibioticsDrugCount: String = "",
    @ColumnInfo(name = "regularDrugCount") val regularDrugCount: String = ""
) {

    companion object {
        val DEFAULT_POULTRY_INVENTORY = listOf(
            PoultryInventoryDbModel(
                1, System.currentTimeMillis(), "Sample",false,
                "10", "10", "10", "10",
                "10", "10", "10",
                "10", "10", "10",
                "10"
            )
        )
    }

}