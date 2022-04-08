package dev.rodosteam.questtime.screen.preview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.FragmentLibraryPreviewBinding
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.screen.common.base.BaseFragment

class QuestPreviewFragment : BaseFragment() {

    companion object {

        const val QUEST_KEY = "quest"
        const val DOWNLOADED_KEY = "downloaded"

        fun newInstance() = QuestPreviewFragment()
    }

    private lateinit var viewModel: QuestPreviewViewModel
    private var _binding: FragmentLibraryPreviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[QuestPreviewViewModel::class.java]
        _binding = FragmentLibraryPreviewBinding.inflate(inflater, container, false)
        if (arguments == null) {
            return binding.root
        }
        val id = arguments!!.getInt(QUEST_KEY)
        val quest = app.findQuestItemRepo.findById(id)
        quest?.let {
            binding.fragmentPreviewTitle.text = it.title
            binding.fragmentPreviewDescription.text = it.description
            binding.fragmentPreviewAuthor.text = it.author
            binding.fragmentPreviewInfo.text = "${it.downloads} установок"
        }
        //TODO вся логика должна быть в ViewModel но пока что так
        val downloaded = arguments!!.getBoolean(DOWNLOADED_KEY)
        if (downloaded) {
            if (quest == null) {
                findNavController().navigateUp()
                return binding.root
            }
            setQuestDownloaded(quest)
        } else {
            binding.fragmentPreviewLeftButton.text = "Download"
            binding.fragmentPreviewPlayButton.visibility = View.GONE
        }

        return binding.root
    }

    private fun setQuestDownloaded(quest: QuestMeta) {
        binding.fragmentPreviewLeftButton.text = "Delete"
        binding.fragmentPreviewPlayButton.visibility = View.VISIBLE
        binding.fragmentPreviewPlayButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_questPreviewFragment_to_questContentFragment,
                bundleOf("quest" to (quest.title))
            )
        }
        binding.fragmentPreviewLeftButton.setOnClickListener {
            app.findQuestItemRepo.remove(quest.id)
            setQuestDeleted(quest)
        }
    }

    private fun setQuestDeleted(quest: QuestMeta) {
        binding.fragmentPreviewLeftButton.text = "Download"
        binding.fragmentPreviewPlayButton.visibility = View.GONE
        binding.fragmentPreviewLeftButton
        binding.fragmentPreviewLeftButton.setOnClickListener {
            downloadQuest(quest)
        }
    }

    private fun downloadQuest(quest: QuestMeta) {
        app.findQuestItemRepo.add(quest)
        setQuestDownloaded(quest)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[QuestPreviewViewModel::class.java]
        // TODO: Use the ViewModel
    }

}