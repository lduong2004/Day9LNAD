package com.example.day7lnad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day7lnad.R;
import com.example.day7lnad.adapters.CartAdapter;
import com.example.day7lnad.databases.SQLiteHelper;
import com.example.day7lnad.models.CartItem;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CartAdapter adapter;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerCart);
        db = new SQLiteHelper(this);

        List<CartItem> items = db.getAllCartItems();
        adapter = new CartAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
