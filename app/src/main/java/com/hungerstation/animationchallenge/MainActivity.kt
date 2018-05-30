package com.hungerstation.animationchallenge

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionInflater
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with animals
    val items: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    // Adds animals to the empty animals ArrayList

    fun addItems() {
        for (i in 0..15) {
            items.add("Burger $i delicious :D ")
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= 21) {
            window.sharedElementExitTransition = TransitionInflater.from(this).inflateTransition(R.transition.shared_element)
        }

        // Loads items into the ArrayList
        addItems()

        // Creates a vertical Layout Manager
        rv_meals_list.layoutManager = LinearLayoutManager(this)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        // Access the RecyclerView Adapter and load the data into it
        rv_meals_list.adapter = ItemsAdapter(items, this)
    }
}
