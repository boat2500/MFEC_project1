package com.example.testretrofit


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testretrofit.databinding.FragmentProfileBinding
import com.example.testretrofit.databinding.RowLayoutBinding
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 */
class profile : Fragment() {

    var intent = Intent()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var x = arguments?.getString("amount").toString()
        //textView.setText("111")
        Log.i("xxx",arguments?.getString("amount"))
    }


}
