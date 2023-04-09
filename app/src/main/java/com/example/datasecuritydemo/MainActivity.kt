package com.example.datasecuritydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datasecuritydemo.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var messageToEncrypt = ""
    var messageToDecrypt = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cryptoManager = CryptoManager()
        val file = File(filesDir, "secret.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fos = FileOutputStream(file)
        binding.btnEncrypt.setOnClickListener {
            messageToDecrypt = cryptoManager.encrypt(
                bytes = binding.etInput.text.toString().encodeToByteArray(),
                outputStream = fos
            ).decodeToString()
            binding.txtData.text = messageToDecrypt
        }

        binding.btnDecrypt.setOnClickListener {
            messageToEncrypt = cryptoManager.decrypt(
                inputStream = FileInputStream(file)
            ).decodeToString()
            binding.etInput.setText(messageToEncrypt)
        }
    }
}