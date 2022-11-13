package com.example.geoquiz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.geoquiz.R;
import com.example.geoquiz.classes.QuestionViewModel;
import com.example.geoquiz.databinding.ConfrontFragmentBinding;

public class ConfrontFragment extends Fragment {

    private ConfrontFragmentBinding binding;
    private QuestionViewModel questionViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = ConfrontFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        questionViewModel = new ViewModelProvider(this.requireActivity()).get(QuestionViewModel.class);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionViewModel.setNaughty(false);
                NavHostFragment.findNavController(ConfrontFragment.this)
                        .navigate(R.id.action_ConfrontFragment_to_QuestionFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}