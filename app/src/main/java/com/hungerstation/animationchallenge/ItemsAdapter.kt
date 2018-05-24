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
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick


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
        holder.tvName?.text = items.get(position)
        holder.container.onClick {
            val pair1 = Pair.create(holder.imgMeal as View, holder.imgMeal.transitionName) as Pair<View, String>
            val pair2 = Pair.create(holder.imgMeal, holder.tvName.transitionName) as Pair<View, String>
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(holder.container.context as Activity, pair1, pair2)
            val intent = Intent(holder.container.context, DetailsActivity::class.java)
            (holder.imgMeal.context as Activity).startActivity(intent, optionsCompat.toBundle())
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvName = view.tv_animal_type
    val imgMeal = view.imgMeal
    val container = view
}