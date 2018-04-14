package nu.dropud.bundr.feature.main

import android.os.Bundle
import nu.dropud.bundr.R
import nu.dropud.bundr.feature.base.BaseActivity
import nu.dropud.bundr.feature.main.video.VideoFragment


class MainActivity : BaseActivity() {

    private val videoFragment = VideoFragment.instance

    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            getReplaceFragmentTransaction(R.id.fragmentContainer, videoFragment, VideoFragment.TAG).commit()
        }
    }

    override fun onPause() {
        super.onPause()
        videoFragment.getPresenter().disconnectByUser()
    }
}