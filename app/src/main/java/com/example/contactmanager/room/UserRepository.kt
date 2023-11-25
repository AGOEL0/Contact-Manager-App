package com.example.contactmanager.room

class UserRepository(private val dao: UserDao) {
    val users=dao.getAllUserInDb()

    suspend fun insert(users: User):Long{
        return dao.insertUser(users)
    }
    suspend fun update(users: User): Int {
        return dao.updateUser(users)
    }
    suspend fun delete(users: User): Int {
        return dao.deleteUser(users)
    }
       suspend fun deleteAll(){
           return dao.deleteAll()
       }
}