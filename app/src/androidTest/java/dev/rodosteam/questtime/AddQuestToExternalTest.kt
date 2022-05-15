package dev.rodosteam.questtime

import androidx.test.core.app.launchActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dev.rodosteam.questtime.pageObjects.NavigationPanel
import dev.rodosteam.questtime.pages.ExternalScreen
import dev.rodosteam.questtime.pages.LibraryScreen
import dev.rodosteam.questtime.pages.LibraryScreenItemView
import dev.rodosteam.questtime.screen.common.MainActivity
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Test

class AddQuestToExternalTest : TestCase() {

    private val navigationPanel = NavigationPanel()

    @Test
    fun downloadFirstQuestTest() = before {
        launchActivity<MainActivity>()
    }.after {

    }.run {
        var questTitle = ""
        var countOfExternalQuests = 0
        var countOfQuestsInLibrary = 0

        step("Go to library") {
            navigationPanel.library.click()
        }

        step("Get initial count of downloaded quests") {
            val libraryScreen = LibraryScreen()
            libraryScreen {
                countOfQuestsInLibrary = questsList.getSize()
            }
        }

        step("Go to external") {
            navigationPanel.external.click()
        }

        step("Click download button") {
            val externalScreen = ExternalScreen()
            externalScreen {
                countOfExternalQuests = quests.getSize()

                quests {
                    firstChild<ExternalScreen.Quest> {
                        isVisible()

                        questTitle = title.toString()
                        button {
                            isClickable()
                            click()
                        }
                    }
                }
            }
        }

        step("Check that one quest has been deleted from the external") {
            val externalScreen = ExternalScreen()
            externalScreen {
                assertTrue(quests.getSize() == countOfExternalQuests - 1)
            }
        }

        step("Go to library") {
            navigationPanel.library.click()
        }

        step("Check that quest has been added to library") {
            val libraryScreen = LibraryScreen()
            libraryScreen {
                questsList {
                    firstChild<LibraryScreen.Quest> {
                        isEnabled()
                        title {
                            toString().contentEquals(questTitle)
                        }
                    }
                }

                assertTrue(questsList.getSize() == countOfQuestsInLibrary + 1)
            }
        }
    }

    @After
    fun cleanup() {
        navigationPanel.library.click()
        val libraryScreen = LibraryScreen()
        libraryScreen {
            questsList {
                firstChild<LibraryScreen.Quest> {
                    click()
                }
            }
        }

        val libraryScreenItemView = LibraryScreenItemView()
        libraryScreenItemView {
            deleteButton {
                isClickable()
                click()
            }
        }
    }
}
