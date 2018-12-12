package com.example.androidlearningapp.movies.data.viewModels

import android.arch.lifecycle.ViewModel
import com.example.androidlearningapp.movies.data.useCase.*
import com.example.androidlearningapp.movies.entity.Movie
import com.example.androidlearningapp.movies.entity.MovieElement
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

const val MOVIES_PER_PAGE = 7

class DataViewModel : ViewModel(), DataViewModelInterface {

    override val isAbleToLoadMovies: PublishSubject<Boolean> = PublishSubject.create()
    override val isLoadingMore: PublishSubject<Boolean> = PublishSubject.create()
    override val isRefreshing: PublishSubject<Boolean> = PublishSubject.create()
    override val emptyViewVisibility: PublishSubject<Boolean> = PublishSubject.create()
    override val recyclerViewVisibility: PublishSubject<Boolean> = PublishSubject.create()
    override val isError: PublishSubject<Unit> = PublishSubject.create()

    private val loadMoviesUseCase: UseCase<Unit, Single<List<Movie>>> = LoadMoviesUseCase()
    private val saveMoviesCacheUseCase: UseCase<List<Movie>, Unit> = SaveMoviesCacheUseCase()
    private val loadMoviesCacheUseCase: UseCase<Unit, List<Movie>> = LoadMoviesCacheUseCase()
    private val cleanMoviesCacheUseCase: UseCase<Unit, Unit> = CleanMoviesCacheUseCase()

    override val moviesSubject: BehaviorSubject<List<MovieElement>> = BehaviorSubject.create()
    private var pageNumber = 1
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getMovies(refresh: Boolean) {
        if (refresh) {
            pageNumber = 1
        }
        showLoading()
        compositeDisposable.add(loadMoviesUseCase.execute(Unit)
                .subscribe(this::onGetMoviesSuccess, this::onGetMovieError))
    }

    private fun showLoading() {
        isAbleToLoadMovies.onNext(false)
        if (pageNumber == 1) {
            isRefreshing.onNext(true)
        } else {
            isLoadingMore.onNext(true)
        }
    }

    private fun hideLoading() {
        isRefreshing.onNext(false)
        isLoadingMore.onNext(false)
    }

    private fun updateViewsVisibility() {
        recyclerViewVisibility.onNext(true)
        emptyViewVisibility.onNext(false)
    }

    private fun onGetMoviesSuccess(movies: List<Movie>) {
        hideLoading()
        isAbleToLoadMovies.onNext(movies.size == MOVIES_PER_PAGE)
        if (pageNumber > 1) {
            moviesSubject.onNext(if (moviesSubject.hasValue()) moviesSubject.value!!.plus(movies) else movies)
        } else {
            cleanMoviesCacheUseCase.execute(Unit)
            moviesSubject.onNext(movies)
        }
        saveMoviesCacheUseCase.execute(movies)
        pageNumber++
        updateViewsVisibility()
    }

    private fun onGetMovieError(e: Throwable) {
        hideLoading()
        moviesSubject.onNext(loadMoviesCacheUseCase.execute(Unit))
        isError.onNext(Unit)
        isAbleToLoadMovies.onNext(false)
    }

    override fun clearMovies() {
        compositeDisposable.clear()
    }
}