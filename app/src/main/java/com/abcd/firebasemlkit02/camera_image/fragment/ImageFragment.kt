package com.abcd.firebasemlkit02.camera_image.fragment

import android.content.Context
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.abcd.firebasemlkit02.R
import com.abcd.firebasemlkit02.camera_image.utils.FaceContourGraphic
import com.abcd.firebasemlkit02.camera_image.viewmodel.VModel
import com.abcd.firebasemlkit02.custom_dialog.BaseDialogPresenter
import com.abcd.firebasemlkit02.databinding.FragmentImageBinding
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
import kotlinx.android.synthetic.main.activity_image.*

class ImageFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var vModel: VModel
    private lateinit var bi: FragmentImageBinding
    private lateinit var dialog: BaseDialogPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set view model
        vModel = activity?.run {
            ViewModelProviders.of(this)[VModel::class.java]
        } ?: throw Exception("Invalid Activity")

        dialog = BaseDialogPresenter(activity as Context)
        dialog.setAlertDialogView(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bi = FragmentImageBinding.inflate(inflater, container, false)

        // High-accuracy landmark detection and face classification
        val highAccuracyOpts = FirebaseVisionFaceDetectorOptions.Builder()
            .setPerformanceMode(FirebaseVisionFaceDetectorOptions.FAST)
            .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
            .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
            .build()

        bi.imgPic.setImageBitmap(
            BitmapFactory.decodeResource(
                this.resources,
                R.drawable.samplea
            )
        )

        val image = FirebaseVisionImage.fromBitmap(bi.imgPic.drawable.toBitmap())
        val detector = FirebaseVision.getInstance().getVisionFaceDetector(highAccuracyOpts)
        detector.detectInImage(image)
            .addOnSuccessListener { faces ->

                if (faces.size == 0) {
                    dialog.setMessage("No Face Found!!")
                    return@addOnSuccessListener
                }

                val mutableBitmap = imgPic.drawable.toBitmap().copy(Bitmap.Config.ARGB_8888, true)
                val canvas = Canvas(mutableBitmap)
                val graphics = onGettingGraphics()

                for (i in 0 until faces.size) FaceContourGraphic(
                    vModel,
                    canvas
                ).updateFace(faces[i], i + 1)

                dialog.setAlertDialogView(false)

                bi.imgPic.setImageBitmap(mutableBitmap)
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }

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

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


    private fun onGettingGraphics(): Triple<Paint, Paint, Paint> {
        val rectPaint = Paint()
        rectPaint.color = Color.RED
        rectPaint.style = Paint.Style.STROKE
        rectPaint.strokeWidth = 10F

        val textPaint = Paint()
        textPaint.color = Color.RED
        textPaint.textSize = 40F

        val circlePaint = Paint()
        circlePaint.color = Color.BLUE
        circlePaint.textSize = 10F
        circlePaint.style = Paint.Style.FILL

        return Triple(rectPaint, textPaint, circlePaint)
    }

}
