package com.example.androidexam.Models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoJob(val task: String, @DrawableRes val warningimage: Int, val islastday:Boolean): Parcelable
{
}