package com.example.myapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.databinding.FragmentSignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.signupbutton.setOnClickListener {

            val email=binding.usernamesignup.text.toString()
            val password=binding.passwordsignup.text.toString()

            if(email.isEmpty()){
                Toast.makeText(context, "Please enter the email...", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                Toast.makeText(context, "Please enter the password...", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Firebase.auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                Toast.makeText(context, "You are registered...", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.homePageFragment)
            }

        }


    }

}