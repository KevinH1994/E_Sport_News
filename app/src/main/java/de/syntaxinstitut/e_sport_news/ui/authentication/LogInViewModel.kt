package de.syntaxinstitut.e_sport_news.ui.authentication

import android.app.Application
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

const val TAG ="MAINVIEWMODEL"

class LogInViewModel(application: Application):  AndroidViewModel(application) {

    //Kommunikartion mit der FirebaseAuth

    private val firebaseAuth = FirebaseAuth.getInstance()

    //currentuse ist null wenn nimand eingeloggt ist
    private val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
    get() = _currentUser

    //hier wird versucht einen User zu erstellen und ihn einzuloggen

    fun signUp( email:String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                login(email,password)
            }else{
                Log.e(TAG,"SignUp failed: ${it.exception}")
            }
        }
    }
    // hier versucht sich ein bestehender User einzuloggen
    fun login(email:String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                _currentUser.value =firebaseAuth.currentUser
            }else{
                Log.e(TAG,"Login failed: ${it.exception}")
            }
        }
    }
    // Hier kann man sich ausloggen, um sich z.B. als anderer User anmelden. !! Wird aber noch nicht benutzt.
    fun logOut(){
        firebaseAuth.signOut()
        _currentUser.value = firebaseAuth.currentUser
    }
}