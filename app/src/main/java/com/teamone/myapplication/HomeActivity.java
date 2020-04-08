package com.teamone.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewHome;

    private LinearLayout stickyTextLayout;
    private ProductsRecyclerViewAdapter productsRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        setUpRecyclerView();

    }

    private void initViews() {
        recyclerViewHome = findViewById(R.id.recycler_view_home);
        stickyTextLayout = findViewById(R.id.sticky_view);

    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerViewHome.setLayoutManager(linearLayoutManager);
        productsRecyclerViewAdapter = new ProductsRecyclerViewAdapter();
        recyclerViewHome.addOnScrollListener(onScrollListener);
        recyclerViewHome.setAdapter(productsRecyclerViewAdapter);
    }



    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            View view = recyclerView.getChildAt(0);

            int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
            if (viewHolder != null) {
                TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.title);

            }
            if (dy > 0) {
                if (position > 0) {
                    stickyTextLayout.setVisibility(View.VISIBLE);
                }
            } else {
                if (position == 0) {
                    stickyTextLayout.setVisibility(View.GONE);
                }
            }
        }
    };

    private class ProductsRecyclerViewAdapter extends RecyclerView.Adapter {

        private static final int TYPE_HEADER = 0;
        private static final int TYPE_FOOTER = 1;
        private static final int TYPE_STICKY = 2;


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEADER) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
                return new VHHeader(view);

            } else if (viewType == TYPE_STICKY) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticky_view, parent, false);
                return new VHSticky(view);

            } else if (viewType == TYPE_FOOTER) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false);
                return new VHProducts(view);

            }
            throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

        }


        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position)) {
                return TYPE_HEADER;
            } else if (isPositionStickyHeader(position)) {
                return TYPE_STICKY;
            }
            return TYPE_FOOTER;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

        private boolean isPositionStickyHeader(int position) {
            return position == 1;
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof VHHeader) {
                VHHeader vhHeader = (VHHeader) holder;

            } else if (holder instanceof VHProducts) {
                VHProducts vhProducts = (VHProducts) holder;



            } else if (holder instanceof VHSticky) {
                VHSticky vhSticky = (VHSticky) holder;

            }


        }



        @Override
        public int getItemCount() {
            return 10;
        }


        private class VHHeader extends RecyclerView.ViewHolder {



            public VHHeader(View view) {
                super(view);


            }


        }

        private class VHProducts extends RecyclerView.ViewHolder {



            public VHProducts(View view) {
                super(view);
            }
        }


        private class VHSticky extends RecyclerView.ViewHolder {

            private TextView textViewTitle;

            public VHSticky(View view) {
                super(view);
                textViewTitle = view.findViewById(R.id.title);
            }
        }
    }


}
