package com.karim.marveldemo.extensions

import androidx.activity.ComponentActivity
import com.karim.marveldemo.TRANSFORMTION_PARAMS
import com.skydoves.transformationlayout.onTransformationEndContainer

fun ComponentActivity.onTransformationEndContainerApplyParams() {
    onTransformationEndContainer(intent.getParcelableExtra(TRANSFORMTION_PARAMS))
}