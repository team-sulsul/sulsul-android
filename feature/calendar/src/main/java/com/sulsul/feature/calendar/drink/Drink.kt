package com.sulsul.feature.calendar.drink

import com.sulsul.feature.calendar.R

// TODO: 술계산기
enum class Drink(
    var title: String,
    var isSelected: Boolean,
    var bottleCnt: Int,
    var glassCnt: Int,
    val mainColor: Int,
    val background: Int,
    val buttonColor: Int,
    val mainImage: Int,
    val bottleImage: Int?,
    val glassImage: Int,
) {
    SOJU(
        title = "소주",
        isSelected = false,
        bottleCnt = 0,
        glassCnt = 0,
        mainColor = com.sulsul.core.designsystem.R.color.green_soju,
        background = R.drawable.selector_soju_bg,
        buttonColor = R.drawable.selector_soju_btn,
        mainImage = R.drawable.selector_soju_img,
        bottleImage = R.drawable.img_soju_bottle,
        glassImage = R.drawable.img_soju_glass
    ),
    BEER(
        title = "맥주",
        isSelected = false,
        bottleCnt = 0,
        glassCnt = 0,
        mainColor = com.sulsul.core.designsystem.R.color.yellow_beer,
        background = R.drawable.selector_beer_bg,
        buttonColor = R.drawable.selector_beer_btn,
        mainImage = R.drawable.selector_beer_img,
        bottleImage = R.drawable.img_beer_bottle,
        glassImage = R.drawable.img_beer_glass
    ),
    SOJUBEER(
        title = "소맥",
        isSelected = false,
        bottleCnt = 0,
        glassCnt = 0,
        mainColor = com.sulsul.core.designsystem.R.color.yellow_beer,
        background = R.drawable.selector_beer_bg,
        buttonColor = R.drawable.selector_beer_btn,
        mainImage = R.drawable.selector_sojubeer_img,
        bottleImage = null,
        glassImage = R.drawable.img_sojubeer_glass
    ),
    WINE(
        title = "와인",
        isSelected = false,
        bottleCnt = 0,
        glassCnt = 0,
        mainColor = com.sulsul.core.designsystem.R.color.purple_wine,
        background = R.drawable.selector_wine_bg,
        buttonColor = R.drawable.selector_wine_btn,
        mainImage = R.drawable.selector_wine_img,
        bottleImage = R.drawable.img_wine_bottle,
        glassImage = R.drawable.img_wine_glass
    ),
    RICE_WINE(
        title = "막걸리",
        isSelected = false,
        bottleCnt = 0,
        glassCnt = 0,
        mainColor = com.sulsul.core.designsystem.R.color.beige_rice_wine,
        background = R.drawable.selector_rice_wine_bg,
        buttonColor = R.drawable.selector_rice_wine_btn,
        mainImage = R.drawable.selector_rice_wine_img,
        bottleImage = R.drawable.img_rice_wine_bottle,
        glassImage = R.drawable.img_rice_wine_glass
    ),
    COCKTAIL(
        title = "칵테일",
        isSelected = false,
        bottleCnt = 0,
        glassCnt = 0,
        mainColor = com.sulsul.core.designsystem.R.color.red_cocktail,
        background = R.drawable.selector_cocktail_bg,
        buttonColor = R.drawable.selector_cocktail_btn,
        mainImage = R.drawable.selector_cocktail_img,
        bottleImage = null,
        glassImage = R.drawable.img_cocktail_glass
    ),
    WHISKY(
        title = "위스키",
        isSelected = false,
        bottleCnt = 0,
        glassCnt = 0,
        mainColor = com.sulsul.core.designsystem.R.color.brown_whisky,
        background = R.drawable.selector_whisky_bg,
        buttonColor = R.drawable.selector_whisky_btn,
        mainImage = R.drawable.selector_whisky_img,
        bottleImage = R.drawable.img_whisky_bottle,
        glassImage = R.drawable.img_whisky_glass
    ),
    VODKA(
        title = "보드카",
        isSelected = false,
        bottleCnt = 0,
        glassCnt = 0,
        mainColor = com.sulsul.core.designsystem.R.color.blue_vodka,
        background = R.drawable.selector_vodka_bg,
        buttonColor = R.drawable.selector_vodka_btn,
        mainImage = R.drawable.selector_vodka_img,
        bottleImage = R.drawable.img_vodka_bottle,
        glassImage = R.drawable.img_vodka_glass
    ),
    SAKE(
        title = "사케",
        isSelected = false,
        bottleCnt = 0,
        glassCnt = 0,
        mainColor = com.sulsul.core.designsystem.R.color.green_sake,
        background = R.drawable.selector_sake_bg,
        buttonColor = R.drawable.selector_sake_btn,
        mainImage = R.drawable.selector_sake_img,
        bottleImage = R.drawable.img_sake_bottle,
        glassImage = R.drawable.img_sake_glass
    )
}
