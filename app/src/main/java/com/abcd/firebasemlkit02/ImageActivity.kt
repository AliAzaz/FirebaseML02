package com.abcd.firebasemlkit02

import android.graphics.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.abcd.firebasemlkit02.baseDialog.BaseDialogPresenter
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
import kotlinx.android.synthetic.main.activity_image.*


class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val dialog = BaseDialogPresenter(this)
        dialog.setAlertDialogView(true)

        // High-accuracy landmark detection and face classification
        val highAccuracyOpts = FirebaseVisionFaceDetectorOptions.Builder()
            .setPerformanceMode(FirebaseVisionFaceDetectorOptions.FAST)
            .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
            .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
            .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)
            .build()

        // Real-time contour detection of multiple faces
        val realTimeOpts = FirebaseVisionFaceDetectorOptions.Builder()
            .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)
            .build()

        imgPic.setImageBitmap(
            BitmapFactory.decodeResource(
                this.resources,
                R.drawable.samplec
            )
        )

        val image = FirebaseVisionImage.fromBitmap(
            imgPic.drawable.toBitmap()
        )

        val detector = FirebaseVision.getInstance().getVisionFaceDetector(highAccuracyOpts)

        detector.detectInImage(image)
            .addOnSuccessListener { faces ->

                if (faces.size === 0) {
                    dialog.setMessage("No Face Found!!")
                    return@addOnSuccessListener
                }

                val mutableBitmap = imgPic.drawable.toBitmap().copy(Bitmap.Config.ARGB_8888, true)
                val canvas = Canvas(mutableBitmap)
                val graphics = onGettingGraphics()

                for (face in faces) {
                    FaceContour(canvas, graphics).updateFace(face)
//                    FaceContourGraphic(canvas).updateFace(face)
                }

                dialog.setAlertDialogView(false)

                imgPic.setImageBitmap(mutableBitmap)


            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }


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
