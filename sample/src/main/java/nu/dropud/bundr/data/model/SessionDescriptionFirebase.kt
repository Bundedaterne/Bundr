package nu.dropud.bundr.data.model

import nu.dropud.bundr.app.App
import org.webrtc.SessionDescription


data class SessionDescriptionFirebase(val senderUuid: String = App.CURRENT_DEVICE_UUID,
                                      val type: SessionDescription.Type? = null,
                                      val description: String? = null) {
    companion object {
        fun fromSessionDescriptionWithDefaultSenderUuid(sessionDescription: SessionDescription): SessionDescriptionFirebase =
                SessionDescriptionFirebase(type = sessionDescription.type, description = sessionDescription.description)
    }

    fun toSessionDescription() = SessionDescription(type, description)
}