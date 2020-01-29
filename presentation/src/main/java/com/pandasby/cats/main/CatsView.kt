package com.pandasby.cats.main

import com.pandasby.domain.entity.CatEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CatsView: MvpView {

    @StateStrategyType(SingleStateStrategy::class)
    fun showCats(catList: ArrayList<CatEntity>)
    @StateStrategyType(SingleStateStrategy::class)
    fun showProgress()
    @StateStrategyType(SingleStateStrategy::class)
    fun hideRefreshProgress()
    @StateStrategyType(SkipStrategy::class)
    fun showAddFavoriteCatMessage()
    @StateStrategyType(SkipStrategy::class)
    fun showCatImageSavedMessage()
}