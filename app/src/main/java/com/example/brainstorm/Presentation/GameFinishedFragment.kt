package com.example.brainstorm.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.FragmentManager
import com.example.brainstorm.Domain.entity.GameResult
import com.example.brainstorm.Domain.entity.Level
import com.example.brainstorm.R
import com.example.brainstorm.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {
    private lateinit var gameResult: GameResult
    private  var _binding: FragmentGameFinishedBinding? =null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameFinishedBinding.inflate(inflater, container ,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRetry.setOnClickListener{
            retryGame()
        }
        val callBack = object  : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callBack)

    }
    private fun parseArgs (){
         requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT) ?.let {
             gameResult = it
         }
    }

    private fun retryGame(){
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val KEY_GAME_RESULT = "game_result"

        @JvmStatic
        fun newInstance(gameResult:GameResult):GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT,gameResult)
                }
            }
        }
    }
}