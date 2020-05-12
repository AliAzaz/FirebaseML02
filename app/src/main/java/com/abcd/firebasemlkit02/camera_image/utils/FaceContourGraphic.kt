package com.abcd.firebasemlkit02.camera_image.utils

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.abcd.firebasemlkit02.camera_image.viewmodel.VModel
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark

/** Graphic instance for rendering face contours graphic overlay view.  */
class FaceContourGraphic(var vModel: VModel, var canvas: Canvas) {

    private val facePositionPaint: Paint
    private val idPaint: Paint
    private val boxPaint: Paint

    @Volatile
    private var firebaseVisionFace: FirebaseVisionFace? = null

    init {
        currentColorIndex = (currentColorIndex + 1) % COLOR_CHOICES.size
        val selectedColor = COLOR_CHOICES[currentColorIndex]

        facePositionPaint = Paint()
        facePositionPaint.color = selectedColor

        idPaint = Paint()
        idPaint.color = selectedColor
        idPaint.textSize =
            ID_TEXT_SIZE

        boxPaint = Paint()
        boxPaint.color = selectedColor
        boxPaint.style = Paint.Style.STROKE
        boxPaint.strokeWidth =
            BOX_STROKE_WIDTH
    }

    /**
     * Updates the face instance from the detection of the most recent frame. Invalidates the relevant
     * portions of the overlay to trigger a redraw.
     */
    fun updateFace(face: FirebaseVisionFace, count: Int) {
        firebaseVisionFace = face
        draw(count)
    }

    /** Draws the face annotations for position on the supplied canvas.  */
    private fun draw(count: Int) {
        val face = firebaseVisionFace ?: return

        // Draws a circle at the center position of the detected face.
        val x = face.boundingBox.centerX().toFloat()
        val y = face.boundingBox.centerY().toFloat()
        canvas.drawCircle(
            x, y,
            FACE_POSITION_RADIUS, facePositionPaint
        )

        // Draws a bounding box around the face.
        val xOffset = face.boundingBox.width() / 2.0f
        val yOffset = face.boundingBox.height() / 2.0f
        val left = x - xOffset
        val top = y - yOffset
        val right = x + xOffset
        val bottom = y + yOffset
        canvas.drawRect(left, top, right, bottom, boxPaint)

        val contour = face.getContour(FirebaseVisionFaceContour.ALL_POINTS)
        for (point in contour.points) {
            val px = (point.x)
            val py = (point.y)
            canvas.drawCircle(
                px, py,
                FACE_POSITION_RADIUS, facePositionPaint
            )
        }

        canvas.drawText(
            "Face: $count",
            x + ID_X_OFFSET,
            y - ID_Y_OFFSET,
            idPaint
        )

        var values = "Face: $count "

        if (face.smilingProbability >= 0) {
            values += "smilingProbability: ${String.format("%.2f", face.smilingProbability)} "
        }

        if (face.rightEyeOpenProbability >= 0) {
            values += "rightEyeOpenProbability: ${String.format(
                "%.2f",
                face.rightEyeOpenProbability
            )} "

        }
        if (face.leftEyeOpenProbability >= 0) {
            values += "leftEyeOpenProbability: ${String.format(
                "%.2f",
                face.leftEyeOpenProbability
            )}"
        }

        if (values.checkValue()) vModel.passData(values)

        val leftEyeBrow = face.getContour(FirebaseVisionFaceContour.LEFT_EYEBROW_TOP).points
        for ((j, contour) in leftEyeBrow.withIndex()) {
            if (j != leftEyeBrow.lastIndex)
                canvas.drawLine(
                    contour.x,
                    contour.y,
                    leftEyeBrow[j + 1].x,
                    leftEyeBrow[j + 1].y,
                    facePositionPaint
                )
            canvas.drawCircle(contour.x, contour.y, 4F, facePositionPaint)
        }


        val leftEye = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EYE)
        if (leftEye != null) {
            canvas.run {
                drawCircle(
                    leftEye.position.x,
                    leftEye.position.y,
                    FACE_POSITION_RADIUS,
                    facePositionPaint
                )
            }
        }
        val rightEye = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_EYE)
        if (rightEye != null) {
            canvas.run {
                drawCircle(
                    rightEye.position.x,
                    rightEye.position.y,
                    FACE_POSITION_RADIUS,
                    facePositionPaint
                )
            }
        }

        val leftCheek = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_CHEEK)
        if (leftCheek != null) {
            canvas.run {
                drawCircle(
                    leftCheek.position.x,
                    leftCheek.position.y,
                    FACE_POSITION_RADIUS,
                    facePositionPaint
                )
            }
        }
        val rightCheek = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_CHEEK)
        if (rightCheek != null) {
            canvas.run {
                drawCircle(
                    rightCheek.position.x,
                    rightCheek.position.y,
                    FACE_POSITION_RADIUS,
                    facePositionPaint
                )
            }
        }
    }

    companion object {

        private val FACE_POSITION_RADIUS = 5.0f
        private val ID_TEXT_SIZE = 40.0f
        private val ID_Y_OFFSET = 80.0f
        private val ID_X_OFFSET = -70.0f
        private val BOX_STROKE_WIDTH = 5.0f

        private val COLOR_CHOICES = intArrayOf(
            Color.BLUE,
            Color.CYAN,
            Color.GREEN,
            Color.MAGENTA,
            Color.RED,
            Color.WHITE,
            Color.YELLOW
        )
        private var currentColorIndex = 0
    }

    private fun String.checkValue(): Boolean {
        return this != ""
    }

}
