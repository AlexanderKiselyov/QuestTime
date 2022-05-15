package dev.rodosteam.questtime.pageObjects

import dev.rodosteam.questtime.R
import io.github.kakaocup.kakao.text.KButton

class NavigationPanel {
    val library = KButton { withId(R.id.navigation_library) }
    val external = KButton { withId(R.id.navigation_external) }
    val settings = KButton { withId(R.id.navigation_settings) }
}