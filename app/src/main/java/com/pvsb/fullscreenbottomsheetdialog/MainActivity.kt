package com.pvsb.fullscreenbottomsheetdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
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
    }
}