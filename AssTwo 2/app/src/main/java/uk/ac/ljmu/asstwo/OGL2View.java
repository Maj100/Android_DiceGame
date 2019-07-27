package uk.ac.ljmu.asstwo;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;


public class OGL2View extends GLSurfaceView {

    public OGL2View(Context context) {
        super(context);
        init();
    }

    public OGL2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // use opengl es 2.0
        setEGLContextClientVersion(2);

        // store opengl context
        setPreserveEGLContextOnPause(true);

        // set renderer
        setRenderer(new OGL2Renderer(getContext()));


    }

}
