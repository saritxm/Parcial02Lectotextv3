/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Controller;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;


public class HiloServidor {

    public void leerTexto(String texto, String idioma) {
        try {
  // Configura la configuración del sintetizador de voz
            SynthesizerModeDesc desc = new SynthesizerModeDesc();
         // Configurar el idioma
             Locale locale = new Locale(idioma);
            desc.setLocale(locale);

            // Obtiene un sintetizador de voz compatible con la configuración
            Synthesizer synthesizer = Central.createSynthesizer(desc);


            // Verifica si se pudo crear el sintetizador de voz
            if (synthesizer == null) {
                System.out.println("No se pudo crear el sintetizador de voz");
                return;
            }
            
            synthesizer.allocate();
            synthesizer.resume();


            synthesizer.speakPlainText(texto, null);

            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            synthesizer.deallocate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        HiloServidor hiloServidor = new HiloServidor();
        String texto = "hello my friend"; // El texto que deseas sintetizar
        String idioma = "En"; // Valor predeterminado
        // Puedes modificar este valor de idioma según lo que recibas de los radio buttons
        hiloServidor.leerTexto(texto, idioma);

    }
}
