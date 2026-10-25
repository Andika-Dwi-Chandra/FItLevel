package com.example.andika_3tia.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.andika_3tia.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    // Data 1 teks untuk ArrayAdapter (simple_list_item_1)
    private val dataList = listOf(
        "Push-Up Blitz", "Diamond Push-Up", "Squat Warrior", "Plank Iron Core",
        "Sprint 3 KM", "Burpees Boss Fight", "Pull-Up Titan", "Lunges Stride",
        "Jumping Jacks", "Mountain Climber"
    )

    // Data 2 teks (Judul & Deskripsi) untuk SimpleAdapter (simple_list_item_2 - Latihan 5.2)
    private val dataListWithDesc = listOf(
        mapOf("title" to "Push-Up Blitz", "desc" to "Latihan otot dada & tricep • Hadiah: +120 EXP (+2 STR)"),
        mapOf("title" to "Diamond Push-Up", "desc" to "Gerakan lanjutan (Unlock Lv. 6) • Hadiah: +160 EXP (+3 STR)"),
        mapOf("title" to "Squat Warrior", "desc" to "Memperkuat otot kaki & kuda-kuda • Hadiah: +100 EXP (+2 STR)"),
        mapOf("title" to "Plank Iron Core", "desc" to "Menahan beban perut selama 2 menit • Hadiah: +90 EXP (+3 STA)"),
        mapOf("title" to "Sprint 3 KM", "desc" to "Lari kardio tanpa henti target 25 menit • Hadiah: +150 EXP (+3 AGI)"),
        mapOf("title" to "Burpees Boss Fight", "desc" to "Kombinasi kardio & kekuatan penuh • Hadiah: +200 EXP (+2 All Stats)"),
        mapOf("title" to "Pull-Up Titan", "desc" to "Latihan punggung atas intensitas tinggi • Hadiah: +180 EXP (+4 STR)"),
        mapOf("title" to "Lunges Stride", "desc" to "Melatih keseimbangan & kelincahan langkah • Hadiah: +110 EXP (+2 AGI)"),
        mapOf("title" to "Jumping Jacks", "desc" to "Pemanasan kardio seluruh tubuh 100 repetisi • Hadiah: +80 EXP (+2 STA)"),
        mapOf("title" to "Mountain Climber", "desc" to "Latihan ketahanan jantung & otot inti • Hadiah: +130 EXP (+2 AGI)")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "DAFTAR WORKOUT"
            subtitle = "Pilih latihan untuk meraih EXP"
        }

        /*
        // JIKA INGIN MENCOBA ARRAYADAPTER (simple_list_item_1), BUKA KOMENTAR INI:
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            dataList
        )
        binding.listViewItems.adapter = arrayAdapter
        */

        // Menggunakan SimpleAdapter (simple_list_item_2) sesuai Tahap 3.4 & Latihan 5.2
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewItems.adapter = adapter

        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(
                requireContext(),
                "Kamu memilih workout: $title ($desc)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}