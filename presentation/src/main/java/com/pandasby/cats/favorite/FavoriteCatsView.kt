package com.pandasby.cats.favorite

import com.pandasby.domain.entity.FavoriteCatEntity
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface FavoriteCatsView : MvpView {

    @StateStrategyType(SingleStateStrategy::class)
    fun showCats(catList: ArrayList<FavoriteCatEntity>)
    @StateStrategyType(SingleStateStrategy::class)
    fun showProgress()
}