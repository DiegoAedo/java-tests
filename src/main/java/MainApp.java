import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.Double.valueOf;


@Slf4j
public class MainApp {

    public static void main(String[] args) throws IOException {

        String[] titulos = new String[] {
                "1.-¿Cómo casteas un objeto Object a String?",
                "2.-¿Qué ocurre si casteas un objeto a un tipo incompatible?",
                "3.-¿Cómo conviertes un double a BigDecimal para evitar pérdida de precisión?",
                "4.-¿Cómo conviertes un BigDecimal a double?",
                "5.-¿Cómo imprimir variables usando java.util.logging.Logger?",
                "6.-¿Cuál es la forma correcta de convertir una String a double?",
                "7.-¿Qué método usas para convertir un double en String con notación exponencial?",
                "8.-¿Cómo conviertes un Long a String para imprimirlo en logs?",
                "9.-¿Cómo comparar dos BigDecimal para saber cuál es mayor?",
                "10.-¿Cómo comparar dos double para saber cuál es mayor?",
                "11.-¿Cómo defines un HashMap en Java?",
                "12.-¿Cómo agregar un elemento a un HashMap?",
                "13.-¿Cómo eliminar un elemento de un HashMap por clave?",
                "14.-¿Cómo recorrer un HashMap con un bucle for?",
                "15.-¿Cómo recorrer un HashMap con Iterator?"
        };

        String[] codigos = new String[] {
                "Object obj = \"Diego\";\nString texto;\nif (obj instanceof String) {\n    texto = (String) obj;\n    log.info(texto);\n} else {\n    log.warn(\"El objeto NO es un String, es un {}\", obj.getClass().getSimpleName());\n    return;\n}",
                "",
                "double precio = 1958445345343.15515d;\nBigDecimal precioBigdecimal = BigDecimal.valueOf(precio);\nlog.info(\"Precio en Bigdecimal: {}\",precioBigdecimal);",
                "BigDecimal precio2 = new BigDecimal(\"1958445345343.15515\");\ndouble precioDouble = precio2.doubleValue();\nlog.info(\"Precio bigdecimal a double: {}\", precioDouble);",
                "String nombre = \"Diego\";\nString apellido = \"Aedo\";\nlog.info(\"Nombre y apellido: {} {}\", nombre, apellido);",
                "String valor = \"1550.520\";\nDouble valorObjeto = Double.valueOf(valor);\nlog.info(\"String a Double: {}\", valorObjeto);",
                "double valor2 = 1.6789;\nDecimalFormat df = new DecimalFormat(\"0.####E0\");\nString s = df.format(valor2);\nlog.info(\"String a Double: {}\", s);",
                "Long valor3 = 123456789L;\nString valorStr = String.valueOf(valor3);\nlog.info(\"Long a String: {}\", valorStr);",
                "BigDecimal big1 = BigDecimal.valueOf(30);\nBigDecimal big2 = BigDecimal.valueOf(20);\nint cmp = big1.compareTo(big2);\nif (cmp < 0) {\n    log.info(\"big1 {} es menor que big2 {}\", big1, big2);\n} else if (cmp > 0) {\n    log.info(\"big1 {} es mayor que big2 {}\", big1, big2);\n} else {\n    log.info(\"big1 {} es igual que big2 {}\", big1, big2);\n}",
                "Double valor5 = Double.valueOf(30.30d);\nDouble valor6 = Double.valueOf(20.30d);\ndouble cmp2 = valor5.compareTo(valor6);\nif (cmp2 < 0) {\n    log.info(\"valor1 {} es menor que valor2 {}\", valor5, valor6);\n} else if (cmp2 > 0) {\n    log.info(\"valor1 {} es mayor que valor2 {}\", valor5, valor6);\n} else {\n    log.info(\"valor1 {} es igual que valor2 {}\", valor5, valor6);\n}",
                "Map<String, Integer> mapa = new HashMap<>();\nlog.info(\"mapa {}\", mapa);",
                "Map<String, Integer> mapa2 = new HashMap<>();\nmapa2.put(\"Diego\", 36);\nlog.info(\"mapa {}\", mapa2);",
                "Map<String, Integer> mapa3 = new HashMap<>();\nmapa3.put(\"Diego\", 37);\nmapa3.put(\"Victor\", 39);\nmapa3.remove(\"Victor\");\nInteger valorEliminado = mapa3.get(\"Victor\");\nlog.info(\"Valor después de eliminar: {}\", valorEliminado);",
                "Map<String, Integer> mapa4 = new HashMap<>();\nmapa4.put(\"Diego\", 37);\nmapa4.put(\"Victor\", 39);\nmapa4.put(\"Leo\", 38);\nmapa4.put(\"Javier\", 40);\nfor (Map.Entry<String, Integer> entry : mapa4.entrySet()) {\n    String key = entry.getKey();\n    Integer value = entry.getValue();\n    log.info(\"Recorrer un HashMap con bucle for: {} {}\", key, value);\n}",
                "Map<String, Integer> mapa5 = new HashMap<>();\nmapa5.put(\"Diego\", 37);\nmapa5.put(\"Victor\", 39);\nmapa5.put(\"Leo\", 38);\nmapa5.put(\"Javier\", 40);\nIterator<Map.Entry<String, Integer>> iterator = mapa5.entrySet().iterator();\nwhile (iterator.hasNext()) {\n    Map.Entry<String, Integer> entry = iterator.next();\n    String key = entry.getKey();\n    Integer value = entry.getValue();\n    log.info(\"Recorrer HashMap con Iterator: {} {}\", key, value);\n}"
        };

        String dest = "test.pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        PdfFont fontTitulo = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont fontMono   = PdfFontFactory.createFont(StandardFonts.COURIER);

        for (int i = 0; i < titulos.length; i++) {
            Paragraph pTitulo = new Paragraph(titulos[i])
                    .setFont(fontTitulo)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(10);
            document.add(pTitulo);

            if (!codigos[i].isEmpty()) {
                Paragraph pCodigo = new Paragraph(codigos[i])
                        .setFont(fontMono)
                        .setFontSize(12)
                        .setFontColor(ColorConstants.BLUE)
                        .setBackgroundColor(new DeviceGray(0.9f))
                        .setMarginLeft(20)
                        .setPadding(5);
                document.add(pCodigo);
            }
        }

        document.close();
        log.info("PDF '{}' creado exitosamente.", dest);
    }
}
