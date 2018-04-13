package nu.dropud.bundr.webrtc.service

import nu.dropud.bundr.common.di.ServiceScope
import dagger.Subcomponent

@ServiceScope
@Subcomponent
interface WebRtcServiceComponent {

    fun inject(webRtcService: WebRtcService)

}