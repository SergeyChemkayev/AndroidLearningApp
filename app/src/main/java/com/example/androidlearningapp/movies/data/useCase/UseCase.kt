package com.example.androidlearningapp.movies.data.useCase

interface UseCase<INPUT, OUTPUT> {

    fun execute(request: INPUT): OUTPUT
}