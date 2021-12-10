package dev.bahodir.lesson26thtask1thmovieapp.db

import dev.bahodir.lesson26thtask1thmovieapp.user.User

interface DBInterface {

    fun addUser(user: User)

    fun editUser(user: User)

    fun deleteUser(user: User)

    fun getUserList(): MutableList<User>
}