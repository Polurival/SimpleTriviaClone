package com.github.polurival.stc.game.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import com.github.polurival.stc.androidutil.findNavControllerSafely
import com.github.polurival.stc.coreapi.di.getFeature
import com.github.polurival.stc.coreui.BaseFragment
import com.github.polurival.stc.game.R
import com.github.polurival.stc.game.di.DaggerGameFragmentComponent
import com.github.polurival.stc.game.ui.viewmodel.StartGameViewModel
import com.github.polurival.stc.game.ui.viewmodel.StartGameViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 *
 *
 * @author Юрий Польщиков on 24.07.2021
 */
class StartGameFragment : BaseFragment(R.layout.game_fragment_start_game) {

    @Inject
    lateinit var factory: StartGameViewModelFactory
    private val startGameViewModel by viewModels<StartGameViewModel> { factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerGameFragmentComponent.factory()
            .create(gameFeatureApi = api.getFeature())
            .inject(this)

        // todo рассмотреть самое трушное апи для запуска корутин в связке с lifecycle
        startGameViewModel.triviaGameData
            .onEach { locateToGameScreen() }
            .launchIn(lifecycleScope)
    }

    /**
     * todo move to Router class
     * https://developer.android.com/guide/navigation/navigation-pass-data - еще тут про обфускацию написано
     */
    private fun locateToGameScreen() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.github.polurival.stc/app_nav_fragment_game".toUri())
            .build()
        findNavControllerSafely()?.apply {
            // todo если запрос завершился раньше 1 секунды, подождать 1 секунду и запустить игру
            // todo иначе подождать до timeout и либо запустить игру либо показать ошибку в случае наступления timeout
            popBackStack()
            navigate(request)
        }
    }
}
