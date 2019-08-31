import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Triangle extends GLCanvas implements GLEventListener {

    //Properties
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 400;
    private static final String WINDOW_TITLE = "Triangle";
    private static final double FOV_Y = 45.0; //field of view, y-axis
    private static final double ASPECT_RATIO = 1.00; // w/h
    private static final double Z_NEAR = 2.0;
    private static final double Z_FAR = 9.0;

    private GLU glu = new GLU();

    private Triangle(GLCapabilities c) {
        super(c);
        this.addGLEventListener(this);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(FOV_Y,ASPECT_RATIO,Z_NEAR,Z_FAR);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        this.drawTriangle(glAutoDrawable);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    private void drawTriangle(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glTranslatef(-1.5f, 0.0f, -8.0f);
        gl.glColor3f(1.0f, 0.0f, 0.0f);

        gl.glBegin(GL_TRIANGLES);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 0.0f);
        gl.glEnd();
    }

    public static void main(String[] args) {

        final GLCanvas glCanvas= new Triangle(null);
        glCanvas.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        final JFrame frame = new JFrame (WINDOW_TITLE);
        frame.getContentPane().add(glCanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}
