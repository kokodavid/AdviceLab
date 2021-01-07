package com.koko.advice.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import com.koko.advice.R
import com.koko.advice.databinding.FragmentAdviceBinding
import com.koko.advice.models.Slip
import com.koko.advice.util.NetworkResult
import com.koko.advice.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdviceFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mView: View
    private lateinit var binding: FragmentAdviceBinding

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = FragmentAdviceBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_advice, container,false)
        binding = FragmentAdviceBinding.inflate(layoutInflater)
        mView = binding.root
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        requestApiData()

        binding.nextAdvice.setOnClickListener(View.OnClickListener { v ->
            requestApiData()
        })

        return mView
    }

    private fun requestApiData() {
        Log.d("AdviceFragment", "request Api Code Called")
        mainViewModel.getAdvices()
        mainViewModel.adviceResponse.observe(viewLifecycleOwner, {response ->
            when(response){
                is NetworkResult.Success -> {
                binding.adviceWord.setText(response.data?.slip?.advice.toString())
//                    Toast.makeText(
//                        requireContext(),
//                        response.data.toString(),
//                        Toast.LENGTH_LONG
//                    ).show()

                } is NetworkResult.Error -> {
                   Toast.makeText(
                       requireContext(),
                       response.message.toString(),
                       Toast.LENGTH_LONG
                   ).show()
                }
            }
        })
    }


}

