package com.pandasby.cats.main

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.pandasby.cats.R
import com.pandasby.cats.main.list.CatsAdapter
import com.pandasby.domain.entity.CatEntity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class CatsActivity : MvpAppCompatActivity(), CatsView {

    @InjectPresenter
    lateinit var presenter: CatsPresenter

    @BindView(R.id.rv_cats)
    lateinit var recyclerView: RecyclerView

    private val adapter: CatsAdapter = CatsAdapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cats)
        ButterKnife.bind(this)

        with(recyclerView) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = this@CatsActivity.adapter
            setHasFixedSize(true)
        }
    }

    override fun showCats(catList: ArrayList<CatEntity>) {
        if (catList.isNotEmpty()) {
            adapter.update(catList)
        }
    }
}