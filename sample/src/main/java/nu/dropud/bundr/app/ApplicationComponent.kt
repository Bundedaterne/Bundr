package nu.dropud.bundr.app

import nu.dropud.bundr.data.firebase.FirebaseModule
import nu.dropud.bundr.feature.main.video.VideoFragmentComponent
import nu.dropud.bundr.webrtc.service.WebRtcServiceComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, FirebaseModule::class))
interface ApplicationComponent {

    fun videoFragmentComponent(): VideoFragmentComponent

    fun webRtcServiceComponent(): WebRtcServiceComponent
}
