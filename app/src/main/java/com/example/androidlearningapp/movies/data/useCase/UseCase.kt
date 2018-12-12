package com.example.androidlearningapp.movies.data.useCase

interface UseCase<REQUEST, RESPONSE> {

    fun execute(request: REQUEST): RESPONSE
}