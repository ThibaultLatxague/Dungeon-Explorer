package engine.graphics;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class Texture {

    private final int id;
    private final int width;
    private final int height;

    public Texture(String path) {

        int texID;
        int w;
        int h;

        try (MemoryStack stack = MemoryStack.stackPush()) {

            IntBuffer widthBuffer  = stack.mallocInt(1);
            IntBuffer heightBuffer = stack.mallocInt(1);
            IntBuffer channels     = stack.mallocInt(1);

            // Inverser l'image (PNG = origine en haut à gauche)
            STBImage.stbi_set_flip_vertically_on_load(true);

            ByteBuffer image = STBImage.stbi_load(
                    path,
                    widthBuffer,
                    heightBuffer,
                    channels,
                    4
            );

            if (image == null) {
                throw new RuntimeException("Impossible de charger la texture : " + path);
            }

            w = widthBuffer.get();
            h = heightBuffer.get();

            texID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, texID);

            // Paramètres de texture
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            // Envoi des pixels à OpenGL
            glTexImage2D(
                    GL_TEXTURE_2D,
                    0,
                    GL_RGBA,
                    w,
                    h,
                    0,
                    GL_RGBA,
                    GL_UNSIGNED_BYTE,
                    image
            );

            glGenerateMipmap(GL_TEXTURE_2D);

            STBImage.stbi_image_free(image);
            glBindTexture(GL_TEXTURE_2D, 0);
        }

        this.id = texID;
        this.width = w;
        this.height = h;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getID() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void delete() {
        glDeleteTextures(id);
    }
}
