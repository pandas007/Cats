package com.pandasby.cats.main

import com.pandasby.cats.app.App
import com.pandasby.domain.CATS_LIMIT
import com.pandasby.domain.business.GetCatsInteractor
import com.pandasby.domain.entity.CatEntity
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CatsPresenter: MvpPresenter<CatsView>() {

    @Inject
    lateinit var getCatsInteractor: GetCatsInteractor

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appComponent?.inject(this)

        subscribeOnCatList()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun subscribeOnCatList() {
        compositeDisposable.add(getCatsInteractor.getCatListSingle(CATS_LIMIT)
            .subscribe(this::onCatListReceived))
    }

    private fun onCatListReceived(catList: List<CatEntity>) {
        viewState.showCats(ArrayList(catList))
    }

}