package nu.dropud.bundr.data.firebase

import nu.dropud.bundr.app.App
import nu.dropud.bundr.common.extension.rxValueEvents
import nu.dropud.bundr.data.model.SessionDescriptionFirebase
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.rxkotlin.toMaybe
import org.webrtc.SessionDescription
import javax.inject.Inject


class FirebaseSignalingAnswers @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {

    companion object {
        private const val ANSWERS_PATH = "answers/"
    }

    private fun deviceAnswersPath(deviceUuid: String) = ANSWERS_PATH.plus(deviceUuid)

    fun create(recipientUuid: String, localSessionDescription: SessionDescription): Completable = Completable.create {
        val reference = firebaseDatabase.getReference(deviceAnswersPath(recipientUuid))
        reference.onDisconnect().removeValue()
        reference.setValue(SessionDescriptionFirebase.fromSessionDescriptionWithDefaultSenderUuid(localSessionDescription))
        it.onComplete()
    }

    fun listenForNewAnswers(): Flowable<SessionDescription> {
        return Single.just { firebaseDatabase.getReference(deviceAnswersPath(App.CURRENT_DEVICE_UUID)) }
                .flatMapPublisher { it().rxValueEvents(SessionDescriptionFirebase::class.java) }
                .flatMapMaybe { it.data.toMaybe() }
                .map { it.toSessionDescription() }
    }
}