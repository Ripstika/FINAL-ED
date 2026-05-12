package com.miniredsocial.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;

public class ArchivoUtil {
    public static String getDataFilePath(ServletContext context, String nombreArchivo) {
        String carpeta = context.getRealPath("/WEB-INF/data");
        File carpetaData = new File(carpeta);
        if (!carpetaData.exists()) {
            carpetaData.mkdirs();
        }
        File archivo = new File(carpetaData, nombreArchivo);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return archivo.getAbsolutePath();
    }

    public static List<String> leerLineas(String ruta) {
        List<String> lineas = new ArrayList<>();
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            return lineas;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }

    public static void anexarLinea(String ruta, String linea) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, true), StandardCharsets.UTF_8))) {
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escribirLineas(String ruta, List<String> lineas) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, false), StandardCharsets.UTF_8))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
