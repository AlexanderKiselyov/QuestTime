package dev.rodosteam.questtime.pages

import android.view.View
import dev.rodosteam.questtime.R
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

class ExternalScreen: Screen<ExternalScreen>() {
    val quests = KRecyclerView({ withId(R.id.external__recycler_view) }, itemTypeBuilder = {
        itemType(ExternalScreen::Quest)
    })

    class Quest(parent: Matcher<View>) : KRecyclerItem<Quest>(parent) {
        val icon = KImageView(parent) { withId(R.id.fragment_external_item__image) }
        val title = KTextView(parent) { withId(R.id.fragment_external_item__title) }
        val button = KButton(parent) { withId(R.id.fragment_external_item__downloadButton) }
    }
}
