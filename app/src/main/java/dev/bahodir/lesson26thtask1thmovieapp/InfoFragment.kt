package dev.bahodir.lesson26thtask1thmovieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.bahodir.lesson26thtask1thmovieapp.databinding.FragmentInfoBinding
import dev.bahodir.lesson26thtask1thmovieapp.db.DBHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "position"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    //private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding: FragmentInfoBinding
    lateinit var dbHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        dbHelper = DBHelper(requireContext())

        val list = dbHelper.getUserList()
        var user = list[param1?:0]

        binding.tv.text = user.movieName
        binding.mavieName.text = "Movie name: ${user.movieName}"
        binding.mavieAuthors.text = "Movie authors: ${user.movieAuthors}"
        binding.aboutMovie.text = "About movie: ${user.aboutMovie}"
        binding.mavieDate.text = "Date: ${user.movieDate}"

        binding.save.setOnClickListener {
            findNavController().popBackStack()
        }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int?) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1?:0)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}