package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentHomeBinding;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.UserWithExaminees;
import com.whut.driving_test_system.models.repository.UserRepository;
import com.whut.driving_test_system.ui.viewmodels.HomeViewModel;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

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
    private MainViewModel mainViewModel;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private UserRepository userRepository;
    private RecyclerView rcv;
    private ExamineeAdapter examineeAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // viewModel
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setHomeViewModel(homeViewModel);
        binding.setLifecycleOwner(this);

        // 记录当前页面位置
        mainViewModel.anchor.setValue(R.id.homeFragment);

        // 考生列表
        rcv = binding.iclHomeContent.rcvExaminees;
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        examineeAdapter = new ExamineeAdapter(new ArrayList<Examinee>());
        rcv.setAdapter(examineeAdapter);

        userRepository = new UserRepository(getContext());
        homeViewModel.userWithExaminees = userRepository.getUserWithExamineesById(mainViewModel.loginedUser.getValue().userId);
        homeViewModel.userWithExaminees.observe(getViewLifecycleOwner(), new Observer<UserWithExaminees>() {
            @Override
            public void onChanged(UserWithExaminees userWithExaminees) {
                updateDataset(-1);
                int[] cnt = new int[4];
                for (Examinee examinee : userWithExaminees.examinees) {
                    if(examinee.examStatus == Examinee.ExamStatus.WAIT.ordinal()){
                        cnt[1]++;
                    }else if(examinee.examStatus == Examinee.ExamStatus.FAILED.ordinal()){
                        cnt[2]++;
                    }else if(examinee.examStatus == Examinee.ExamStatus.PASSED.ordinal()){
                        cnt[3]++;
                    }
                    cnt[0]++;
                }
                binding.iclHomeContent.tvAlln.setText(String.valueOf(cnt[0]) + "人");
                binding.iclHomeContent.tvWaitn.setText(String.valueOf(cnt[1]) + "人");
                binding.iclHomeContent.tvFailedn.setText(String.valueOf(cnt[2]) + "人");
                binding.iclHomeContent.tvPassedn.setText(String.valueOf(cnt[3]) + "人");
                for (Examinee examinee : userWithExaminees.examinees) {
                    if(examinee.examStatus == Examinee.ExamStatus.WAIT.ordinal()){
                        homeViewModel.waitNumber.setValue(homeViewModel.waitNumber.getValue() + 1);
                    }else if(examinee.examStatus == Examinee.ExamStatus.FAILED.ordinal()){
                        homeViewModel.failedNumber.setValue(homeViewModel.failedNumber.getValue() + 1);
                    }else if(examinee.examStatus == Examinee.ExamStatus.PASSED.ordinal()){
                        homeViewModel.passedNumber.setValue(homeViewModel.passedNumber.getValue() + 1);
                    }
                    homeViewModel.allNumber.setValue(homeViewModel.allNumber.getValue() + 1);
                }
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
            // 数据源更新，通知刷新页面
            this.notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_examinee, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
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
            Glide.with(holder.img_header.getContext()).load(examinees.get(position).imageUrl).into(holder.img_header);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainViewModel.selectedExamniee.setValue(examinee);
                    homeViewModel.choisedExaminee.setValue(examinee);
                    binding.drawerLayout.openDrawer(GravityCompat.END);

                    Glide.with(binding.imgHeader.getContext()).load(examinees.get(position).imageUrl).into(binding.imgHeader);
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
