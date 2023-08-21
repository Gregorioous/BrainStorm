package com.example.brainstorm.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.brainstorm.Domain.entity.Level
import com.example.brainstorm.R
import com.example.brainstorm.databinding.FragmentChooseLevelBinding


class ChooseLevelFragment : Fragment() {
    private  var _binding: FragmentChooseLevelBinding? =null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChooseLevelBinding.inflate(inflater, container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            buttonLevelTest.setOnClickListener{
                launchGameFragment(level = Level.TEST)
            }
            buttonLevelEasy.setOnClickListener{
                launchGameFragment(level = Level.EASY)
            }
            buttonLevelNormal.setOnClickListener{
                launchGameFragment(level = Level.NORMAL)
            }
            buttonLevelHard.setOnClickListener{
                launchGameFragment(level = Level.HARD)
            }
        }
    }

    private fun launchGameFragment(level: Level) {
            findNavController().navigate(
                ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}