package com.abcd.firebasemlkit02

import android.content.res.Resources
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // High-accuracy landmark detection and face classification
        val highAccuracyOpts = FirebaseVisionFaceDetectorOptions.Builder()
            .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
            .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
            .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
            .build()

        // Real-time contour detection of multiple faces
        val realTimeOpts = FirebaseVisionFaceDetectorOptions.Builder()
            .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)
            .build()

        val image = FirebaseVisionImage.fromBitmap(
            BitmapFactory.decodeResource(
                this.resources,
                R.drawable.pic
            )
        )

        val detector = FirebaseVision.getInstance().getVisionFaceDetector(highAccuracyOpts)

        val result = detector.detectInImage(image)
            .addOnSuccessListener { faces ->
                // Task completed successfully
                // ...
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }




    }
}
