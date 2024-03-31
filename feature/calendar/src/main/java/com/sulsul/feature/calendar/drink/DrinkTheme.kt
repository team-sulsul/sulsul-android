package com.sulsul.feature.calendar.drink

import com.sulsul.feature.calendar.R

enum class DrinkTheme(
    val drinkName: String,
    val textColor: Int,
    val backgroundColor: Int,
    val buttonColor: Int,
    val mainImage: Int,
    val bottleImage: Int?,
    val glassImage: Int,
) {
    SOJU(
        drinkName = "소주",
        textColor = com.sulsul.core.designsystem.R.color.green_soju,
        backgroundColor = R.drawable.selector_soju_bg,
        buttonColor = R.drawable.selector_soju_btn,
        mainImage = R.drawable.selector_soju_img,
        bottleImage = R.drawable.img_soju_bottle,
        glassImage = R.drawable.img_soju_glass
    ),
    BEER(
        drinkName = "맥주",
        textColor = com.sulsul.core.designsystem.R.color.yellow_beer,
        backgroundColor = R.drawable.selector_beer_bg,
        buttonColor = R.drawable.selector_beer_btn,
        mainImage = R.drawable.selector_beer_img,
        bottleImage = R.drawable.img_beer_bottle,
        glassImage = R.drawable.img_beer_glass
    ),
    SOJUBEER(
        drinkName = "소맥",
        textColor = com.sulsul.core.designsystem.R.color.yellow_beer,
        backgroundColor = R.drawable.selector_beer_bg,
        buttonColor = R.drawable.selector_beer_btn,
        mainImage = R.drawable.selector_sojubeer_img,
        bottleImage = null,
        glassImage = R.drawable.img_sojubeer_glass
    ),
    WINE(
        drinkName = "와인",
        textColor = com.sulsul.core.designsystem.R.color.purple_wine,
        backgroundColor = R.drawable.selector_wine_bg,
        buttonColor = R.drawable.selector_wine_btn,
        mainImage = R.drawable.selector_wine_img,
        bottleImage = R.drawable.img_wine_bottle,
        glassImage = R.drawable.img_wine_glass
    ),
    RICE_WINE(
        drinkName = "막걸리",
        textColor = com.sulsul.core.designsystem.R.color.beige_rice_wine,
        backgroundColor = R.drawable.selector_rice_wine_bg,
        buttonColor = R.drawable.selector_rice_wine_btn,
        mainImage = R.drawable.selector_rice_wine_img,
        bottleImage = R.drawable.img_rice_wine_bottle,
        glassImage = R.drawable.img_rice_wine_glass
    ),
    COCKTAIL(
        drinkName = "칵테일",
        textColor = com.sulsul.core.designsystem.R.color.red_cocktail,
        backgroundColor = R.drawable.selector_cocktail_bg,
        buttonColor = R.drawable.selector_cocktail_btn,
        mainImage = R.drawable.selector_cocktail_img,
        bottleImage = null,
        glassImage = R.drawable.img_cocktail_glass
    ),
    WHISKY(
        drinkName = "위스키",
        textColor = com.sulsul.core.designsystem.R.color.brown_whisky,
        backgroundColor = R.drawable.selector_whisky_bg,
        buttonColor = R.drawable.selector_whisky_btn,
        mainImage = R.drawable.selector_whisky_img,
        bottleImage = R.drawable.img_whisky_bottle,
        glassImage = R.drawable.img_whisky_glass
    ),
    VODKA(
        drinkName = "보드카",
        textColor = com.sulsul.core.designsystem.R.color.blue_vodka,
        backgroundColor = R.drawable.selector_vodka_bg,
        buttonColor = R.drawable.selector_vodka_btn,
        mainImage = R.drawable.selector_vodka_img,
        bottleImage = R.drawable.img_vodka_bottle,
        glassImage = R.drawable.img_vodka_glass
    ),
    SAKE(
        drinkName = "사케",
        textColor = com.sulsul.core.designsystem.R.color.green_sake,
        backgroundColor = R.drawable.selector_sake_bg,
        buttonColor = R.drawable.selector_sake_btn,
        mainImage = R.drawable.selector_sake_img,
        bottleImage = R.drawable.img_sake_bottle,
        glassImage = R.drawable.img_sake_glass
    )
}
