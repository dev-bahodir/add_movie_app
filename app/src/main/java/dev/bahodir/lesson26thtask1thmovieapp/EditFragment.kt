package dev.bahodir.lesson26thtask1thmovieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.bahodir.lesson26thtask1thmovieapp.databinding.FragmentEditBinding
import dev.bahodir.lesson26thtask1thmovieapp.db.DBHelper
import dev.bahodir.lesson26thtask1thmovieapp.user.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "position"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment() {
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
    lateinit var binding: FragmentEditBinding
    lateinit var dbHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater, container, false)

        dbHelper = DBHelper(requireContext())
        var list = dbHelper.getUserList()
        var user = list[param1?:0]

        binding.mavieName.setText(user.movieName)
        binding.mavieAuthors.setText(user.movieAuthors)
        binding.aboutMovie.setText(user.aboutMovie)
        binding.mavieDate.setText(user.movieDate)

        binding.save.setOnClickListener {
            val users = User(
                id = user.id,
                movieName = binding.mavieName.text.toString(),
                movieAuthors = binding.mavieAuthors.text.toString(),
                aboutMovie = binding.aboutMovie.text.toString(),
                movieDate = binding.mavieDate.text.toString()
            )
            dbHelper.editUser(users)

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
         * @return A new instance of fragment EditFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int?) =
            EditFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1?:0)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}