package com.abcd.firebasemlkit02

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark

/** Graphic instance for rendering face contours graphic overlay view.  */
class FaceContourGraphic(var canvas: Canvas) {

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
        idPaint.textSize = ID_TEXT_SIZE

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
    fun updateFace(face: FirebaseVisionFace) {
        firebaseVisionFace = face
        draw()
    }

    /** Draws the face annotations for position on the supplied canvas.  */
    fun draw() {
        val face = firebaseVisionFace ?: return

        // Draws a circle at the position of the detected face, with the face's track id below.
        val x = face.boundingBox.centerX().toFloat()
        val y = face.boundingBox.centerY().toFloat()
        canvas.drawCircle(
            x, y,
            FACE_POSITION_RADIUS, facePositionPaint
        )
//        canvas.drawText("id: " + face.trackingId, x + ID_X_OFFSET, y + ID_Y_OFFSET, idPaint)

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

        /*if (face.smilingProbability >= 0) {
            canvas.drawText(
                "happiness: " + String.format("%.2f", face.smilingProbability),
                x + ID_X_OFFSET * 3,
                y - ID_Y_OFFSET,
                idPaint
            )
        }

        if (face.rightEyeOpenProbability >= 0) {
            canvas.drawText(
                "right eye: " + String.format("%.2f", face.rightEyeOpenProbability),
                x - ID_X_OFFSET,
                y,
                idPaint
            )
        }
        if (face.leftEyeOpenProbability >= 0) {
            canvas.drawText(
                "left eye: " + String.format("%.2f", face.leftEyeOpenProbability),
                x + ID_X_OFFSET * 6,
                y,
                idPaint
            )
        }*/
        val leftEye = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EYE)
        if (leftEye != null && leftEye.position != null) {
            canvas.drawCircle(
                (leftEye.position.x),
                (leftEye.position.y),
                FACE_POSITION_RADIUS,
                facePositionPaint
            )
        }
        val rightEye = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_EYE)
        if (rightEye != null && rightEye.position != null) {
            canvas.drawCircle(
                (rightEye.position.x),
                (rightEye.position.y),
                FACE_POSITION_RADIUS,
                facePositionPaint
            )
        }

        val leftCheek = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_CHEEK)
        if (leftCheek != null && leftCheek.position != null) {
            canvas.drawCircle(
                (leftCheek.position.x),
                (leftCheek.position.y),
                FACE_POSITION_RADIUS,
                facePositionPaint
            )
        }
        val rightCheek = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_CHEEK)
        if (rightCheek != null && rightCheek.position != null) {
            canvas.drawCircle(
                (rightCheek.position.x),
                (rightCheek.position.y),
                FACE_POSITION_RADIUS,
                facePositionPaint
            )
        }
    }

    companion object {

        private val FACE_POSITION_RADIUS = 20.0f
        private val ID_TEXT_SIZE = 70.0f
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
}
