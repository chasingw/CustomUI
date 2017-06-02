package com.robosense.customui.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View

import com.robosense.customui.R

class ConstraintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)
        val constraintLayout: ConstraintLayout = findViewById(R.layout.activity_constraint) as ConstraintLayout
        val constraintSet1 = ConstraintSet()
//        constraintSet1.clone(constraintLayout)

        val constraintSet2 = ConstraintSet()
        constraintSet2.clone(constraintLayout)
//        constraintSet2.centerVertically(R.id.imageView, 0)

        var changed = false
        findViewById(R.id.button).setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)
            val constraint = if(changed) constraintSet1 else constraintSet2
            constraint.applyTo(constraintLayout)
            changed = !changed
        }
    }
}

private fun ConstraintSet.clone(constraintLayout: View?) {


}
