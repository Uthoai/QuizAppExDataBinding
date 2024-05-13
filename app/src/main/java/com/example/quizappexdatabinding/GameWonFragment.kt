package com.example.quizappexdatabinding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
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

        setUpMenu()


        return binding.root
    }

    private fun shareAchievement() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,scoreBoard)
        startActivity(shareIntent)
    }

    private fun setUpMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.win_menu,menu )
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.share -> {
                        shareAchievement()
                        true
                    }
                    else -> false
                }
            }
        })
    }
}