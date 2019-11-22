package com.abcd.firebasemlkit02

import android.graphics.Canvas
import android.graphics.Paint
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour

class FaceContour(var canvas: Canvas, val graphics: Triple<Paint, Paint, Paint>) {

    fun updateFace(face: FirebaseVisionFace) {
        val faceContours = face.getContour(FirebaseVisionFaceContour.FACE).points
        for ((i, contour) in faceContours.withIndex()) {
            if (i != faceContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    faceContours[i + 1].x,
                    faceContours[i + 1].y,
                    graphics.first
                )
            else
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    faceContours[0].x,
                    faceContours[0].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val leftEyebrowTopContours =
            face.getContour(FirebaseVisionFaceContour.LEFT_EYEBROW_TOP).points
        for ((i, contour) in leftEyebrowTopContours.withIndex()) {
            if (i != leftEyebrowTopContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    leftEyebrowTopContours[i + 1].x,
                    leftEyebrowTopContours[i + 1].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val leftEyebrowBottomContours =
            face.getContour(FirebaseVisionFaceContour.LEFT_EYEBROW_BOTTOM).points
        for ((i, contour) in leftEyebrowBottomContours.withIndex()) {
            if (i != leftEyebrowBottomContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    leftEyebrowBottomContours[i + 1].x,
                    leftEyebrowBottomContours[i + 1].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val rightEyebrowTopContours =
            face.getContour(FirebaseVisionFaceContour.RIGHT_EYEBROW_TOP).points
        for ((i, contour) in rightEyebrowTopContours.withIndex()) {
            if (i != rightEyebrowTopContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    rightEyebrowTopContours[i + 1].x,
                    rightEyebrowTopContours[i + 1].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val rightEyebrowBottomContours =
            face.getContour(FirebaseVisionFaceContour.RIGHT_EYEBROW_BOTTOM).points
        for ((i, contour) in rightEyebrowBottomContours.withIndex()) {
            if (i != rightEyebrowBottomContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    rightEyebrowBottomContours[i + 1].x,
                    rightEyebrowBottomContours[i + 1].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val leftEyeContours = face.getContour(FirebaseVisionFaceContour.LEFT_EYE).points
        for ((i, contour) in leftEyeContours.withIndex()) {
            if (i != leftEyeContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    leftEyeContours[i + 1].x,
                    leftEyeContours[i + 1].y,
                    graphics.first
                )
            else
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    leftEyeContours[0].x,
                    leftEyeContours[0].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val rightEyeContours =
            face.getContour(FirebaseVisionFaceContour.RIGHT_EYE).points
        for ((i, contour) in rightEyeContours.withIndex()) {
            if (i != rightEyeContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    rightEyeContours[i + 1].x,
                    rightEyeContours[i + 1].y,
                    graphics.first
                )
            else
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    rightEyeContours[0].x,
                    rightEyeContours[0].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val upperLipTopContours =
            face.getContour(FirebaseVisionFaceContour.UPPER_LIP_TOP).points
        for ((i, contour) in upperLipTopContours.withIndex()) {
            if (i != upperLipTopContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    upperLipTopContours[i + 1].x,
                    upperLipTopContours[i + 1].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val upperLipBottomContours =
            face.getContour(FirebaseVisionFaceContour.UPPER_LIP_BOTTOM).points
        for ((i, contour) in upperLipBottomContours.withIndex()) {
            if (i != upperLipBottomContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    upperLipBottomContours[i + 1].x,
                    upperLipBottomContours[i + 1].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val lowerLipTopContours =
            face.getContour(FirebaseVisionFaceContour.LOWER_LIP_TOP).points
        for ((i, contour) in lowerLipTopContours.withIndex()) {
            if (i != lowerLipTopContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    lowerLipTopContours[i + 1].x,
                    lowerLipTopContours[i + 1].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val lowerLipBottomContours =
            face.getContour(FirebaseVisionFaceContour.LOWER_LIP_BOTTOM).points
        for ((i, contour) in lowerLipBottomContours.withIndex()) {
            if (i != lowerLipBottomContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    lowerLipBottomContours[i + 1].x,
                    lowerLipBottomContours[i + 1].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val noseBridgeContours =
            face.getContour(FirebaseVisionFaceContour.NOSE_BRIDGE).points
        for ((i, contour) in noseBridgeContours.withIndex()) {
            if (i != noseBridgeContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    noseBridgeContours[i + 1].x,
                    noseBridgeContours[i + 1].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }

        val noseBottomContours =
            face.getContour(FirebaseVisionFaceContour.NOSE_BOTTOM).points
        for ((i, contour) in noseBottomContours.withIndex()) {
            if (i != noseBottomContours.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    noseBottomContours[i + 1].x,
                    noseBottomContours[i + 1].y,
                    graphics.first
                )
            canvas.drawCircle(contour.x, contour.y, 4F, graphics.third)
        }


        /* if (cameraFacing == Facing.FRONT) {
             val matrix = Matrix()
             matrix.preScale(-1F, 1F)
             val flippedBitmap = Bitmap.createBitmap(
                 bitmap,
                 0,
                 0,
                 bitmap.width,
                 bitmap.height,
                 matrix,
                 true
             )
             face_detection_camera_image_view.setImageBitmap(flippedBitmap)
         } else {
             face_detection_camera_image_view.setImageBitmap(bitmap)
         }*/
    }

}