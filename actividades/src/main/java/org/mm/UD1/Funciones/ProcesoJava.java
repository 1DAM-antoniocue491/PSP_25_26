package org.mm.UD1.Funciones;

import java.io.*;

public class ProcesoJava {
    public Process exec(Class clase) throws IOException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = clase.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);
        return builder.start();
    }

    public Process exec(Class clase, String[] args) throws IOException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = clase.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);

        for (String arg : args) {
            builder.command().add(arg);
        }

        return builder.start();
    }

    public String getSalidaProceso(Process p) throws IOException {
        String line;
        String contenido = "";
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // Se muestra la salida del proceso por pantalla
        while ((line = br.readLine()) != null) {
            contenido += line + "\n";
        }

        // Cuando finaliza se cierra el descriptor del proceso
        is.close();

        return contenido;
    }
}
