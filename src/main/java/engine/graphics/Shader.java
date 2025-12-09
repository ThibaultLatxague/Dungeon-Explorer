package engine.graphics;

import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Shader {

    private final int programID;

    public Shader(String vertexPath, String fragmentPath) {
        int vertexID   = compileShader(vertexPath, GL_VERTEX_SHADER);
        int fragmentID = compileShader(fragmentPath, GL_FRAGMENT_SHADER);

        programID = glCreateProgram();
        glAttachShader(programID, vertexID);
        glAttachShader(programID, fragmentID);
        glLinkProgram(programID);

        if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
            System.err.println("ERROR - Shader program linking failed");
            System.err.println(glGetProgramInfoLog(programID));
            throw new IllegalStateException("Shader linking failed");
        }

        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);
    }

    private int compileShader(String path, int type) {
        String source;

        try {
            source = Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException("Could not read shader file : " + path, e);
        }

        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, source);
        glCompileShader(shaderID);

        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("ERROR - Shader compilation failed (" + path + ")");
            System.err.println(glGetShaderInfoLog(shaderID));
            throw new IllegalStateException("Shader compilation failed");
        }

        return shaderID;
    }

    public void bind() {
        glUseProgram(programID);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public int getID() {
        return programID;
    }

    public int getUniformLocation(String name) {
        return glGetUniformLocation(programID, name);
    }


    // =======================
    //   UNIFORMS HELPERS
    // =======================

    public void setFloat(String name, float v) {
        glUniform1f(getUniformLocation(name), v);
    }

    public void setInt(String name, int v) {
        glUniform1i(getUniformLocation(name), v);
    }

    public void setVec3(String name, float x, float y, float z) {
        glUniform3f(getUniformLocation(name), x, y, z);
    }

    public void setVec4(String name, float x, float y, float z, float w) {
        glUniform4f(getUniformLocation(name), x, y, z, w);
    }

    public void setMat4(String name, float[] matrix16) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(16);
            buffer.put(matrix16).flip();
            glUniformMatrix4fv(getUniformLocation(name), false, buffer);
        }
    }


    public void delete() {
        glDeleteProgram(programID);
    }
}
