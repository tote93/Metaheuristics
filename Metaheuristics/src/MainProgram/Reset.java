package MainProgram;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que nos permitirá resetear/crear la estructura de ficheros necesaria para funcionamiento
 * @version 03/08/2019
 * @author i62gorej
 */
public class Reset {

    /**
     * Constructor por defecto de la clase Reset, generará la estructura de carpetas necesaria
     */
    public void createDirectoryHierarchy(){
        try {
            String url = new java.io.File(".").getCanonicalPath();
            url = url.replace("\\", "/");
            url = url + "/Algorithms";
            File f = new File(url);
            this.genDirectory(f, url);
        } catch (IOException ex) {
            Logger.getLogger(main_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     *
     * @param f Fichero o directorio el cual se comprobará que exista para generar la jerarquia
     * @param url Ruta donde se creará la estructura de carpetas y archivos
     */
    public void genDirectory(File f, String url) {
        if (!f.exists()) {
            try {
                f.mkdir();
                String newUrl = url + "/Knapsack";
                File knapsack = new File(newUrl);
                knapsack.mkdir();
                this.genKnapsackFile(newUrl);
            } catch (IOException ex) {
                Logger.getLogger(Reset.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     *
     * @param url Ruta donde se creará el fichero de ejemplo del problema de la mochila
     * @throws IOException
     */
    public void genKnapsackFile(String url) throws IOException {
        FileWriter fw = new FileWriter(url + "/exampleKnapsack.txt");
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("100\n");
            bw.write("0 85 0 11 0 0 28 0 0 0 18 0 75 0 21 20 0 72 0 59 0 0 0 89 0 76 86 0 0 0 0 0 96 0 67 0 0 0 0 80 93 0 0 0 0 0 0 28 30 0 0 21 0 0 0 0 0 0 50 0 94 0 0 92 47 0 92 0 25 40 80 0 22 0 97 0 0 0 0 70 37 0 69 0 0 0 57 0 0 0 91 81 0 0 0 30 1 0 0 74\n");
            bw.write("0 0 0 0 0 0 99 0 0 92 0 0 0 0 0 34 0 30 0 69 0 0 0 15 0 0 0 0 0 0 32 28 0 0 23 0 0 0 0 0 0 25 0 0 0 0 48 0 0 0 0 11 76 8 0 0 0 0 0 0 98 77 0 0 0 72 38 0 0 0 0 0 0 0 0 0 10 84 0 0 0 0 0 56 98 0 0 0 0 0 49 0 0 0 38 0 41 22 0 \n" +
                     " 10 0 0 0 0 0 0 0 0 0 90 0 22 51 0 0 0 0 0 0 0 61 0 0 77 0 0 0 0 58 0 94 0 12 0 0 0 0 0 0 0 84 0 60 38 0 0 0 0 0 0 0 0 0 27 0 0 0 0 0 0 0 35 0 0 0 0 0 15 0 0 59 0 0 0 0 0 0 0 0 48 22 0 0 73 0 8 0 0 0 49 76 0 0 0 0 0 9 \n" +
                     " 0 88 0 0 0 0 0 0 21 0 0 0 0 0 0 80 0 0 0 0 0 0 0 0 0 0 0 0 19 0 0 0 0 0 0 0 12 0 59 0 0 0 0 0 0 0 0 0 0 85 15 27 20 0 0 0 0 0 92 0 0 0 0 47 0 0 0 0 0 0 0 79 33 0 0 0 0 0 0 0 0 0 64 0 34 0 0 0 0 42 0 0 0 0 0 91 0 \n" +
                     " 0 0 0 0 0 13 18 0 0 0 0 73 41 0 85 0 28 0 0 0 0 0 0 0 0 0 0 0 22 0 0 0 71 0 0 0 0 0 0 0 0 0 0 0 30 0 0 0 0 0 50 0 0 0 0 0 36 90 0 0 0 43 13 0 0 0 0 0 0 21 0 0 0 93 66 42 0 0 0 0 0 0 98 0 0 0 0 0 0 8 90 0 0 0 0 0 \n" +
                     " 0 38 0 0 0 0 0 4 0 0 0 0 0 0 64 0 0 0 0 37 0 34 60 0 0 0 0 0 0 0 37 35 8 0 0 73 0 51 0 54 0 63 0 0 0 71 0 0 0 29 64 22 65 0 0 26 53 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 24 68 37 0 0 0 0 0 0 0 0 0 0 0 0 58 0 87 52 0 0 0 36 \n" +
                     " 7 0 0 4 0 0 0 0 49 0 0 0 0 0 0 46 0 63 0 0 0 0 0 28 87 47 0 0 86 91 0 0 58 69 85 23 0 0 55 0 0 0 0 62 98 4 91 0 80 0 78 0 14 0 29 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 37 0 0 0 10 0 0 0 0 0 0 0 0 0 0 0 0 87 0 3 0 41 0 0 \n" +
                     " 0 0 0 0 0 49 0 0 15 0 0 0 0 0 0 65 0 0 0 0 0 66 0 0 0 83 0 0 0 0 76 70 72 0 34 0 91 0 0 0 0 0 0 0 0 57 0 0 0 0 39 78 0 0 6 0 0 0 0 0 46 0 0 0 0 0 71 0 0 13 0 0 0 0 0 0 0 72 95 0 0 59 0 0 0 57 0 66 0 0 0 0 98 \n" +
                     " 47 0 0 10 0 36 0 59 0 0 0 0 0 0 0 0 0 0 0 0 0 11 0 0 83 0 19 0 0 0 0 37 0 45 0 0 0 0 0 0 0 0 0 0 0 0 54 0 0 83 0 58 17 0 0 0 0 0 0 0 28 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 0 0 0 36 0 0 0 0 0 0 0 0 0 0 0 0 \n" +
                     " 0 42 0 0 0 0 0 0 0 0 0 0 19 0 0 0 94 0 0 0 0 25 21 87 0 58 6 0 0 0 57 0 0 0 33 0 0 0 80 0 49 0 0 0 0 0 0 34 0 0 0 44 0 0 0 71 0 0 0 3 0 0 0 0 0 55 63 0 92 0 0 0 0 0 0 0 0 0 0 92 0 0 0 0 0 0 0 0 37 91 0 \n" +
                     " 0 0 59 53 0 0 0 0 0 0 2 0 0 0 0 0 80 0 0 0 0 0 0 0 67 0 51 0 0 0 0 0 0 0 0 0 0 0 0 0 0 100 0 0 0 0 35 0 69 1 0 16 49 83 0 0 0 6 0 0 36 0 63 0 0 0 0 0 0 0 0 0 0 0 0 0 0 49 0 45 0 34 91 0 0 2 0 70 41 66 \n" +
                     " 0 0 0 0 96 0 0 0 0 8 0 0 0 0 0 0 0 0 0 63 0 0 0 0 0 60 29 0 0 0 0 0 0 0 0 0 0 0 0 0 92 0 0 0 0 0 0 0 0 15 0 51 0 0 0 0 44 0 44 0 0 0 0 0 0 0 4 0 0 63 0 57 0 0 0 0 40 0 6 0 0 0 37 59 72 99 84 0 0 \n" +
                     " 0 72 0 0 0 16 0 36 63 1 14 0 0 0 24 0 0 13 0 0 0 0 54 0 0 0 0 0 0 0 0 0 0 2 82 0 0 0 0 0 0 0 0 35 0 0 0 35 0 0 0 0 0 0 0 20 40 0 0 0 53 0 0 0 0 0 0 0 0 0 0 89 1 68 0 1 0 0 0 0 0 0 0 97 32 38 64 0 \n" +
                     " 0 0 0 0 0 0 0 0 0 0 0 58 0 0 60 0 0 93 0 0 0 3 0 0 0 65 0 48 54 0 0 0 68 63 0 0 0 23 0 0 0 0 0 70 0 0 0 0 0 0 0 0 0 0 0 0 0 0 83 0 87 0 0 0 0 0 82 13 6 17 0 50 25 81 0 0 53 86 0 0 62 0 0 0 0 0 0 \n" +
                     " 34 0 0 0 77 0 0 0 0 0 0 0 0 5 0 0 69 0 0 30 82 0 100 0 0 25 33 0 0 68 0 0 0 76 0 0 0 0 35 0 16 8 89 66 0 0 91 0 63 0 0 0 0 0 0 0 0 7 0 0 88 0 4 0 0 55 0 0 42 0 0 0 0 0 0 0 0 0 0 0 0 0 5 0 56 39 \n" +
                     " 0 0 50 82 0 25 0 0 0 0 0 0 0 99 0 87 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 32 76 52 0 52 0 0 0 67 19 0 0 23 0 56 0 0 0 0 0 0 0 0 0 47 0 68 95 0 94 0 0 0 0 0 39 0 7 0 0 0 92 0 0 0 0 0 0 0 0 0 \n" +
                     " 0 0 54 0 0 0 0 0 0 0 40 0 75 0 0 0 0 0 44 69 0 0 0 43 0 0 22 0 53 0 97 45 0 0 0 0 0 0 0 0 0 0 84 0 51 0 46 0 0 34 0 0 0 0 15 23 0 0 0 0 0 0 54 0 0 32 0 8 0 0 0 0 0 0 87 0 0 0 0 0 0 51 0 0 \n" +
                     " 0 0 0 0 0 0 0 0 18 0 68 0 0 0 0 95 0 37 0 0 0 0 0 0 0 0 0 0 0 0 0 50 0 0 28 0 0 0 86 12 37 0 0 0 0 0 0 25 0 0 39 8 0 0 0 0 0 0 24 0 0 0 0 0 30 75 0 47 0 0 0 0 0 0 0 79 0 0 0 56 0 0 0 \n" +
                     " 0 92 0 0 81 51 0 0 79 0 0 0 26 33 0 32 0 45 0 45 0 0 0 0 66 0 0 0 0 0 15 0 0 0 0 0 0 0 0 0 0 27 82 0 19 0 38 0 0 0 0 0 0 0 0 0 0 0 17 0 0 59 52 0 0 91 0 90 0 57 94 64 61 15 60 0 0 0 20 0 0 0 \n" +
                     " 0 0 54 0 0 75 0 0 0 1 0 0 95 0 75 0 77 0 80 0 0 0 83 0 0 19 0 54 9 0 0 84 0 0 0 0 0 0 0 81 0 98 0 0 4 0 0 0 50 94 0 0 0 0 0 64 97 0 0 11 0 0 0 14 0 0 68 84 0 0 38 60 0 0 0 0 0 26 0 18 73 \n" +
                     " 0 0 7 0 40 0 0 56 0 71 89 38 0 0 0 9 0 0 0 47 0 0 0 0 26 9 44 0 0 0 0 0 0 0 0 0 0 0 0 0 39 0 0 86 0 40 0 0 0 0 29 76 0 0 0 0 15 0 0 0 0 0 0 0 23 0 0 0 0 0 0 0 23 0 25 0 0 0 0 18 \n" +
                     " 0 0 0 0 0 0 0 86 0 94 27 0 14 0 0 0 0 0 0 0 0 0 0 0 46 55 0 0 47 0 26 0 0 0 0 82 0 9 0 0 0 0 97 0 0 16 41 0 0 0 0 0 0 0 0 0 0 23 21 0 43 0 0 44 0 0 0 0 28 49 0 0 0 0 0 0 50 0 0 \n" +
                     " 0 0 0 0 0 28 0 0 0 0 0 46 0 0 0 0 0 33 0 97 39 0 26 15 0 93 0 0 0 0 55 0 0 0 0 0 0 0 0 0 0 0 0 0 13 0 0 40 0 0 0 0 19 24 44 0 0 0 0 0 0 0 5 0 0 0 22 60 0 65 0 0 1 0 0 42 0 0 \n" +
                     " 0 66 0 0 0 54 0 0 0 90 0 0 0 0 0 0 14 0 0 0 100 0 0 0 0 39 34 0 0 0 0 0 0 0 89 0 7 0 2 0 0 61 0 0 0 0 0 5 0 14 0 0 0 0 0 0 0 0 0 0 52 0 29 0 0 0 0 0 0 0 0 0 0 0 0 87 0 \n" +
                     " 0 0 0 0 19 0 0 0 0 8 93 0 0 0 0 72 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 61 28 0 89 0 0 0 0 0 0 45 0 0 0 0 86 0 0 0 0 0 52 0 76 0 36 0 0 0 0 0 0 0 0 0 0 0 0 0 41 78 0 0 \n" +
                     " 30 0 0 0 27 0 0 0 99 0 90 0 0 0 76 0 0 29 0 0 0 0 16 0 0 0 0 0 0 0 0 0 58 0 0 0 0 19 89 0 67 13 0 0 0 0 0 0 0 0 0 0 40 0 93 0 51 0 0 0 0 58 0 0 87 0 0 0 0 0 0 58 0 0 0 \n" +
                     " 0 33 0 0 49 0 37 0 14 0 0 0 47 0 0 84 0 0 9 60 62 0 0 69 0 40 0 0 80 64 0 0 0 69 0 0 0 29 0 0 0 16 73 0 22 0 0 0 0 0 99 62 0 99 54 0 18 0 0 0 0 47 94 0 0 87 0 36 58 0 91 0 0 0 \n" +
                     " 0 0 0 0 0 0 78 0 0 0 0 0 91 0 83 74 78 0 0 0 0 19 0 0 0 0 0 0 0 34 0 0 8 87 0 0 0 0 86 0 0 0 0 15 0 1 0 0 7 25 54 0 95 0 76 0 0 0 0 0 0 0 0 0 51 0 0 45 8 34 0 0 0 \n" +
                     " 0 0 0 0 0 0 45 0 81 0 0 0 84 0 0 41 0 94 0 46 0 0 8 100 0 0 0 0 0 0 19 0 0 0 0 99 0 0 0 0 0 0 0 0 0 0 0 0 0 94 0 0 0 0 0 0 0 49 0 60 0 93 0 0 0 47 0 90 0 96 92 0 \n" +
                     " 0 0 0 0 0 0 0 0 37 77 0 0 0 0 0 0 21 5 31 34 0 56 0 38 4 0 10 21 0 79 0 29 0 89 0 0 0 0 0 0 20 42 0 0 0 0 49 0 0 43 35 0 0 0 0 64 0 0 0 8 34 0 53 0 53 0 0 0 0 0 14 \n" +
                     " 0 0 94 0 0 0 0 75 0 0 0 0 0 12 0 0 68 0 0 0 0 71 0 0 0 0 30 29 0 47 0 0 0 0 21 0 0 0 0 0 0 0 0 0 0 0 0 0 75 81 0 0 0 0 74 0 38 0 0 0 0 77 87 0 42 0 0 0 0 0 \n" +
                     " 0 0 0 0 0 0 24 0 0 0 0 59 89 0 0 0 0 36 0 0 0 7 0 0 21 67 0 0 39 0 0 0 0 0 0 0 0 0 0 0 0 61 0 82 7 43 0 78 87 0 0 32 70 21 75 0 0 98 0 0 0 0 0 0 0 0 0 0 0 \n" +
                     " 0 46 24 0 20 0 0 0 0 0 0 0 33 0 0 0 82 0 0 0 0 53 0 5 0 0 0 55 0 0 62 0 0 60 0 0 0 0 0 0 0 51 12 0 0 0 0 52 0 0 0 79 100 0 0 0 71 0 0 0 90 75 0 0 0 0 0 0 \n" +
                     " 0 13 0 0 67 0 0 0 0 0 84 0 0 48 0 0 61 0 0 0 0 0 0 0 39 0 32 0 0 0 31 0 0 0 15 0 0 0 0 0 0 0 0 35 0 0 60 20 0 0 0 0 0 0 0 0 0 3 0 0 86 0 36 0 0 0 75 \n" +
                     " 0 0 0 16 0 0 0 0 0 0 0 0 0 7 0 0 0 0 0 0 14 0 0 0 52 0 0 0 0 0 0 0 12 52 0 0 69 0 0 0 0 0 41 33 0 0 0 80 0 0 0 72 0 0 0 0 88 0 63 0 0 0 0 0 0 0 \n" +
                     " 0 0 0 0 68 0 0 0 0 9 0 0 0 10 0 0 0 0 43 0 0 0 0 0 0 0 0 0 0 92 0 54 96 0 0 99 0 0 0 0 0 0 0 78 0 0 0 92 0 0 0 0 0 26 0 0 46 54 0 0 0 0 71 0 0 \n" +
                     " 0 0 93 0 0 0 10 0 19 0 0 0 0 0 0 0 0 0 0 0 48 0 0 0 0 23 20 12 0 0 28 0 0 0 0 0 0 0 70 0 49 0 0 0 0 0 81 0 0 0 0 91 0 0 49 0 0 0 31 0 0 0 0 0 \n" +
                     " 0 0 8 0 0 0 0 0 52 63 88 0 0 0 0 0 0 0 0 0 0 0 0 87 0 0 19 0 0 0 0 0 70 0 0 0 98 0 0 51 47 0 0 0 0 0 26 83 40 0 0 0 0 34 0 0 0 0 25 5 100 0 0 \n" +
                     " 0 0 0 0 0 29 0 63 0 0 23 11 0 48 0 0 76 0 0 0 0 17 0 0 0 5 0 0 24 54 0 0 0 0 0 11 0 0 0 0 0 0 0 0 0 42 68 90 0 0 0 0 0 0 0 94 80 0 63 81 0 0 \n" +
                     " 86 0 0 0 100 0 0 0 0 0 28 0 0 0 0 0 0 0 1 98 0 0 0 0 70 0 0 0 0 0 0 0 0 7 43 0 78 18 0 0 0 0 81 0 0 0 0 0 0 0 0 0 0 0 46 0 0 0 0 0 0 \n" +
                     " 0 62 63 0 0 0 63 0 0 97 0 8 0 0 0 0 0 0 0 0 11 57 66 0 0 0 0 0 0 0 0 0 49 0 47 5 25 33 0 0 0 0 0 0 0 0 2 7 0 7 13 0 0 100 0 49 0 0 73 0 \n" +
                     " 0 67 61 0 0 0 0 0 0 0 0 0 55 0 0 0 0 0 0 4 55 20 0 75 0 0 0 12 65 0 20 0 93 0 0 96 0 0 0 53 54 0 0 0 0 44 0 0 0 0 8 0 0 0 69 72 0 0 0 \n" +
                     " 0 0 0 0 26 0 0 13 24 0 0 0 0 35 0 78 0 0 0 0 79 0 0 0 58 0 62 0 0 0 0 0 0 0 0 0 0 0 25 19 0 0 0 0 0 0 0 0 95 0 16 30 0 0 0 0 81 100 \n" +
                     " 0 93 0 96 20 82 0 0 39 0 87 68 0 0 0 0 0 60 0 0 0 40 0 65 0 40 0 0 0 0 0 31 88 0 0 0 0 0 0 93 0 1 8 0 0 14 62 0 0 24 0 70 31 0 36 0 76 \n" +
                     " 0 0 0 0 36 0 0 0 0 0 0 21 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 88 46 61 0 86 0 0 63 0 0 0 0 0 0 0 46 0 18 0 0 0 0 0 0 24 0 \n" +
                     " 0 0 37 98 0 0 20 0 0 0 73 0 50 22 25 0 0 0 0 0 0 59 66 0 0 0 51 0 0 0 0 0 36 0 68 0 0 0 81 0 0 0 0 70 0 26 91 0 0 0 0 66 96 0 0 \n" +
                     " 20 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 99 0 20 0 0 0 0 0 0 0 0 14 0 0 0 0 58 0 0 0 0 14 57 0 0 70 0 0 0 0 0 30 0 0 0 0 0 0 \n" +
                     " 28 2 100 0 0 0 0 35 0 0 0 0 0 0 0 0 0 48 60 42 5 0 0 0 0 22 0 0 4 0 0 24 21 0 36 0 0 0 0 0 0 0 0 0 49 0 0 79 0 84 0 100 0 \n" +
                     " 12 0 0 0 0 0 38 0 33 0 80 0 49 0 0 0 88 29 0 0 92 0 89 0 0 0 5 0 0 0 0 0 42 0 0 0 0 0 0 0 80 0 0 0 0 60 59 0 0 0 53 0 \n" +
                     " 0 11 69 0 0 0 48 0 0 46 0 0 68 0 0 0 0 0 0 100 0 38 0 0 0 91 0 9 0 0 0 76 78 0 0 0 0 0 0 0 0 59 0 0 63 0 0 78 0 98 78 \n" +
                     " 0 0 0 0 0 0 12 0 0 0 0 0 0 0 0 0 0 0 0 0 0 80 0 0 0 0 0 0 12 0 0 0 0 0 0 0 0 98 0 0 0 0 0 0 66 53 43 0 70 0 \n" +
                     " 67 62 0 0 11 93 0 0 0 0 0 65 0 0 0 66 0 0 0 0 0 0 0 0 0 0 0 98 0 0 0 0 0 0 0 0 0 12 0 0 0 1 53 0 0 0 85 0 44 \n" +
                     " 30 0 0 77 0 89 0 0 0 0 0 0 0 0 0 0 30 0 0 0 22 74 0 0 0 0 0 0 0 0 0 45 0 41 0 0 0 0 87 0 0 0 0 0 0 47 0 0 \n" +
                     " 0 0 0 0 39 6 0 0 0 0 0 0 0 0 0 0 0 0 0 98 48 0 14 0 0 96 0 0 96 0 29 62 1 75 0 0 0 0 0 28 0 0 88 0 0 0 0 \n" +
                     " 0 0 0 0 0 0 0 93 0 0 0 13 72 82 0 0 0 0 0 0 34 0 0 0 0 0 0 0 0 79 0 0 0 0 0 0 0 11 0 0 0 0 0 20 0 0 \n" +
                     " 0 0 0 0 27 32 0 0 0 0 0 66 21 47 0 0 0 0 0 0 46 66 0 79 0 0 0 0 73 0 0 0 0 0 54 0 75 13 0 0 12 9 0 0 0 \n" +
                     " 0 13 0 0 82 0 0 83 9 0 0 0 0 1 0 56 0 87 42 0 0 0 45 90 80 0 0 0 0 0 0 0 0 0 5 0 0 0 46 0 0 76 0 0 \n" +
                     " 0 0 0 0 0 0 0 0 0 35 0 0 45 82 0 0 0 0 0 0 0 78 68 0 0 0 0 0 42 0 31 15 0 0 0 0 0 0 63 0 0 0 0 \n" +
                     " 0 0 0 0 0 0 0 0 93 92 0 0 15 0 0 0 0 0 0 52 0 0 0 0 0 0 0 24 0 0 29 0 12 0 0 0 0 21 0 0 36 0 \n" +
                     " 99 0 0 32 0 0 0 24 0 22 0 0 65 0 0 91 65 0 71 22 0 0 74 0 45 0 0 55 81 0 0 0 0 68 29 64 0 0 50 0 0 \n" +
                     " 0 0 0 0 0 0 0 87 0 0 0 0 0 0 0 59 55 0 0 0 0 0 0 0 0 0 0 0 0 70 0 89 0 0 0 0 0 18 0 0 \n" +
                     " 33 0 81 0 0 59 0 0 29 24 0 0 9 7 0 94 0 0 0 0 0 0 0 0 70 15 0 0 0 0 0 0 0 99 0 76 0 0 31 \n" +
                     " 0 23 100 83 0 42 0 0 0 22 34 0 0 0 80 0 0 0 0 0 0 58 10 4 15 0 0 42 0 0 78 0 0 0 0 0 61 59 \n" +
                     " 0 74 0 0 33 0 89 82 0 0 0 0 0 0 0 55 95 0 0 0 0 0 27 0 0 0 19 1 0 92 28 30 12 29 0 0 59 \n" +
                     " 59 0 94 0 71 0 0 0 0 0 54 43 0 0 0 0 0 0 36 0 0 0 0 0 0 0 86 0 0 0 0 0 72 0 0 0 \n" +
                     " 0 0 0 0 0 0 0 0 0 0 33 0 0 0 0 0 0 0 0 0 0 0 0 68 25 0 0 0 0 41 60 0 60 0 59 \n" +
                     " 0 73 0 0 0 0 23 0 0 0 98 0 0 0 0 0 17 62 43 0 0 0 0 0 0 0 0 0 0 0 0 0 0 56 \n" +
                     " 0 0 0 18 0 43 0 0 0 38 58 0 77 0 36 0 0 0 0 0 5 0 0 0 0 90 0 0 0 0 24 0 44 \n" +
                     " 0 0 0 0 63 0 0 0 94 0 0 0 100 0 0 0 12 0 10 0 0 0 0 86 0 20 0 0 0 0 0 0 \n" +
                     " 0 0 93 0 80 16 94 0 0 0 0 0 0 0 0 65 0 0 40 0 0 0 47 0 80 0 0 0 0 0 74 \n" +
                     " 0 0 67 0 39 0 93 0 0 33 37 18 0 0 0 0 0 0 0 0 0 52 0 0 7 21 19 0 0 0 \n" +
                     " 0 33 0 0 0 0 0 0 0 8 0 0 71 0 94 0 25 72 0 2 38 0 13 0 99 0 70 55 0 \n" +
                     " 0 0 0 0 0 33 100 0 0 0 91 0 0 90 0 0 53 0 0 0 0 0 10 57 0 0 0 0 \n" +
                     " 0 0 59 0 0 0 0 0 70 0 0 0 0 0 89 2 0 0 0 0 0 0 0 0 0 0 83 \n" +
                     " 0 0 0 0 0 0 0 61 0 0 0 0 0 72 0 0 71 0 0 0 0 0 0 87 19 0 \n" +
                     " 0 0 0 0 0 0 0 0 0 0 0 84 88 85 0 0 0 0 0 0 0 0 0 0 0 \n" +
                     " 0 0 31 0 0 0 0 0 0 0 0 0 94 0 44 0 0 5 0 0 0 0 0 0 \n" +
                     " 0 0 0 9 8 0 0 0 0 0 0 0 0 13 0 0 0 0 0 0 0 0 24 \n" +
                     " 0 84 0 0 83 0 0 0 0 0 0 0 0 0 67 0 0 0 0 0 87 84 \n" +
                     " 0 0 0 0 0 0 0 23 0 99 0 0 0 0 0 0 79 0 0 3 28 \n" +
                     " 0 62 0 36 22 41 0 33 0 81 0 0 0 0 0 0 0 0 0 0 \n" +
                     " 6 0 0 99 0 0 0 70 0 0 0 0 0 0 0 0 0 0 27 \n" +
                     " 94 0 53 5 0 0 0 0 0 0 0 0 0 0 0 75 0 69 \n" +
                     " 0 19 0 49 0 0 0 0 0 0 0 18 0 0 66 0 0 \n" +
                     " 0 0 0 0 0 14 0 0 0 0 0 15 0 0 77 0 \n" +
                     " 8 76 0 0 40 25 0 0 0 0 0 0 0 0 0 \n" +
                     " 0 0 0 0 0 0 77 0 0 0 0 0 0 0 \n" +
                     " 0 0 0 0 0 0 0 0 0 0 0 0 0 \n" +
                     " 0 0 0 0 0 0 0 0 0 0 0 0 \n" +
                     " 0 0 0 0 91 0 34 0 30 0 0 \n" +
                     " 0 55 0 0 0 0 0 8 0 0 \n" +
                     " 0 0 0 0 0 0 10 30 27 \n" +
                     " 0 0 0 0 0 0 0 0 \n" +
                     " 0 83 79 0 0 0 82 \n" +
                     " 48 0 0 76 0 9 \n" +
                     " 40 0 20 0 0 \n" +
                     " 72 94 92 0 \n" +
                     " 0 0 0 \n" +
                     " 0 0 \n" +
                     " 0 \n" +
                     " 42 46 49 44 38 29 41 5 40 47 3 19 39 24 9 49 50 45 47 38 40 34 41 28 46 42 16 50 2 10 29 24 24 40 41 32 49 43 33 2 17 11 49 25 23 29 45 44 5 2 34 32 36 29 21 9 40 49 35 49 17 2 46 18 19 16 1 29 42 16 22 12 29 32 48 37 26 13 9 30 29 27 49 49 18 40 9 21 15 3 49 47 32 33 46 33 38 21 47 32 \n");
        }
    }
}
