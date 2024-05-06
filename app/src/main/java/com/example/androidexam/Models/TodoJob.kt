package com.example.androidexam.Models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoJob(val task: String?=null, @DrawableRes val warningimage: Int?=null, val islastday:Boolean?=null): Parcelable
{
}