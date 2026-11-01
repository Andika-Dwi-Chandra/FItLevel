package com.example.andika_3tia.Message.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.andika_3tia.BaseActivity
import com.example.andika_3tia.R

class Tutorial3Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnMulaiQuest).setOnClickListener {
            startActivity(Intent(requireContext(), BaseActivity::class.java))
            requireActivity().finish()
        }
    }
}