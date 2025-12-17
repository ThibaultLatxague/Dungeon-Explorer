package engine.core;

import org.lwjgl.opengl.GL11;

public class Camera {

    private float x, y;           // Position de la caméra
    private float zoom;           // Niveau de zoom (1.0 = normal)
    private float smoothness;     // Lissage du mouvement (0.0 à 1.0)

    // Limites optionnelles de la caméra
    private float minX, maxX, minY, maxY;
    private boolean useBounds;

    public Camera() {
        this.x = 0;
        this.y = 0;
        this.zoom = 1.0f;
        this.smoothness = 0.1f;
        this.useBounds = false;
    }

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
        this.zoom = 1.0f;
        this.smoothness = 0.1f;
        this.useBounds = false;
    }

    /**
     * Fait suivre la caméra au joueur avec un mouvement lissé
     * @param targetX Position X du joueur
     * @param targetY Position Y du joueur
     */
    public void followPlayer(float targetX, float targetY, Window window) {
        // Interpolation linéaire pour un mouvement fluide
        float aspectRatio = window.getAspectRatio();
        x += (targetX - (x*aspectRatio)) * smoothness;
        y += (targetY - y) * smoothness;

        // Applique les limites si elles sont activées
        if (useBounds) {
            x = Math.max(minX, Math.min(maxX, x*aspectRatio));
            y = Math.max(minY, Math.min(maxY, y));
        }
    }

    /**
     * Fait suivre la caméra au joueur instantanément (sans lissage)
     * @param targetX Position X du joueur
     * @param targetY Position Y du joueur
     */
    public void followPlayerInstant(float targetX, float targetY, Window window) {
        float aspectRatio = window.getAspectRatio();
        x = targetX*aspectRatio;
        y = targetY;

        if (useBounds) {
            x = Math.max(minX, Math.min(maxX, x*aspectRatio));
            y = Math.max(minY, Math.min(maxY, y));
        }
    }

    /**
     * Applique la transformation de la caméra à OpenGL
     * @param window Fenêtre pour obtenir l'aspect ratio
     */
    public void applyTransform(Window window) {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

        window.updateAspectRatio();
        float aspectRatio = window.getAspectRatio();
        float viewWidth = zoom * aspectRatio;
        float viewHeight = zoom * aspectRatio;

        // Ortho 2D centré sur la position de la caméra
        GL11.glOrtho(
                x - viewWidth, x + viewWidth,   // left, right
                y - viewHeight, y + viewHeight, // bottom, top
                -1, 1                            // near, far
        );

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }

    /**
     * Définit les limites de déplacement de la caméra
     */
    public void setBounds(float minX, float maxX, float minY, float maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.useBounds = true;
    }

    /**
     * Désactive les limites de la caméra
     */
    public void removeBounds() {
        this.useBounds = false;
    }

    // Getters et Setters
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public float getZoom() { return zoom; }
    public void setZoom(float zoom) {
        this.zoom = Math.max(0.1f, zoom); // Empêche un zoom négatif ou nul
    }

    public float getSmoothness() { return smoothness; }
    public void setSmoothness(float smoothness) {
        this.smoothness = Math.max(0, Math.min(1, smoothness)); // Entre 0 et 1
    }

    /**
     * Zoom progressif
     */
    public void zoom(float amount) {
        setZoom(zoom + amount);
    }
}