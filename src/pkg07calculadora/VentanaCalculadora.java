package pkg07calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class VentanaCalculadora extends JFrame implements KeyListener
{
    /* contenedor general y paneles donde colocaremos los botones */
    JPanel panel, panelNumeros, panelOperaciones;
    /* display de introducción datos y resultados */
    JTextField pantalla;
    /* guarda el resultado de la operacion anterior o el número tecleado */
    double resultado;
    /* para guardar el operador introducido (+,-,*,/) a realizar */
    String operacion;
    /* Indica si estamos iniciando o no una operación */
    boolean nuevaOperacion = true;
 
    /*
     * Constructor. Crea los botones y componentes de la calculadora
     */
    public VentanaCalculadora() 
    {
        super();//crea JFrame como contenedor
        setSize(300, 400);//dimensiones de la ventana contenedora
        setTitle("Calculadora");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setResizable(false);//para que no se pueda modificar las dimensiones de ventana
 
        // Vamos a dibujar sobre el panel
        panel = new JPanel();
        //this.getContentPane().add(panel);//añadimos panel a la ventana principal
        this.add(panel);
        panel.setLayout(new BorderLayout());
 
        pantalla = new JTextField("0");
        pantalla.setBorder(new EmptyBorder(5, 5, 5, 5));//zona donde no escribe (padding de CSS)
        pantalla.setFont(new Font("Arial", Font.BOLD, 25));
       // pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setHorizontalAlignment(SwingConstants.RIGHT);
        pantalla.setEditable(false);//el usuario no puede cambiar el texto
        pantalla.setBackground(Color.WHITE);//fondo del editor
      
        panel.add("North", pantalla);//lo colocamos arriba del panel
 
        panelNumeros = new JPanel();
        panelNumeros.setLayout(new GridLayout(4, 3));//4 filas 3 columas de botones
        panelNumeros.setBorder(new EmptyBorder(5, 5, 5, 5));//espacio libre del panel
 
        for (int n = 9; n >= 0; n--) //creamos botones de numeros  9 a 0
        {
           // nuevoBotonNumerico("" + n);//pasamos el entero como string
           // nuevoBotonNumerico(String.valueOf(n));
            nuevoBotonNumerico(Integer.toString(n));
        }
 
        nuevoBotonNumerico("."); //creamos botón .
//las dimensiones de los botones internos las ajusta a lo que queda(prioridad east) 
        panel.add("Center", panelNumeros);//lo colocamos centrado. No esta west
 
        panelOperaciones = new JPanel();
        panelOperaciones.setLayout(new GridLayout(6, 1));
        panelOperaciones.setBorder(new EmptyBorder(5, 5, 5, 5));
 
        nuevoBotonOperacion("+");
        nuevoBotonOperacion("-");
        nuevoBotonOperacion("*");
        nuevoBotonOperacion("/");
        nuevoBotonOperacion("=");
        nuevoBotonOperacion("CE");
 //las dimensiones de los botones las ajusta a la letra y los coloca a la derecha
        panel.add("East", panelOperaciones);
      //Necesario para que la ventana capture el teclado
        addKeyListener(this);
        this.setFocusable(true);
       
        
    }
    
  
    
    /*
     * Crea un boton del teclado numérico y enlaza sus eventos con el listener
     * correspondiente
     */
    private void nuevoBotonNumerico(String digito) 
    {
        JButton btn = new JButton();
        btn.setForeground(Color.BLUE);//color azul para dígitos
        btn.setText(digito);
        btn.setFocusable(false);
      
        btn.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseReleased(MouseEvent e) 
            {//btn lo tengo que crear cada vez al implementar la escucha
                JButton btn = (JButton) e.getSource();
                //JOptionPane.showMessageDialog(null,btn.getName());
               // JOptionPane.showMessageDialog(null,e.getSource());
                numeroPulsado(btn.getText());
                System.out.print("click");
            }
        });
        panelNumeros.add(btn);//los va metiendo en el panel correspondiente
    }
    /*
     * Crea un botón de operacion y lo enlaza con sus eventos.
     */
    private void nuevoBotonOperacion(String operacion) 
    {
        JButton btn = new JButton(operacion);
        btn.setForeground(Color.RED);//color rojo para operadores
        btn.setFocusable(false);
        btn.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseReleased(MouseEvent e) 
            {
                JButton btn = (JButton) e.getSource();
                operacionPulsado(btn.getText());
            }
        });
        panelOperaciones.add(btn);
    }
    /*
     * Gestiona las pulsaciones de teclas numéricas
     * 
     * digito es  tecla pulsada
     */
    private void numeroPulsado(String digito) 
    {
        if (pantalla.getText().equals("0") || nuevaOperacion) 
        {
            pantalla.setText(digito);
        } else 
        {
            pantalla.setText(pantalla.getText() + digito);
        }
        nuevaOperacion = false;
        
        
    }
    /*
     * Gestiona las pulsaciones de teclas de operación
     * 
     */
    private void operacionPulsado(String tecla) 
    {
        if (tecla.equals("=")) 
        {
            calcularResultado();
        } else if (tecla.equals("CE")) 
        {
            resultado = 0;
            pantalla.setText("");
            nuevaOperacion = true;
        } else 
        {
            operacion = tecla;
            if ((resultado > 0) && !nuevaOperacion) 
            {
                calcularResultado();
            } else 
            {
                resultado = new Double(pantalla.getText());
            }
        }
        nuevaOperacion = true;
    }
 
    /*
     * Calcula el resultado y lo muestra por pantalla
     */
    private void calcularResultado() 
    {
        if (operacion.equals("+")) 
        {
            resultado += new Double(pantalla.getText());
        } else if (operacion.equals("-")) 
        {
            resultado -= new Double(pantalla.getText());
        } else if (operacion.equals("/")) 
        {
            resultado /= new Double(pantalla.getText());
        } else if (operacion.equals("*")) 
        {
            resultado *= new Double(pantalla.getText());
        }
 
        pantalla.setText("" + resultado);
        operacion = "";
    }
    
     
      @Override
      public void keyPressed(KeyEvent e) {
     //    if (e.getKeyCode()==49){System.out.print(numeroPulsado(e.getKeyCode;}
     //Llamamos al método número pulsado, tambien cuando pulsan un número en el teclado, tanto el númerico como el de caracteres
     //Pasando previamente el caracter a string
    
        if((e.getKeyCode()>=48 && e.getKeyCode()<=57) || (e.getKeyCode()>=96 && e.getKeyCode()<=105)){
           
            numeroPulsado(String.valueOf(e.getKeyChar()));
             // if (e.getKeyCode()==KeyEvent.v){System.out.print("Has pulsado un 1");}
         }
        
        //Llamamos a operación utilizando getKeyChar en caso de que sean  + - / **
        if (e.getKeyChar() == '+' || e.getKeyChar() == '-' || e.getKeyChar() == '/' ||  e.getKeyChar() == '*'){
           operacionPulsado(String.valueOf(e.getKeyChar()));
        }
        
        //Pasamos como parámetro un igual tanto si pulsan enter como si pulsan igual
         if (e.getKeyCode() == 10 || e.getKeyChar() == '='){
           operacionPulsado(String.valueOf("="));
        }
         
         //Pasamos como parámetro un CE en caso de que pulsen la tecla borrar
         if (e.getKeyCode() == 8 ){
           operacionPulsado(String.valueOf("CE"));
        }
        System.out.println("Has pulsado: " + e.getKeyCode());
         System.out.println("tecla pulsada pulsado: " + e.getKeyChar());
        
        
      }
      
      
      @Override
      public void keyReleased(KeyEvent e) {
      }
      @Override
      public void keyTyped(KeyEvent e) {}

}
