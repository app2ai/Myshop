package com.rtech.myshoppy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.rtech.myshoppy.db.ShoppyRoomDatabase
import com.rtech.myshoppy.db.dao.UserDao
import com.rtech.myshoppy.db.entities.UserDetailsModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserDaoTest {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppyRoomDatabase
    private lateinit var dao: UserDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppyRoomDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.userDao()
    }

    @Test
    fun insertUser_expectedInsert_singleUser() = runBlocking{
        val user = UserDetailsModel(0, "test111", "22", "2222222222", "222222", false)
        val res = dao.addUser(user)

        val userData = dao.getUserDetails(0)  // Use here LiveData extension if dao return the LiveData thing from db
        print("User data: $userData")
        Assert.assertEquals(1, res)
        // Assert.assertEquals(userData.uname, user.uname)
    }

    @Test
    fun getUser_expected_userId() {

    }


    @After
    fun tearDown() {
        database.close()
    }
}