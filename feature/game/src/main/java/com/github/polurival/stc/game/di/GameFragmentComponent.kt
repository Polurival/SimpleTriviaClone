package com.github.polurival.stc.game.di

import com.github.polurival.stc.game.ui.fragment.GameFragment
import com.github.polurival.stc.game.ui.fragment.StartGameFragment
import com.github.polurival.stc.gameapi.GameFeatureApi
import dagger.Component

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
@Component(
    dependencies = [
        GameFeatureApi::class
    ]
)
interface GameFragmentComponent {

    fun inject(target: StartGameFragment)
    fun inject(target: GameFragment)

    @Component.Factory
    interface Factory {
        fun create(
            gameFeatureApi: GameFeatureApi,
        ): GameFragmentComponent
    }
}
