/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Controller;

import java.util.Scanner;
import javax.speech.*;
import javax.speech.synthesis.*;
import java.util.Locale;


public class HiloServidor {

    public void leerTexto() {
        try {
            // Configura la configuración del sintetizador de voz
            SynthesizerModeDesc desc = new SynthesizerModeDesc();

            // Obtiene un sintetizador de voz compatible con la configuración
            Synthesizer synthesizer = Central.createSynthesizer(desc);

            // Verifica si se pudo crear el sintetizador de voz
            if (synthesizer == null) {
                System.out.println("No se pudo crear el sintetizador de voz");
                return;
            }

            // Abre el sintetizador
            synthesizer.allocate();
            synthesizer.resume();

            // Lee el texto desde la entrada estándar
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el texto a convertir en voz:");
            String texto = scanner.nextLine();

            // Convierte el texto en voz
            synthesizer.speakPlainText(texto, null);

            // Espera hasta que la síntesis de voz haya terminado
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

            // Libera los recursos del sintetizador
            synthesizer.deallocate();

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static void main(String[] args) {
            HiloServidor hiloServidor = new HiloServidor();
            hiloServidor.leerTexto();
        }
}
