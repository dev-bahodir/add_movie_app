package dev.bahodir.lesson26thtask1thmovieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import dev.bahodir.lesson26thtask1thmovieapp.databinding.FragmentAddBinding
import dev.bahodir.lesson26thtask1thmovieapp.db.DBHelper
import dev.bahodir.lesson26thtask1thmovieapp.user.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding: FragmentAddBinding
    lateinit var dbHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        dbHelper = DBHelper(requireContext())

        binding.save.setOnClickListener {
            val movieName = binding.mavieName.text.toString()
            val movieAuthors = binding.mavieAuthors.text.toString()
            val aboutMovie = binding.aboutMovie.text.toString()
            val movieDate = binding.mavieDate.text.toString()

            if (movieName.isNotEmpty() && movieAuthors.isNotEmpty() && aboutMovie.isNotEmpty() && movieDate.isNotEmpty()) {
                val user = User(
                    movieName = movieName,
                    movieAuthors = movieAuthors,
                    aboutMovie = aboutMovie,
                    movieDate = movieDate
                )
                dbHelper.addUser(user)

                Navigation.findNavController(it).popBackStack()
            } else {
                Toast.makeText(requireContext(), "Fill in all the boxes", Toast.LENGTH_SHORT).show()
            }
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
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}