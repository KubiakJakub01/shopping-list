package com.example.shopplan.model.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopplan.model.menager.ShopPlanManager
import com.example.shopplan.model.table.ShopPlanModel

class ShopPlanDao(shopPlanDbHelper: ShopPlanDbHelper) {
    private var shopPlanList = mutableListOf<ShopPlanModel>()
    private val shopPlans = MutableLiveData<List<ShopPlanModel>>()
    private var shopPlanManager: ShopPlanManager

    init {
        shopPlanManager = ShopPlanManager(shopPlanDbHelper)
        shopPlanList = shopPlanManager.getShopPlans().toMutableList()
        shopPlans.value = shopPlanList
    }

    fun addShopPlan(shopPlan: ShopPlanModel) {
        shopPlanList.add(shopPlan)
        shopPlans.value = shopPlanList
        shopPlanManager.addShopPlan(shopPlan)
    }

    fun getShopPlans() = shopPlans as LiveData<List<ShopPlanModel>>

    fun updateShopPlan(shopPlan: ShopPlanModel) {
        val index = shopPlanList.indexOfFirst { it.shopPlanID == shopPlan.shopPlanID }
        shopPlanList[index] = shopPlan
        shopPlans.value = shopPlanList
        shopPlanManager.updateShopPlan(shopPlan)
    }
}