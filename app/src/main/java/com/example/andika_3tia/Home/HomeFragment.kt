package com.example.andika_3tia.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.andika_3tia.DetailActivity
import com.example.andika_3tia.LoginActivity
import com.example.andika_3tia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "FITLEVEL"
            subtitle = "Selamat datang, Hero!"
        }

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna")
        binding.tvWelcome.text = "Halo, $username"

        // Listener 6 Tombol GridLayout Fokus Latihan
        binding.btnGridChest.setOnClickListener { showGridToast("Chest Workout (+Strength)") }
        binding.btnGridBack.setOnClickListener { showGridToast("Back Workout (+Strength)") }
        binding.btnGridLegs.setOnClickListener { showGridToast("Legs Workout (+Strength & Agility)") }
        binding.btnGridCardio.setOnClickListener { showGridToast("Cardio Run (+Agility & Stamina)") }
        binding.btnGridCore.setOnClickListener { showGridToast("Core Plank (+Stamina)") }
        binding.btnGridBoss.setOnClickListener { showGridToast("Boss Raid: 100 Burpees (+150 EXP)") }

        binding.btnDetail.setOnClickListener {
            startActivity(Intent(requireContext(), DetailActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit().clear().apply()
                    dialog.dismiss()

                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun showGridToast(menuName: String) {
        Toast.makeText(requireContext(), "Fokus dipilih: $menuName", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}