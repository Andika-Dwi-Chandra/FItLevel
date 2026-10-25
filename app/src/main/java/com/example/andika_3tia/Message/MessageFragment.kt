package com.example.andika_3tia.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.andika_3tia.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // 10 Data Quest RPG FitLevel untuk Custom Adapter
    private val messageList = listOf(
        MessageModel("Lari 3 km", "Tanpa berhenti, target 25 menit", "https://avatar.iran.liara.run/public/1", "+50 EXP"),
        MessageModel("Minum Air 2 Liter", "8 gelas sepanjang hari", "https://avatar.iran.liara.run/public/2", "+20 EXP"),
        MessageModel("Push-up 30 Repetisi", "Boleh dibagi menjadi 3 set", "https://avatar.iran.liara.run/public/3", "+40 EXP"),
        MessageModel("Plank 2 Menit", "Jaga posisi tubuh tetap lurus", "https://avatar.iran.liara.run/public/4", "+35 EXP"),
        MessageModel("Jalan 8.000 Langkah", "Selesaikan sebelum pukul 21.00", "https://avatar.iran.liara.run/public/5", "+30 EXP"),
        MessageModel("Tidur Cukup 7 Jam", "Mulai istirahat sebelum 23.00", "https://avatar.iran.liara.run/public/6", "+30 EXP"),
        MessageModel("Squat 45 Repetisi", "Latihan kekuatan otot tubuh bawah", "https://avatar.iran.liara.run/public/7", "+45 EXP"),
        MessageModel("Hindari Minuman Manis", "Quest disiplin nutrisi harian", "https://avatar.iran.liara.run/public/8", "+25 EXP"),
        MessageModel("Peregangan Pagi", "10 menit mobilitas setelah bangun", "https://avatar.iran.liara.run/public/9", "+15 EXP"),
        MessageModel("Boss Fight: 100 Burpees", "Tantangan spesial mingguan!", "https://avatar.iran.liara.run/public/10", "+150 EXP")
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
            subtitle = "Selesaikan misi harian untuk membuka Peti!"
        }

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}