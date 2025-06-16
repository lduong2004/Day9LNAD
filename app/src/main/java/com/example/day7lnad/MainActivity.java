package com.example.day7lnad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day7lnad.adapters.ProductAdapter;
import com.example.day7lnad.api.ProductApi;
import com.example.day7lnad.interfaces.ProductClickListener;
import com.example.day7lnad.models.AllProductResponse;
import com.example.day7lnad.models.Product;
import com.example.day7lnad.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView reDemo;
    private ProductAdapter mProductAdapter;
    private List<Product> fullProductList = new ArrayList<>();

    private EditText edtSearch;
    private Button btnSearch;
    private ProductApi productApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        reDemo = findViewById(R.id.reDemo);
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        Button btnAddToCart = findViewById(R.id.btnAddToCart);

        productApi = RetrofitClient.getProductApi();

        // Set layout 2 cột cho RecyclerView
        reDemo.setLayoutManager(new GridLayoutManager(this, 2));

        // Gọi API lấy danh sách sản phẩm
        productApi.getAllProducts().enqueue(new Callback<AllProductResponse>() {
            @Override
            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    fullProductList = response.body().getProducts();

                    // Hiển thị 10 sản phẩm đầu tiên
                    List<Product> topProducts = fullProductList.subList(0, Math.min(10, fullProductList.size()));
                    mProductAdapter = new ProductAdapter(new ArrayList<>(topProducts));
                    reDemo.setAdapter(mProductAdapter);

                    mProductAdapter.setProductClickListener(new ProductClickListener() {
                        @Override
                        public void onItemClickListener(int position, Product product) {
                            Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                            intent.putExtra("name", product.getTitle());
                            intent.putExtra("description", product.getDescription());
                            intent.putExtra("price", product.getPrice().toString());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AllProductResponse> call, Throwable t) {
                Log.e(TAG, "API failed: " + t.getMessage());
            }
        });

        // Xử lý sự kiện Search
        btnSearch.setOnClickListener(v -> {
            String keyword = edtSearch.getText().toString().trim().toLowerCase();
            List<Product> filteredList = new ArrayList<>();

            for (Product p : fullProductList) {
                if (p.getTitle().toLowerCase().contains(keyword)) {
                    filteredList.add(p);
                }
            }

            mProductAdapter.updateData(filteredList);
        });

        Button btnCart = findViewById(R.id.btnAddToCart);
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
