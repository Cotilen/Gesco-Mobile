package com.api.gesco.service.response.aluno

data class AlunoResponse(
    val id: Int,
    val nome: String,
    val foto: String,
    val cpf:String,
    val data_nascimento: String,
    val matricula: String,
    val id_email: Int,
    val email: String,
    val id_telefone: Int,
    val telefone: String,
    val sexo: String,
    val logradouro: String,
    val cep: String,
    val bairro: String,
    val numero: String,
    val complemento: String,
    val id_cidade: Int,
    val cidade: String,
    val estado: String,
    val id_turma: Int,
    val id_escola: Int
)
