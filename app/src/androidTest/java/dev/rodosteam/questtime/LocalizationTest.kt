package dev.rodosteam.questtime

import androidx.test.core.app.launchActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dev.rodosteam.questtime.pageObjects.NavigationPanel
import dev.rodosteam.questtime.pages.SettingsScreen
import dev.rodosteam.questtime.screen.common.MainActivity
import dev.rodosteam.questtime.utils.Languages
import org.junit.Test

class LocalizationTest : TestCase() {

    private val navigationPanel = NavigationPanel()

    @Test
    fun localizationSwitchTest() = before {
        launchActivity<MainActivity>()
    }.after {

    }.run {
        step("Go to settings") {
            navigationPanel.settings.click()
        }

        step("Set Russian language") {
            val settingsScreen = SettingsScreen()
            settingsScreen {
                languageButton {
                    click()
                }
                alertDialog {
                    onChoiceItem(Languages.RUSSIAN.label).click()
                }
                languageSettingsText {
                    containsText("Язык")
                }
            }
        }

        step("Set English language") {
            val settingsScreen = SettingsScreen()
            settingsScreen {
                languageButton {
                    click()
                }
                alertDialog {
                    onChoiceItem(Languages.ENGLISH.label).click()
                }
                languageSettingsText {
                    containsText("Language")
                }
            }
        }

        step("Set German language") {
            val settingsScreen = SettingsScreen()
            settingsScreen {
                languageButton {
                    click()
                }
                alertDialog {
                    onChoiceItem(Languages.GERMAN.label).click()
                }
                languageSettingsText {
                    containsText("Sprache")
                }
            }
        }
    }
}
