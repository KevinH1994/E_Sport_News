package de.syntaxinstitut.e_sport_news.ui.authentication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.databinding.SignupFragmentBinding


/**
 * Das ViewModel des One Fragments
 */
class SignUpFragment : Fragment() {

  private val viewModel: LogInViewModel by activityViewModels()


  private lateinit var binding: SignupFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.signup_fragment,container, false)
    (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.signupCancelButton.setOnClickListener {
      findNavController().navigateUp()
    }

    binding.signupSignupButton.setOnClickListener {
      signUp()

    }
    viewModel.currentUser.observe(
      viewLifecycleOwner, Observer {
        if (it!= null){
          findNavController().navigate(R.id.HomeFragment)
        }
      }
    )
  }


  private fun signUp(){
    val email = binding.signupMail.text.toString()
    val password = binding.signupPassword.text.toString()

    if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){
      viewModel.signUp(email, password)
    }
  }


}
