package com.example.quizappexdatabinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quizappexdatabinding.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {
    lateinit var binding: FragmentGameWonBinding
    lateinit var scoreBoard: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_won,container,false)
        binding.gameWon = this

        val totalQuestion = GameWonFragmentArgs.fromBundle(requireArguments()).numberOfQuestion
        val score = GameWonFragmentArgs.fromBundle(requireArguments()).score

        scoreBoard = "Score: $score/$totalQuestion"

        binding.btnPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
        }

        return binding.root
    }
}