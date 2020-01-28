package com.pandasby.cats.main

import com.pandasby.cats.app.App
import com.pandasby.domain.CATS_LIMIT
import com.pandasby.domain.business.FavoriteCatsInteractor
import com.pandasby.domain.business.CatsInteractor
import com.pandasby.domain.business.SourceInteractor
import com.pandasby.domain.entity.CatEntity
import com.pandasby.domain.entity.FavoriteCatEntity
import com.pandasby.domain.entity.Source
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CatsPresenter: MvpPresenter<CatsView>() {

    @Inject
    lateinit var catsInteractor: CatsInteractor
    @Inject
    lateinit var favoriteCatsInteractor: FavoriteCatsInteractor
    @Inject
    lateinit var sourceInteractor: SourceInteractor

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

    internal fun saveCatImage(bitmapSource: Source) {
        sourceInteractor.saveSource(bitmapSource)
        viewState.showCatImageSavedMessage()
    }

    private fun subscribeOnCatList() {
        compositeDisposable.add(catsInteractor.getCatListSingle(CATS_LIMIT)
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