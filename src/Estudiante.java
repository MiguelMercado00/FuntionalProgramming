public class Estudiante {
    private String id;
    private String primerNombre;
    private String apellidoPaterno;
    private String genero;
    private String carrera;
    private int puntaje;

    public Estudiante(String id,String primerNombre, String apellidoPaterno, String genero, String carrera, int puntaje) {
        this.id=id;
        this.primerNombre = primerNombre;
        this.apellidoPaterno = apellidoPaterno;
        this.genero = genero;
        this.carrera = carrera;
        this.puntaje = puntaje;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera= carrera;
    }

    public int getPuntaje() {
        return puntaje;
    }


    @Override
    public String toString() {
        return "Estudiante{" +
                "primerNombre='" + primerNombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", Genero=" + genero +
                ", Carrera='" + carrera + '\'' +
                ", Puntaje=" + puntaje +
                '}';
    }
}
