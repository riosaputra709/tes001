package com.example.androideatit.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.example.androideatit.Common.Common;
import com.example.androideatit.Common.SpacesItemDecoration;
import com.example.androideatit.R;
import com.example.androideatit.adapter.MyAppBestDealsAdapter;
import com.example.androideatit.adapter.MyAppPopularCategoryAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    Unbinder unbinder;
    @BindView(R.id.recycler_popular)
    RecyclerView recycler_popular;
    @BindView(R.id.viewpager)
    LoopingViewPager viewpager;

    AlertDialog dialog;
    LayoutAnimationController layoutAnimationController;
    MyAppPopularCategoryAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this,root);
        initViews();
        
        homeViewModel.getMessageError().observe(this, s -> {
            Toast.makeText(getContext(), ""+s, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        homeViewModel.getPopularList().observe(this, popularCategoryModels -> {
            dialog.dismiss();

            //create adapter
            adapter = new MyAppPopularCategoryAdapter(getContext(), popularCategoryModels);
            recycler_popular.setAdapter(adapter);
            recycler_popular.setLayoutAnimation(layoutAnimationController);
        });

        homeViewModel.getBestDealList().observe(this, bestDealModels -> {
            MyAppBestDealsAdapter adapter = new MyAppBestDealsAdapter(getContext(), bestDealModels, true);
            viewpager.setAdapter(adapter);
        });
        return root;
    }

    private void initViews() {
//        dialog = new SpotsDialog.Builder().setContext(getContext()).setCancelable(false).build();
//        dialog.show();

//        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (adapter != null)
//                {
//                    switch (adapter.getItemViewType(position))
//                    {
//                        case Common.DEFAULT_COLUMN_COUNT: return 1;
//                        case Common.FULL_WIDTH_COLUMN: return 2;
//                        default:return -1;
//                    }
//                }
//                return -1;
//            }
//        });
//        recycler_level_menu.setLayoutManager(layoutManager);
//        recycler_level_menu.addItemDecoration(new SpacesItemDecoration(8));
        dialog = new SpotsDialog.Builder().setContext(getContext()).setCancelable(false).build();
        //dialog.show(); remove it to fix loading show when resume fragment
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recycler_popular.setHasFixedSize(true);
        recycler_popular.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        recycler_popular.addItemDecoration(new DividerItemDecoration(getContext(),layoutManager.getOrientation()));
    }

    @Override
    public void onResume() {
        super.onResume();
        viewpager.resumeAutoScroll();
    }

    @Override
    public void onPause() {
        viewpager.pauseAutoScroll();
        super.onPause();
    }
}
