package com.pandasby.cats.main

import com.pandasby.cats.app.App
import com.pandasby.domain.CATS_LIMIT
import com.pandasby.domain.business.FavoriteCatsInteractor
import com.pandasby.domain.business.GetCatsInteractor
import com.pandasby.domain.entity.CatEntity
import com.pandasby.domain.entity.FavoriteCatEntity
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CatsPresenter: MvpPresenter<CatsView>() {

    @Inject
    lateinit var getCatsInteractor: GetCatsInteractor
    @Inject
    lateinit var favoriteCatsInteractor: FavoriteCatsInteractor

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

    internal fun onCatClicked(catEntity: CatEntity) {
        compositeDisposable.add(
            favoriteCatsInteractor.addFavoriteCat(FavoriteCatEntity(catEntity.id, catEntity.url))
            .subscribe { viewState.showAddFavoriteCatMessage() }
        )
    }

    private fun subscribeOnCatList() {
        compositeDisposable.add(getCatsInteractor.getCatListSingle(CATS_LIMIT)
            .doOnSubscribe { viewState.showProgress() }
            .subscribe(this::onCatListReceived, this::onErrorReceived))
    }

    private fun onCatListReceived(catList: List<CatEntity>) {
        if (catList.isNotEmpty()) {
            viewState.showCats(ArrayList(catList))
        }
    }

    private fun onErrorReceived(throwable: Throwable) {
        //TODO обработка ошибки
    }

}