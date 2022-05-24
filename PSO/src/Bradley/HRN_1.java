package Bradley;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class HRN_1 implements Runnable{
    Proceso[] procesos;
    
    Random r = new Random();
    
    int indexSuspL=0;
    int indexBloqueado=0;
    int indexSuspB=0;
    int indexListo=Diagrama.contador;
    double priori;
    int tiempoLis = -1;
    
    Timer timerPrincipal = new Timer();
    public TimerTask tarea = new TimerTask() {
        @Override
        public void run() {
            tiempoLis ++;
            System.out.println("segundo: " + tiempoLis);
            
        }
    };
    
    Proceso arregloTiem[] = new Proceso[200];
    
    int a = 1;
    int indVec = 0;
    
    
    public HRN_1(Proceso[] pr, int length){
        procesos = new Proceso[length];
        procesos = pr;
    }
    
    @Override
    public void run() {
        for (int i = 0; i <Diagrama.listos.length; i++) {
            
            if(procesos[i]!=null){
                //llegada=(ThreadLocalRandom.current().nextInt(1, 3 + 1))*1000;
                Dormir(1000);
                Diagrama.listos[i]=procesos[i];
                Mostrar(Diagrama.listos,Diagrama.areaListos);
            }
            
        }
        Diagrama.listos=procesos;
        System.out.println("==================================");
        
        
        int ind=0;
        int length = Diagrama.listos.length;
        boolean bandera = true;
        
        do{
            
            Proceso p = new Proceso();
            p = Diagrama.listos[ind];
            //p.prior = 1;

            if(p!=null){
                
                
                // baja los procesos a diagrama de listos
                Mostrar(Diagrama.listos,Diagrama.areaListos);
                Dormir(1000);
                
                // ? ejecución/suspendidolisto [1/2]
                status randomListo = getStatus(2,1);
                a = (ThreadLocalRandom.current().nextInt(1, 3 + 1));
                Diagrama.listos[ind] = null;
                Mostrar(Diagrama.listos,Diagrama.areaListos);
                System.out.println("ESTATUS INICIAL :" + randomListo);
                
                
                CallProceso(p, status.EJECUTAR, a);
                //aqui regresa a tiempo de ejecucion
                Proceso[] newProcesos = actualizarPrioridad(Diagrama.listos, a );
                
                Diagrama.listos = newProcesos;
                Mostrar(Diagrama.listos,Diagrama.areaListos);
                
                p = null;
            }
            ind++;
            
            bandera = length != ind;
            
        }while(bandera);
        System.out.println("Se termino el RUN");
        Diagrama.areaProcess.setText("ID    " + "   Proceso");
        Diagrama.k = 1;
        
    }
    
    public Proceso[] actualizarPrioridad(Proceso[] procesos, int tiempo ){
        
        Proceso[] newProcesos= new Proceso[procesos.length];
        
            //actualiza prioridades
            for (int i = 0; i < procesos.length; i++) {
                //operacion HRN p =(w+t)/t
                //?? if  not null
                if(procesos[i]!=null){
                    procesos[i].prioridad = (procesos[i].restante + tiempo)/tiempo;
                }
            }
            newProcesos = burbuja(procesos);



            return newProcesos;
    }
    
    public static Proceso[] burbuja(Proceso[] arreglo) {
        
        Proceso auxiliar;
        Proceso[] arregloOrdenado;
        
        for(int i = 2; i < arreglo.length; i++)
        {
            for(int j = 0;j < arreglo.length-i;j++)
            {
                if(arreglo[j]!=null && arreglo[j+1]!=null){
                    if(arreglo[j].prioridad < arreglo[j+1].prioridad)
                    {
                        auxiliar = arreglo[j];
                        arreglo[j] = arreglo[j+1];
                        arreglo[j+1] = auxiliar;
                    }
                }
            }
        }
        arregloOrdenado = arreglo;
        return arregloOrdenado;
    }
    
    public int CallProceso (Proceso p, status s, int numR){
        
        int tiempoTot = p.tiempo;
        p.restante = p.tiempo;
        //p.servicio = Diagrama.aux;
        
        
        switch (s) {
            case EJECUTAR:
                Diagrama.lbProceso.setText(p.nombre);
                //Se ejecuta completo el programa
                System.out.println("Ejecutar");
                Dormir(1000);
                p.restante -= numR;
                //p.tiempo = p.restante;
                //? 3: EJECUTARCOMPLETO/4: SUSPENDIDOLISTO/5: BLOQUEADO
                status randomListo= getStatus(3,2);
                
                if(p.restante > 0){ //Ejecutando Procesos
                    //? 3: EJECUTARCOMPLETO/4: SUSPENDIDOLISTO/5: BLOQUEADO
                    randomListo= getStatus(3,2);
                    System.out.println("ESTATUS:" + randomListo);
                    CallProceso(p, randomListo, numR);
                    Diagrama.lbProceso.setText("");
                    
                } else {
                    mostrarFinalizados(p);
                }
                
                break;
                
            case SUSPENDIDOLISTO:
                
                System.out.println("suspendido listo");
                Diagrama.lbProceso.setText("");
                indexSuspL = Añadir(Diagrama.suspListo,p,indexSuspL);
                Mostrar(Diagrama.suspListo,Diagrama.areaSuspLis);
                
                
                Dormir(2000);
                Diagrama.tiempoTot += (a / 1000);
                returnToListos(Diagrama.suspListo,Diagrama.areaSuspLis );
                break;
                
            case EJECUTARCOMPLETO:
                Diagrama.lbProceso.setText(p.nombre);
                //Se ejecuta completo el programa
                System.out.println("EJECUTAR COMPLETO");
                Dormir(p.tiempo);
                
                mostrarFinalizados(p);
                Diagrama.lbProceso.setText("");
                
                break;
                
            case BLOQUEADO:
                
                System.out.println(" BLOQUEO");
                indexBloqueado = Añadir(Diagrama.bloqueado,p,indexBloqueado);
                Diagrama.lbProceso.setText("");
                Mostrar(Diagrama.bloqueado,Diagrama.areaBloqueado);
                Dormir(2000);
                int randomblo=r.nextInt(2);
                
                if(randomblo==0){
                    returnToListos(Diagrama.bloqueado,Diagrama.areaBloqueado );
                }else{
                    int indRemover =   obtenerPrimerProcesoIndex(Diagrama.bloqueado);
                    removeProceso(Diagrama.bloqueado,Diagrama.areaBloqueado, indRemover);
                    CallProceso(p,status.SUSPENDIDOBLOQUEADO, numR);
                }
                Diagrama.tiempoTot += (a / 1000);
                break;
                
            case SUSPENDIDOBLOQUEADO:
                int randomSblo=r.nextInt(2);
                
                System.out.println("SUSPENDIDO BLOQUEADO");
                Diagrama.lbProceso.setText("");
                indexSuspB = Añadir(Diagrama.suspBlo,p,indexSuspB);
                Mostrar(Diagrama.suspBlo,Diagrama.areaSusBlo);
                Dormir(2000);
                
                int indRemover =   obtenerPrimerProcesoIndex(Diagrama.suspBlo);
                removeProceso(Diagrama.suspBlo,Diagrama.areaSusBlo, indRemover);
                if(randomSblo==0)
                    CallProceso(p,status.BLOQUEADO, numR);
                else
                    CallProceso(p,status.SUSPENDIDOLISTO, numR);
                
                break;
            default:
                break;
        }
        return numR;
    }
    
    public void returnToListos(Proceso[] lista,JTextArea area ){
        int index = obtenerPrimerProcesoIndex(lista);
        Proceso p = obtenerProceso(lista, index);
        indexListo = Añadir(Diagrama.listos,p,indexListo);
        removeProceso(lista, area, index);
    }
    
    public int obtenerPrimerProcesoIndex(Proceso[] lista){
        int y=0;
        if(lista[0] == null){
            for(int x=1;x<lista.length;x++){
                if(lista[x] != null){
                    y=x;
                    x=lista.length;
                }
            }
        }
        return y;
    }
    
    public Proceso obtenerProceso(Proceso[] lista,int index){
        return lista[index];
    }
    
    public void removeProceso(Proceso[] lista,JTextArea area,int index){
        lista[index]=null;
        Mostrar(lista, area);
    }
    
    public void mostrarFinalizados(Proceso p){
        
        // obtenga tiempo
        int va = Diagrama.aux;
        
        Diagrama.list [0] = p.nombre;
        Diagrama.list [1] = Integer.toString(va);
        p.fin = Diagrama.aux;
        Diagrama.vecFinal[indVec] = p; //<-
        indVec++;
        Diagrama.miMod.addRow(Diagrama.list);
        Diagrama.areaFinalizados.setModel(Diagrama.miMod);
        
    }
    
    
    public int  Añadir(Proceso[] lista,Proceso p, int index){ //Mandar la lista de el estado a donde ira
        if(index == lista.length){
            index=0;
        }
        lista[index] = p;
        System.out.println("Se Añadio: "+p.nombre+" en la posicion: "+index);
        return index=index+1;
    }
    
    public status getStatus(int rangoinicial, int rangofinal){
        int number =r.nextInt(rangoinicial)+rangofinal;
        status s = null;
        
        switch (number) {
            case 1:
                s = status.EJECUTAR;
                break;
            case 2:
                s = status.SUSPENDIDOLISTO;
                break;
            case 3:
                s = status.EJECUTARCOMPLETO;
                break;
            case 4:
                s = status.BLOQUEADO;
                break;
            case 5:
                s = status.SUSPENDIDOBLOQUEADO;
                break;
        }
        return s;
    }
    
    enum status{
        LISTO, //0
        EJECUTAR, //1
        SUSPENDIDOLISTO, //2
        EJECUTARCOMPLETO, //3
        BLOQUEADO, //4
        SUSPENDIDOBLOQUEADO, //5
        FINALIZADO //6
    }
    
    public void Dormir(int sueño){
        try {
            Thread.sleep(sueño);
        } catch (InterruptedException ex) {
            Logger.getLogger(HRN_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Mostrar(Proceso[] listo, JTextArea area){//Lista que se mostrara y area
        area.setText("");
        String list="";
        for(int x=0;x<listo.length;x++){
            if(listo[x] != null){
                list += listo[x].nombre+"\n";
            }
        }
        System.out.println(list);
        area.setText(list);
    }
    
    public void sumarTiempEsp(Proceso p, int tiempoEsp, Proceso[] vp) {
        
        for (int i = 0; i < vp.length; i++){
            if(vp[i] != null){
                vp[i].prior += a;
                System.out.println("Entro a metodo sumarTiem " + vp[i].nombre + " Prioridad: " + vp[i].prior + "\n");
                vp[i] = null;
            }
        }
    }
}
