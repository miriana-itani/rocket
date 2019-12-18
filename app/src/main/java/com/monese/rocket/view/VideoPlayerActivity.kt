package com.monese.rocket.view

import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Rational
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.monese.rocket.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var youTubePlayerView: YouTubePlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        youTubePlayerView = findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)
        val videoId = intent.extras?.getString("videoId")
        if (!TextUtils.isEmpty(videoId)) {
            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoId!!, 0f)
                }
            })
        } else finish()
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if (isInPictureInPictureMode) {
            youTubePlayerView.enterFullScreen()
            youTubePlayerView.getPlayerUiController().showUi(false)
        } else {
            youTubePlayerView.exitFullScreen()
            youTubePlayerView.getPlayerUiController().showUi(true)
        }
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val aspectRatio = Rational(3, 4)
            val params = PictureInPictureParams.Builder().setAspectRatio(aspectRatio).build()
            enterPictureInPictureMode(params)
        } else super.onBackPressed()
    }
}