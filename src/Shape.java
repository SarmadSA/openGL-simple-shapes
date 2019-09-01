import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import static com.jogamp.opengl.util.ImmModeSink.*;

public class Shape extends GLCanvas implements GLEventListener {

    //Properties
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 800;
    private static final String WINDOW_TITLE = "Shapes";

    private GLU glu = new GLU();

    private Shape(GLCapabilities c) {
        super(c);
        this.addGLEventListener(this);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL_DEPTH_TEST);
        gl.glDepthFunc(GL_LEQUAL);
        gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        gl.glShadeModel(GL_SMOOTH);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        this.drawShapes(glAutoDrawable);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        if (height == 0) height = 1;
        float aspect = (float) width / height;

        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, aspect, 0.1, 100.0);

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    private void drawShapes(GLAutoDrawable drawable){
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(-5.5f, -2.0f, -6.0f);
        gl.glScalef(0.2f, 0.2f, -6.0f);

        //GL_POINTS
        this.drawShape(gl, GL_POINTS);

        //GL_LINES
        this.drawShape(gl, GL_LINES);

        //GL_LINE_STRIP
        this.drawShape(gl, GL_LINE_STRIP);

        //GL_LINE_LOOP
        this.drawShape(gl, GL_LINE_LOOP);

        //GL_TRIANGLES
        this.drawShape(gl, GL_TRIANGLES);

        //GL_TRIANGLE_STRIP
        this.drawShape(gl, GL_TRIANGLE_STRIP);

        //TRIANGLE_FAN
        this.drawShape(gl, GL_TRIANGLE_FAN);

        //GL_QUADS
        this.drawShape(gl, GL_QUADS);

        //GL_QUAD_STRIP
        this.drawShape(gl, GL_QUAD_STRIP);

        //GL_POLYGON
        this.drawShape(gl, GL_POLYGON);
    }

    private void drawShape(GL2 gl, int type){
        gl.glTranslatef(5.0f, 0.0f, 0.0f);
        gl.glBegin(type);
        gl.glVertex3f(0.0f, 2.0f, 0.0f);
        gl.glVertex3f(1.5f, 1.5f, 0.0f);
        gl.glVertex3f(2.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.5f, -1.5f, 0.0f);
        gl.glVertex3f(0.0f, -2.0f, 0.0f);
        gl.glVertex3f(-1.5f, -1.5f, 0.0f);
        gl.glVertex3f(-2.0f, 0.0f, 0.0f);
        gl.glVertex3f(-1.5f, 1.5f, 0.0f);
        gl.glEnd();
    }

    public static void main(String[] args) {

        final GLCanvas glCanvas= new Shape(null);
        glCanvas.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        final JFrame frame = new JFrame (WINDOW_TITLE);
        frame.getContentPane().add(glCanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}
