import com.teamsulsul.sulsul.configureCoroutineAndroid
import com.teamsulsul.sulsul.configureHiltAndroid
import com.teamsulsul.sulsul.configureKotlinAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureCoroutineAndroid()
configureHiltAndroid()