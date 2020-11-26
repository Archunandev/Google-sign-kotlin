package com.example.onbordlogin

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class SecoundActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var signInButton: SignInButton

    private val RC_SIGN_IN = 0

    private var mGoogleSignInClient: GoogleSignInClient? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secound)

        signInButton = findViewById(R.id.sign_in_button)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        signInButton.setOnClickListener(this)


    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)

        /*  if(account!=null) {
              val intent = Intent(this, ThirdActivity::class.java)
              startActivity(intent)
          } */
    }

    override fun onClick(v: View?) {
        when (v?.getId()) {
            R.id.sign_in_button -> signIn()
        }// ...
    }

    private fun signIn() {
        val intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try
        {
            if (completedTask.isSuccessful) {
                val account = completedTask.getResult(ApiException::class.java)
                if (account != null) {
                    val intent = Intent(this@SecoundActivity, ThirdActivity::class.java)
                    intent.putExtra("name", account.displayName.toString())
                    intent.putExtra("email", account.email.toString())
                    intent.putExtra("url", account.photoUrl.toString())
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                }
            }

        }
        catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("error", "signInResult:failed code=" + e.getStatusCode())

        }
    }

    private fun signnext() {
        val intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)

    }
}



























































      /*  signInButton.setOnClickListener {
            val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putBoolean("isUserLogin", true)
            editor.commit()
            signIn()

        }





    }
    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)

        if(account!=null) {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
    private fun signIn() {
        val intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

                val intent = Intent(this, ThirdActivity::class.java)
                startActivity(intent)

            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.e("TAG", "signInResult:failed code=" + e.statusCode)
            }
        }
    }


} */