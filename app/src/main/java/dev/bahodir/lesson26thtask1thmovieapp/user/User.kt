package dev.bahodir.lesson26thtask1thmovieapp.user

import java.io.Serializable

data class User(
    var id: Int? = null,
    var movieName: String? = null,
    var movieAuthors: String? = null,
    var aboutMovie: String? = null,
    var movieDate: String? = null
) : Serializable