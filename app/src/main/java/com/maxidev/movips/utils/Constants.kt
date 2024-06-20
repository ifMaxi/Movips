package com.maxidev.movips.utils

object Constants {

    const val CONTENT_TYPE = "application/json"
    const val BASE_URL = " https://api.themoviedb.org/3/"

    // Movies
    const val NOW_PLAYING_MOVIES = "movie/now_playing"
    const val POPULAR_MOVIES = "movie/popular"
    const val TOP_RATED_MOVIES = "movie/top_rated"
    const val UPCOMING_MOVIES = "movie/upcoming"

    // Detail
    const val DETAIL_MOVIE = "movie/{movie_id}"

    // Search
    const val SEARCH_MOVIE = "search/movie"

    // Credits
    const val CREDITS_MOVIE = "movie/{movie_id}/credits"

    // Recommendations
    const val RECOMMENDATIONS_MOVIE = "movie/{movie_id}/recommendations"

    // Trending
    const val TRENDING_MOVIE = "trending/movie/{time_window}"
}