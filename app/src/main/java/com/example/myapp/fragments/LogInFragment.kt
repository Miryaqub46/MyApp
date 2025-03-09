package com.example.myapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.databinding.FragmentLogInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class LogInFragment : Fragment() {

    lateinit var binding: FragmentLogInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signuptext.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
        binding.loginbutton.setOnClickListener {
            val email = binding.usernamelogin.text.toString()
            val password = binding.passwordlogin.text.toString()
            if(email.isEmpty()){
                Toast.makeText(context, "Please enter the email...", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                Toast.makeText(context, "Please enter the password...", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Firebase.auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                Toast.makeText(context, "Your email and password are correct...", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_logInFragment_to_homePageFragment)
                requireContext().getSharedPreferences("Memory",Context.MODE_PRIVATE).edit {
                    putBoolean("Registered",true)
                }

            }.addOnFailureListener {
                Toast.makeText(context, "Your email and password are not  correct...", Toast.LENGTH_SHORT).show()
            }

        }
    }
}


