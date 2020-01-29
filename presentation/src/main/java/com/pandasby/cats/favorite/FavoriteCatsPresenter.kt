package com.pandasby.cats.favorite

import com.pandasby.cats.app.App
import com.pandasby.domain.business.FavoriteCatsInteractor
import com.pandasby.domain.entity.FavoriteCatEntity
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class FavoriteCatsPresenter : MvpPresenter<FavoriteCatsView>() {

    @Inject
    lateinit var favoriteCatsInteractor: FavoriteCatsInteractor

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appComponent?.inject(this)

        subscribeOnFavoriteCatsList()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun subscribeOnFavoriteCatsList() {
        compositeDisposable.add(favoriteCatsInteractor.getFavoriteCatsList()
            .doOnSubscribe { viewState.showProgress() }
            .subscribe(this::onFavoriteCatListReceived, this::onErrorReceived))
    }

    private fun onFavoriteCatListReceived(catList: List<FavoriteCatEntity>) {
        viewState.showCats(ArrayList(catList))
    }

    private fun onErrorReceived(throwable: Throwable) {
        //TODO обработка ошибки
    }

}