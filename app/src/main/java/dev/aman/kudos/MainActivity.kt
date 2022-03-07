package dev.aman.kudos

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dev.aman.kudos.databinding.ActivityMainBinding
import dev.aman.kudos.databinding.ActivitySigninBinding


class MainActivity : AppCompatActivity() {
    private lateinit var  binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = FirebaseAuth.getInstance().currentUser
        Glide.with(this).load(currentUser!!.photoUrl).into(binding.profileimg)



    }

    fun onSignOutButtonClicked(view: View) {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                Toast.makeText(this,
                    "signout success",
                    Toast.LENGTH_SHORT
                ).show()
               val intent=Intent(this,SigninActivity::class.java)
                startActivity(intent)
                finish()
            }

    }
}