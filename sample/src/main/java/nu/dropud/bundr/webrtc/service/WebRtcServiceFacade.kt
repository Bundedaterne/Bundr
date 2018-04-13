package nu.dropud.bundr.webrtc.service

import nu.dropud.bundr.feature.base.service.ServiceFacade


interface WebRtcServiceFacade : ServiceFacade {
    fun stop()
}