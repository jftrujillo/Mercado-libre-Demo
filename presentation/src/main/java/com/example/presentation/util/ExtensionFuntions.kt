package com.example.presentation.util

import android.content.Context
import com.example.domain.util.ErrorType
import com.example.presentation.R

fun ErrorType.getErrorMessage(context: Context): String {
    return when (this) {
        is ErrorType.NetworkError -> context.resources.getString(R.string.network_error_message)
        is ErrorType.UnknownError -> context.resources.getString(R.string.unknow_error_message)
    }
}