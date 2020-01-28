package com.pandasby.cats.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.pandasby.cats.R
import com.pandasby.cats.favorite.FavoriteCatsActivity
import com.pandasby.cats.main.list.CatsAdapter
import com.pandasby.data.entity.CatBitmapSource
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

    private val PERMISSION_REQUEST_CODE = 9

    private var adapter: CatsAdapter? = null
    private var tempCatBitmapSource: CatBitmapSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cats)
        ButterKnife.bind(this)


        adapter = CatsAdapter(
            null,
            presenter::onCatClicked,
            this::onCatLongClicked
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

    override fun showCatImageSavedMessage() {
        Toast.makeText(this, getString(R.string.cat_image_saved), Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onWriteStoragePermissionGranted()
            }
        }
    }

    private fun checkWriteStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                //Already granted
                onWriteStoragePermissionGranted()
            } else {
                //Didn't grant -> request permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE
                )
            }
        } else {
            onWriteStoragePermissionGranted()
        }
    }

    private fun onCatLongClicked(bitmap: Bitmap, name: String) {
        tempCatBitmapSource = CatBitmapSource(bitmap, name)
        checkWriteStoragePermission()
    }

    private fun onWriteStoragePermissionGranted() {
        if (tempCatBitmapSource != null) {
            presenter.saveCatImage(tempCatBitmapSource!!)
            tempCatBitmapSource = null
        }
    }

    private fun showFavoriteCatsScreen() {
        startActivity(Intent(this, FavoriteCatsActivity::class.java))
    }
}