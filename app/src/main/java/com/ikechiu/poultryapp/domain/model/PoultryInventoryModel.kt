package com.ikechiu.poultryapp.domain.model

import com.ikechiu.poultryapp.util.NEW_ID

data class PoultryInventoryModel(
    val id: Long = NEW_ID,
    var time: Long = System.currentTimeMillis(),
    val sample: String = "",
    var isEdited: Boolean = false,
    val chicksCount: String = "",
    val hensCount: String = "",
    val cockerelsCount: String = "",
    val mortalityCount: String = "",
    val crackedCornFeedCount: String = "",
    val comboFeedCount: String = "",
    val standardBrownEggCount: String = "",
    val organicEggCount: String = "",
    val waterConsumptionCount: String = "",
    val antibioticsDrugCount: String = "",
    val regularDrugCount: String = ""
)