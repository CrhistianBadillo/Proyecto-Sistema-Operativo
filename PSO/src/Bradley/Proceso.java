package Bradley;



public class Proceso {
    //tiempo = tiempo de ejecucion
    
    String id,nombre,memoria,estado;
    int llegada,tiempo,restante,espera,servicio,fin, prior;
    double inxserv,prioridad;

    public int getLlegada() {
        return llegada;
    }

    public int getRestante() {
        return restante;
    }

    public void setRestante(int restante) {
        this.restante = restante;
    }

    public int getEspera() {
        return espera;
    }

    public void setEspera(int espera) {
        this.espera = espera;
    }

    public int getServicio() {
        return servicio;
    }

    public void setServicio(int servicio) {
        this.servicio = servicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public void setInxserv(int inxserv) {
        this.inxserv = inxserv;
    }


    public void setLlegada(int llegada) {
        this.llegada = llegada;
    }
    
    public Proceso(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(double prioridad) {
        this.prioridad = prioridad;
    }

    public int getTiempo() { 
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        int t=Integer.parseInt(tiempo)*1000;
        this.tiempo = t;
    }
   
    
    
}
