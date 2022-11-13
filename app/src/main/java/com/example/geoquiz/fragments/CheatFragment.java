package com.example.geoquiz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.geoquiz.R;
import com.example.geoquiz.classes.Question;
import com.example.geoquiz.classes.QuestionViewModel;
import com.example.geoquiz.databinding.CheatFragmentBinding;

public class CheatFragment extends Fragment {

    private CheatFragmentBinding binding;
    private QuestionViewModel questionViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        questionViewModel = new ViewModelProvider(this.requireActivity()).get(QuestionViewModel.class);

        binding = CheatFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.cheat_textview)).setText(questionViewModel.getResult(true));

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CheatFragment.this)
                        .navigate(R.id.action_CheatFragment_to_QuizFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}