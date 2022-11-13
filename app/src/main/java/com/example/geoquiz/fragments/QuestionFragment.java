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
import com.example.geoquiz.databinding.QuestionFragmentBinding;
import com.google.android.material.snackbar.Snackbar;

public class QuestionFragment extends Fragment {

    private QuestionFragmentBinding binding;
    private QuestionViewModel questionViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        questionViewModel = new ViewModelProvider(this.requireActivity()).get(QuestionViewModel.class);
        binding = QuestionFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.questionView.setText(questionViewModel.getQuestion());

        binding.cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionViewModel.setNaughty(true);
                NavHostFragment.findNavController(QuestionFragment.this)
                        .navigate(R.id.action_QuestionFragment_to_CheatFragment);
            }
        });

        binding.trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar answerConfirmation =  Snackbar.make(binding.questionView, questionViewModel.getResult(true),Snackbar.LENGTH_LONG).setAction("Next Question", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (questionViewModel.ifNext()) {
                            questionViewModel.nextQuestion();
                            binding.questionView.setText(questionViewModel.getQuestion());
                        }
                        else
                        {
                            binding.questionView.setText(R.string.game_over);
                            binding.newGame.setVisibility(View.VISIBLE);
                            binding.falseButton.setVisibility(View.GONE);
                            binding.trueButton.setVisibility(View.GONE);
                            binding.cheatButton.setVisibility(View.GONE);
                        }
                    }
                });
                cheatCheck(answerConfirmation);
            }
        });

        binding.falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar answerConfirmation =  Snackbar.make(binding.questionView, questionViewModel.getResult(false),Snackbar.LENGTH_LONG).setAction("Next Question", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (questionViewModel.ifNext()) {
                            questionViewModel.nextQuestion();
                            binding.questionView.setText(questionViewModel.getQuestion());
                        }
                        else
                        {
                            binding.questionView.setText(R.string.game_over);
                            binding.newGame.setVisibility(View.VISIBLE);
                            binding.falseButton.setVisibility(View.GONE);
                            binding.trueButton.setVisibility(View.GONE);
                            binding.cheatButton.setVisibility(View.GONE);
                        }
                    }
                });
                cheatCheck(answerConfirmation);
            }

        });

        binding.newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionViewModel.setupQuiz();
                binding.questionView.setText(questionViewModel.getQuestion());
                binding.newGame.setVisibility(View.GONE);
                binding.falseButton.setVisibility(View.VISIBLE);
                binding.trueButton.setVisibility(View.VISIBLE);
                binding.cheatButton.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void cheatCheck(Snackbar answerConfirmation)
    {
        if (questionViewModel.cheatCheck())
        {
            NavHostFragment.findNavController(QuestionFragment.this)
                    .navigate(R.id.action_QuestionFragment_to_confrontFragment);
        }
        else
        {
            answerConfirmation.show();
        }

    }

}