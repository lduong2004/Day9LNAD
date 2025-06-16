package com.example.day7lnad.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day7lnad.R;
import com.example.day7lnad.models.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<CartItem> cartItems;

    public CartAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.txtName.setText(item.name);
        holder.txtPrice.setText(String.format("₫%.0f", item.price));
        holder.txtQuantity.setText(String.valueOf(item.quantity));
        // Có thể dùng Glide/Picasso để load ảnh nếu bạn cần
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtQuantity;
        Button btnIncrease, btnDecrease;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtCartName);
            txtPrice = itemView.findViewById(R.id.txtCartPrice);
            txtQuantity = itemView.findViewById(R.id.txtCartQuantity);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);

            btnIncrease.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                cartItems.get(pos).quantity++;
                notifyItemChanged(pos);
            });

            btnDecrease.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (cartItems.get(pos).quantity > 1) {
                    cartItems.get(pos).quantity--;
                    notifyItemChanged(pos);
                }
            });
        }
    }
}
