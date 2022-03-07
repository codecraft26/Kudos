package dev.aman.kudos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dev.aman.kudos.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var  binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = FirebaseAuth.getInstance().currentUser
        Glide.with(this).load(currentUser!!.photoUrl).into(binding.profileimg)

        val displayName = currentUser.displayName // creating a variable for store the display name from google account
        if (displayName == null) {
            binding.textView3.setText("Not Available")
        } else {
            binding.textView3.setText(displayName)
        }
        // now creating an variable for loading email storge
        val email = currentUser.email
        if (email == null) {
            binding.emailtext.setText("Not Available")
        } else {
            binding.emailtext.setText(email)
        }

        val phoneNumber = currentUser.phoneNumber
        if (phoneNumber == null) {
            binding.phonetext.setText("Not Available")
        } else {
            binding.phonetext.setText(phoneNumber)
        }
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