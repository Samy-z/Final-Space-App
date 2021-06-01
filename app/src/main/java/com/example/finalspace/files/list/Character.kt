package com.example.finalspace.files.list

data class Character(
        val id: Int,
        val name: String,
        val status: String,
        val species: String,
        val gender: String,
        val hair: String,
        val abilities: Array<String>,
        val origin: String,
        val alias: Array<String>,
        val img_url: String
)
