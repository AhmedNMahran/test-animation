package com.hungerstation.animationchallenge


import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.os.Build
import android.support.v4.util.Pair
import android.support.annotation.RequiresApi
import com.appolica.flubber.Flubber
import com.appolica.flubber.animation.providers.FadeOut
import com.github.florent37.kotlin.pleaseanimate.please
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

lateinit var callback: View.OnClickListener

class ItemsAdapter(val items: ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.container.onClick {

            callback?.onClick(null)
            holder.container.animate().alpha(0f).setStartDelay(200).setDuration(200).start()


            val pair1 = Pair.create(holder.imgMeal as View, holder.imgMeal.transitionName) as Pair<View, String>
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(holder.container.context as Activity, pair1, pair1)
            val intent = Intent(holder.container.context, DetailsActivity::class.java)
            (holder.imgMeal.context as Activity).startActivity(intent, optionsCompat.toBundle())
        }
    }

    fun setListener(listener: View.OnClickListener) {
        callback = listener
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val imgMeal = view.imgMeal
    val imgLogo = view.imgLogo
    val rating = view.tvRating
    val container = view.item_root

}


