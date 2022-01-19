package com.ikechiu.poultryapp.domain.model

import com.ikechiu.poultryapp.util.NEW_ID

data class PoultryAccountModel(
    val id: Long = NEW_ID,
    var time: Long = System.currentTimeMillis(),
    val sample: String = "",
    var isEdited: Boolean = false,
    val standardBrownEggSales: String = "",
    val organicEggSales: String = "",
    val chickSales: String = "",
    val henSales: String = "",
    val cockerelSales: String = "",
    val dungSales: String = "",
    var totalSales: String = "",
    val crackedCornFeedExpenses: String = "",
    val comboFeedExpenses: String = "",
    val waterExpenses: String = "",
    val antibioticsDrugExpenses: String = "",
    val regularDrugExpenses: String = "",
    val utilityExpenses: String = "",
    var totalExpenses: String = "",
    var profitLoss: String = ""
)