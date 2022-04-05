package dev.rodosteam.questtime.screen.searchquest.row

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestItem
import dev.rodosteam.questtime.screen.common.mvp.MvpViewBase

class SearchQuestRowMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: SearchQuestRowMvpView.Listener
) : MvpViewBase(), SearchQuestRowMvpView {

    override var rootView: View = layoutInflater.inflate(R.layout.search_items_row, parent, false)
    private val tvTitle: TextView = findViewById(R.id.quest_item_fragment__titleTv)
    private val tvDescription: TextView = findViewById(R.id.quest_item_fragment__descriptionTv)

    private lateinit var blogItem: QuestItem

    init {
        rootView.setOnClickListener { onQuestItemClicked() }
    }


    override fun bindData(questItem: QuestItem) {
        tvTitle.text = questItem.title
        tvDescription.text = questItem.description
    }


    private fun onQuestItemClicked() {
        listener.onQuestItemClicked(blogItem.id)
    }

}