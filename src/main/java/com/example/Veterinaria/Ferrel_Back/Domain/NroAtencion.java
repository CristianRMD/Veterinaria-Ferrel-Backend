package com.example.Veterinaria.Ferrel_Back.Domain;

import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class NroAtencion {
    private static final String FILE_PATH = "nroAtencionMemory.txt";
    private int nroAtencion;

    public NroAtencion() {
        this.nroAtencion = leerNroAtencion();
    }

    public int obtenerNroAtencion() {
        return nroAtencion;
    }

    public void incrementarNroAtencion() {
        nroAtencion++;
        guardarNroAtencion();
    }

    private int leerNroAtencion() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return 1;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            return (line != null) ? Integer.parseInt(line) : 1;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 1;
        }
    }

    private void guardarNroAtencion() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write(String.valueOf(nroAtencion));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
