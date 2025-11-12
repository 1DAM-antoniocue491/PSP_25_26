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
>	Posteriormente se printea por pantalla el texto de "Finaliza el hilo secundario".

2. **¿Qué ocurriría si se anula hiloCronometro.start()?**
>	Si se anula la llamada a `start()`, el hilo nunca se inicia como un hilo independiente.  
>	En consecuencia, el método `run()` no se ejecutaría en paralelo, por lo tanto, el cronómetro no imprimiría nada ni se ejecutaría el bucle.  
>	El programa principal continuaría su ejecución normalmente sin esperar al hilo.

3. **¿Qué estado tendría durante la ejecución del padre o hilo principal si se anula hiloCronometro.start()? ¿Y el hilo hijo?**
>	Si no se llama a `start()`, el hilo principal estaría **en ejecución (Runnable)** durante todo el tiempo.  
>	El hilo hijo (`hiloCronometro`) nunca pasaría al estado **Runnable**, sino que permanecería en el estado **"New" (nuevo)**, ya que no se ha iniciado.

4. **¿Qué ocurriría si se usa en lugar de hiloCronometro.start() por hiloCronometro.run()?**
>	En este caso, el método `run()` se ejecutaría **dentro del mismo hilo principal**, como si fuera una llamada normal a un método.  
>	No habría ejecución en paralelo ni creación de un hilo secundario.  
>	Por tanto, el cronómetro se ejecutaría completamente antes de continuar con las siguientes instrucciones del método `main()`.

5. **¿En qué estado se encuentra el padre o hilo principal cuando acaba el hilo de ejecución lanzado?**
>	Cuando el hilo secundario termina su ejecución, el hilo principal pasa del estado **bloqueado (Blocked)** —debido a la llamada `join()`— al estado **runnable (en ejecución)**, retomando su ejecución para continuar con las siguientes instrucciones.

6. **¿En qué estado se encuentra el hilo tras pasar el primer join()?**
>	El primer `join(3000)` bloquea al hilo principal durante un máximo de **3 segundos** o hasta que el hilo secundario finalice, lo que ocurra antes.  
>	Una vez transcurren esos 3 segundos, si el hilo secundario aún no ha terminado, el hilo principal continúa y el hilo secundario sigue en estado **Runnable (ejecutándose)**.

7. **¿Qué diferencias existe entre el primer y segundo join()? ¿Posee el mismo efecto y salida si se anula el primer join()?**
>	- El **primer `join(3000)`** tiene un **tiempo límite de espera** (3 segundos).  
>	   Permite que el hilo principal siga ejecutándose incluso si el hilo secundario sigue vivo.
>	   
>	- El **segundo `join()`** es **bloqueante total**, es decir, el hilo principal se detiene hasta que el hilo secundario termina completamente.
> 
>	Si se anula el primer `join()`, el programa podría imprimir `"¡Sigo vivo!"` o no, dependiendo del momento en que se verifique `isAlive()`.  
>	Además, el hilo principal esperaría directamente al segundo `join()`, por lo que la salida sería diferente (más sincronizada y sin el aviso de “¡Sigo vivo!” en ese punto concreto).

8. **¿En qué estado se encuentra el hilo tras pasar el segundo join()?**
>	Después del segundo `join()`, el hilo secundario ya ha completado su ejecución, por lo tanto, se encuentra en estado **Terminado (Terminated)**.  
>	El hilo principal también retoma su ejecución y continúa hasta finalizar su propio código.

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

>	Para conseguir esta salida, el hilo principal debe **reanudar su ejecución después de 2 segundos** sin esperar a que el hilo secundario termine.  
>	Una forma de lograrlo es modificar la línea del primer `join`:
>	
>	`hiloCronometro.join(2000);`
>	
>	De esta manera:
>	- El hilo principal esperará solo **2 segundos**.
>	- En ese tiempo, el hilo secundario imprimirá `1 seg.` y `2 seg.`. 	   
>	- Luego el hilo principal continuará, detectará que el hilo sigue vivo (`isAlive()`), imprimirá `"¡Sigo vivo!"`, terminará su ejecución e imprimirá `"Finaliza el hilo principal"`.
>	- Mientras tanto, el hilo secundario continuará ejecutándose y mostrará los segundos 3, 4, 5 y su mensaje final.