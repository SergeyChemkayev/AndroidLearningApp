package com.example.androidlearningapp.movies.data.useCase

interface UseCase<INPUT, OUTPUT> {

    fun execute(input: INPUT): OUTPUT
}