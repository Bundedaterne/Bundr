package nu.dropud.bundr.feature.main.video

import nu.dropud.bundr.common.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface VideoFragmentComponent {
    fun inject(videoFragment: VideoFragment)

    fun videoFragmentPresenter(): VideoFragmentPresenter
}