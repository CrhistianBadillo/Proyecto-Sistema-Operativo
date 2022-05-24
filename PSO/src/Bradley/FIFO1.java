package Bradley;



import Bradley.Diagrama;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FIFO1 implements Runnable{
    Proceso[] procesos;
    
    Random r = new Random();
    
    int indexSuspL=0;
    int indexBloqueado=0;
    int indexSuspB=0;
    int indexListo=Diagrama.contador;
    public static boolean bandera = true;
    int pseudoQuantum=0;
    
    int indVec = 0;
    
    public FIFO1(Proceso[] pr, int length){
        procesos = new Proceso[length];
        procesos = pr;
    }
    
    @Override
    public void run() {
                
        //int llegada = 0;
        
        for (int i = 0; i <Diagrama.listos.length; i++) {
            
            if(procesos[i]!=null){
                //llegada=(ThreadLocalRandom.current().nextInt(1, 3 + 1))*1000;
                Dormir(1000);
                Diagrama.listos[i]=procesos[i];
                Mostrar(Diagrama.listos,Diagrama.areaListos);
                
            }
            
        }
        //llegada += r.nextInt(2) + 1;

        
        Diagrama.listos=procesos;
        System.out.println("==================================");
        
        
        int ind=0;
        int length = Diagrama.listos.length;
        //bandera = true;
        
        do{
            
//           if(Diagrama.act==true && Diagrama.pau==false){
            Proceso p = new Proceso();
            p = Diagrama.listos[ind];
           

            if(p!=null){    
                // baja los procesos a diagrama de listos
               Mostrar(Diagrama.listos,Diagrama.areaListos);
               Dormir(1000);
                 
                // ? ejecución/suspendidolisto [1/2]
                status randomListo= getStatus(2,1);
                
                Diagrama.listos[ind] = null;
                Mostrar(Diagrama.listos,Diagrama.areaListos);
                System.out.println("ESTATUS INICIAL :" + randomListo);
                CallProceso(p, randomListo);
                p = null;
            }
            ind++;
            
            bandera = length != ind;
            //}
            /*else{
            bandera = true;
            }*/
        }while(bandera);
        System.out.println("Se termino el RUN");
        Diagrama.areaProcess.setText("ID    " + "   Proceso");
        Diagrama.k = 1;
         
    }
    
  
    
    
    public void CallProceso (Proceso p, status s){
        
       //if(Diagrama.act==true && Diagrama.pau==false){
        
        p.restante = p.tiempo;
        int a = (ThreadLocalRandom.current().nextInt(1, 3 + 1)) * 1000; 
        System.out.println("Dormir = " + a);
        
        switch (s) {
            case EJECUTAR:
                Diagrama.lbProceso.setText(p.nombre);
                //Se ejecuta completo el programa
                Diagrama.lbProceso.setText(p.nombre);
                //Se ejecuta completo el programa
                System.out.println("Ejecutar");
                p.restante -=  pseudoQuantum;
                Dormir(pseudoQuantum+1000);
                
                //? 3: EJECUTARCOMPLETO/4: SUSPENDIDOLISTO/5: BLOQUEADO
                if(p.restante > 0){ //Ejecutando Procesos
                    //? 3: EJECUTARCOMPLETO/4: SUSPENDIDOLISTO/5: BLOQUEADO
                    status randomListo= getStatus(3,2);
                    System.out.println("ESTATUS:" + randomListo);
                    CallProceso(p, randomListo);
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
//                if (Diagrama.lbProceso.getText().equals("")){
//                    run();
//                }
                Dormir(a);
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
                Diagrama.ProcesosRestantes--;
                break;
                
            case BLOQUEADO:
                
                System.out.println(" BLOQUEO");
                indexBloqueado = Añadir(Diagrama.bloqueado,p,indexBloqueado);
                Diagrama.lbProceso.setText("");
                Mostrar(Diagrama.bloqueado,Diagrama.areaBloqueado);

                Dormir(a);
                int randomblo=r.nextInt(2);
                
                if(randomblo==0){
                    returnToListos(Diagrama.bloqueado,Diagrama.areaBloqueado );
                }else{
                    int indRemover =   obtenerPrimerProcesoIndex(Diagrama.bloqueado);
                    removeProceso(Diagrama.bloqueado,Diagrama.areaBloqueado, indRemover);
                    CallProceso(p,status.SUSPENDIDOBLOQUEADO);
                }
                Diagrama.tiempoTot += (a / 1000);
                break;                
                
            case SUSPENDIDOBLOQUEADO:
                int randomSblo=r.nextInt(2);
                
                System.out.println("SUSPENDIDO BLOQUEADO");
                Diagrama.lbProceso.setText("");
                indexSuspB = Añadir(Diagrama.suspBlo,p,indexSuspB);
                Mostrar(Diagrama.suspBlo,Diagrama.areaSusBlo);
                                
                Dormir(a);
                Diagrama.tiempoTot += (a / 1000);
                
                int indRemover =   obtenerPrimerProcesoIndex(Diagrama.suspBlo);
                removeProceso(Diagrama.suspBlo,Diagrama.areaSusBlo, indRemover);
                if(randomSblo==0)
                    CallProceso(p,status.BLOQUEADO);
                else
                    CallProceso(p,status.SUSPENDIDOLISTO);
                
                break;
            default:
                break;
        }
    //}        
    }
    
      public void reaProcesos(){
        //declarar arreglo detipo proceso,
        
        Proceso [][] todo = new Proceso[][]{Diagrama.bloqueado,Diagrama.suspListo, Diagrama.suspBlo};      
        //todo = Diagrama.suspBlo,Diagrama.suspListo,Diagrama.bloqueado;
     
        for (int i = 0; i <todo.length; i++) {
                Proceso sac= new Proceso();
            sac  = todo[i][i];
            
            for (int j = 0; j < todo.length; j++) {
                
                
                
                CallProceso(sac,status.LISTO);
            }
        }
        
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
        System.out.println("*" + p.id + "*");
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
            Logger.getLogger(FIFO1.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
}



