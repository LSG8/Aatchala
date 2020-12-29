package com.example.aatchala;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Order.class, Items.class, ItemCategory.class}, version = 1)
public abstract class AatchalaDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract OrderDao orderDao();

    public abstract ItemsDao itemsDao();

    public abstract ItemCategoryDao itemCategoryDao();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile AatchalaDatabase INSTANCE;

    public static AatchalaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AatchalaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AatchalaDatabase.class, "aatchala_database").allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {


            ItemsDao itemsDao = INSTANCE.itemsDao();
            Items item1 = new Items(1, "Potato/alu", "Vegetable", true, 0, "kg");
            itemsDao.add(item1);
            Items item2 = new Items(2, "Onion/piyanz", "Vegetable", true, 0, "kg");
            itemsDao.add(item2);
            Items item3 = new Items(3, "Ginger/ada", "Vegetable", true, 0, "gm");
            itemsDao.add(item3);
            Items item4 = new Items(4, "Rosun", "Vegetable", true, 0, "gm");
            itemsDao.add(item4);
            Items item5 = new Items(5, "Lonka", "Vegetable", true, 0, "gm");
            itemsDao.add(item5);
            Items item6 = new Items(6, "Potol", "Vegetable", true, 0, "gm");
            itemsDao.add(item6);
            Items item7 = new Items(7, "Sosha", "Vegetable", true, 0, "kg");
            itemsDao.add(item7);
            Items item8 = new Items(8, "Pan", "Vegetable", true, 0, "pcs");
            itemsDao.add(item8);
            Items item9 = new Items(9, "Rice", "Grocery", true, 0, "kg");
            itemsDao.add(item9);
            Items item10 = new Items(10, "Dal", "Grocery", true, 0, "kg");
            itemsDao.add(item10);
            Items item11 = new Items(11, "Mustard oil", "Grocery", true, 0, "ltr");
            itemsDao.add(item11);
            Items item12 = new Items(12, "Salt", "Grocery", true, 0, "kg");
            itemsDao.add(item12);
            Items item13 = new Items(13, "Ghee", "Grocery", true, 0, "gm");
            itemsDao.add(item13);
            Items item14 = new Items(14, "Tea", "Grocery", true, 0, "gm");
            itemsDao.add(item14);
            Items item15 = new Items(15, "Sugar", "Grocery", true, 0, "kg");
            itemsDao.add(item15);
            Items item16 = new Items(16, "Muri", "Grocery", true, 0, "gm");
            itemsDao.add(item16);
            Items item17 = new Items(17, "Chire bhaja", "Grocery", true, 0, "pkt");
            itemsDao.add(item17);
            Items item18 = new Items(18, "Chanachur", "Grocery", true, 0, "pkt");
            itemsDao.add(item18);
            Items item19 = new Items(19, "Misti doi", "Sweets", true, 0, "gm");
            itemsDao.add(item19);
            Items item20 = new Items(20, "Rosogolla", "Sweets", true, 0, "pcs");
            itemsDao.add(item20);
            Items item21 = new Items(21, "Kalo jamun", "Sweets", true, 0, "pcs");
            itemsDao.add(item21);
            Items item22 = new Items(22, "Paneer", "Sweets", true, 0, "gm");
            itemsDao.add(item22);
            Items item23 = new Items(23, "Bread", "Breakfast", true, 0, "pkt");
            itemsDao.add(item23);
            Items item24 = new Items(24, "Butter", "Breakfast", true, 0, "gm");
            itemsDao.add(item24);
            Items item25 = new Items(25, "Egg", "Breakfast", true, 0, "pcs");
            itemsDao.add(item25);
            Items item26 = new Items(26, "Banana", "Breakfast", true, 0, "pcs");
            itemsDao.add(item26);
            });

            /*ItemCategoryDao categoryDao = INSTANCE.itemCategoryDao();


            ItemCategory type1 = new ItemCategory(1, "Vegetable");
            categoryDao.addCategory(type1);
            ItemCategory type2 = new ItemCategory(2, "Grocery");
            categoryDao.addCategory(type1);
            ItemCategory type3 = new ItemCategory(3, "Breakfast");
            categoryDao.addCategory(type1);
            ItemCategory type4 = new ItemCategory(4, "Sweets");
            categoryDao.addCategory(type1);*/
        }
    };

}
