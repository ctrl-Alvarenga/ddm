package com.example.tp_03_ex2

/**
 * DAO em memória: centraliza o acesso à lista de alunos cadastrados,
 * mantendo as telas sem conhecimento de como os dados são guardados.
 */
object AlunoDao {

    private val alunos = mutableListOf<Aluno>()

    fun salvar(aluno: Aluno) {
        alunos.add(aluno)
    }

    fun listarTodos(): List<Aluno> = alunos.toList()
}
