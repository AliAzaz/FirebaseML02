package com.abcd.firebasemlkit02.camera_image.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.abcd.firebasemlkit02.camera_image.viewmodel.VModel
import com.abcd.firebasemlkit02.databinding.FragmentImageDtBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ImageDTFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ImageDTFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageDTFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var bi: FragmentImageDtBinding
    private lateinit var vModel: VModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = activity?.run { ViewModelProviders.of(this)[VModel::class.java] }
            ?: throw Exception("Invalidate Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bi = FragmentImageDtBinding.inflate(inflater, container, false)

        // Get data from viewmodel
        vModel.selected.observe(this, Observer { item ->
            item.showLog()
            bi.dt.text = "${bi.dt.text}\n $item"
        })

        return bi.root
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ImageDTFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageDTFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun String.showLog() {
        Log.i(activity!!::class.java.name, this)
    }

}
