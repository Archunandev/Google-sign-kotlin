package com.example.onbordlogin

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {

    private var mGoogleSignInClient: GoogleSignInClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val acct = GoogleSignIn.getLastSignedInAccount(this)

        val person_name = findViewById<TextView>(R.id.person_name)
        val person_email = findViewById<TextView>(R.id.person_email)
        val person_Photo= findViewById<ImageView>(R.id.imageView2)
        val intent = intent
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val imageURL = intent.getStringExtra("url")

        val signout = findViewById<Button>(R.id.signoutbtn)

        try {
            person_name.setText("Name : $name")
            person_email.setText("Email : $email")

            if (!imageURL.equals("null : $imageURL")) {
                Glide.with(this).load(imageURL).into(person_Photo)
            } else {
                person_Photo.visibility = View.GONE
            }
        }
        catch (e: Exception) { }


        signout.setOnClickListener {
            signOut() }


}
    fun signOut(){

        mGoogleSignInClient?.signOut()?.addOnCompleteListener(
            this,
            object : OnCompleteListener<Void> {
                override fun onComplete(p0: Task<Void>) {
                    Toast.makeText(this@ThirdActivity, "Signed Out", Toast.LENGTH_LONG).show()
                }
            })

        val intent = Intent(this@ThirdActivity, SecoundActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id: Int) {
                    super@ThirdActivity.onBackPressed()
                }
            })
            .setNegativeButton("No", null)
            .show()
    }

}