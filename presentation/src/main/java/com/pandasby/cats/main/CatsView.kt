package com.pandasby.cats.main

import com.pandasby.domain.entity.CatEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CatsView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCats(catList: ArrayList<CatEntity>)
}