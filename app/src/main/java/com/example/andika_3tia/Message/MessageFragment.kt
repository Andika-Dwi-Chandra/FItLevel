package com.example.andika_3tia.Message

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.andika_3tia.Message.tutorial.TutorialMessageActivity
import com.example.andika_3tia.R
import com.example.andika_3tia.databinding.FragmentMessageBinding
import com.google.android.material.chip.Chip

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // Daftar Lengkap Quest FitLevel beserta Kategorinya (Harian, Mingguan, Spesial)
    private val allQuestList = listOf(
        MessageModel("Lari 3 km", "Tanpa berhenti, target 25 menit", "https://avatar.iran.liara.run/public/1", "+50 EXP", "Harian"),
        MessageModel("Minum Air 2 Liter", "8 gelas sepanjang hari", "https://avatar.iran.liara.run/public/2", "+20 EXP", "Harian"),
        MessageModel("Push-up 30 Repetisi", "Boleh dibagi menjadi 3 set", "https://avatar.iran.liara.run/public/3", "+40 EXP", "Harian"),
        MessageModel("Plank 2 Menit", "Jaga posisi tubuh tetap lurus", "https://avatar.iran.liara.run/public/4", "+35 EXP", "Harian"),
        MessageModel("Jalan 8.000 Langkah", "Selesaikan sebelum pukul 21.00", "https://avatar.iran.liara.run/public/5", "+30 EXP", "Harian"),
        MessageModel("Tidur Cukup 7 Jam", "Mulai istirahat sebelum 23.00", "https://avatar.iran.liara.run/public/6", "+30 EXP", "Harian"),
        MessageModel("Lari Total 15 km", "Akumulasi jarak lari dalam 7 hari", "https://avatar.iran.liara.run/public/7", "+120 EXP", "Mingguan"),
        MessageModel("Workout 5 Hari Beruntun", "Jaga streak latihan mingguanmu", "https://avatar.iran.liara.run/public/8", "+100 EXP", "Mingguan"),
        MessageModel("Boss Fight: 100 Burpees", "Tantangan spesial pembuka Golden Gear!", "https://avatar.iran.liara.run/public/9", "+150 EXP", "Spesial"),
        MessageModel(" Spartan Trial: Pull-Up 50x", "Ujian kekuatan otot punggung atas", "https://avatar.iran.liara.run/public/10", "+200 EXP", "Spesial")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "QUEST & REWARDS"
            subtitle = "Selesaikan misi untuk membuka Peti!"
        }

        // 1. Pasang Option Menu Info di Toolbar untuk membuka Tutorial Screen (Modul Hal. 17)
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.message_toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_tutorial -> {
                        startActivity(Intent(requireContext(), TutorialMessageActivity::class.java))
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // 2. Tampilkan seluruh Quest saat pertama kali dibuka
        updateQuestList(allQuestList)

        // 3. Deteksi Chip yang dipilih pada ChipGroup untuk memfilter Quest (Modul Hal. 9)
        binding.chipGroupQuest.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                val selectedCategory = chip.text.toString()

                Toast.makeText(requireContext(), "Filter Quest: $selectedCategory", Toast.LENGTH_SHORT).show()

                val filteredList = if (selectedCategory == "Semua") {
                    allQuestList
                } else {
                    allQuestList.filter { it.category == selectedCategory }
                }
                updateQuestList(filteredList)
            }
        }
    }

    private fun updateQuestList(list: List<MessageModel>) {
        val adapter = MessageAdapter(requireContext(), list)
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}