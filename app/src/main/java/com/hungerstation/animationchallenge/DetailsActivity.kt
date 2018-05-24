package com.hungerstation.animationchallenge

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.view.View
import com.appolica.flubber.Flubber
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        if (Build.VERSION.SDK_INT >= 21) {
            window.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.shared_element)
        }
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tags = arrayOf(tagsView, tv1, tv2, tv3)

        for (i in tags){
            Thread.sleep(200)
            Flubber.with()
                    .autoStart(true)
                    .animation(Flubber.AnimationPreset.MORPH) // Slide up animation
                    .repeatCount(0)                              // Repeat once
                    .duration(500)                              // Last for 1000 milliseconds(1 second)
                    .createFor(i)
                    // Apply it to the view
                    .start()
        }



    }
}
