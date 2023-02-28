package com.pvsb.fullscreenbottomsheetdialog

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomSheetParent = findViewById<ConstraintLayout>(R.id.clBottomSheet)
        val mBottomSheetBehaviour = BottomSheetBehavior.from(bottomSheetParent)
        val tv = TypedValue()
        if (theme.resolveAttribute(com.google.android.material.R.attr.actionBarSize, tv, true)) {
            val actionBarHeight = TypedValue.complexToDimensionPixelSize(
                tv.data,
                resources.displayMetrics
            )
            mBottomSheetBehaviour.peekHeight = actionBarHeight / 2
        }

        val ivPeek = findViewById<ImageView>(R.id.ivPeek)

        ivPeek.setOnClickListener {
            if (mBottomSheetBehaviour.state == BottomSheetBehavior.STATE_COLLAPSED) {
                mBottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                mBottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        val callback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        ivPeek.apply {
                            rotate(true)
                            translateX(true)
                        }
                    }
                    else -> {
                        ivPeek.rotate(false)
                        ivPeek.translateX(false)
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        }

        mBottomSheetBehaviour.addBottomSheetCallback(callback)

        val tvTest = findViewById<TextView>(R.id.tvTest)
        tvTest.setOnClickListener {
            mBottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}

fun View.rotate(isRotated: Boolean) {
    val angle = if (!isRotated) {
        0F
    } else {
        180F
    }
    animate().rotation(angle).duration = 250
}

fun View.translateX(isRotated: Boolean) {

    val offSet = if (!isRotated) {
        0F
    } else {
        -450f
    }

    animate().translationX(offSet).duration = 250
}
