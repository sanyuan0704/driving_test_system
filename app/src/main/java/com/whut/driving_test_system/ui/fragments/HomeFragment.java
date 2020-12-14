package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentHomeBinding;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.models.eneities.UserWithExaminees;
import com.whut.driving_test_system.models.repository.UserRepository;
import com.whut.driving_test_system.ui.viewmodels.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
    private UserRepository userRepository;
    private RecyclerView rcv;
    private ExamineeAdapter examineeAdapter;
    private String userId;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // 登录的UserID
        userId = getArguments().getString("userId", null);

        // viewModel
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setHomeViewModel(homeViewModel);
        binding.setLifecycleOwner(this);

        // 考生列表
        rcv = binding.iclHomeContent.rcvExaminees;
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        examineeAdapter = new ExamineeAdapter(new ArrayList<Examinee>());
        rcv.setAdapter(examineeAdapter);

        userRepository = new UserRepository(getContext());
        homeViewModel.userWithExaminees = userRepository.getUserWithExamineesById(userId);
        homeViewModel.userWithExaminees.observe(getViewLifecycleOwner(), new Observer<UserWithExaminees>() {
            @Override
            public void onChanged(UserWithExaminees userWithExaminees) {
                updateDataset(-1);
            }
        });
        binding.iclHomeContent.crdWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataset(Examinee.ExamStatus.WAIT.ordinal());
            }
        });
        binding.iclHomeContent.crdFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataset(Examinee.ExamStatus.FAILED.ordinal());
            }
        });
        binding.iclHomeContent.crdPassed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataset(Examinee.ExamStatus.PASSED.ordinal());
            }
        });
        binding.iclHomeContent.crdAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataset(-1);
            }
        });


        // 侧边栏不允许拖出来
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        return binding.getRoot();
    }

    private void updateDataset(int examStatus) {
        if (examStatus == -1) {
            examineeAdapter.setExaminees(homeViewModel.userWithExaminees.getValue().examinees);
        } else {
            List<Examinee> newExaminees = new ArrayList<>();
            for (Examinee examinee : homeViewModel.userWithExaminees.getValue().examinees) {
                if (examinee.examStatus == examStatus) {
                    newExaminees.add(examinee);
                }
            }
            examineeAdapter.setExaminees(newExaminees);
        }
    }

    class ExamineeAdapter extends RecyclerView.Adapter<ExamineeAdapter.ViewHolder> {
        private List<Examinee> examinees;

        public ExamineeAdapter(List<Examinee> examinees) {
            this.examinees = examinees;
        }

        public void setExaminees(List<Examinee> examinees) {
            this.examinees = examinees;
            this.notifyDataSetChanged();
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
            if (examinee.examStatus == Examinee.ExamStatus.WAIT.ordinal()) {
                holder.tv_status.setText("等待中");
                holder.img_status.setImageResource(R.drawable.ic_watch_later_black_24dp);
            } else if (examinee.examStatus == Examinee.ExamStatus.PASSED.ordinal()) {
                holder.tv_status.setText("已通过");
                holder.img_status.setImageResource(R.drawable.ic_check_circle_black_24dp);
            } else if (examinee.examStatus == Examinee.ExamStatus.FAILED.ordinal()) {
                holder.tv_status.setText("未通过");
                holder.img_status.setImageResource(R.drawable.ic_cancel_black_24dp);
            }

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
