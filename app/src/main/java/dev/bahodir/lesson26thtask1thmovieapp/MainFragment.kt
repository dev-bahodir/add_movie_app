package dev.bahodir.lesson26thtask1thmovieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dev.bahodir.lesson26thtask1thmovieapp.adapter.RV
import dev.bahodir.lesson26thtask1thmovieapp.databinding.FragmentMainBinding
import dev.bahodir.lesson26thtask1thmovieapp.db.DBHelper
import dev.bahodir.lesson26thtask1thmovieapp.user.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
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
    lateinit var binding: FragmentMainBinding
    lateinit var dbHelper: DBHelper
    lateinit var rv: RV

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        dbHelper = DBHelper(requireContext())
        val list = dbHelper.getUserList()

        rv = RV(list, object : RV.OnItemTouchClickListener{
            override fun deleteMovie(user: User, position: Int, view: View) {
                dbHelper.deleteUser(user)
                list.removeAt(position)
                rv.notifyItemRemoved(position)
                rv.notifyItemRangeChanged(position, list.size)
            }

            override fun editMovie(user: User, position: Int, view: View) {
                val bundle = Bundle()
                bundle.putInt("position", position)
                Navigation.findNavController(view).navigate(R.id.editFragment,bundle)
            }

            override fun itemClick(user: User, position: Int, view: View) {
                val bundle = Bundle()
                bundle.putInt("position", position)
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_infoFragment,bundle)
            }

        })
        binding.rv.adapter = rv

        binding.add.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_addFragment)
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
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}