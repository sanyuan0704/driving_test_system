package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentHomeBinding;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.ui.viewmodels.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 实现功能：
 * 1. 考生情况列表
 * 2，跳转到考生验证界面
 */
public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // viewModel
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setHomeViewModel(homeViewModel);
        binding.setLifecycleOwner(this);

        // 考生列表
        RecyclerView rcv = binding.iclHomeContent.findViewById(R.id.rcv_examinees);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Examinee> examinees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            examinees.add(new Examinee("121321", "45345345", "xxx", "school", i, "C1", "wait", 90, null));
        }
        ExamineeAdapter examineeAdapter = new ExamineeAdapter(examinees);
        rcv.setAdapter(examineeAdapter);


        // 侧边栏不允许拖出来
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        return binding.getRoot();
    }

    class ExamineeAdapter extends RecyclerView.Adapter<ExamineeAdapter.ViewHolder> {
        private List<Examinee> examinees;

        public ExamineeAdapter(List<Examinee> examinees) {
            this.examinees = examinees;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_examinee, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            final Examinee examinee = examinees.get(position);
            holder.tv_name.setText(examinee.name);
            holder.tv_school.setText(examinee.school);
            holder.tv_examnumber.setText(examinee.examNumber);
            holder.tv_idnumber.setText(examinee.idNumber);
            holder.tv_status.setText(examinee.examStatus);
            // TODO: set image

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.drawerLayout.openDrawer(GravityCompat.END);
                    homeViewModel.choisedExaminee.setValue(examinee);
                    // TODO: set image
                    // TODO: set fbtn
                }
            });
        }

        @Override
        public int getItemCount() {
            return examinees.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img_header, img_status;
            TextView tv_name, tv_school, tv_examnumber, tv_idnumber, tv_status;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.img_header = itemView.findViewById(R.id.img_header);
                this.img_status = itemView.findViewById(R.id.img_status);
                this.tv_name = itemView.findViewById(R.id.tv_name);
                this.tv_school = itemView.findViewById(R.id.tv_school);
                this.tv_examnumber = itemView.findViewById(R.id.tv_examnumber);
                this.tv_idnumber = itemView.findViewById(R.id.tv_idnumber);
                this.tv_status = itemView.findViewById(R.id.tv_status);
            }
        }

    }
}
