package com.app.presentation.utlis

import android.content.Context
import com.app.domain.model.Gender
import com.app.domain.model.HealthStatus
import com.app.domain.model.HousingStatus
import com.app.domain.model.Relation
import com.app.presentation.R

fun Gender.toLabelResId(context: Context) = when (this) {
    Gender.MALE -> context.getString(R.string.male)
    Gender.FEMALE -> context.getString(R.string.female)
}

fun HealthStatus.toLabelResId(context: Context) = when (this) {
    HealthStatus.NORMAL -> context.getString(R.string.normal)
    HealthStatus.CHRONIC_DISEASE -> context.getString(R.string.chronic_disease)
    HealthStatus.SPECIAL_NEEDS -> context.getString(R.string.special_needs)
    HealthStatus.MARTYR -> context.getString(R.string.martyr)
}

fun HousingStatus.toLabelResId(context: Context) = when (this) {
    HousingStatus.TENT -> context.getString(R.string.tent)
    HousingStatus.RENT -> context.getString(R.string.rent)
    HousingStatus.OWNED -> context.getString(R.string.owned)
}

fun Relation.toLabelResId(context: Context) = when (this) {
    Relation.PARENT -> context.getString(R.string.parent)
    Relation.SPOUSE -> context.getString(R.string.spouse)
    Relation.CHILD -> context.getString(R.string.child)
}