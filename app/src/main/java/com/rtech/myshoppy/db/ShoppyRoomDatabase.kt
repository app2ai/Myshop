package com.rtech.myshoppy.db

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rtech.myshoppy.db.dao.ProductDao
import com.rtech.myshoppy.db.dao.UserDao
import com.rtech.myshoppy.db.entities.CartModel
import com.rtech.myshoppy.db.entities.ProductDetailsModel
import com.rtech.myshoppy.db.entities.UserDetailsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [UserDetailsModel::class, ProductDetailsModel::class, CartModel::class], version = 1, exportSchema = false)
abstract class ShoppyRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao

    companion object{
        @Volatile
        private var INSTANCE: ShoppyRoomDatabase? = null

        fun getDbInstance(
            context: Context,
            scope: CoroutineScope
        ): ShoppyRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ShoppyRoomDatabase::class.java,
                    "shoppy_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(ShoppyDatabaseCallback(scope))
                    //.addMigrations()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(@NonNull database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `tblProduct` (`id` INTEGER, `productName` TEXT, `originalPrice` NUMBER, `sellingPrice` NUMBER, `productImagePath` TEXT, `productDesc` TEXT, `rating` INTEGER, discount INTEGER, Primary key(`id`))")
            }
        }
    }

    private class ShoppyDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE.let { database ->
                scope.launch {
                    populateProducts(database?.productDao())
                }
            }
        }

        suspend fun populateProducts(productDao: ProductDao?) {
            productDao?.deleteAllProducts()

            // Add all products at all
            addProduct(productDao)
        }

        private suspend fun addProduct(dao: ProductDao?) {
            val p1 = ProductDetailsModel(productName = "Test1", productImagePath = "", productDesc = "Test1 Desc", originalPrice = 100.00, sellingPrice = 99.00, rating = 4, discount = 1)
            dao?.addProduct(p1)
            val p2 = ProductDetailsModel(productName = "Test2", productImagePath = "", productDesc = "Test2 Desc", originalPrice = 1000.00, sellingPrice = 500.00, rating = 1, discount = 50)
            dao?.addProduct(p2)
            val p3 = ProductDetailsModel(productName = "Test3", productImagePath = "", productDesc = "Test3 Desc", originalPrice = 1200.00, sellingPrice = 999.00, rating = 1, discount = 11)
            dao?.addProduct(p3)
            val p4 = ProductDetailsModel(productName = "Test4", productImagePath = "", productDesc = "Test4 Desc", originalPrice = 1100.00, sellingPrice = 1000.00, rating = 2, discount = 10)
            dao?.addProduct(p4)
            val p5 = ProductDetailsModel(productName = "Test5", productImagePath = "", productDesc = "Test5 Desc", originalPrice = 700.00, sellingPrice = 600.00, rating = 3, discount = 9)
            dao?.addProduct(p5)
            val p6 = ProductDetailsModel(productName = "Test6", productImagePath = "", productDesc = "Test6 Desc", originalPrice = 1900.00, sellingPrice = 1800.00, rating = 3, discount = 7)
            dao?.addProduct(p6)
            val p7 = ProductDetailsModel(productName = "Test7", productImagePath = "", productDesc = "Test7 Desc", originalPrice = 1800.00, sellingPrice = 1500.00, rating = 4, discount = 5)
            dao?.addProduct(p7)
            val p8 = ProductDetailsModel(productName = "Test8", productImagePath = "", productDesc = "Test8 Desc", originalPrice = 10000.00, sellingPrice = 9999.00, rating = 5, discount = 0)
            dao?.addProduct(p8)
            val p9 = ProductDetailsModel(productName = "Test9", productImagePath = "", productDesc = "Test9 Desc", originalPrice = 9990.00, sellingPrice = 9900.00, rating = 2, discount = 2)
            dao?.addProduct(p9)
            val p10 = ProductDetailsModel(productName = "Test10", productImagePath = "", productDesc = "Test10 Desc", originalPrice = 1500.00, sellingPrice = 999.00, rating = 3, discount = 24)
            dao?.addProduct(p10)
        }
    }
}