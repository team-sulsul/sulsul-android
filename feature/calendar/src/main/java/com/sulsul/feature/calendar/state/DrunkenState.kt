package com.sulsul.feature.calendar.state

import com.sulsul.feature.calendar.R

enum class DrunkenState(
    val level: Int,
    val whale: Int,
) {
    DRUNKEN_DEFAULT(
        level = 0,
        whale = R.drawable.img_drunken_whale_empty
    ),
    DRUNKEN_LEVEL_1(
        level = 1,
        whale = com.sulsul.core.designsystem.R.drawable.img_drunken_whale_1
    ),
    DRUNKEN_LEVEL_2(
        level = 2,
        whale = com.sulsul.core.designsystem.R.drawable.img_drunken_whale_2
    ),
    DRUNKEN_LEVEL_3(
        level = 3,
        whale = com.sulsul.core.designsystem.R.drawable.img_drunken_whale_3
    ),
    DRUNKEN_LEVEL_4(
        level = 4,
        whale = com.sulsul.core.designsystem.R.drawable.img_drunken_whale_4
    ),
    DRUNKEN_LEVEL_5(
        level = 5,
        whale = com.sulsul.core.designsystem.R.drawable.img_drunken_whale_5
    )
}
