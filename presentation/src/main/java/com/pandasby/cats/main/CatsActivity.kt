package com.pandasby.cats.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.pandasby.cats.R
import com.pandasby.cats.favorite.FavoriteCatsActivity
import com.pandasby.cats.main.list.CatsAdapter
import com.pandasby.domain.entity.CatEntity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class CatsActivity : MvpAppCompatActivity(), CatsView {

    @InjectPresenter
    lateinit var presenter: CatsPresenter

    @BindView(R.id.rv_cats)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.progress)
    lateinit var progressBar: ProgressBar
    @BindView(R.id.iv_favorite)
    lateinit var favoriteCats: ImageView

    private var adapter: CatsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cats)
        ButterKnife.bind(this)

        adapter = CatsAdapter(
            null,
            presenter::onCatClicked,
            presenter::onCatLongClicked
        )
        with(recyclerView) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = this@CatsActivity.adapter
            setHasFixedSize(true)
        }

        favoriteCats.setOnClickListener{ showFavoriteCatsScreen() }
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showCats(catList: ArrayList<CatEntity>) {
        progressBar.visibility = View.GONE

        adapter?.update(catList)
    }

    override fun showAddFavoriteCatMessage() {
        Toast.makeText(this, getString(R.string.favorite_cat_added), Toast.LENGTH_SHORT).show()
    }

    private fun showFavoriteCatsScreen() {
        startActivity(Intent(this, FavoriteCatsActivity::class.java))
    }
}