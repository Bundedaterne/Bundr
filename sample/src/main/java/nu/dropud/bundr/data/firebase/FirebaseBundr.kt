package nu.dropud.bundr.data.firebase

import nu.dropud.bundr.app.App
import nu.dropud.bundr.data.model.IceCandidateFirebase
import com.google.firebase.database.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.ofType
import nu.dropud.bundr.common.extension.*
import nu.dropud.bundr.data.model.BundrDataFirebase
import org.webrtc.IceCandidate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseBundr @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {
    companion object {
            private const val BUNDR_PATH = "bundr/"
    }

    private fun connectionBundrPath(localUuid: String, remoteUuid: String): String {
        val uuidSortedArray = arrayOf(localUuid, remoteUuid).sortedArray()
        return BUNDR_PATH.plus(uuidSortedArray[0] + "@" + uuidSortedArray[1] + "/")
    }

    private fun localBundrPath(localUuid: String, remoteUuid: String): String {
        return connectionBundrPath(localUuid, remoteUuid).plus(localUuid + "/")
    }

    private fun remoteBundrPath(localUuid: String, remoteUuid: String): String {
        return connectionBundrPath(localUuid, remoteUuid).plus(remoteUuid + "/")
    }

    fun sendReady(remoteUuid: String, isReady: Boolean): Completable = Completable.create {
        val reference = firebaseDatabase.getReference(localBundrPath(App.CURRENT_DEVICE_UUID, remoteUuid))
        with(reference) {
            onDisconnect().removeValue()
            setValue(BundrDataFirebase(isReady))
        }
        it.onComplete()
    }

    fun listenForReadyState(remoteUuid: String): Flowable<Boolean>? {
        val remoteBundrPath = remoteBundrPath(App.CURRENT_DEVICE_UUID, remoteUuid)
        return firebaseDatabase.getReference(remoteBundrPath).rxChildEvents()
                .filter { it is ChildEventAdded || it is ChildEventChanged }
                .map {
                    if(it.data.key.equals("readyToChug")) {
                        val ret : Boolean = it.data.getValue() as Boolean
                        ret
                    }
                    else {
                        false
                    }
                }
    }
}
