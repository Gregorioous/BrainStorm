package com.example.brainstorm.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brainstorm.Domain.entity.GameResult
import com.example.brainstorm.Domain.entity.Level
import com.example.brainstorm.R
import com.example.brainstorm.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var level: Level
    private  var _binding: FragmentGameBinding? =null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOption3.setOnClickListener{

        }
    }

    private fun parseArgs (){
         requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
             level = it
         }
    }
    private fun launchGameFinishedFragment(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val NAME = "GameFragment"
        private const val KEY_LEVEL = "level"

        @JvmStatic
        fun newInstance(level: Level):GameFragment {
           return GameFragment().apply {
               arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL,level)
               }
           }
        }
    }
}