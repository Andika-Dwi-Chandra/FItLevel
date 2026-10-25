package com.example.andika_3tia.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.andika_3tia.R
import com.example.andika_3tia.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root

        val data = messages[position]

        // Memuat gambar dari URL menggunakan Glide + fallback logo jika offline
        Glide.with(context)
            .load(data.avatarUrl)
            .placeholder(R.drawable.ic_logo_fitlevel)
            .error(R.drawable.ic_logo_fitlevel)
            .into(binding.avatarImg)

        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText
        binding.textTime.text = data.time

        // Aksi klik pada setiap baris item memunculkan Snackbar (Sesuai Modul Hal. 18)
        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Quest dipilih: ${data.senderName} (${data.time})",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}