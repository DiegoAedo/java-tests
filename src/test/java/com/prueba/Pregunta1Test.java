package com.prueba;

import static java.lang.Double.valueOf;
import static org.junit.jupiter.api.Assertions.*;
import lombok.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import lombok.Getter;
import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class Pregunta1Test {

    @Test
    public void testCasteoObjectAString() {

        //1.-¿Cómo casteas un objeto Object a String?

        Object objeto = "Diego";
        String string = assertInstanceOf(String.class, objeto);
        //assertEquals("Diego", texto);
    }

    @Test
    public void testCasteoTipoIncompatibleLanzaExcepcion() {

        //2.-¿Qué ocurre si casteas un objeto a un tipo incompatible?

        Object obj = "Diego";
        assertThrows(ClassCastException.class, () -> {
            Integer num = (Integer) obj;
        });
    }

    @Test
    public void testDoubleABigDecimal() {

        //3.-¿Cómo conviertes un double a BigDecimal para evitar pérdida de precisión?

        double precio = 1958445345343.15515d;
        BigDecimal precioBigdecimal = BigDecimal.valueOf(precio);

        assertNotNull(precioBigdecimal);
        assertTrue(precioBigdecimal.doubleValue() > 0);

    }

    @Test
    public void testBigDecimalADouble() {

        // 4.-¿Cómo conviertes un BigDecimal a double?

        BigDecimal precio = new BigDecimal("1958445345343.15515");
        double precioDouble = precio.doubleValue();

        assertTrue(precioDouble > 0, "El valor double debe ser mayor que cero");

    }

    @Test
    public void testImpresionVariables() {

        //5.-¿Cómo imprimir variables usando java.util.logging.Logger?

        String nombre = "Diego";
        String apellido = "Aedo";
        log.info("5R.-Nombre y apellido: {} {}", nombre, apellido);
    }

    @Test
    public void testStringADouble() {

        //6.-¿Cuál es la forma correcta de convertir una String a double?

        String valor = "1550.520";
        Double valorObjeto = valueOf(valor);

    }

    @Test
    public void testDoubleANotacionExponencial() {

        //7.-¿Qué método usas para convertir un double con notación exponencial en String ?

        double valor = 1.67E89d;
        BigDecimal bigDecimal = BigDecimal.valueOf(valor);
        String texto = bigDecimal.toPlainString();


    }

    @Test
    public void testLongAString() {

        //8.-¿Cómo conviertes un Long a String para imprimirlo en logs?

        long valor = 123456789L;
        String valorStr = String.valueOf(valor);
        assertEquals("123456789", valorStr);
    }

    @Test
    public void testCompararBigDecimal() {

        //9.-¿Cómo comparar dos BigDecimal para saber cuál es mayor?

        BigDecimal valor = BigDecimal.valueOf(20);
        BigDecimal valor2 = BigDecimal.valueOf(50);

        if (valor.compareTo(valor2) > 0) {
            log.info("Bigdecimal1 {} es mayor que Bigdecimal2 {}",valor,valor2);
        }else if (valor.compareTo(valor2) < 0) {
            log.info("Bigdecimal2 {} es mayor que Bigdecimal1 {}",valor2,valor);
        }else{
            log.info("Bigdecimal1 {} es igual que Bigdecimal2 {}",valor,valor2);
        }
    }

    @Test
    public void ejercicio10() {

        //¿Cómo comparar dos double para saber cuál es mayor?

        Double valor1 = Double.valueOf(30.30d);
        Double valor2 = Double.valueOf(20.30d);

        int cmp = valor1.compareTo(valor2);

        if (cmp < 0) {
            fail("valor1 debería ser mayor que valor2");
        } else if (cmp > 0) {
            assertTrue(true);
        } else {
            fail("valor1 no debería ser igual a valor2");
        }
    }

    @Test
    public void ejercicio11() {

        //¿Cómo defines un HashMap en Java?

        Map<String, Integer> mapa = new HashMap<>();

        assertNotNull(mapa, "El mapa no debe ser null");
        assertTrue(mapa.isEmpty(), "El mapa debe estar vacío al crearlo");
    }

    @Test
    public void ejercicio12() {

        //¿Cómo agregar un elemento a un HashMap?

        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("Diego", 36);

        assertTrue(mapa.containsKey("Diego"), "El mapa debe contener la clave 'Diego'");
        assertEquals(36, mapa.get("Diego"));
    }

    @Test
    public void ejercicio13() {

        //¿Cómo eliminar un elemento de un HashMap por clave?

        Map<String, Integer> mapa = new HashMap<>();

        mapa.put("Diego", 37);
        mapa.put("Victor", 39);
        mapa.remove("Victor");

        assertFalse(mapa.containsKey("Victor"), "El mapa no debe contener la clave 'Victor'");
        assertTrue(mapa.containsKey("Diego"), "El mapa debe contener la clave 'Diego'");
        assertEquals(37, mapa.get("Diego"));
    }

    @Test
    public void ejercicio14() {

        //¿Cómo recorrer un HashMap con un bucle for?

        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("Diego", 37);
        mapa.put("Victor", 39);
        mapa.put("Leo", 38);
        mapa.put("Javier", 40);

        StringBuilder resultado = new StringBuilder();

        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            resultado.append(key).append(":").append(value).append(";");
        }

        String resultadoStr = resultado.toString();

        assertTrue(resultadoStr.contains("Diego:37;"));
        assertTrue(resultadoStr.contains("Victor:39;"));
        assertTrue(resultadoStr.contains("Leo:38;"));
        assertTrue(resultadoStr.contains("Javier:40;"));
    }

    @Test
    public void ejercicio15() {

        //¿Cómo recorrer un HashMap con Iterator?

        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("Diego", 37);
        mapa.put("Victor", 39);
        mapa.put("Leo", 38);
        mapa.put("Javier", 40);

        StringBuilder resultado = new StringBuilder();

        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            resultado.append(key).append(":").append(value).append(";");
        }

        String resultadoStr = resultado.toString();

        assertTrue(resultadoStr.contains("Diego:37;"));
        assertTrue(resultadoStr.contains("Victor:39;"));
        assertTrue(resultadoStr.contains("Leo:38;"));
        assertTrue(resultadoStr.contains("Javier:40;"));
    }

    @Test

    public void ejercicio16() {

        //¿Cómo definir una lista (ArrayList) en Java?


        List<Object> lista = new ArrayList<>();

        assertNotNull(lista, "La lista no debe ser null");
        assertTrue(lista.isEmpty(), "La lista debe estar vacía al crearla");
    }

    @Test
    public void ejercicio17() {

        //¿Cómo agregar un elemento a una lista?

        List<String> lista = new ArrayList<>();
        lista.add("Diego");


        assertFalse(lista.isEmpty(), "La lista no debe estar vacía después de agregar un elemento");
        assertEquals(1, lista.size(), "La lista debe tener tamaño 1");
        assertTrue(lista.contains("Diego"), "La lista debe contener el elemento 'Diego'");
    }

    @Test
    public void ejercicio18() {

        //¿Cómo eliminar un elemento de una lista?

        List<String> lista = new ArrayList<>();
        lista.add("Diego");
        lista.add("Victor");

        boolean eliminado = lista.remove("Victor");

        assertTrue(eliminado, "El elemento 'Victor' debería ser eliminado");
        assertFalse(lista.contains("Victor"), "La lista no debe contener 'Victor'");
        assertTrue(lista.contains("Diego"), "La lista debe contener 'Diego'");
    }

    @Test
    public void ejercicio19() {

        //¿Cómo recorrer una lista con un bucle for-each?

        List<String> lista = new ArrayList<>();
        lista.add("Diego");
        lista.add("Victor");
        lista.add("Leo");

        StringBuilder resultado = new StringBuilder();

        for (String elemento : lista) {
            resultado.append(elemento).append(";");
        }

        String resultadoStr = resultado.toString();

        assertTrue(resultadoStr.contains("Diego;"));
        assertTrue(resultadoStr.contains("Victor;"));
        assertTrue(resultadoStr.contains("Leo;"));
    }

    @Test
    public void ejercicio20() {

        //¿Cómo recorrer una lista con un Iterator?

        List<String> lista = new ArrayList<>();
        lista.add("Diego");
        lista.add("Victor");
        lista.add("Leo");

        StringBuilder resultado = new StringBuilder();

        for (String elemento : lista) {
            resultado.append(elemento).append(";");
        }

        String resultadoStr = resultado.toString();


        assertTrue(resultadoStr.contains("Diego;"));
        assertTrue(resultadoStr.contains("Victor;"));
        assertTrue(resultadoStr.contains("Leo;"));
    }


    public static class ejercicio21 {

        //¿Cómo funciona el switch-case en Java?

        public String obtenerDiaSemana(int dia) {
            switch (dia) {
                case 1:
                    return "Lunes";
                case 2:
                    return "Martes";
                case 3:
                    return "Miércoles";
                case 4:
                    return "Jueves";
                case 5:
                    return "Viernes";
                case 6:
                    return "Sábado";
                case 7:
                    return "Domingo";
                default:
                    return "Día inválido";
            }
        }
    }


    public class ejercicio22 {

        //¿Se pueden usar String en switch-case?

        public String saludoPorIdioma(String idioma) {

            if (idioma == null) {
                return "Idioma no especificado";
            }

            switch (idioma.toLowerCase()) {
                case "es":
                    return "Hola";
                case "en":
                    return "Hello";
                case "fr":
                    return "Bonjour";
                default:
                    return "Idioma no reconocido";
            }
        }
    }


    public enum DiaSemana {

        //23.-¿Cómo definir una clase enum en Java?

        LUNES,
        MARTES,
        MIERCOLES,
        JUEVES,
        VIERNES,
        SABADO,
        DOMINGO


    }

    /*
    24.-¿Qué ventajas tiene usar enum en lugar de constantes estáticas?

    *-Los enum representan conjuntos inmutables de valores, significa que solo se pueden utilizar los valores definidos y no otros fuera del conjunto.
    *-Los enum pueden tener métodos. */

    public enum Color {
        ROJO("#FF0000"),
        VERDE("#00FF00"),
        AZUL("#0000FF");

        private final String codigoHex;

        Color(String codigoHex) {
            this.codigoHex = codigoHex;
        }

        public String getCodigoHex() {
            return codigoHex;
        }
    }


    //25.-¿Cómo acceder a un valor específico dentro de un enum?

    public enum DiaSemanas {
        LUNES,
        MARTES,
        MIERCOLES,
        JUEVES,
        VIERNES,
        SABADO,
        DOMINGO
    }

    @Test
    public void testEjercicio25() {
        DiaSemanas dia = DiaSemanas.LUNES;
        assertEquals(DiaSemanas.LUNES, dia);
    }

    //26.-¿Cómo transformar una variable estática en variable de instancia?

    public static class Contador {
        private int cuenta;

        public Contador(int cuentaInicial) {
            this.cuenta = cuentaInicial;
        }

        public int getCuenta() {
            return cuenta;
        }

        public void setCuenta(int cuenta) {
            this.cuenta = cuenta;
        }
    }

    @Test
    public void testVariableDeInstancia() {
        Contador c1 = new Contador(5);
        Contador c2 = new Contador(10);

        // Cambiar el valor de c1
        c1.setCuenta(20);

        // Validar que c1 y c2 tienen valores independientes
        assertEquals(20, c1.getCuenta());
        assertEquals(10, c2.getCuenta());
    }

    //27.-¿Por qué evitar variables estáticas en la programación orientada a objetos?

    /*
    Pérdida de encapsulamiento e independencia: Las variables estáticas pertenecen a la clase, no a instancias, por lo que comparten estado entre todos los objetos. Esto rompe la idea de que cada objeto debe tener su propio estado independiente.

Dificultan la reutilización y el testing: Al compartir estado global, las variables estáticas hacen que el comportamiento dependa de un estado compartido, lo que complica pruebas unitarias aisladas y genera dependencias ocultas entre objetos.

Problemas de concurrencia: Cuando varios hilos acceden o modifican variables estáticas sin sincronización adecuada, pueden surgir condiciones de carrera y errores difíciles de depurar.

Menor flexibilidad: Variables estáticas no pueden ser polimórficas ni parte de la herencia en forma natural, limitando el diseño orientado a objetos.

Acoplamiento fuerte: El uso excesivo de variables estáticas genera dependencias rígidas entre clases, dificultando cambios y mantenimiento.

    */


    public static class Ejercicio28 {
        public Integer getI() {
            // autoboxing: el int 42 se convierte en Integer.valueOf(42)
            Integer i = 42;
            return i;
        }

        public Boolean getB() {
            // autoboxing: el boolean true se convierte en Boolean.valueOf(true)
            Boolean b = true;
            return b;
        }
    }

    @Test
    public void testEjercicio28() {
        Ejercicio28 e = new Ejercicio28();
        assertEquals(42, e.getI().intValue());
        assertTrue(e.getB());
    }

    public static class Ejercicio29 {

        // 29.- ¿Qué es el unboxing en Java?

        public int getX() {

            Integer temp = Integer.valueOf(7);
            return temp;  // aquí ocurre el unboxing automático
        }

        public double getD() {
            // unboxing: Double.valueOf(3.14).doubleValue()
            Double temp = Double.valueOf(3.14);
            return temp;  // aquí ocurre el unboxing automático
        }
    }

    @Test
    public void testEjercicio29() {
        Ejercicio29 e = new Ejercicio29();
        assertEquals(7, e.getX());
        assertEquals(3.14, e.getD(), 1e-9);
    }


    //30.-¿Cómo conviertes un Integer a int?

    Integer enteroObjeto = Integer.valueOf(42);
    int enteroPrimitivo = enteroObjeto;


    //31.-¿Cómo convertir un double a String usando String.valueOf?

    double valor = 3.9d;
    String valorStr = String.valueOf(valor);

    //32.-¿Cómo transformar un String en un objeto Double?

    String texto = "3.1";
    Double obj = Double.parseDouble(texto);

    //33.-¿Qué diferencia hay entre Double.parseDouble() y Double.valueOf()?

    /* Double.parseDouble() Devuelve un primitivo double.
       Double.valueOf() deuuelve un objeto Double.
     */

    //34.-¿Qué es la clase BigDecimal y para qué se usa?

    /* Se utiliza para precisión exacta en cálculos decimales y control absoluto de cómo se redondean los resultados.
     */

    //35.-¿Cómo sumar dos BigDecimal?

    BigDecimal bg1 = BigDecimal.valueOf(5.5);
    BigDecimal bg2 = BigDecimal.valueOf(10.5);

    BigDecimal suma = bg1.add(bg2);

    //36.-¿Cómo restar dos BigDecimal?

    BigDecimal bg3 = new BigDecimal("15.5");
    BigDecimal bg4 = new BigDecimal("4.3");

    BigDecimal resultado = bg3.subtract(bg4);

    //37.-¿Cómo multiplicar dos BigDecimal?

    BigDecimal bd1 = new BigDecimal("2.5");
    BigDecimal bd2 = new BigDecimal("4.0");

    BigDecimal producto = bd1.multiply(bd2);

    //38.-¿Cómo dividir dos BigDecimal?

    BigDecimal dividendo = new BigDecimal("10");
    BigDecimal divisor = new BigDecimal("3");

    BigDecimal resultados = dividendo.divide(divisor, 2, RoundingMode.HALF_UP);

    //39.-¿Qué método de BigDecimal usas para comparar valores?

    BigDecimal a = new BigDecimal("1.0");
    BigDecimal b = new BigDecimal("1.00");

    int cmp = a.compareTo(b);


    //40.-¿Qué pasa si divides por cero con BigDecimal?

    BigDecimal numerador = new BigDecimal("10");
    BigDecimal divisorCero = BigDecimal.ZERO;

    // Usando divide() sin especificar redondeo
    //numerador.divide(divisorCero);
    // → lanza ArithmeticException: Division by zero

    // Incluso si usas la versión con escala y modo de redondeo:
    //numerador.divide(divisorCero, 2, RoundingMode.HALF_UP);
    // → también lanza ArithmeticException: Division by zero

    //41.-¿Cómo formatear un número decimal con dos decimales usando BigDecimal?

    BigDecimal valor10 = new BigDecimal("3.14159");
    BigDecimal formateado = valor10.setScale(2, RoundingMode.HALF_UP);

    //42.-¿Qué es el redondeo en BigDecimal y cómo aplicarlo?
    /*
    En el contexto de BigDecimal, redondeo es el proceso de ajustar un número decimal a una escala (número de dígitos fraccionarios)
    determinada cuando la operación produce un resultado con más decimales de los que deseas almacenar o mostrar.
    Como BigDecimal es inmutable, cada operación de redondeo devuelve un nuevo objeto.

     */

    /*
    Modos de redondeo (RoundingMode)

    * Java define varios modos en el enum java.math.RoundingMode, entre los más comunes:
    * HALF_UP: redondeo “común” (si la parte siguiente es ≥ 0.5, sube).
    * HALF_EVEN: “redondeo bancario” (en .5 empata al par más cercano).
    * DOWN: trunca (elimina décimales sobrantes).
    * UP: aleja siempre del cero.
    * CEILING: hacia arriba (más positivo).
    * FLOOR: hacia abajo (más negativo).

     */

    BigDecimal valor22 = new BigDecimal("3.14159");
    BigDecimal redondeado = valor22.setScale(2, RoundingMode.HALF_UP);

    //43.-¿Qué es el método equals() en Java?
    /*
   En resumen, equals() es la forma canónica y personalizable de definir qué significa que dos objetos sean iguales en Java.
    */

    //44.-¿Cómo sobrescribir el método equals() para una clase propia?

    public static class Personas {
        private final String nombre;
        private final int edad;

        public Personas(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;                 // misma referencia
            if (o == null || getClass() != o.getClass()) return false;
            Personas otra = (Personas) o;
            return edad == otra.edad &&
                    Objects.equals(nombre, otra.nombre); // compara campos
        }

        @Override
        public int hashCode() {
            return Objects.hash(nombre, edad);          // mismos campos que equals
        }
    }

    @Test
    public void ejercicio44_equals() {
        Personas p1 = new Personas("Ana", 30);
        Personas p2 = new Personas("Ana", 30);
        Personas p3 = new Personas("Luis", 30);

        // Igualdad lógica
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());

        // Distinto contenido
        assertNotEquals(p1, p3);
    }

    //45.-¿Qué es el método hashCode() y por qué es importante?

    /*
    hashCode() es un un entero que resume el estado del objeto.
    hashCode() es la clave para que las colecciones de dispersión funcionen correctamente y rápido;
    por eso debe implementarse junto con equals() y con una distribución uniforme de valores.
     */

    //46.-¿Cómo convertir un array de String en lista (List<String>)?

    String[] array = {"uno", "dos", "tres"};

    List<String> listaInmutable = Arrays.asList(array);

    List<String> listaMutable = new ArrayList<>(Arrays.asList(array));

    // 47.- ¿Cómo ordenar una lista de objetos en Java?

    @Getter
    @EqualsAndHashCode
    public static class Persona implements Comparable<Persona>, Serializable {
        private static final long serialVersionUID = 1L;

        private final String nombre;
        private final int edad;

        public Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        @Override
        public int compareTo(Persona otra) {
            return Integer.compare(this.edad, otra.edad);
        }
    }

    @Test
    public void ejercicio47_ordenarPersonas() {
        List<Persona> personas = new ArrayList<>(List.of(
                new Persona("Luis", 25),
                new Persona("Ana", 30),
                new Persona("Carlos", 20)
        ));

        // Usa el orden natural definido en compareTo (edad asc)
        Collections.sort(personas);               // o personas.sort(null);

        assertEquals(20, personas.get(0).getEdad()); // Carlos (20) queda primero
        assertEquals(25, personas.get(1).getEdad());
        assertEquals(30, personas.get(2).getEdad());
    }

    // 48.- ¿Cómo ordenar un HashMap por valores?
    @Test
    public void ejercicio48() {

        // HashMap original (orden impredecible)
        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("A", 3);
        mapa.put("B", 1);
        mapa.put("C", 2);

        // 1) Convertir a stream de entries y ordenar por value ascendente
        Map<String, Integer> ordenado = mapa.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())           // ascendente
                .collect(
                        LinkedHashMap::new,                         // 2) contenedor ordenado
                        (m, e) -> m.put(e.getKey(), e.getValue()),
                        LinkedHashMap::putAll
                );

        // ===== Verificación sencilla =====
        Iterator<Integer> it = ordenado.values().iterator();
        assertEquals(1, it.next());  // primero el valor más pequeño (B=1)
        assertEquals(2, it.next());  // luego (C=2)
        assertEquals(3, it.next());  // por último (A=3)

        // (opcional) imprimir para ver el orden
        ordenado.forEach((k, v) -> log.info("{} -> {}", k, v));
    }

    //49.-¿Qué es un LinkedHashMap y cuándo usarlo?

    /*

    LinkedHashMap es una implementación de Map que combina:
    Usa LinkedHashMap cuando:
    Quieres velocidad HashMap + orden predecible (inserción o acceso).
    Necesitas una estructura sencilla para cachés LRU.
    Requieres mantener la secuencia original de datos (por ejemplo, leer propiedades de un archivo y escribirlas en el mismo orden).

    Map<String, Integer> lruCache =
    new LinkedHashMap<>(16, 0.75f, true) {
        private static final int MAX = 100;
        @Override
        protected boolean removeEldestEntry(Map.Entry<?,?> e) {
            return size() > MAX;   // elimina la entrada más antigua al exceder MAX
        }
    };

     */

    //50.-¿Qué es un TreeMap y cuándo usarlo?

    /*TreeMap<K,V> es una implementación de la interfaz Map basada en un árbol rojo-negro (balanced binary search tree).
    En síntesis, TreeMap es la opción cuando el orden de las claves y las operaciones por rango son requisitos fundamentales de tu aplicación.

    Map<Person, Integer> porApellido =
    new TreeMap<>(Comparator
        .comparing(Person::getApellido)
        .thenComparing(Person::getNombre));

     */

    //51.-¿Cómo manejar excepciones con try-catch?

    public static int safeDivide(int a, int b) {
        try {
            return a / b;                    // puede lanzar ArithmeticException
        } catch (ArithmeticException ex) {   // se captura la excepción
            log.warn("División por cero: {}", ex.getMessage());
            return 0;                        // valor por defecto si hay error
        }
    }

    @Test
    public void ejercicio51_tryCatch() {
        assertEquals(5, safeDivide(10, 2));  // caso normal
        assertEquals(0, safeDivide(10, 0));  // la excepción se maneja y devuelve 0
    }

    //52.-¿Qué es un finally y cuándo se ejecuta?

    /*

    try {
        // Código que puede lanzar excepción
    } catch (Exception e) {
        // Manejo de la excepción
    } finally {
        // Siempre se ejecuta
    }

    ¿Cuándo se ejecuta?
        Situación en el try	¿Se ejecuta finally?
        No ocurre ninguna excepción	Sí
        Ocurre y se captura una excepción en el catch	Sí
        Ocurre una excepción no capturada y el método propaga	Sí
        Se hace return, break o continue dentro del try	Sí, antes de salir
        Se lanza System.exit() o la JVM se apaga bruscamente	No
        La propia cláusula finally lanza una excepción	Se ejecuta hasta el punto donde lanza la nueva excepción

     */

    //53.-¿Qué es la diferencia entre excepción chequeada y no chequeada?

    /*
    Chequeada: cuando el llamador razonablemente puede tomar medidas para corregir o informar (p.ej. falta un archivo, error de red).

    No chequeada: para violaciones de contrato o errores lógicos que indican bugs y deberían arreglarse, no capturarse (p.ej. índices fuera de rango, nulos inesperados).
     */

    //54.-¿Qué hace el método toString()?
    /*
    toString() sirve para transformar un objeto en un texto descriptivo; sobrescribirlo mejora la legibilidad y facilita el diagnóstico de tu código.

     */

    //55.-¿Cómo imprimir el valor de una variable usando System.out.println?
    /*
    int edad = 25;
    System.out.println(edad);
     */

    //56.-¿Qué es un constructor en Java?
    /*
    Un constructor es un bloque especial de código dentro de una clase cuya función es inicializar las nuevas instancias de esa clase.
     */

    // 57.- ¿Cómo crear un constructor con parámetros?
    public static class Rectangulo {
        private final int ancho;
        private final int alto;

        // Constructor con dos parámetros que inicializan los campos
        public Rectangulo(int ancho, int alto) {
            this.ancho = ancho;
            this.alto = alto;
        }

        public int getAncho() {
            return ancho;
        }

        public int getAlto() {
            return alto;
        }
    }

    @Test
    public void ejercicio57_constructorConParametros() {
        Rectangulo r = new Rectangulo(3, 4);   // se pasan los valores al constructor
        assertEquals(3, r.getAncho());
        assertEquals(4, r.getAlto());
    }

    //58.-¿Qué es la herencia en Java?
    /*
    La herencia es un pilar de la programación orientada a objetos que permite definir una clase nueva (subclase o clase hija)
    reutilizando y ampliando el código de una clase existente (superclase o clase padre)

     */
    //59.-¿Cómo sobrescribir un método de una clase padre?

    public static class Animal {
        /**
         * Método de la clase padre
         */
        public String hablar() {
            return "Sonido genérico";
        }
    }

    public static class Perro extends Animal {
        /**
         * Sobrescritura: mismo nombre, misma firma.
         * La anotación @Override avisa al compilador
         * si la firma no coincide.
         */
        @Override
        public String hablar() {
            return "Guau";
        }
    }

    @Test
    public void ejercicio58_override() {
        Animal a = new Perro();              // polimorfismo
        assertEquals("Guau", a.hablar());    // se ejecuta la versión sobrescrita
    }

    //60.-¿Qué es el polimorfismo?
    /*
    En resumen, polimorfismo permite que referencias de una clase base o interfaz apunten a objetos de diferentes clases derivadas,
    y que las invocaciones de métodos se resuelvan dinámicamente, otorgando flexibilidad y modularidad al diseño del software.

     */

    //61.-¿Qué es una clase abstracta?
    /*
    Una clase abstracta es un tipo de clase que:
    Se declara con la palabra clave abstract y no puede instanciarse directamente.
    Sirve como plantilla; define atributos y métodos comunes para sus subclases.
    Puede contener métodos abstractos (sin cuerpo) que obligan a las subclases a implementar su comportamiento, además de métodos concretos que se heredan tal cual o se pueden sobrescribir.
    Facilita la reutilización de código y garantiza que las subclases cumplan ciertos contratos, evitando crear objetos de un tipo conceptualmente incompleto.

     */
    //62.-¿Qué es una interfaz en Java?
    /*
    Una interfaz en Java es un tipo de contrato que declara un conjunto de métodos (y, desde Java 8, también puede incluir métodos default, static y constantes public static final).
    Las clases que la implementan se comprometen a proporcionar la lógica de esos métodos. Las interfaces:

    + No se pueden instanciar directamente.

    + Permiten la herencia múltiple de comportamiento (una clase puede implementar varias interfaces).

    + Separan la definición del “qué se debe hacer” (la interfaz) del “cómo se hace” (la clase implementadora), favoreciendo el desacoplamiento y la programación orientada a abstracciones.

     */

    //63.-¿Cómo implementar una interfaz?

    interface Conducible {
        void conducir();          // método abstracto
    }

    class Coche implements Conducible {
        @Override
        public void conducir() {
            System.out.println("El coche está en marcha");
        }
    }

    //64.-¿Qué es una clase anónima?
    /*
    Una clase anónima es una definición in-line de una subclase o de una implementación de interfaz que:

    No tiene nombre propio: se declara y se instancia en la misma expresión, tras la palabra new.

    Vive dentro de un bloque de código (normalmente un método) y su alcance se limita a ese punto.

    Sirve para proporcionar rápidamente una única implementación concreta —generalmente corta y de un solo uso— sin crear un archivo o una clase con nombre separado.

    Puede acceder a miembros de la clase envolvente y a variables locales marcadas como effectively final.

     */

    //65.-¿Qué es un método estático?
    /*
    Un método estático es un método asociado a la clase en sí y no a sus instancias. Se declara con la palabra clave static
    y se invoca usando el nombre de la clase (Clase.metodo()), sin necesidad de crear un objeto.

    Características principales:

    No puede acceder directamente a campos ni métodos de instancia (solo a otros miembros static).

    Suele utilizarse para utilidades o funciones de propósito general, contadores globales, fábricas estáticas, etc.

    Existe una única copia del método en memoria, compartida por todas las instancias (o incluso sin instancias)

     */

    //66.-¿Qué es una variable estática?
    /*
    Una variable estática (campo static) es un atributo que pertenece a la clase y no a cada objeto individual.
    Solo existe una única copia en la JVM, compartida por todas las instancias (o incluso sin instancias).
    Se inicializa una vez cuando la clase se carga y puede accederse mediante el nombre de la clase.
    Se usa para estado o constantes que deben ser globales a todos los objetos de esa clase.

     */

    //67.-¿Cuál es la diferencia entre variable estática y variable de instancia?

    /*

    Variable estática

    * Pertenece a la clase en sí, no a sus objetos.

    * Solo hay una copia compartida por todas las instancias.

    * Se inicializa cuando la clase se carga en la JVM.

    * Se accede normalmente como Clase.nombreVariable.

    Variable de instancia

    * Pertenece a cada objeto concreto de la clase.

    * Cada instancia tiene su propia copia de la variable.

    * Se inicializa cuando se crea cada objeto.

    * Se accede a través de la referencia al objeto, p. ej. objeto.nombreVariable.

     */

    //68.-¿Qué es el modificador final?
    /*
    El modificador final en Java:
    Sobre variables: hace que, una vez asignado, su valor no pueda cambiar (inmutabilidad de la referencia).
    Sobre métodos: impide que sean sobrescritos por las subclases (asegura que su implementación permanezca fija).
    Sobre clases: evita que puedan heredarse (no admite subclases).
     */
    //69.-¿Cómo evitar la modificación de una variable?
    final int edad = 30;

    //70.-¿Qué es un bloque estático?
    /*
    Un bloque estático en Java es un bloque de código que se ejecuta una sola vez cuando la clase se carga en memoria,
    antes de que se cree cualquier instancia o se invoque cualquier método estático

     */

    static {
        // código de inicialización estática
    }

    //71.-¿Cómo usar instanceof para verificar el tipo de un objeto?

    @Test
    public void ejercicio71() {

        Object obj = List.of(1, 2, 3);        // devuelve una List inmutable
        assertTrue(obj instanceof List<?>, "Debe ser una List");
        assertFalse(obj instanceof ArrayList<?>, "No debe ser ArrayList");

        // (Opcional) si quieres castear y usar el objeto:
        if (obj instanceof List<?>) {
            List<?> lista = (List<?>) obj;
            log.info("Tamaño de la lista: {}", lista.size());
        }
    }

    //72.-¿Cómo convertir un Object a Integer?
    @Test
    public void ejercicio72() {

        Object obj = Integer.valueOf(42);   // podría venir de cualquier parte

        // 1) Verificamos que realmente sea un Integer
        assertTrue(obj instanceof Integer, "El objeto debe ser Integer");

        // 2) Cast seguro
        Integer numero = (Integer) obj;

        // 3) Podemos usarlo con total confianza
        assertEquals(42, numero.intValue());
        log.info("Número convertido: {}", numero);
    }

    //73.-¿Qué es un ArrayList?
    /*
    ArrayList es una estructura de datos de la librería estándar (java.util) que implementa la interfaz List.
    En esencia, es un arreglo dinámico: mantiene los elementos en orden de inserción, permite acceder por índice en tiempo O(1) y crece (o reduce)
    automáticamente su capacidad cuando añades o eliminas elementos, sin que tú tengas que gestionar el tamaño del arreglo subyacente.

     */
    //74.-¿Qué diferencia hay entre ArrayList y LinkedList?
    /*
    En síntesis, ArrayList es la mejor opción cuando necesitas acceso rápido por índice y las modificaciones son poco frecuentes;
    LinkedList resulta ventajosa cuando predominan las inserciones/eliminaciones dispersas y no dependes del acceso aleatorio.
     */

    //75.-¿Cómo añadir elementos a un LinkedList?

    @Test
    public void ejercicioLinkedListAdd() {
        LinkedList<String> lista = new LinkedList<>();

        lista.add("A");                // Agrega al final
        lista.addFirst("Inicio");      // Agrega al principio
        lista.addLast("Fin");          // Agrega al final
        lista.add(1, "Medio");         // Inserta en la posición 1

        // Verificación del orden esperado
        assertEquals("Inicio", lista.get(0));
        assertEquals("Medio", lista.get(1));
        assertEquals("A", lista.get(2));
        assertEquals("Fin", lista.get(3));
        assertEquals(4, lista.size());
    }

    //76.-¿Cómo eliminar elementos de una lista mientras la recorres?

    @Test
    public void ejercicioEliminarMientrasSeRecorre() {
        List<String> nombres = new ArrayList<>(List.of("Ana", "Luis", "Pedro", "Ana"));

        Iterator<String> it = nombres.iterator();
        while (it.hasNext()) {
            if (it.next().equals("Ana")) {
                it.remove();  // Elimina de forma segura durante la iteración
            }
        }

        // Verifica que ya no contiene "Ana"
        assertFalse(nombres.contains("Ana"));
        assertEquals(2, nombres.size());  // Solo quedan "Luis" y "Pedro"
    }

    //77.-¿Cómo convertir un List a un array?

    @Test
    public void convertirListAArray() {
        List<String> lista = new ArrayList<>();
        lista.add("uno");
        lista.add("dos");
        lista.add("tres");

        // Conversión a array
        String[] array = lista.toArray(new String[0]);

        // Verificación
        assertEquals(3, array.length);
        assertArrayEquals(new String[]{"uno", "dos", "tres"}, array);
    }

    //78.-¿Cómo manejar NullPointerException?

    @Test
    public void manejarNullPointerException() {
        String texto = null;
        String resultado;

        try {
            resultado = texto.toUpperCase(); // esto lanza NullPointerException
            fail("Se esperaba NullPointerException");
        } catch (NullPointerException e) {
            resultado = "valor por defecto";
        }

        assertEquals("valor por defecto", resultado);
    }

    //79.-¿Qué es un HashSet y cuándo usarlo?
    /*
    Un HashSet es una colección que no permite elementos duplicados y no mantiene orden.
    Se usa cuando necesitas almacenar valores únicos con operaciones rápidas de búsqueda, inserción o eliminación.
    */

    //80.-¿Cómo iterar sobre un HashSet?

    @Test
    public void ejercicioIterarHashSet() {
        Set<String> nombres = new HashSet<>(Set.of("Ana", "Luis", "Pedro"));

        StringBuilder resultado = new StringBuilder();
        for (String nombre : nombres) {
            resultado.append(nombre).append(" ");
        }

        assertTrue(resultado.toString().contains("Ana"));
        assertTrue(resultado.toString().contains("Luis"));
        assertTrue(resultado.toString().contains("Pedro"));
    }

    //81.-¿Cómo eliminar elementos duplicados de una lista usando HashSet?

    @Test
    public void ejercicioEliminarDuplicadosConHashSet() {
        List<String> nombres = new ArrayList<>(List.of("Ana", "Luis", "Ana", "Pedro", "Luis"));

        Set<String> sinDuplicados = new HashSet<>(nombres);

        List<String> resultado = new ArrayList<>(sinDuplicados);

        assertEquals(3, resultado.size());
        assertTrue(resultado.contains("Ana"));
        assertTrue(resultado.contains("Luis"));
        assertTrue(resultado.contains("Pedro"));
    }

    //82.-¿Qué es la inmutabilidad?
    /*
    La inmutabilidad es una propiedad de los objetos cuyo estado no puede cambiar después de ser creado.
    Una vez asignados sus valores, no se pueden modificar.

     */

    //83.-¿Cómo hacer una clase inmutable?

    @Getter
    public final class Person {
        private final String nombre;
        private final int edad;

        public Person(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }
    }

    //84.-¿Qué es el método compareTo()?
    /*
    compareTo() compara dos objetos para ordenarlos.
    Devuelve un número que indica si es menor, igual o mayor.
     */

    //85.-¿Cómo implementar Comparable en una clase?

    @Getter
    @AllArgsConstructor
    public static class Personast implements Comparable<Persona> {
        private final String nombre;
        private final int edad;

        @Override
        public int compareTo(Persona otra) {
            return Integer.compare(this.edad, otra.edad);
        }
    }

    //86.-¿Qué es un Comparator?
    /*
    Un Comparator es una interfaz funcional que define el método compare(T o1, T o2) para establecer un orden personalizado entre dos objetos.
    Se usa cuando necesitas ordenar colecciones de manera diferente a su orden natural (Comparable), por ejemplo con Collections.sort() o Stream.sorted().

     */
    //87.-¿Cómo ordenar una lista usando Comparator?

    @Test
    public void ejercicio87() {
        List<Persona> personas = new ArrayList<>(List.of(
                new Persona("Luis", 25),
                new Persona("Ana", 30),
                new Persona("Carlos", 20)
        ));

        // 1) Definir un Comparator: aquí comparamos por nombre ascendente
        Comparator<Persona> porNombre = Comparator.comparing(Persona::getNombre);

        // 2a) Ordenar con List.sort (Java 8+)
        personas.sort(porNombre);

        assertEquals("Ana", personas.get(0).getNombre());
        assertEquals("Carlos", personas.get(1).getNombre());
        assertEquals("Luis", personas.get(2).getNombre());
    }

    //88.-¿Qué es la serialización en Java?
    /*
    Serialización es el proceso de convertir un objeto en una secuencia de bytes (o texto) para poder almacenarlo o enviarlo;
    luego esos bytes se “des-serializan” para recrear el mismo objeto en otro momento o lugar.

     */

    //89.-¿Cómo serializar un objeto?

    @Test
    public void testSerializacionYDeserializacion() throws IOException, ClassNotFoundException {
        // Creamos el objeto original
        Persona original = new Persona("Ana", 30);

        // --- SERIALIZACIÓN a un array de bytes ---
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(original);
        }

        // --- DESERIALIZACIÓN desde el array de bytes ---
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        Persona deserializada;
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            deserializada = (Persona) ois.readObject();
        }

        // --- ASSERTS para verificar que la deserialización restaura el objeto correctamente ---
        assertNotSame(original, deserializada, "Debe ser una instancia distinta");
        assertEquals(original, deserializada, "Los datos deben coincidir tras la deserialización");
    }

    //90.-¿Qué es la reflexión?

    /*
    La reflexión en Java es una API que, en tiempo de ejecución, permite inspeccionar y manipular la estructura interna de clases, métodos, campos y constructores (incluso los privados),
    creando objetos e invocando métodos cuyos nombres solo se conocen dinámicamente; aunque otorga gran flexibilidad para frameworks,
    pruebas y mapeos, su uso conlleva penalizaciones de rendimiento y posibles riesgos de seguridad al romper el encapsulamiento.

     */

    //91.-¿Cómo obtener el nombre de una clase en tiempo de ejecución?

    /* 1. A partir de una instancia:
    Object objeto = new Pregunta1Test();
    String nombreCompleto = objeto.getClass().getName();      // paquete.MiClase
    String nombreSimple = objeto.getClass().getSimpleName(); // MiClase

    // 2. Directamente desde la clase:
    String nombreDirecto = Pregunta1Test.class.getName();      // paquete.MiClase
    String simpleDirecto = Pregunta1Test.class.getSimpleName(); // MiClase


     */

    //92.-¿Cómo obtener los métodos de una clase con reflexión?

    // Method[] todos = clazz.getDeclaredMethods();
    // Method[] publicos = clazz.getMethods();

    //93.-¿Qué es una expresión lambda?

    /*
    es una función anónima escrita con la sintaxis parámetros -> cuerpo.
    Sirve para pasar comportamiento (código) como dato—por ejemplo, a métodos que reciben un Predicate, Comparator, Runnable, etc.—de forma más breve que con clases anónimas.
     */

    //94.-¿Cómo crear una expresión lambda para una interfaz funcional?

    @FunctionalInterface
    interface Operacion {
        int aplicar(int a, int b);
    }

    @Nested
    public class LambdaTest {

        @Test
        public void testExpresionesLambdaParaOperacion() {
            // Lambda para sumar
            Operacion suma = (x, y) -> x + y;
            assertEquals(8, suma.aplicar(3, 5), "3 + 5 debe ser 8");

            // Lambda para obtener el máximo
            Operacion maximo = (x, y) -> {
                return x >= y ? x : y;
            };
            assertEquals(5, maximo.aplicar(3, 5), "El máximo entre 3 y 5 debe ser 5");
        }
    }

    //95.-¿Qué es un Stream en Java?
    /*
    Un Stream en Java es una secuencia de datos tratada de forma declarativa en pipeline (por ejemplo map, filter, reduce), p
    erezosa hasta una operación terminal (como collect o forEach).
    Permite procesar colecciones de manera concisa, secuencial o paralela, sin gestionar manualmente bucles ni hilos.

     */

    //96.-¿Cómo filtrar una lista usando Stream?

    public class StreamFilterExample {
        public static void main(String[] args) {
            List<String> nombres = List.of("Ana", "Luis", "Pedro", "Lucía");

            // Queremos quedarnos solo con los que empiezan por 'L'
            List<String> empiezanPorL = nombres.stream()
                    .filter(s -> s.startsWith("L"))
                    .collect(Collectors.toList());

            System.out.println(empiezanPorL); // [Luis, Lucía]
        }

    }

    //97.-¿Cómo mapear una lista a otra con Stream?

    public class StreamMapExample {
        public static void main(String[] args) {
            List<String> nombres = List.of("Ana", "Luis", "Pedro");

            // 1) Stream + 2) map cada String a su longitud 3) collect en List<Integer>
            List<Integer> longitudes = nombres.stream()
                    .map(String::length)
                    .collect(Collectors.toList());

            System.out.println(longitudes); // [3, 4, 5]
        }
    }

    //98.-¿Qué es la programación funcional?
    /*
    La programación funcional es un estilo que trata el código como una composición de funciones puras (sin efectos secundarios) y datos inmutables,
    favoreciendo un enfoque declarativo y facilitando el razonamiento y la paralelización.
     */

    //99.-¿Qué son las anotaciones en Java?
    /*
    Las anotaciones en Java son metadatos que puedes colocar sobre clases, métodos, campos o parámetros (usando la sintaxis @NombreAnotación)
    para aportar información adicional al compilador o frameworks en tiempo de compilación o ejecución. Sirven, por ejemplo,
    para indicar comportamientos especiales (@Override, @Deprecated), configurar librerías (Spring, JPA)
    o generar código automáticamente, y pueden procesarse vía reflexión según su política de retención (SOURCE, CLASS, RUNTIME).
     */

    //100.-¿Cómo crear una anotación personalizada?

    @Retention(RetentionPolicy.RUNTIME)    // alcance en tiempo de ejecución
    @Target({ElementType.TYPE, ElementType.METHOD})  // dónde puede usarse
    public @interface MiAnotacion {
        String value();                   // elemento obligatorio

        String autor() default "anonimo"; // elemento opcional con valor por defecto
    }

    //101.-¿Qué es el tipo de dato primitivo boolean?
    /*
    El tipo de dato primitivo boolean en Java es un tipo sencillo que solo puede tomar dos valores: true o false. S
    Se utiliza para representar condiciones y controlar el flujo de ejecución (por ejemplo en if, while o expresiones lógicas)
     */

    //102.-¿Cómo convertir un boolean a String?

    boolean flag = true;

    String s1 = Boolean.toString(flag);

    String s2 = String.valueOf(flag);

    //103.-¿Qué pasa si haces cast de double a int?
    /*
    Al castear un double a int en Java se descarta la parte decimal (truncamiento hacia cero),
    sin redondeo; valores especiales como NaN pasan a 0 y los que superan el rango se ajustan a Integer.MAX_VALUE o Integer.MIN_VALUE.
     */

    //104.-¿Qué es un método static?
    /*
    es uno que pertenece a la clase en sí y no a sus instancias, de modo que puedes invocarlo como Clase.metodo() sin crear un objeto.
    No puede acceder directamente a campos o métodos de instancia (solo a otros estáticos) y suele emplearse para funciones utilitarias
    o de propósito general compartidas por todas las instancias.
     */

    //105.-¿Cómo acceder a un método estático?

    public class Util {
        public static String saludar(String nombre) {
            return "¡Hola, " + nombre + "!";
        }
    }

    // En cualquier otro sitio:
    String mensaje = Util.saludar("Ana");

    //106.-¿Qué es la encapsulación?
    /*
    La encapsulación es un principio de la programación orientada a objetos que consiste en ocultar los detalles internos de una clase (su estado)
    usando modificadores de acceso (private, protected, public) y exponer únicamente una interfaz controlada (métodos getters/setters u otras operaciones);
    de este modo se protege el objeto contra usos indebidos y facilita el mantenimiento y la evolución del código.
     */

    //107.-¿Cómo usar getters y setters?

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Auto {
        private String marca;
        private int puertas;
    }

    public class Main {
        public static void main(String[] args) {
            Auto p = new Auto();
            p.setMarca("Volvo");
            p.setPuertas(4);

            System.out.println(p.getMarca());
            System.out.println(p.getPuertas());
        }
    }

    //108.-¿Qué es una clase interna?
    /*
    Es una clase declarada dentro de otra clase. Si es no estática (inner class), cada instancia lleva implícita una referencia al objeto exterior y puede acceder a sus miembros privados;
    si es estática (nested static class), no guarda esa referencia y se comporta como una clase top-level anidada en el ámbito de la exterior.
     */

    //109.-¿Qué es un Thread en Java?
    /*
    Ss una unidad de ejecución independiente dentro de un mismo proceso, representada por la clase java.lang.Thread.
    Cada hilo ejecuta su propio método run() de forma concurrente con los demás, y se inicia llamando a start().
    Sirve para paralelizar tareas dentro de una misma aplicación sin necesidad de levantar procesos separados.
     */

    //110.-¿Cómo crear un hilo usando Runnable?


    @Test
    public void ejercicio110() {
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                System.out.println("¡Hola desde el hilo con clase anónima!");
            }
        };
        Thread hilo = new Thread(tarea, "Hilo-Anonimo");
        hilo.start();

    }

    //111.-¿Qué es la sincronización?
    /*
    Es un mecanismo que garantiza que, cuando varios hilos acceden a un mismo recurso compartido,
    solo uno a la vez pueda ejecutar la sección crítica de código, evitando condiciones de carrera y datos inconsistentes.
    Se logra principalmente con la palabra clave synchronized (sobre métodos o bloques) o mediante las clases del paquete java.util.concurrent.locks (como ReentrantLock),
    que coordinan el acceso y liberan el bloqueo al terminar la operación.
     */

    //112.-¿Qué es un ConcurrentHashMap?
    /*
    ConcurrentHashMap es un Map seguro para hilos que permite múltiples lecturas y escrituras concurrentes sin bloquear
    todo el mapa, gracias a bloqueos de nivel fino y estructuras internas optimizadas.

     */

    //113.-¿Cómo leer un archivo en Java?


    public void leeArchivo() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("ruta/al/archivo.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        }
    }

    //114.-¿Cómo escribir en un archivo en Java?

    public void escribirConWriteString() throws IOException {
        Path ruta = Path.of("salida.txt");
        String contenido = "Texto completo\nen varias líneas";
        Files.writeString(ruta, contenido);
    }

    //115.-¿Qué es el bloque try-with-resources?
    /*
    Es un try especial (desde Java 7) que declara recursos AutoCloseable para cerrarlos automáticamente al terminar el bloque, sin finally.

     */

    //116.-¿Qué son las colecciones en Java?
    /*
    Conjunto de interfaces y clases del paquete java.util (como List, Set, Queue y Map) diseñadas para almacenar y gestionar grupos de objetos de forma dinámica,
    ofreciendo operaciones estándar de inserción, acceso, búsqueda y ordenación.
     */

    //117.-¿Qué diferencia hay entre una interfaz y una clase abstracta?
    /*
    Una interfaz define solo un contrato: declara métodos (abstractos, y desde Java 8 también default o static) sin estado ni constructores, y permite herencia múltiple.
    Una clase abstracta puede tener estado (campos), constructores y métodos con implementación, pero solo admite herencia simple, usándose para compartir código base entre subclases.
     */

    //118.-¿Qué es el operador ternario?
    /*
    El operador ternario en Java es un condicional compacto que, dado un booleano, devuelve uno de dos valores según la expresión sea true o false.

     */
    //119.-¿Cómo usar el operador ternario para asignar un valor?
    /*
    int a = 5, b = 8;
    int max = (a > b) ? a : b;
     */

    //120.-¿Cómo declarar un arreglo de enteros?
    /*

    1) Declaración sin inicializar
   int[] arreglo;
     2) Declaración con tamaño fijo (todos los elementos valdrán 0)
    int[] arreglo = new int[5];
     3) Declaración e inicialización literal
    int[] arreglo = {1, 2, 3, 4, 5};
     */

    //121.-¿Cómo obtener la longitud de un arreglo?

    int[] numeros = {1, 2, 3, 4, 5};
    int tamanio = numeros.length;

    //122.¿Cómo inicializar un arreglo con valores?

    // 1) Sintaxis literal (inferencia de tamaño automática)
    int[] numerost = {1, 2, 3, 4, 5};

    // 2) Con “new” explícito
    int[] masNumeros = new int[]{10, 20, 30};


    //123.--¿Qué es un Map en Java?
    /*
    es una colección que almacena pares clave → valor, donde cada clave única se asocia a un valor.
     */

    //124.-¿Qué tipos de Map existen en Java?
    /*
    Tipos de MAP como HashMap, TreeMap, LinkedHashMap,Hashtable,ConcurrentHashMap,WeakHashMap,  cada una con diferentes garantías de orden y rendimiento.

     */
    //125.-¿Cómo usar un WeakHashMap?

    public class EjemploWeakHashMap {
        public static void main(String[] args) {
            Map<Object, String> cache = new WeakHashMap<>();

            Object clave = new Object();
            cache.put(clave, "datos asociados");

            System.out.println("Antes GC: " + cache); // {java.lang.Object@...=datos asociados}

            // Eliminamos la referencia fuerte a la clave
            clave = null;
            System.gc(); // sugerimos recolector

            // Tras un rato, la entrada puede haber desaparecido
            System.out.println("Después GC: " + cache); // {}
        }
    }

    //126.-¿Qué es el autoboxing con ejemplos?
    /*
    El autoboxing es la conversión automática que hace el compilador de Java entre tipos primitivos y sus clases envolventes (wrappers) al asignar o pasar valores.
     */
    // Autoboxing: de int a Integer
    Integer enteroObj = 42;     // antes: Integer enteroObj = Integer.valueOf(42);

    // Autoboxing: de double a Double
    Double dobleObj = 3.14;     // antes: Double dobleObj = Double.valueOf(3.14);

    // Autoboxing: de boolean a Boolean
    Boolean flagObj = true;     // antes: Boolean flagObj = Boolean.valueOf(true);

    Integer i = 100;
    int primitivo = i;          // unboxing implícito, antes: i.intValue()

    Double d = 2.718;
    double valors = d;            // unboxing implícito, antes: d.doubleValue()

    //127.-¿Qué es un paquete (package) en Java?
    /*
    Un paquete en Java es un espacio de nombres que agrupa clases e interfaces relacionadas, evitando colisiones de nombres y organizando el código en carpetas
     */
    //128.-¿Para qué sirve el modificador protected?
    /*
    El modificador protected permite que un miembro (campo o método) sea accesible desde:

   + Su propia clase,
   + Cualquier clase del mismo paquete,
   + Cualquier subclase, incluso si está en otro paquete,
   + pero lo oculta a clases no relacionadas que estén fuera de ese paquete o jerarquía.
     */

    //129.-¿Qué es el polimorfismo en tiempo de ejecución?
    /*
    El polimorfismo en tiempo de ejecución es cuando tienes una referencia de la clase padre y, al llamar a un método,
    Java decide en ejecución qué versión sobrescrita de la subclase concreta debe ejecutar.
     */
    //130.-¿Qué es el polimorfismo en tiempo de compilación?
    /*
    Es la sobrecarga de métodos (y constructores): el compilador elige, antes de ejecutar, qué versión llamar según el número y tipo de parámetros de la invocación.
     */

    //131.-¿Qué es el método super?
    /*
    La palabra clave super no es un método, sino un mecanismo para referirse a la clase padre desde una subclase.
    public Subclase(int x) {
    super(x); // invoca el constructor de la clase padre con parámetro x
}
     */

    //132.-¿Cómo llamar al constructor padre desde el hijo?

    class Padre {
        Padre(int valor) { /* ... */ }
    }

    class Hijo extends Padre {
        Hijo(int valor, String nombre) {
            super(valor);       // llama al constructor de Padre(int)
        }
    }

    //133.-¿Qué es la sobrecarga de métodos?
    /*
    Es la posibilidad de definir en una misma clase varios métodos con el mismo nombre pero distinta lista de parámetros (tipo, número u orden).
    El compilador elige cuál invocar en tiempo de compilación según los argumentos que le pases.
     */

    //134.-¿Qué es la sobreescritura de métodos?
    /*
    Sobrescribir un método es cuando una clase hija define un método con el mismo nombre y parámetros que su padre pero con un cuerpo distinto.
    Al llamar al método en un objeto de la hija, Java ejecuta su versión en vez de la del padre.
     */

    //135.-¿Qué es el instanceof y cómo se usa?
    /*El operador instanceof en Java comprueba en tiempo de ejecución si una referencia apunta a un objeto de un tipo concreto (clase o interfaz).
    Devuelve true si la expresión no es null y la instancia coincide o hereda/implementa ese tipo. Por ejemplo:
    if (objeto instanceof MiClase) {
    MiClase m = (MiClase) objeto;
    // …
        }
     */

    //136.-¿Cómo manejar excepciones múltiples en un bloque catch?
    /*
    try {
    // …
        } catch (IOException e) {
            // manejo de I/O
        } catch (SQLException e) {
            // manejo de BD
        }
     */

    //137.-¿Qué es el try-catch-finally?
    /*
    El try-catch-finally es una estructura de control de Java para gestionar errores en tiempo de ejecución:
     */

    //138.-¿Qué es un assert?
    /*
    assert es una instrucción que verifica en tiempo de ejecución que una condición sea verdadera;
    si no lo es, lanza un AssertionError. Por defecto está desactivada y se activa arrancando la JVM con -ea.
     */

    //139.-¿Cómo convertir un número entero a binario?

    int n = 42;
    String binario = Integer.toBinaryString(n);

    //140.-¿Cómo convertir un número binario a decimal?

    String binariot = "101010";
    int decimal = Integer.parseInt(binariot, 2);

    //141.-¿Cómo concatenar Strings en Java?
    String s = "Hola" + " Mundo"; // "Hola Mundo"
    String f = "Hola".concat(" Mundo"); // "Hola Mundo"

    //142.-¿Qué es la clase StringBuilder y cuándo usarla?
    /*
    Es una clase de la biblioteca estándar de Java que representa una secuencia de caracteres mutable: puedes añadir, insertar o borrar texto sin crear nuevas instancias cada vez.
    Se usa cuando vas a hacer muchas concatenaciones o manipulaciones de cadenas (por ejemplo, dentro de bucles o al construir textos grandes), porque es mucho más eficiente que ir sumando String inmutables con el operador +.
     */

    //143.-¿Cuál es la diferencia entre String, StringBuilder y StringBuffer?
    /*
    String: inmutable; cada cambio crea un nuevo objeto.

    StringBuilder: mutable y rápido, pero no seguro para hilos.

    StringBuffer: mutable y sincronizado (thread-safe), con algo menos de rendimiento.
     */

    //144.-¿Cómo convertir una lista de objetos a un arreglo?

    List<Persona> lista = List.of(
            new Persona("Diego", 36),
            new Persona("Victor", 39)
    );

    Persona[] array1 = lista.toArray(new Persona[0]);

    //145.-¿Qué es el método clone()?
    /*
    clone() es un método de Object que devuelve una copia superficial (“shallow”) del objeto;
    para usarlo la clase debe implementar Cloneable y, al sobrescribir, llamar a super.clone().
     */

    //146.-¿Cómo evitar la clonación de un objeto?

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clonación no permitida.");
    }

    //147.-¿Qué es un Optional en Java 8?
    /*
    Optional en Java 8 es un contenedor que puede tener un valor o estar vacío.
    Se usa para evitar errores por null y dejar claro que un valor puede no estar presente. Ayuda a escribir código más seguro y limpio.
     */
    //148.-¿Cómo usar un Optional para evitar NullPointerException?

    private String obtenerNombre() {
        return null; // o puedes devolver "Diego" para probar
    }

    @Test
    public void testOptionalEvitaNullPointer() {
        Optional<String> nombre = Optional.ofNullable(obtenerNombre());
        String resultado = nombre.orElse("Desconocido");
        System.out.println(resultado);

        assertEquals("Desconocido", resultado); // si obtenerNombre() devuelve null
    }

    //149.-¿Qué es la inyección de dependencias?
    /*
    Es darle a una clase lo que necesita (por ejemplo, otro objeto o servicio) en lugar de que lo busque o lo cree por sí sola.
     */

    //150.-¿Qué es el patrón Singleton?
    /*
    El patrón Singleton es un patrón de diseño creacional que asegura que una clase tenga una única instancia y proporciona un punto de acceso global a esa instancia.
     */

    //151.-¿Cómo implementar un Singleton en Java?

    public static class MiSingleton {
        private static final MiSingleton instancia = new MiSingleton();

        private MiSingleton() {
        }

        public static MiSingleton getInstancia() {
            return instancia;
        }

        public void saludar() {
            System.out.println("¡Hola desde Singleton!");
        }
    }

    @Test
    public void testSingleton() {
        MiSingleton s = MiSingleton.getInstancia();
        s.saludar(); // Debe imprimir "¡Hola desde Singleton!"
    }

    //152.-¿Qué es la recursividad?
    /*
    La recursividad es una técnica de programación en la que una función se llama a sí misma para resolver un problema.
    Se usa cuando un problema puede dividirse en subproblemas más pequeños del mismo tipo.
     */

    //153.-¿Cómo escribir un método recursivo para calcular factorial?
    public class Matematica {

        public int factorial(int n) {
            if (n < 0) {
                throw new IllegalArgumentException("El factorial no está definido para números negativos.");
            }
            if (n == 0 || n == 1) {
                return 1; // caso base
            }
            return n * factorial(n - 1); // llamada recursiva
        }


        //154.-¿Qué es un método estático y cuándo usarlo?
    /*
    Es un método asociado a la clase en sí y no a sus instancias.
    Se declara con la palabra clave static y se invoca usando el nombre de la clase (Clase.metodo()),
    sin necesidad de crear un objeto.

      Cuándo usarlo:
    - Cuando el método **no depende del estado de una instancia** (no usa variables de instancia).
    - Para definir **métodos utilitarios o de ayuda**, como en clases Math o Collections.
    - Cuando necesitas un **punto de entrada** (por ejemplo, `main()`).
    - Para **operaciones comunes y reutilizables**, como conversiones o cálculos simples.
     */



    }
    //155.-¿Cómo funciona el recolector de basura (Garbage Collector)?
    @Nested
    class GarbageCollectorSimpleTest {
        @Test
        public void testGCRecoleccion() {
            WeakReference<Object> ref;
            {
                Object objeto = new Object();
                ref = new WeakReference<>(objeto);
                objeto = null;
            }

            System.gc();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            assertNull(ref.get(), "El objeto debe haber sido recolectado por el Garbage Collector");
        }
    }

    //156.-¿Qué es un enum con campos y métodos?
    /*
    Un enum en Java con campos y métodos es una forma avanzada de enumeración donde cada constante
    puede tener datos asociados (campos) y comportamiento (métodos), igual que una clase normal.
     */

    //157.-¿Cómo iterar sobre los valores de un enum?

    @Nested
    class EnumTest {

        // Definición del enum
        public enum Dia {
            LUNES,
            MARTES,
            MIERCOLES,
            JUEVES,
            VIERNES,
            SABADO,
            DOMINGO
        }

        @Test
        public void testIterarEnum() {
            // Recorremos todos los valores del enum
            for (Dia dia : Dia.values()) {
                assertNotNull(dia);  // Verificamos que cada valor no sea null
                System.out.println(dia); // Imprimimos cada día (opcional)
            }

            // Verificamos que tenga 7 valores
            assertEquals(7, Dia.values().length);
        }


    }

    //158.-¿Cómo usar anotaciones @Override?

    // Clase base
    class Animals {
        public String sonido() {
            return "sonido";
        }
    }

    // Clase que sobrescribe el método
    class Gato extends Animals {
        @Override
        public String sonido() {
            return "miau";
        }
    }

    @Nested
    class OverrideEjemploTest {

        @Test
        public void testOverride() {
            Animals miGato = new Gato();
            assertEquals("miau", miGato.sonido());
        }
    }

    //159.-¿Qué es el modificador volatile?
    /*
    El modificador volatile en Java asegura que todos los hilos vean siempre el valor más reciente de una variable.
    Se usa para garantizar la visibilidad de cambios en entornos multihilo.
     */

    //160.-¿Cómo definir una variable constante?
    public static final int EDAD_MINIMA = 18;

    //161.-¿Qué es el operador instanceof?
    /*
    El operador instanceof en Java se usa para verificar si un objeto es una instancia de una clase o implementa una interfaz.
    Devuelve true o false.
     */

    //162.-¿Cómo convertir un arreglo a lista?

    String[] arreglo = {"uno", "dos", "tres"};
    List<String> listaModificable = new ArrayList<>(Arrays.asList(arreglo));

    //163.-¿Cómo eliminar elementos duplicados de una lista?

    @Test
    public void ejercicio163_eliminarDuplicados() {
        List<String> listaConDuplicados = Arrays.asList("a", "b", "a", "c", "b");

        Set<String> conjuntoSinDuplicados = new HashSet<>(listaConDuplicados);
        List<String> listaSinDuplicados = new ArrayList<>(conjuntoSinDuplicados);

        assertEquals(3, listaSinDuplicados.size());
        assertTrue(listaSinDuplicados.containsAll(List.of("a", "b", "c")));
    }

    //164.-¿Cómo ordenar un arreglo?

    @Test
    public void ejercicio164() {
        int[] numeros = { 5, 2, 8, 1, 3 };
        Arrays.sort(numeros);
        assertArrayEquals(new int[] { 1, 2, 3, 5, 8 }, numeros);
    }

    //165.-¿Cómo ordenar una lista?

    @Test
    public void ejercicio165() {

        List<Integer> numeros = new ArrayList<>(List.of(5, 2, 8, 1, 3));

        Collections.sort(numeros);

        assertEquals(List.of(1, 2, 3, 5, 8), numeros);
    }

    //166.-¿Qué es la interfaz Iterable?
    /*
    Iterable es el contrato mínimo que debe cumplir un objeto para poder ser recorrido uno a uno; garantiza que puedes obtener los elementos de la colección en secuencia.
     */

    //167.-¿Cómo implementar la interfaz Iterable?

    public class MiContenedor implements Iterable<String> {
        private final String[] elementos = {"uno", "dos", "tres"};

        @Override
        public Iterator<String> iterator() {
            return new Iterator<>() {
                private int index = 0;

                @Override
                public boolean hasNext() {
                    return index < elementos.length;
                }

                @Override
                public String next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return elementos[index++];
                }
            };
        }

        @Nested
        class MiContenedorTest {
            @Test
            public void testIterable() {
                MiContenedor contenedor = new MiContenedor();
                StringBuilder resultado = new StringBuilder();

                for (String s : contenedor) {
                    resultado.append(s).append(" ");
                }

                assertEquals("uno dos tres ", resultado.toString());
            }
        }
    }

    //168.-¿Qué es el método forEach?
    /*
    forEach recorre todos los elementos de la colección o Stream y aplica la acción indicada a cada uno, sin que tengas que escribir el bucle.
     */

    //169.-¿Qué son las expresiones lambda?
    /*
    es una función anónima escrita con la sintaxis parámetros -> cuerpo.
    Sirve para pasar comportamiento (código) como dato—por ejemplo, a métodos que reciben un Predicate, Comparator, Runnable, etc.—de forma más breve que con clases anónimas.

     */

    //170.-¿Cómo implementar una interfaz funcional con lambda?

    @FunctionalInterface
    interface Operaciont {
        int aplicar(int a, int b);
    }

    @Nested
    public class LambdaTest2 {

        @Test
        public void testExpresionesLambdaParaOperaciont() {
            // Lambda para sumar
            Operaciont suma = (x, y) -> x + y;
            assertEquals(8, suma.aplicar(3, 5), "3 + 5 debe ser 8");

            // Lambda para obtener el máximo
            Operaciont maximo = (x, y) -> {
                return x >= y ? x : y;
            };
            assertEquals(5, maximo.aplicar(3, 5), "El máximo entre 3 y 5 debe ser 5");
        }
    }

    //171.-¿Qué es un método predicado (Predicate)?
    /*
    Predicate<T> es una interfaz funcional que recibe un argumento y devuelve true o false, usándose para expresar condiciones.
     */
    //172.-¿Qué es un método función (Function)?
    /*
    Function<T,R> es una interfaz funcional que recibe un objeto de tipo T y devuelve un resultado de tipo R, sirviendo para representar transformaciones de datos.
     */
    //173.-¿Qué es el método map en streams?
    /*
    En un Stream, map es una operación intermedia que toma cada elemento, le aplica una función de transformación y genera un nuevo Stream con los valores resultantes.
     */
    //174.-¿Qué es el método filter en streams?
    /*
    filter es una operación intermedia de un Stream que, a partir de un predicado (una función que devuelve true o false),
    conserva solo los elementos que cumplen la condición y descarta el resto, produciendo un nuevo Stream con los valores filtrados.
     */

    //175.-¿Cómo convertir un stream en lista?

    @Nested
    class StreamToListTest {

        @Test
        void convertirStreamEnLista() {
            // 1) Fuente inicial
            List<String> nombres = List.of("Ana", "Pedro", "Alba", "Luis");

            // 2) Creamos un Stream, lo filtramos y lo convertimos a lista
            List<String> soloConA = nombres.stream()          // Ana, Pedro, Alba, Luis
                    .filter(n -> n.startsWith("A"))
                    .collect(Collectors.toList()); // Ana, Alba

            // 3) Verificación
            assertEquals(List.of("Ana", "Alba"), soloConA);
        }
    }

    //176.-¿Qué es la clase Collectors?
    /*
    Collectors es una clase utilitaria de java.util.stream que contiene métodos estáticos que fabrican objetos Collector.
    Esos Collector se usan en la operación terminal collect(...) de un Stream para acumular o reducir los elementos a un resultado final (por ejemplo toList(), toSet(), joining(), groupingBy(), averagingInt(), etc.).
     */

    //177.-¿Qué es el método reduce en streams?
    /*
    reduce es una operación terminal de los Stream que toma los elementos de la secuencia y los combina
    iterativamente mediante una función asociativa (acumulador) hasta producir un único resultado (por ejemplo, la suma, el producto, la cadena concatenada, etc.).
     */

    //178.-¿Cómo usar la API de fechas en Java 8?

    @Nested
    class FechaApiTest {

        @Test
        void ejemploFechas() {
            // 1) Crear fechas
            LocalDate inicio = LocalDate.of(2025, 1, 1);      // 01-ene-2025
            LocalDate fin    = inicio.plusDays(5);            // 06-ene-2025

            // 2) Stream de días intermedios (1-ene … 5-ene)   ← datesUntil es Java 9+
            List<LocalDate> dias = inicio.datesUntil(fin)     // fin excluido
                    .collect(Collectors.toList());
            assertEquals(5, dias.size());
            assertEquals(LocalDate.of(2025, 1, 5), dias.get(4));

            // 3) Periodo entre las dos fechas
            Period p = Period.between(inicio, fin);
            assertEquals(5, p.getDays());

            // 4) Duración exacta (en horas) entre los dos instantes a medianoche
            Duration d = Duration.between(inicio.atStartOfDay(ZoneId.systemDefault()),
                    fin.atStartOfDay(ZoneId.systemDefault()));
            assertEquals(120, d.toHours());   // 5 días * 24 h
        }
    }

    //179.-¿Cómo obtener la fecha actual?

    LocalDate     hoyFecha     = LocalDate.now();        // solo fecha (2025-05-23)

    //180.-¿Cómo formatear una fecha en Java?


    LocalDate hoy = LocalDate.now();                       // 2025-05-23
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //181.-¿Qué es la serialización?
    /*
    Serialización es el proceso de convertir un objeto en una secuencia de bytes (o texto) para poder almacenarlo o enviarlo;
    luego esos bytes se “des-serializan” para recrear el mismo objeto en otro momento o lugar.o
     */
    //182.-¿Cómo serializar un objeto?

    @Test
    public void testSerializacionYDeserializaci() throws IOException, ClassNotFoundException {
        // Creamos el objeto original
        Persona original = new Persona("Ana", 30);

        // --- SERIALIZACIÓN a un array de bytes ---
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(original);
        }

        // --- DESERIALIZACIÓN desde el array de bytes ---
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        Persona deserializada;
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            deserializada = (Persona) ois.readObject();
        }

        // --- ASSERTS para verificar que la deserialización restaura el objeto correctamente ---
        assertNotSame(original, deserializada, "Debe ser una instancia distinta");
        assertEquals(original, deserializada, "Los datos deben coincidir tras la deserialización");
    }

    //183.-¿Qué es una excepción personalizada?
    /*
    Una excepción personalizada es una clase que tú mismo defines (extiende Exception o RuntimeException) para representar de forma explícita un tipo de error propio de tu aplicación,
    en lugar de reutilizar las excepciones genéricas de Java.
     */

    //184.-¿Cómo crear una excepción personalizada?

    public class MiExcepcion extends RuntimeException {
        public MiExcepcion(String mensaje) {
            super(mensaje);
        }
    }

    public void lanzarSiNegativo(int valor) {
        if (valor < 0) {
            throw new MiExcepcion("El valor no puede ser negativo");
        }
    }

    @Test
    public void ejercicio184_excepcionPersonalizada() {
        // Verifica que la excepción se lanza con valores negativos
        MiExcepcion ex = assertThrows(
                MiExcepcion.class,
                () -> lanzarSiNegativo(-5)
        );
        assertEquals("El valor no puede ser negativo", ex.getMessage());

        // Verifica que NO se lanza con valores positivos
        assertDoesNotThrow(() -> lanzarSiNegativo(10));
    }

    //185.-¿Qué es el synchronized y para qué se usa?
    /*
    synchronized es una palabra clave que bloquea un método o un bloque de código para que solo un hilo a la vez pueda ejecutarlo.
    Se usa para:

    Proteger datos compartidos y evitar que varios hilos los modifiquen simultáneamente (condiciones de carrera).

    Asegurar la visibilidad: cuando un hilo libera el bloqueo, los cambios que hizo en las variables compartidas se vuelven visibles para los demás hilos que adquieran ese mismo bloqueo

     */

    //186.-¿Cómo crear un hilo con Thread?

    public class MiHilo extends Thread {
        @Override
        public void run() {
            System.out.println("¡Hola desde MiHilo extendido!");
        }
    }

    @Test
    public void ejercicio186_ExtendiendoThread() {
        Thread hilo = new MiHilo();
        hilo.start(); // arranca el hilo (invoca run() de forma asíncrona)
    }

    //187.-¿Cómo crear un hilo con Runnable?

    public class MiTarea implements Runnable {
        @Override
        public void run() {
            // Lógica que quieres ejecutar en el hilo
            System.out.println("¡Hola desde MiTarea!");
        }
    }

    @Test
    public void ejercicioCrearHiloConRunnable_Nombrado() {
        Runnable tarea = new MiTarea();
        Thread hilo = new Thread(tarea, "Hilo-Nombrado");
        hilo.start(); // crea y arranca el hilo
    }

    //188.-¿Cómo detener un hilo?

    public class HiloParable implements Runnable {
        // Volatile para visibilidad inmediata entre hilos
        private volatile boolean ejecutando = true;

        @Override
        public void run() {
            while (ejecutando) {
                // … trabajo del hilo …
                System.out.println("Hilo en ejecución");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // Si también quieres que la interrupción pare el hilo:
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("Hilo detenido limpiamente");
        }

        // Método para solicitar la parada
        public void detener() {
            ejecutando = false;
        }
    }

    // Uso:
    @Test
    public void testDetenerConFlag() throws InterruptedException {
        HiloParable tarea = new HiloParable();
        Thread hilo = new Thread(tarea);
        hilo.start();

        // Dejamos correr un rato…
        Thread.sleep(2000);

        // Pedimos la parada
        tarea.detener();

        // Esperamos a que termine
        hilo.join();
    }


    //189.-¿Qué es una condición de carrera (race condition)?
    /*
    Una condición de carrera es una situación en la que el resultado de una operación depende del orden en que dos o más hilos o
    procesos acceden o modifican datos compartidos, provocando comportamientos impredecibles.
     */

    //190.-¿Qué es la sincronización en hilos?
    /*
    La sincronización en hilos es el mecanismo que asegura que, cuando varios hilos acceden a un mismo recurso o sección crítica,
    lo hagan de forma ordenada y no simultánea, evitando así estados inconsistentes o condiciones de carrera.
     */

    //191.-¿Qué es un ExecutorService?
    /*
    Un ExecutorService es una interfaz de Java que gestiona un conjunto de hilos para ejecutar tareas de forma asíncrona.
    Se encarga de recibir trabajos, distribuirlos a los hilos disponibles y controlar su ciclo de vida (inicio, espera y cierre).
     */

    //192.-¿Cómo usar un ExecutorService para manejar hilos?
    @Test
    public void testExecutorService() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            Future<String> futuro = executor.submit(() -> "¡Hecho!");
            assertEquals("¡Hecho!", futuro.get());
        } finally {
            executor.shutdown();
            assertTrue(executor.awaitTermination(1, TimeUnit.SECONDS));
        }
    }

    //193.-¿Qué es la clase Callable?
    /*
    Callable es una interfaz de Java que sirve para definir tareas que se ejecutan en otro hilo,
    te devuelven un resultado cuando terminan y te permiten saber si ha ocurrido algún error.
     */

    //194.-¿Qué es un Future?
    /*
    Un Future es un objeto que representa el resultado de una tarea que se está ejecutando en otro hilo;
    te permite saber si ya terminó, esperar a que acabe y luego obtener el valor que produjo, o incluso cancelar la tarea si aún no se ha completado.
     */

    //195.-¿Cómo manejar múltiples excepciones?
    @Test
    public void testManejoDeMultiplesExcepciones() {
        try {
            // Simulamos un código que puede lanzar IOException o SQLException
            lanzarError(true, false);
            fail("Se esperaba una excepción");
        } catch (IOException | SQLException e) {
            // Aquí manejamos ambas excepciones igual
            assertTrue(
                    e instanceof IOException || e instanceof SQLException,
                    "Debe ser IOException o SQLException"
            );
        }
    }

    // Método de apoyo que lanza según los parámetros
    private void lanzarError(boolean ioError, boolean sqlError) throws IOException, SQLException {
        if (ioError) {
            throw new IOException("Error de E/S simulado");
        }
        if (sqlError) {
            throw new SQLException("Error de SQL simulado");
        }
    }

    //196.-¿Qué es un bloque try-with-resources?
    /*
    El bloque try-with-resources es un try que declara recursos autocloseables
    y los cierra automáticamente al salir del bloque, sin necesidad de un finally.
     */

    //197.-¿Cómo leer datos de un archivo de texto?

    public void leeArchivos() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("ruta/al/archivo.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        }
    }

    //198.-¿Cómo escribir datos en un archivo de texto?

    public void escribeArchivo() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ruta/al/archivo.txt"))) {
            bw.write("Primera línea de texto");
            bw.newLine();
            bw.write("Segunda línea de texto");
        }
    }

    //199.-¿Cómo lanzar una excepción desde un método?

    public class MiExcepciont extends Exception {
        public MiExcepciont(String msg) {
            super(msg);
        }
    }

    // 2) Método que arroja esa excepción
    public void metodoQueFalla() throws MiExcepciont {
        // alguna lógica…
        // al detectar el error:
        throw new MiExcepciont("¡Algo salió mal!");
    }

    // 3) Test con JUnit 5 para verificar que efectivamente se lanza
    @Test
    public void testMetodoQueFallaLanzaMiExcepcion() {
        assertThrows(
                MiExcepciont.class,
                () -> metodoQueFalla(),
                "Se esperaba que metodoQueFalla() lanzara MiExcepcion"
        );
    }

    //200.-¿Qué es la reflexión en Java?

    /*
    La reflexión en Java es una API que, en tiempo de ejecución, permite inspeccionar y manipular la estructura interna de clases, métodos, campos y constructores (incluso los privados),
    creando objetos e invocando métodos cuyos nombres solo se conocen dinámicamente; aunque otorga gran flexibilidad para frameworks,
    pruebas y mapeos, su uso conlleva penalizaciones de rendimiento y posibles riesgos de seguridad al romper el encapsulamiento.

     */

}











