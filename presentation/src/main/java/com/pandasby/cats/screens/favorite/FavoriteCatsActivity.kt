package com.pandasby.cats.screens.favorite

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.pandasby.cats.R
import com.pandasby.cats.screens.favorite.list.FavoriteCatsAdapter
import com.pandasby.domain.entity.FavoriteCatEntity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class FavoriteCatsActivity : MvpAppCompatActivity(), FavoriteCatsView {

    @InjectPresenter
    lateinit var presenter: FavoriteCatsPresenter

    @BindView(R.id.rv_cats)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.progress)
    lateinit var progressBar: ProgressBar
    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    private var adapter: FavoriteCatsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_cats)
        ButterKnife.bind(this)

        adapter = FavoriteCatsAdapter(null)
        with(recyclerView) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = this@FavoriteCatsActivity.adapter
            setHasFixedSize(true)
        }

        setSupportActionBar(toolbar)
    }


    override fun showCats(catList: ArrayList<FavoriteCatEntity>) {
        progressBar.visibility = View.GONE
        adapter?.update(catList)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }
}