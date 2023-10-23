import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.*;



class Main {
    static List<Estudiante> estudiantes;

    public static void main(String[] args) throws IOException {

        cargarArchivo();
        // 1
        mostrarAspirantesPorCarrera();
        cantidadEstudiantesPorCarrera();
        // 2
        mostrarTotalMujeresPorCarrera();
        // 3
        mostrarTotalHombresPorCarrera();
        // 4
        mostrarEstudianteConMayorPuntajePorCarrera();
        // 5
        mostrarEstudianteConMayorPuntaje();
        // 6
        promPuntajePorCarrera();


    }

    static void cargarArchivo() throws IOException {
        Pattern pattern = Pattern.compile(",");
        String filename = "student-scores.csv";

        try (Stream<String> lines = Files.lines(Path.of(filename))) {
            estudiantes = lines.skip(1).map(line -> {
                String[] arr = pattern.split(line);
                return new Estudiante(arr[0], arr[1], arr[2], arr[4], arr[9], Integer.parseInt(arr[10]));
            }).collect(Collectors.toList());
            estudiantes.forEach(System.out::println);
        }
    }

    static void mostrarAspirantesPorCarrera(){
        System.out.printf("%nAspirantes por carrera:%n");
        Map<String, List<Estudiante>> agrupadoPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Estudiante::getCarrera));
        agrupadoPorCarrera.forEach(
                (carrera, estudiantesPorCarrera) ->
                {
                    System.out.println(carrera);
                    estudiantesPorCarrera.forEach(
                            estudiante -> System.out.printf(" %s%n", estudiante));
                }
        );
    }
    static void cantidadEstudiantesPorCarrera(){
        // cuenta el número de estudiantes por carrera.
        System.out.printf("%nConteo de estudiantes por carrera:%n");
        Map<String, Long> conteoEstudiantesPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Estudiante::getCarrera,
                                TreeMap::new, Collectors.counting()));
        conteoEstudiantesPorCarrera.forEach(
                (carrera, conteo) -> System.out.printf(
                        "%s tiene %d estudiante(s)%n", carrera, conteo));
    }

    static void mostrarTotalMujeresPorCarrera() {
        // cuenta el número de estudiantes mujeres por carrera.
        System.out.printf("%nConteo de estudiantes mujeres por carrera:%n");
        Predicate<Estudiante> esMujer = estudiante -> estudiante.getGenero().equals("female");
        Map<String, Long> conteoMujeresPorCarrera =
                estudiantes.stream()
                        .filter(esMujer)
                        .collect(Collectors.groupingBy(Estudiante::getCarrera,
                                TreeMap::new, Collectors.counting()));
        conteoMujeresPorCarrera.forEach(
                (carrera, conteo) -> System.out.printf(
                        "%s tiene %d estudiante(s)%n", carrera, conteo));
    }

    static void mostrarTotalHombresPorCarrera() {
        // cuenta el número de estudiantes hombres por carrera.
        System.out.printf("%nConteo de estudiantes hombres por carrera:%n");
        Predicate<Estudiante> esMujer = estudiante -> estudiante.getGenero().equals("male");
        Map<String, Long> conteoMujeresPorCarrera =
                estudiantes.stream()
                        .filter(esMujer)
                        .collect(Collectors.groupingBy(Estudiante::getCarrera,
                                TreeMap::new, Collectors.counting()));
        conteoMujeresPorCarrera.forEach(
                (carrera, conteo) -> System.out.printf(
                        "%s tiene %d estudiante(s)%n", carrera, conteo));
    }

    static void mostrarEstudianteConMayorPuntajePorCarrera(){
        Function<Estudiante, Integer> porPuntaje = Estudiante::getPuntaje;
        Comparator<Estudiante> PuntajeDescendente =
                Comparator.comparing(porPuntaje);
        System.out.printf("%nEstudiantes con mayor puntaje por carrera: %n");
        Map<String, List<Estudiante>> agrupadoPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Estudiante::getCarrera));
        agrupadoPorCarrera.forEach(
                (carrera, estudiantesEnCarrera) ->
                {
                    System.out.print(carrera+": ");
                    Estudiante estudianteMas=estudiantesEnCarrera.stream().sorted(PuntajeDescendente.reversed())
                            .findFirst()
                            .get();
                    System.out.println(estudianteMas.getPrimerNombre()+" "+estudianteMas.getApellidoPaterno()+
                            " ///Cuanto puntaje: "+estudianteMas.getPuntaje());
                }
        );
    }
    static void mostrarEstudianteConMayorPuntaje(){
        Function<Estudiante, Integer> porPuntaje = Estudiante::getPuntaje;
        Comparator<Estudiante> NotasDescendente =
                Comparator.comparing(porPuntaje);
        Estudiante estudianteMas=estudiantes.stream()
                .sorted(NotasDescendente.reversed())
                .findFirst()
                .get();
        System.out.printf(
                "%nEstudiante con el puntaje más alto de todos: %n%s %s%nCuanto puntaje: %d%n",
                estudianteMas
                        .getPrimerNombre(),
                estudianteMas
                        .getApellidoPaterno(),
                estudianteMas
                        .getPuntaje());
    }

    static void promPuntajePorCarrera() {
        Map<String, List<Estudiante>> agrupadoPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Estudiante::getCarrera));
        System.out.println("\nPromedio de puntajes por carrera:");
        agrupadoPorCarrera.forEach((carrera, estudiantesPorCarrera) -> {
            System.out.print(carrera + ": ");
            double promedio = estudiantesPorCarrera
                    .stream()
                    .mapToDouble(Estudiante::getPuntaje)
                    .average()
                    .getAsDouble();
            System.out.println(Math.round(promedio)); // Redondear al valor más cercano
        });
    }


}



