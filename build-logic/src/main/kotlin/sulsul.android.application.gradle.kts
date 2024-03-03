import com.teamsulsul.sulsul.configureHiltAndroid
import com.teamsulsul.sulsul.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()