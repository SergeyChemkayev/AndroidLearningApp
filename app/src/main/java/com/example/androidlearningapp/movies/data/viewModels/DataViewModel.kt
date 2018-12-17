package com.example.androidlearningapp.movies.data.viewModels

import android.arch.lifecycle.ViewModel
import com.example.androidlearningapp.movies.data.useCase.LoadMoviesCacheUseCase
import com.example.androidlearningapp.movies.data.useCase.LoadMoviesUseCase
import com.example.androidlearningapp.movies.data.useCase.UseCase
import com.example.androidlearningapp.movies.entity.Movie
import com.example.androidlearningapp.movies.entity.MovieElement
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class DataViewModel(
        private val loadMoviesUseCase: UseCase<Int, Completable> = LoadMoviesUseCase(),
        private val loadMoviesCacheUseCase: UseCase<Unit, Flowable<List<Movie>>> = LoadMoviesCacheUseCase()
) : ViewModel(), DataViewModelInterface {

    override val isAbleToLoadMovies: PublishSubject<Boolean> = PublishSubject.create()
    override val isLoadingMore: PublishSubject<Boolean> = PublishSubject.create()
    override val isRefreshing: PublishSubject<Boolean> = PublishSubject.create()
    override val emptyViewVisibility: PublishSubject<Boolean> = PublishSubject.create()
    override val recyclerViewVisibility: PublishSubject<Boolean> = PublishSubject.create()
    override val isError: PublishSubject<Unit> = PublishSubject.create()

    override val moviesSubject: BehaviorSubject<List<MovieElement>> = BehaviorSubject.create()
    private var pageNumber = 1
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getMovies(refresh: Boolean) {
        if (refresh) {
            pageNumber = 1
        }
        showLoading()
        compositeDisposable.add(loadMoviesUseCase.execute(pageNumber)
                .subscribe({}, this::onGetMovieError))
        compositeDisposable.add(loadMoviesCacheUseCase.execute(Unit)
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
        isAbleToLoadMovies.onNext(true)
        moviesSubject.onNext(movies)
        pageNumber++
        updateViewsVisibility()
    }

    private fun onGetMovieError(e: Throwable) {
        hideLoading()
        isError.onNext(Unit)
        isAbleToLoadMovies.onNext(false)
    }

    override fun clearMovies() {
        compositeDisposable.clear()
    }
}