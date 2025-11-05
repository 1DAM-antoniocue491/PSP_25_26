```Java
public class Cronometro extends Thread {
    public Cronometro(String nombre) {
        super(nombre);
    }
    
    public void run() {
        // Obtiene un número de milisegundos
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            long inicio2 = System.currentTimeMillis();
            while (System.currentTimeMillis() - inicio2 <= 1000) {
                // No hacer nada
            }
            System.out.println((System.currentTimeMillis() - inicio) / 1000 + " seg.");
        }
        System.out.println("Finaliza el hilo secundario");
    }
    
    public static void main(String args[]) throws InterruptedException {
        Cronometro hiloCronometro = new Cronometro("Cronometro");
        hiloCronometro.start();
        hiloCronometro.join(3000);
        if (hiloCronometro.isAlive()) {
            System.out.println("¡Sigo vivo!");
            hiloCronometro.join();
        }
        System.out.println("Finaliza el hilo principal");
    }
}
```

Contesta a las siguientes preguntas:
1. **¿Qué ocurre al producirse hiloCronometro.start()?**
>	Genera una variable de tipo `long` donde se almacenan un número de milisegundos.
>	Ahora, dentro un bucle `for` se genera otra variable del mismo tipo y se almacena otro número de milisegundos.
>	Mientras que los segundos captados del sistema en tiempo real menos la cantidad de milisegundos que se han obtenido anteriormente dentro del bucle `for` sea menor o igual a 1000  (1 segundo), no hace nada.
>	Posteriormente se printea por pantalla los miliseguntos actuales del sistema menos el número de milisegundos que se obtuvieron anteriormente al principio del código entre 1000 y concatenado con "seg.".
>	Todo eso se realiza 5 veces.
>	Posteriormente se printea por pantalla se printea por pantalla el texto de "Finaliza el hilo secundario".

2. **¿Qué ocurriría si se anula hiloCronometro.start()?**
3. **¿Qué estado tendría durante la ejecución del padre o hilo principal si se anula hiloCronometro.start()? ¿Y el hilo hijo?**
4. **¿Qué ocurriría si se usa en lugar de hiloCronometro.start() por hiloCronometro.run()?**
5. **¿En qué estado se encuentra el padre o hilo principal cuando acaba el hilo de ejecución lanzado?**
6. **¿En qué estado se encuentra el hilo tras pasar el primer join()?**
7. **¿Qué diferencias existe entre el primer y segundo join()? ¿Posee el mismo efecto y salida si se anula el primer join()?**
8. **¿En qué estado se encuentra el hilo tras pasar el segundo join()?**
9. **¿De qué manera obtendrías la siguiente salida?**
```
1 seg.
2 seg.
¡Sigo vivo!
Finaliza el hilo principal
3 seg.
4 seg.
5 seg.
Finaliza el hilo secundario
```