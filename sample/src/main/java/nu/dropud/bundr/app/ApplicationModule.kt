package nu.dropud.bundr.app

import android.app.Application
import android.content.Context
import android.content.res.Resources
import nu.dropud.bundr.data.firebase.FirebaseIceCandidates
import nu.dropud.bundr.data.firebase.FirebaseIceServers
import nu.dropud.bundr.data.firebase.FirebaseSignalingAnswers
import nu.dropud.bundr.data.firebase.FirebaseSignalingOffers
import nu.dropud.bundr.webrtc.service.WebRtcServiceController
import co.netguru.videochatguru.WebRtcClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideResources(): Resources = application.resources

    @Provides
    fun provideWebRtcClient(context: Context) = WebRtcClient(context)

    @Provides
    fun provideWebRtcServiceController(webRtcClient: WebRtcClient, firebaseSignalingAnswers: FirebaseSignalingAnswers,
                                       firebaseSignalingOffers: FirebaseSignalingOffers, firebaseIceCandidates: FirebaseIceCandidates,
                                       firebaseIceServers: FirebaseIceServers): WebRtcServiceController {
        return WebRtcServiceController(
                webRtcClient, firebaseSignalingAnswers, firebaseSignalingOffers,
                firebaseIceCandidates, firebaseIceServers)
    }
}