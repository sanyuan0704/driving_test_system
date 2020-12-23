package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentCheckJudgeBinding;
import com.whut.driving_test_system.databinding.FragmentExamBinding;
import com.whut.driving_test_system.ui.viewmodels.ExamViewModel;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;


public class CheckJudgeFragment extends Fragment {
    private Button button;
    private MainViewModel mainViewModel;
    private ExamViewModel examViewModel;
    private FragmentCheckJudgeBinding binding;

    public CheckJudgeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_check_judge, container, false);
        button = view.findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_checkJudgeFragment_to_judgeFragment);

            }
        });

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_judge, container, false);


        return view;
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_check_judge, container, false);
    }
}