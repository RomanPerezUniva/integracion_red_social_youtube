package com.example.ejemploandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class MainActivity : YouTubeBaseActivity(),YouTubePlayer.OnInitializedListener {
    val claveYoutube="AIzaSyAUT_znVopvoEH_fo7bHvaw_EbbUHqzCn4"
    lateinit var  youtubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        youtubePlayerView = findViewById(R.id.youtube_view)
        youtubePlayerView.initialize(claveYoutube,this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if(!p2){
            p1?.cueVideo("Bg59q4puhmg") //Video a cargar
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        if(p1!!.isUserRecoverableError()){
            p1.getErrorDialog(this,1).show() //equivalente a un toast
        }else{
            Toast.makeText(this,"Error al cargar el reproductor",Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==1){
            getYoutubePlayerProvider().initialize(claveYoutube,this)
        }
    }
    fun getYoutubePlayerProvider():YouTubePlayer.Provider{
        return youtubePlayerView
    }
}