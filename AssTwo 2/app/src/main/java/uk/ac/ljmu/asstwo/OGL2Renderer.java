package uk.ac.ljmu.asstwo;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.renderscript.Float3;
import android.renderscript.Matrix4f;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import uk.ac.ljmu.asstwo.glkit.ShaderProgram;
import uk.ac.ljmu.asstwo.glkit.ShaderUtils;
import uk.ac.ljmu.asstwo.glkit.TextureUtils;


public class OGL2Renderer implements GLSurfaceView.Renderer {

    private static final float ONE_SEC = 200.0f; // 1 second

    private Context context;
    private Cube   cube;
    private long lastTimeMillis = 0L;

    public OGL2Renderer(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {



        ShaderProgram shader = new ShaderProgram(
                ShaderUtils.readShaderFileFromRawResource(context, R.raw.simple_vertex_shader),
                ShaderUtils.readShaderFileFromRawResource(context, R.raw.simple_fragment_shader)
        );

        int textureName = TextureUtils.loadTexture(context, R.drawable.dice);

        cube = new Cube(shader);
        cube.setPosition(new Float3(0.0f, 0.0f, 0.0f));
        cube.setTexture(textureName);

        lastTimeMillis = System.currentTimeMillis();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int w, int h) {
        GLES20.glViewport(0, 0, w, h);

        Matrix4f perspective = new Matrix4f();
        perspective.loadPerspective(85.0f, (float)w / (float)h, 1.0f, -150.0f);

        if(cube != null) {
            cube.setProjection(perspective);
        }
    }

    /**
     * GLSurfaceView has default 16bit depth buffer
     */
    @Override
    public void onDrawFrame(GL10 gl10) {

        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
if(AssTwo.isDraw) {
    long currentTimeMillis = System.currentTimeMillis();
    updateWithDelta(currentTimeMillis - lastTimeMillis);
    lastTimeMillis = currentTimeMillis;
}
         else{
            long currentTimeMillis = System.currentTimeMillis();

            setFixed(AssTwo.dieValue2);
            lastTimeMillis = currentTimeMillis;
        }
    }
    public void setFixed(int x) {
        Matrix4f camera2 = new Matrix4f();
        camera2.translate(0.0f, 0.0f, -5.0f);

        if(x==3) {
            cube.setRotationX(0f);
            cube.setRotationY(90.0f);
            cube.setRotationZ(0f);
        }
        else if(x==2) {
            cube.setRotationX(0f);
            cube.setRotationY(190.0f);
            cube.setRotationZ(0f);
        }
        else if(x==4) {
            cube.setRotationX(0f);
            cube.setRotationY(280.0f);
            cube.setRotationZ(0f);
        }
        else if(x==1) {
            cube.setRotationX(0f);
            cube.setRotationY(0.0f);
            cube.setRotationZ(0f);
        }
        else if(x==5) {
            cube.setRotationX(90f);
            cube.setRotationY(0f);
            cube.setRotationZ(0f);
        }
        else if(x==6) {
            cube.setRotationX(270f);
            cube.setRotationY(0f);
            cube.setRotationZ(0f);
        }
        cube.setCamera(camera2);
        cube.draw(100);
    }
    public void updateWithDelta(long dt) {

    Matrix4f camera2 = new Matrix4f();
   camera2.translate(0.0f, 0.0f, -5.0f);
    cube.setCamera(camera2);
    cube.setRotationY((float) (cube.rotationZ + Math.PI * dt / (ONE_SEC * 0.1f)));
    cube.setRotationZ((float) (cube.rotationY + Math.PI * dt / (ONE_SEC * 0.1f)));
       // Log.d("D: ",(cube.rotationY + Math.PI * dt / (ONE_SEC * 0.1f))+"");
      //  cube.setRotationY(102.0f);
    cube.draw(dt);

    }

}
