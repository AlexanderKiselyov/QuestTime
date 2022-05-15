package dev.rodosteam.questtime.pages

import dev.rodosteam.questtime.R
import io.github.kakaocup.kakao.dialog.KAlertDialog
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class SettingsScreen: Screen<SettingsScreen>() {
    val languageButton = KButton { withId(R.id.fragment_settings_lang_container) }
    val alertDialog = KAlertDialog()
    val languageSettingsText = KTextView { withText(R.string.language_settings) }
}