package com.github.polurival.stc.game.ui.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.polurival.stc.coreapi.di.getFeature
import com.github.polurival.stc.coreui.BaseFragment
import com.github.polurival.stc.game.databinding.GameFragmentGameBinding
import com.github.polurival.stc.game.di.DaggerGameFragmentComponent
import com.github.polurival.stc.game.ui.viewmodel.GameViewModel
import com.github.polurival.stc.game.ui.viewmodel.GameViewModelFactory
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.github.polurival.stc.design.R as designR
import com.github.polurival.stc.game.R as gameR

/**
 *
 *
 * @author Юрий Польщиков on 24.07.2021
 */
class GameFragment : BaseFragment(gameR.layout.game_fragment_game) {

    @Inject
    lateinit var factory: GameViewModelFactory
    private val gameViewModel by viewModels<GameViewModel> { factory }

    private var binding: GameFragmentGameBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerGameFragmentComponent.factory()
            .create(gameFeatureApi = api.getFeature())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = GameFragmentGameBinding.bind(view)

        gameViewModel.startGameFlow
            .onEach {
                bindGame(it)
            }
            .launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun bindGame(state: GameViewModel.State) {
        when (state) {
            is GameViewModel.State.StartGame -> {
                binding?.questionView?.text = state.quiz?.let { Html.fromHtml(it.question) }
                    ?: getString(designR.string.design_something_happened)

                binding?.questionsContainer?.removeAllViews()
                for (answer in state.quiz?.allAnswers.orEmpty()) {
                    val questionView = LayoutInflater.from(requireContext())
                        .inflate(gameR.layout.game_item_answer, binding?.questionsContainer, false) as TextView
                    questionView.text = answer
                    questionView.setOnClickListener {
                        binding?.questionsContainer?.children?.forEach {
                            it.isClickable = false
                        }
                        flow { emit(gameViewModel.checkAnswer(answer)) }
                            .launchIn(lifecycleScope)
                    }
                    binding?.questionsContainer?.addView(questionView)
                }
            }
            is GameViewModel.State.CorrectChoose -> {
                changeAnswerViewColor(state.correctIndex, gameR.color.correct)
            }
            is GameViewModel.State.IncorrectChoose -> {
                changeAnswerViewColor(state.correctIndex, gameR.color.correct)
                changeAnswerViewColor(state.incorrectIndex, gameR.color.incorrect)
            }
            is GameViewModel.State.GameOver -> {
                // todo start EndGameFragment
                parentFragmentManager.popBackStack()
                val toastText = resources.getQuantityString(
                    gameR.plurals.game_correct_answers_count,
                    state.correctAnswersCount,
                    state.correctAnswersCount
                )
                Toast.makeText(requireContext(), toastText, Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
            }
        }
    }

    private fun changeAnswerViewColor(viewIndex: Int, colorRes: Int) {
        binding?.questionsContainer?.getChildAt(viewIndex)
            ?.setBackgroundColor(resources.getColor(colorRes))
    }
}
