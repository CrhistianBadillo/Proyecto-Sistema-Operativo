package Bradley;



import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class Diagrama extends javax.swing.JFrame implements Runnable {
    
    public static DefaultTableModel miModelo = new DefaultTableModel();
    public static DefaultTableModel miMod = new DefaultTableModel();
    
    public static String[] procesosTabla = new String[6];
    Proceso[] procesos = new Proceso[200];
    
    static Proceso[] listos = new Proceso[200];
    static Proceso[] bloqueado = new Proceso[200];
    static Proceso[] suspListo = new Proceso[200];
    static Proceso[] suspBlo = new Proceso[200];
    
    Thread hilo,hiloB,hiloSL,hiloSB;
    Random r = new Random();
    String algo;
    
    static int quantum;
    int index=0;
    static int contador=0;
    
    public static int tiempoTot;
    public static int aux = -1;
    int auxT = -1;
    
    public static int tiempTot[] = new int [200];
    public static String list [] = new String[2];
    
    public static int ProcesosRestantes = 0;
    
    public static int k = 1;
    
    Timer timerPrincipal = new Timer();
    public TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                aux ++;
                auxT ++;
                System.out.println("segundo: "+aux);
                lblCrono.setText("Segundo: "+Integer.toString(aux));

            }
        };
    
    public static Proceso []vecFinal = new Proceso[200];

       
    
    public Diagrama() {
        super("Interfaz");
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        btnMatar.setEnabled(false);
        btnDetener.setEnabled(false);
        btnIniciar.setEnabled(false);
        verTabla();
        //ProcesosDefault();
        areaProcess.setText("ID         Nombre");
        
        btnCap.setVisible(false);
        btnGen.setVisible(false);
        btnDetener.setVisible(false);
        //lblIm.setIcon(setIcono("proceso3.png",lblIm));
        miMod.addColumn("Nombre");
        miMod.addColumn("Tiempo");
        areaFinalizados.setModel(miMod);
    }
    
    public void verTabla(){
        //Titulo de columnas
        miModelo.addColumn("ID");
        miModelo.addColumn("Nombre");
        miModelo.addColumn("Tiempo requerido");
        
        
        miTabla.setModel(miModelo);
    }
    
    public static void guardar(String p[]){
        procesosTabla = p;
        miModelo.addRow(procesosTabla);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        miPanel = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnMatar = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();
        btnDetener = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaProcess = new javax.swing.JTextArea();
        lbProceso = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        miTabla = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaBloqueado = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaListos = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        areaSuspLis = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        areaSusBlo = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCap = new javax.swing.JButton();
        btnGen = new javax.swing.JButton();
        lblIm = new javax.swing.JLabel();
        lblCrono = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        areaFinalizados = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuProcesos = new javax.swing.JMenu();
        mnuFIF = new javax.swing.JMenuItem();
        mnuSJN = new javax.swing.JMenuItem();
        mnuRR = new javax.swing.JMenuItem();
        mnuSRT = new javax.swing.JMenuItem();
        mnuHRN = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        miPanel.setBackground(new java.awt.Color(255, 255, 255));
        miPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        miPanel.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        btnMatar.setText("Detener");
        btnMatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatarActionPerformed(evt);
            }
        });
        miPanel.add(btnMatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        miPanel.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(604, 21, -1, -1));

        btnDetener.setText("Pausar");
        btnDetener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetenerActionPerformed(evt);
            }
        });
        miPanel.add(btnDetener, new org.netbeans.lib.awtextra.AbsoluteConstraints(681, 21, -1, -1));

        areaProcess.setColumns(20);
        areaProcess.setRows(5);
        jScrollPane1.setViewportView(areaProcess);
        areaProcess.setLineWrap(true);

        miPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 105, -1, 240));

        lbProceso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        miPanel.add(lbProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 510, 60, 20));

        jLabel2.setText("Tabla de Procesos");
        miPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 83, -1, -1));

        miTabla = new JTable() {
            public boolean isCellEditable(int row,int columnn){
                for(int i=0;i<miTabla.getRowCount();i++){
                    if(row == i){
                        return false;
                    }
                }
                return true;
            }
        };
        miTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        miTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                miTablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(miTabla);

        miPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 672, 227));

        areaBloqueado.setColumns(20);
        areaBloqueado.setRows(5);
        jScrollPane3.setViewportView(areaBloqueado);
        areaBloqueado.setLineWrap(true);
        areaBloqueado.setEditable(false);

        miPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 110, 120));

        areaListos.setColumns(20);
        areaListos.setRows(5);
        jScrollPane4.setViewportView(areaListos);
        areaListos.setLineWrap(true);
        areaListos.setEditable(false);

        miPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 110, 120));

        areaSuspLis.setColumns(20);
        areaSuspLis.setRows(5);
        jScrollPane5.setViewportView(areaSuspLis);
        areaSuspLis.setLineWrap(true);
        areaSuspLis.setEditable(false);

        miPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 340, 100, 120));

        areaSusBlo.setColumns(20);
        areaSusBlo.setRows(5);
        jScrollPane6.setViewportView(areaSusBlo);
        areaSusBlo.setLineWrap(true);
        areaSusBlo.setEditable(false);

        miPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 540, 100, 120));
        miPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        jLabel5.setText("Arbol de Procesos");
        miPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel7.setText("Procesos Finalizados");
        miPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        btnCap.setText("Capturar");
        btnCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapActionPerformed(evt);
            }
        });
        miPanel.add(btnCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, -1));

        btnGen.setText("Generar");
        btnGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenActionPerformed(evt);
            }
        });
        miPanel.add(btnGen, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        lblIm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Bradley/proceso3.png"))); // NOI18N
        miPanel.add(lblIm, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 590, 330));
        miPanel.add(lblCrono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, 110, 30));

        areaFinalizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(areaFinalizados);

        miPanel.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 170, 220));

        menuProcesos.setText("Procesos");

        mnuFIF.setText("Tabla FIFO");
        mnuFIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFIFActionPerformed(evt);
            }
        });
        menuProcesos.add(mnuFIF);

        mnuSJN.setText("Tabla SJN");
        mnuSJN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSJNActionPerformed(evt);
            }
        });
        menuProcesos.add(mnuSJN);

        mnuRR.setText("Tabla RR");
        mnuRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRRActionPerformed(evt);
            }
        });
        menuProcesos.add(mnuRR);

        mnuSRT.setText("Tabla SRT");
        mnuSRT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSRTActionPerformed(evt);
            }
        });
        menuProcesos.add(mnuSRT);

        mnuHRN.setText("Tabla HRN");
        mnuHRN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHRNActionPerformed(evt);
            }
        });
        menuProcesos.add(mnuHRN);

        jMenuBar1.add(menuProcesos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(miPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(miPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed

        btnCrear.setEnabled(false);
        btnIniciar.setEnabled(false);
        btnMatar.setEnabled(true);
        btnDetener.setEnabled(true);
        btnCap.setVisible(false);
        btnGen.setVisible(false);
        
        
        
        
        algo = (String) JOptionPane.showInputDialog(null,"Seleccione Un Algoritmo",
                "ALGORITMOS", JOptionPane.QUESTION_MESSAGE, null,
                new Object[] { "FIFO", "SJF", "RR","SRT","HRN"},"FIFO");
        
        switch (algo){
            case ("FIFO"):
                aux = -1;
                miMod.setRowCount(0);
                areaFinalizados.setModel(miMod);
                int length = index+1;
                FIFO1 ob = new FIFO1(procesos, length);
                Thread hilo = new Thread(ob);
                hilo.start();
                
                try {
                tarea.run();
                timerPrincipal.schedule(tarea, 0, 1000);
                } catch(IllegalStateException e){}
                
                break;
                
            case ("SJF"):
                aux = -1;
                miMod.setRowCount(0);
                areaFinalizados.setModel(miMod);
                length = index+1;
                SJF1 obj = new SJF1(procesos, length);
                Thread hilo1 = new Thread(obj);
                hilo1.start();
                
                try {
                tarea.run();
                timerPrincipal.schedule(tarea, 0, 1000);
                } catch(IllegalStateException e){}
                
                break;
                
            case ("RR"):
                aux = -1;

                areaFinalizados.setModel(miMod);
                quantum=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tiempo de Quantum"));
                quantum=quantum*1000;
                length = index+1;
                RR rr=new RR(procesos, length);
                Thread hiloRR = new Thread(rr);
                hiloRR.start();
                
                try {
                tarea.run();
                timerPrincipal.schedule(tarea, 0, 1000);
                } catch(IllegalStateException e){}
                
                break;
            
            case ("SRT"):
                aux = -1;
                miMod.setRowCount(0);
                areaFinalizados.setModel(miMod);
                length = index+1;
                SRT srt = new SRT(procesos, length);
                Thread hiloSRT = new Thread(srt);
                hiloSRT.start();
                
                
                try {
                tarea.run();
                timerPrincipal.schedule(tarea, 0, 1000);
                } catch(IllegalStateException e){}
                
                break;
                
            case ("HRN"):
                aux = -1;
                miMod.setRowCount(0);
                areaFinalizados.setModel(miMod);
                length = index+1;
                HRN_1 hrn = new HRN_1(procesos, length);
                Thread hiloHRN_1 = new Thread(hrn);
                hiloHRN_1.start();
                
                
                try {
                tarea.run();
                timerPrincipal.schedule(tarea, 0, 1000);
                } catch(IllegalStateException e){}
                
                break;    
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        btnCap.setVisible(true);
        btnGen.setVisible(true);
    }//GEN-LAST:event_btnCrearActionPerformed

    private void miTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miTablaMouseClicked
        String id;
        int row;
        if(evt.getClickCount() == 2){
            row=miTabla.getSelectedRow();
            System.out.println("Row: "+row);
            process(row);
            String pro=areaProcess.getText();
            pro += "\n" +(String)miTabla.getValueAt(row,0)+"          "+miTabla.getValueAt(row,1);
            
            areaProcess.setText(pro);
            ProcesosRestantes++;
            btnIniciar.setEnabled(true);
        }
    }//GEN-LAST:event_miTablaMouseClicked
    
    public void process(int row){
        Proceso p=new Proceso();
        System.out.println("Index: "+index);
        String id =(String)miTabla.getValueAt(row,0);
        String Nombre=(String)miTabla.getValueAt(row,1);
        String Tiempo=(String)miTabla.getValueAt(row,2);
        
        p.setId(id);
        p.setNombre(Nombre);
        p.setLlegada(k);
        p.setTiempo(Tiempo);
        procesos[index]=p;
        System.out.println("Proceso: "+procesos[index].nombre+" Index: "+index);
        index ++;
        contador++;
        k += r.nextInt(2);
    }
    
    public void procesosa(int n){
        System.out.println("entro al metodo con "+n);
        String prio[] = {"1","5","10"};
        
        Random r= new Random ();
        
        
        for(int nn=1; nn<=n; nn++){
            System.out.println("entro a ciclo");
            String ida="";
            String noma="";
            String mema="";
            String num=String.valueOf(nn);
            ida=num;
            noma="Proceso "+num;
            int ma=r.nextInt(4000);
            String mea=String.valueOf(ma);
            String estaa="Listo";
            int qa=r.nextInt(10)+1;
            String qqa = String.valueOf(qa);
            int ale = r.nextInt(3);
            String priod = prio[ale];
            int ta = r.nextInt(10)+1;
            String tiea=String.valueOf(ta);
            
            procesosTabla[0]=ida;
            procesosTabla[1]=noma;
            procesosTabla[2]=tiea;
            miModelo.addRow(procesosTabla);
            
        }
        
        
        
    }
    
    /*public Icon setIcono(String url, JLabel lblIm){
        ImageIcon icon = new ImageIcon(getClass().getResource(url));

        int ancho = lblIm.getWidth();
        int alto = lblIm.getHeight();

        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        return icono;
    }*/
    
    private void btnDetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetenerActionPerformed
        btnCrear.setEnabled(true);
        btnIniciar.setEnabled(true);
        btnMatar.setEnabled(false);
        btnDetener.setEnabled(false);
    }//GEN-LAST:event_btnDetenerActionPerformed

    private void btnMatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatarActionPerformed
        
    }//GEN-LAST:event_btnMatarActionPerformed

    private void btnCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapActionPerformed
        CrearProceso p = new CrearProceso();
        btnGen.setEnabled(false);
        btnIniciar.setBackground(Color.green);
    }//GEN-LAST:event_btnCapActionPerformed

    private void btnGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenActionPerformed
        btnCap.setEnabled(false);
        btnIniciar.setBackground(Color.green);
        try {
            String res=JOptionPane.showInputDialog(null,"¿Cuantos procesos desea generar?",JOptionPane.QUESTION_MESSAGE);
            System.out.println(res);

            int nn=Integer.parseInt(res);

            procesosa(nn); 
        } catch(NumberFormatException e) {
        }
    }//GEN-LAST:event_btnGenActionPerformed

    private void mnuFIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFIFActionPerformed
        TabFIFO n = new TabFIFO();
        n.setVisible(true);
        
        DefaultTableModel tabF = new DefaultTableModel();
        Object taF[] = new Object[7];
        
        tabF.addColumn("Nombre");
        tabF.addColumn("Instante llegada");
        tabF.addColumn("Tiempo eje");
        tabF.addColumn("Instante fin");
        tabF.addColumn("Tiempo servicio");
        tabF.addColumn("Tiempo espera");
        tabF.addColumn("Indice servicio");
        TabFIFO.tabFF.setModel(tabF);
        int llega[] = new int [vecFinal.length];
        
        for (int i = 0; i < vecFinal.length; i++){
            if(vecFinal[i] != null){
                try {
                taF[0] = vecFinal[i].nombre;
                //System.out.println(vecFinal[i].nombre + "  " + vecFinal[i].llegada);
                taF[1] = vecFinal[i].llegada;
                taF[2] = vecFinal[i].tiempo / 1000;
                taF[3] = vecFinal[i].fin;
                taF[4] = vecFinal[i].fin - vecFinal[i].llegada;
                taF[5] = (vecFinal[i].fin - vecFinal[i].llegada) - (vecFinal[i].tiempo / 1000);
                
                double inxSer = (double)(vecFinal[i].tiempo / 1000) /  (double)(vecFinal[i].fin - vecFinal[i].llegada);
                System.out.println(inxSer);
                String ser = String.valueOf(inxSer) + "0";
                
                taF[6] = ser.substring(0, 4);;
                tabF.addRow(taF);
                
                } catch (NullPointerException e) {
                    
                    System.out.println("nada");
                    
                }
            }
        }
        
        TabFIFO.tabFF.setModel(tabF);
    }//GEN-LAST:event_mnuFIFActionPerformed

    private void mnuSRTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSRTActionPerformed
        TabSRT n = new TabSRT();
        n.setVisible(true);
        
        DefaultTableModel tabF = new DefaultTableModel();
        Object taF[] = new Object[7];
        
        tabF.addColumn("Nombre");
        tabF.addColumn("Instante llegada");
        tabF.addColumn("Tiempo eje");
        tabF.addColumn("Instante fin");
        tabF.addColumn("Tiempo servicio");
        tabF.addColumn("Tiempo espera");
        tabF.addColumn("Indice servicio");
        TabSRT.tabSrt.setModel(tabF);
        
        for (int i = 0; i < vecFinal.length; i++){
            if(vecFinal[i] != null){
                try {
                taF[0] = vecFinal[i].nombre;
                //System.out.println(vecFinal[i].nombre + "  " + vecFinal[i].llegada);
                taF[1] = vecFinal[i].llegada;
                taF[2] = vecFinal[i].tiempo / 1000;
                taF[3] = vecFinal[i].fin;
                taF[4] = vecFinal[i].fin - vecFinal[i].llegada;
                taF[5] = (vecFinal[i].fin - vecFinal[i].llegada) - (vecFinal[i].tiempo / 1000);
                
                double inxSer = (double)(vecFinal[i].tiempo / 1000) /  (double)(vecFinal[i].fin - vecFinal[i].llegada);
                System.out.println(inxSer);
                String ser = String.valueOf(inxSer) + "0";
                
                taF[6] = ser.substring(0, 4);;
                tabF.addRow(taF);
                
                } catch (NullPointerException e) {
                    
                    System.out.println("nada");
                    
                }
            }
        }
        
        TabSRT.tabSrt.setModel(tabF);
    }//GEN-LAST:event_mnuSRTActionPerformed

    private void mnuSJNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSJNActionPerformed
        TabSJN n = new TabSJN();
        n.setVisible(true);
        
        DefaultTableModel tabF = new DefaultTableModel();
        Object taF[] = new Object[7];
        
        tabF.addColumn("Nombre");
        tabF.addColumn("Instante llegada");
        tabF.addColumn("Tiempo eje");
        tabF.addColumn("Instante fin");
        tabF.addColumn("Tiempo servicio");
        tabF.addColumn("Tiempo espera");
        tabF.addColumn("Indice servicio");
        TabSJN.tabSjn.setModel(tabF);
        
        for (int i = 0; i < vecFinal.length; i++){
            if(vecFinal[i] != null){
                try {
                taF[0] = vecFinal[i].nombre;
                //System.out.println(vecFinal[i].nombre + "  " + vecFinal[i].llegada);
                taF[1] = vecFinal[i].llegada;
                taF[2] = vecFinal[i].tiempo / 1000;
                taF[3] = vecFinal[i].fin;
                taF[4] = vecFinal[i].fin - vecFinal[i].llegada;
                taF[5] = (vecFinal[i].fin - vecFinal[i].llegada) - (vecFinal[i].tiempo / 1000);
                
                double inxSer = (double)(vecFinal[i].tiempo / 1000) /  (double)(vecFinal[i].fin - vecFinal[i].llegada);
                System.out.println(inxSer);
                String ser = String.valueOf(inxSer) + "0";
                
                taF[6] = ser.substring(0, 4);;
                tabF.addRow(taF);
                
                } catch (NullPointerException e) {
                    
                    System.out.println("nada");
                    
                }
            }
        }
        
        TabSJN.tabSjn.setModel(tabF);
    }//GEN-LAST:event_mnuSJNActionPerformed

    private void mnuRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRRActionPerformed
        TabRR n = new TabRR();
        n.setVisible(true);
        
        DefaultTableModel tabF = new DefaultTableModel();
        Object taF[] = new Object[7];
        
        tabF.addColumn("Nombre");
        tabF.addColumn("Instante llegada");
        tabF.addColumn("Tiempo eje");
        tabF.addColumn("Instante fin");
        tabF.addColumn("Tiempo servicio");
        tabF.addColumn("Tiempo espera");
        tabF.addColumn("Indice servicio");
        TabRR.tabRr.setModel(tabF);
        
        for (int i = 0; i < vecFinal.length; i++){
            if(vecFinal[i] != null){
                try {
                taF[0] = vecFinal[i].nombre;
                //System.out.println(vecFinal[i].nombre + "  " + vecFinal[i].llegada);
                taF[1] = vecFinal[i].llegada;
                taF[2] = vecFinal[i].tiempo / 1000;
                taF[3] = vecFinal[i].fin;
                taF[4] = vecFinal[i].fin - vecFinal[i].llegada;
                taF[5] = (vecFinal[i].fin - vecFinal[i].llegada) - (vecFinal[i].tiempo / 1000);
                
                double inxSer = (double)(vecFinal[i].tiempo / 1000) /  (double)(vecFinal[i].fin - vecFinal[i].llegada);
                System.out.println(inxSer);
                String ser = String.valueOf(inxSer) + "0";
                
                taF[6] = ser.substring(0, 4);;
                tabF.addRow(taF);
                
                } catch (NullPointerException e) {
                    
                    System.out.println("nada");
                    
                }
            }
        }
        
        TabRR.tabRr.setModel(tabF);
    }//GEN-LAST:event_mnuRRActionPerformed

    private void mnuHRNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHRNActionPerformed
        TabHRN n = new TabHRN();
        n.setVisible(true);
        
        DefaultTableModel tabF = new DefaultTableModel();
        Object taF[] = new Object[7];
        
        tabF.addColumn("Nombre");
        tabF.addColumn("Instante llegada");
        tabF.addColumn("Tiempo eje");
        tabF.addColumn("Instante fin");
        tabF.addColumn("Tiempo servicio");
        tabF.addColumn("Tiempo espera");
        tabF.addColumn("Indice servicio");
        TabHRN.tabHrn.setModel(tabF);
        
        for (int i = 0; i < vecFinal.length; i++){
            if(vecFinal[i] != null){
                try {
                taF[0] = vecFinal[i].nombre;
                //System.out.println(vecFinal[i].nombre + "  " + vecFinal[i].llegada);
                taF[1] = vecFinal[i].llegada;
                taF[2] = vecFinal[i].tiempo / 1000;
                taF[3] = vecFinal[i].fin;
                taF[4] = vecFinal[i].fin - vecFinal[i].llegada;
                taF[5] = (vecFinal[i].fin - vecFinal[i].llegada) - (vecFinal[i].tiempo / 1000);
                
                double inxSer = (double)(vecFinal[i].tiempo / 1000) /  (double)(vecFinal[i].fin - vecFinal[i].llegada);
                System.out.println(inxSer);
                String ser = String.valueOf(inxSer) + "0";
                
                taF[6] = ser.substring(0, 4);;
                tabF.addRow(taF);
                
                } catch (NullPointerException e) {
                    
                    System.out.println("nada");
                    
                }
            }
        }
        
        TabHRN.tabHrn.setModel(tabF);
    }//GEN-LAST:event_mnuHRNActionPerformed
    
    @Override
    public void run(){
        
        
    }
    
    public boolean listoVacio(Proceso[] listo){
        for(int x=0;x<listo.length;x++){
            if((listo[x] != null)&& (x==listo.length-1) ){
                x=0;
            }
        }
        return false;
    }
    public void mostrarListos(Proceso[] listo){
        int y=1;
        for(int x=0;x<listo.length;x++){
            areaListos.setText(listo[x].nombre);
            if(listo[y]== null){
                x=listos.length;
            }
            y++;
        }
    }
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Diagrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Diagrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Diagrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Diagrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Diagrama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea areaBloqueado;
    public static javax.swing.JTable areaFinalizados;
    public static javax.swing.JTextArea areaListos;
    public static javax.swing.JTextArea areaProcess;
    public static javax.swing.JTextArea areaSusBlo;
    public static javax.swing.JTextArea areaSuspLis;
    public static javax.swing.JButton btnCap;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnDetener;
    public static javax.swing.JButton btnGen;
    public static javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnMatar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    public static javax.swing.JLabel lbProceso;
    private javax.swing.JLabel lblCrono;
    private javax.swing.JLabel lblIm;
    private javax.swing.JMenu menuProcesos;
    private javax.swing.JPanel miPanel;
    public static javax.swing.JTable miTabla;
    private javax.swing.JMenuItem mnuFIF;
    private javax.swing.JMenuItem mnuHRN;
    private javax.swing.JMenuItem mnuRR;
    private javax.swing.JMenuItem mnuSJN;
    private javax.swing.JMenuItem mnuSRT;
    // End of variables declaration//GEN-END:variables
}
