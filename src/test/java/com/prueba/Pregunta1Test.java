package com.prueba;

import static java.lang.Double.valueOf;
import static org.junit.jupiter.api.Assertions.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

        BigDecimal big1 = BigDecimal.valueOf(30);
        BigDecimal big2 = BigDecimal.valueOf(20);

        int cmp = big1.compareTo(big2);

        if (cmp < 0) {
            fail("big1 debería ser mayor que big2");
        } else if (cmp > 0) {
            assertTrue(true);
        } else {
            fail("big1 no debería ser igual a big2");
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
    BigDecimal divisor  = new BigDecimal("3");

    BigDecimal resultados = dividendo.divide(divisor, 2, RoundingMode.HALF_UP);

    //39.-¿Qué método de BigDecimal usas para comparar valores?

    BigDecimal a = new BigDecimal("1.0");
    BigDecimal b = new BigDecimal("1.00");

    int cmp = a.compareTo(b);


    //40.-¿Qué pasa si divides por cero con BigDecimal?

    BigDecimal numerador   = new BigDecimal("10");
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
        private final int    edad;

        public Personas(String nombre, int edad) {
            this.nombre = nombre;
            this.edad   = edad;
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
    public static class Persona implements Comparable<Persona> {
        private final String nombre;
        private final int    edad;

        public Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad   = edad;
        }

        /* Orden natural: por edad ascendente */
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
    public void ejercicio48_ordenarHashMapPorValores() {

        // HashMap original (orden impredecible)
        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("A", 3);
        mapa.put("B", 1);
        mapa.put("C", 2);

        // 1) Convertir a stream de entries y ordenar por value ascendente
        Map<String,Integer> ordenado = mapa.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())           // ascendente
                        .collect(
                                LinkedHashMap::new,                         // 2) contenedor ordenado
                                (m,e) -> m.put(e.getKey(), e.getValue()),
                                LinkedHashMap::putAll
                        );

        // ===== Verificación sencilla =====
        Iterator<Integer> it = ordenado.values().iterator();
        assertEquals(1, it.next());  // primero el valor más pequeño (B=1)
        assertEquals(2, it.next());  // luego (C=2)
        assertEquals(3, it.next());  // por último (A=3)

        // (opcional) imprimir para ver el orden
        ordenado.forEach((k,v) -> log.info("{} -> {}", k, v));
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
            this.alto  = alto;
        }

        public int getAncho() { return ancho; }
        public int getAlto()  { return alto;  }
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
        /** Método de la clase padre */
        public String hablar() {
            return "Sonido genérico";
        }
    }

    public static class Perro extends Animal {
        /** Sobrescritura: mismo nombre, misma firma.
         La anotación @Override avisa al compilador
         si la firma no coincide. */
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
        assertTrue(obj instanceof List<?>,      "Debe ser una List");
        assertFalse(obj instanceof ArrayList<?>, "No debe ser ArrayList");

        // (Opcional) si quieres castear y usar el objeto:
        if (obj instanceof List<?>) {
            List<?> lista = (List<?>) obj;
            log.info("Tamaño de la lista: {}", lista.size());
        }
    }

    //72.-¿Cómo convertir un Object a Integer?
    @Test
    public void ejercicio72_objectToInteger() {

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


}











