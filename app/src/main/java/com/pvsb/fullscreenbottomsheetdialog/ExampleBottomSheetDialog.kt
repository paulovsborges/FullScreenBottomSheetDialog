package com.pvsb.fullscreenbottomsheetdialog

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExampleBottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onStart() {
        super.onStart()

        val bottomSheetView = (requireView() as? View)

        val parentHeight = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            dialog?.window?.windowManager?.maximumWindowMetrics?.bounds?.height() ?: 1
        } else {
            dialog?.window?.windowManager?.defaultDisplay?.height ?: 1
        }

        val params = bottomSheetView?.layoutParams
        params?.height = parentHeight

        (dialog as? BottomSheetDialog)?.behavior?.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            params?.height?.let { height ->
                peekHeight = height
            }
            peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        }

        bottomSheetView?.layoutParams = params
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.example_bottom_sheet_dialog, container, false)
    }
}