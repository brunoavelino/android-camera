package com.eyllo.vista.ocr;

import android.util.SparseArray;

import com.eyllo.vista.cameraGoogle.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> mGraphicOverlay;

    public OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay) {
        mGraphicOverlay = ocrGraphicOverlay;
    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        mGraphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            OcrGraphic graphic = new OcrGraphic(mGraphicOverlay, item);
            mGraphicOverlay.add(graphic);
        }
    }

    /**
     * Frees the resources associated with this detection processor.
     */
    @Override
    public void release() {
        mGraphicOverlay.clear();
    }
}
