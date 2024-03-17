package com.sulsul.feature.calendar.drink

import com.sulsul.feature.calendar.R

// TODO: 술계산기, (only잔, 잔/병) 구분, 글자색
enum class Drink(
    val title: String,
    val background: Int,
    val image: Int,
    val buttonColor: Int,
) {
    SOJU(
        title = "소주",
        background = R.drawable.selector_soju_bg,
        image = R.drawable.selector_soju_img,
        buttonColor = R.drawable.selector_soju_btn
    ),
    BEER(
        title = "맥주",
        background = R.drawable.selector_beer_bg,
        image = R.drawable.selector_beer_img,
        buttonColor = R.drawable.selector_beer_btn
    ),
    SOJUBEER(
        title = "소맥",
        background = R.drawable.selector_beer_bg,
        image = R.drawable.selector_sojubeer_img,
        buttonColor = R.drawable.selector_beer_btn
    ),
    WINE(
        title = "와인",
        background = R.drawable.selector_wine_bg,
        image = R.drawable.selector_wine_img,
        buttonColor = R.drawable.selector_wine_btn
    ),
    RICE_WINE(
        title = "막걸리",
        background = R.drawable.selector_rice_wine_bg,
        image = R.drawable.selector_rice_wine_img,
        buttonColor = R.drawable.selector_rice_wine_btn
    ),
    COCKTAIL(
        title = "칵테일",
        background = R.drawable.selector_cocktail_bg,
        image = R.drawable.selector_cocktail_img,
        buttonColor = R.drawable.selector_cocktail_btn
    ),
    WHISKY(
        title = "위스키",
        background = R.drawable.selector_whisky_bg,
        image = R.drawable.selector_whisky_img,
        buttonColor = R.drawable.selector_whisky_btn
    ),
    VODKA(
        title = "보드카",
        background = R.drawable.selector_vodka_bg,
        image = R.drawable.selector_vodka_img,
        buttonColor = R.drawable.selector_vodka_btn
    ),
    SAKE(
        title = "사케",
        background = R.drawable.selector_sake_bg,
        image = R.drawable.selector_sake_img,
        buttonColor = R.drawable.selector_sake_btn
    )
}
