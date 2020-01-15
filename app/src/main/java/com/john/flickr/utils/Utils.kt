package com.john.flickr.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

class Utils {
    companion object {
        fun showSnackBar(v: View?, text: String?) {
            v?.let {
                text?.let { it1 ->
                    Snackbar.make(it, it1, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}