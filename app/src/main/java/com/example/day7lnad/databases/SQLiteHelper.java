package com.example.day7lnad.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.day7lnad.models.CartItem;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context) {
        super(context, "MyDatabase.db", null, 1); // tên DB có thể thay đổi nếu bạn dùng tên khác
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCartTable = "CREATE TABLE Cart (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "product_id TEXT, " +
                "name TEXT, " +
                "price REAL, " +
                "image TEXT, " +
                "quantity INTEGER)";
        db.execSQL(createCartTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addToCart(String productId, String name, double price, String image, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_id", productId);
        values.put("name", name);
        values.put("price", price);
        values.put("image", image);
        values.put("quantity", quantity);
        db.insert("Cart", null, values);
        db.close();
    }

    public List<CartItem> getAllCartItems() {
        List<CartItem> cart = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Cart", null);
        if (cursor.moveToFirst()) {
            do {
                cart.add(new CartItem(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getString(4),
                        cursor.getInt(5)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cart;
    }

}
