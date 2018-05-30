package com.hungerstation.animationchallenge

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.content_details.*
import android.view.ViewAnimationUtils
import android.support.annotation.RequiresApi
import android.view.View.VISIBLE
import com.github.florent37.kotlin.pleaseanimate.please
import org.jetbrains.anko.dip


class DetailsActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        if (Build.VERSION.SDK_INT >= 21) {
            window.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.shared_element)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // previously invisible view
        val myView = tagsView

        myView.postDelayed({
            showView(myView)
        }, 1000)


    }

    private fun showView(myView: LinearLayout) {


        burger.animate().setDuration(820).translationY(-250f).alpha(1f).start()
        itemsToFade.animate().setDuration(700).setStartDelay(300).translationY(-250f).alpha(1f).start()
        close.animate().scaleXBy(30f).alpha(1f).scaleYBy(30f).setStartDelay(150).setDuration(120).start()
        var params = tagsView.layoutParams
        tagsView.postDelayed({
            object : CountDownTimer(350, 1) {
                var i = 0
                override fun onTick(millisUntilFinished: Long) {
                    i += 5
                    if (i / 10 <= 1)
                        tagsView.alpha = i / 10f

                    var x = dip(i)
                    params.width += x
                    if (params.width <= dip(350))
                        tagsView.layoutParams = params
                }

                override fun onFinish() {
                }
            }.start()
        }, 250)

        burger.visibility = VISIBLE
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            val cx = myView.measuredWidth / 2
            val cy = myView.measuredHeight / 2

            // get the final radius for the clipping circle
            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

            // create the animator for this view (the start radius is zero)
            val anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 15f, finalRadius)
            anim.duration = 10000
            // make the view visible and start the animation
            myView.visibility = VISIBLE
            anim.start()
        } else {
            // set the view to visible without a circular reveal animation below Lollipop
            myView.visibility = VISIBLE
        }
    }


}

