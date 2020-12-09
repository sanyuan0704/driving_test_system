package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentHomeBinding;
import com.whut.driving_test_system.models.eneities.Examinee;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(getActivity());

        // 考生列表
        RecyclerView rcv = binding.iclHomeContent.findViewById(R.id.rcv_examinees);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Examinee> examinees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            examinees.add(new Examinee("121321", "45345345", "xxx", "school", i, "C1", "wait"));
        }
        ExamineeAdapter examineeAdapter = new ExamineeAdapter(examinees);
        rcv.setAdapter(examineeAdapter);

        // 侧边栏导航按钮
        binding.navView.findViewById(R.id.fbtn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_verifyFragment);
            }
        });

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
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.drawerLayout.openDrawer(GravityCompat.END);
                }
            });
        }

        @Override
        public int getItemCount() {
            return examinees.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

    }
}
