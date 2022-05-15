package dev.rodosteam.questtime.pages

import dev.rodosteam.questtime.R
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class LibraryScreenItemView: Screen<LibraryScreenItemView>() {
    val icon = KImageView { withId(R.id.fragment_preview__image) }
    val title = KTextView { withId(R.id.fragment_preview__title) }
    val deleteButton = KButton { withId(R.id.fragment_preview__left_button) }
    val addToFavoriteButton = KButton { withId(R.id.fragment_preview__right_button) }
}
