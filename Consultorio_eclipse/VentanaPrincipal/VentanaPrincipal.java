package VentanaPrincipal;

import Utilitario.Path;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JDesktopPane;
import java.io.File;
import java.net.URI;
import java.net.URL;
import javax.swing.JButton;
import java.awt.Point;

public class VentanaPrincipal {

    private JFrame ventana = null;
    private JPanel p_ventana = null;
    private JMenuBar menuBar = null;
    private JMenu m_conexion = null;
    private JMenuItem mi_desconectar = null;
    private GestorVentanaPrincipal gestor;  //  @jve:decl-index=0:
    private JMenu m_historias = null; 
    private JMenuItem mi_crearhistoria = null;
    private JMenuItem mi_buscarHistoria = null;
    private JMenuItem mi_salir = null;
    private JMenuItem mi_minimizar = null;
    private JMenuItem mi_conectar = null;
    private JMenuItem mi_servidor = null;
    private JPanel panel_accesos = null;
    private JDesktopPane desktopPanel = null;
    private String urlArchivos = null;
	private JButton btn_acceso1 = null;
	private JButton btn_acceso3 = null;
	private JButton btn_acceso2 = null;
	private JMenu m_mantenimiento = null;
	private JMenu m_usuarios = null;
	private JMenuItem mi_nuevoUsuario = null;
	private JMenuItem mi_buscarUsuario = null;
	private JMenuItem mi_reporteHistorias = null;
	private SystemTray tray = null;
    private TrayIcon trayIcon = null;
    private javax.swing.JMenuItem menuItemRestore;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JPopupMenu popupContextual;
    private javax.swing.JSeparator separator;
	private JMenuItem mi_vacunas = null;
	private JMenu m_reportes = null;
	private JMenuItem mi_reportesDocx = null;
	private JMenuItem mi_agregarMedicamentos = null;
	private JButton btn_mensajes = null;
	private JMenu m_caja = null;
	private JMenuItem mi_agregarRegistroCaja = null;
	private JMenuItem mi_agregarConceptos = null;
	private JMenuItem mi_agregarTipoPago = null;
	private JMenu m_mantenerCaja = null;
	private JMenu m_mantenerInventario = null;
	private JMenuItem mi_agregarProducto = null;
	private JMenuItem mi_buscarProducto = null;
	private JMenuItem mi_agregarTipoProducto = null;
	private JMenu m_inventario = null;
	private JButton btn_acceso4 = null;
	private JButton btn_acceso5 = null;
	private JMenuItem mi_ordenPacientes = null;
	private JButton btn_acceso6 = null;
	private JMenuItem mi_reporteInventario = null;
	private JMenuItem mi_Eventos = null;
	private JButton btn_acceso7 = null;
	private JMenuItem mi_calculadora = null;
	private JMenu m_mantenerExamenes = null;
	private JMenuItem mi_agregarTipoExamen = null;
	private JMenu m_ayuda = null;
	private JMenuItem mi_acerca = null;
	private JMenuItem mi_manual = null;
	public VentanaPrincipal() {
        getVentana();
        getTray();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setSize((int)((d.getHeight()-100)*1.33), (int)d.getHeight()-100);
        ventana.setLocation((int) (d.getWidth() / 2 - ventana.getWidth() / 2), (int) (d.getHeight() / 2 - ventana.getHeight() / 2));
        ubicarComponentes();
    }

    public JFrame getVentana() {
        if (ventana == null) {
            ventana = new JFrame();
            ventana.setSize(new Dimension(800, 600));
            ventana.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            ventana.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Files/Imagenes/Image046.png")));
            JFrame.setDefaultLookAndFeelDecorated(true);
            ventana.setJMenuBar(getMenuBar());
            ventana.setMinimumSize(new Dimension(800, 600));
            ventana.setTitle("Sistema de Control de Consultas Dr. José Sierra");
            ventana.setContentPane(getP_ventana());
            ventana.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    salir();
                }
            });
            ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
                //@Override
                public void componentResized(java.awt.event.ComponentEvent evt) {
                    ubicarComponentes();
                }
            });
        }
        return ventana;
    }

    public void salir() {
        if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea Salir?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {            
            gestor.salir();
            
            //System.exit(0);
        }
    }

    public void setConectadoServidor(boolean aFlag) {
        mi_conectar.setEnabled(aFlag);
        mi_desconectar.setEnabled(false);
    }

    public void setConectado(boolean aFlag) {
        mi_conectar.setEnabled(!aFlag);
        mi_desconectar.setEnabled(aFlag);
    }

    public void setNivelUsuario(int n){
    	if(n == -1){
    		m_historias.setEnabled(false);
    		m_reportes.setEnabled(false);
    		m_mantenimiento.setEnabled(false);
    		m_caja.setEnabled(false);
    		m_inventario.setEnabled(false);
    		btn_acceso1.setEnabled(false);
    		btn_acceso2.setEnabled(false);
            m_usuarios.setVisible(false);
            btn_mensajes.setVisible(false);
            btn_acceso3.setEnabled(false);
            btn_acceso4.setEnabled(false);
            btn_acceso5.setEnabled(false);
            btn_acceso6.setEnabled(false);
            btn_acceso7.setEnabled(false);
    	}
    	else {
    		if(n <= 3){		//Habilito los del usuario 3 (Min)    	
	    		m_historias.setEnabled(true);
	    		m_reportes.setEnabled(true);
	    		btn_acceso1.setEnabled(true);
	    		btn_acceso2.setEnabled(true);
	    		//btn_mensajes.setVisible(true);
	    		m_caja.setEnabled(true);
	    		m_inventario.setEnabled(true);
	    		btn_acceso3.setEnabled(true);
	    		btn_acceso4.setEnabled(true);
	    		btn_acceso5.setEnabled(true);
	    		btn_acceso6.setEnabled(true);
	    		btn_acceso7.setEnabled(true);
	    	}
	    	if(n <= 2){		//Habilito los del usuario 2 (Medio)
	    		m_mantenimiento.setEnabled(true);
	    	}
	    	if(n <= 1){		//Habilito los del usuario 1 (Max)
                    m_usuarios.setVisible(true);
	    		//Inventario
	    	}
    	}
    }
    
    private JPanel getP_ventana() {
        if (p_ventana == null) {
            p_ventana = new JPanel();
            p_ventana.setBackground(Color.GRAY);
            p_ventana.setLayout(null);
            p_ventana.add(getPanel_accesos());
            p_ventana.add(getDesktopPanel());
        }
        return p_ventana;
    }

    public void setVisible(boolean aFlag) {
        ventana.setVisible(aFlag);
    }

    public void ubicarComponentes() {
        panel_accesos.setSize(ventana.getWidth(), panel_accesos.getHeight());
        desktopPanel.setSize(ventana.getWidth(), ventana.getHeight()-panel_accesos.getHeight());
        btn_mensajes.setLocation(desktopPanel.getWidth()-150, desktopPanel.getHeight()-105);
    }

    /**
     * This method initializes menuBar
     *
     * @return javax.swing.JMenuBar
     */
    private JMenuBar getMenuBar() {
        if (menuBar == null) {
            menuBar = new JMenuBar();
            menuBar.add(getM_historias());
            menuBar.add(getM_caja());
            menuBar.add(getM_inventario());
            menuBar.add(getM_reportes());
            menuBar.add(getM_mantenimiento());
            menuBar.add(getM_conexion());
            menuBar.add(getM_ayuda());
        }
        return menuBar;
    }

    private JMenu getM_historias() {
        if (m_historias == null) {
            m_historias = new JMenu();
            m_historias.setText("Historias");
            m_historias.setMnemonic(KeyEvent.VK_H);
            m_historias.setFont(new Font("Dialog", Font.BOLD, 14));
            m_historias.setForeground(Color.black);
            //m_historias.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image016.png")));
            m_historias.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image016.png")));
            m_historias.add(getMi_crearHistoria());
            m_historias.add(getMi_buscarHistoria());
            m_historias.add(getMi_ordenPacientes());
        }
        return m_historias;
    }
    
    private JMenuItem getMi_crearHistoria() {
        if (mi_crearhistoria == null) {
            mi_crearhistoria = new JMenuItem();
            mi_crearhistoria.setText("Crear historia");
            //mi_crearhistoria.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image015.png")));
            mi_crearhistoria.setFont(new Font("Dialog", Font.BOLD, 14));
            mi_crearhistoria.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image015.png")));
            mi_crearhistoria.setMnemonic(KeyEvent.VK_C);
            mi_crearhistoria.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	gestor.crearNuevaHistoria();
                }
            });
        }
        return mi_crearhistoria;
    }
    
    private JMenuItem getMi_buscarHistoria() {
        if (mi_buscarHistoria == null) {
        	mi_buscarHistoria = new JMenuItem();
            mi_buscarHistoria.setText("Buscar historias");
            //mi_verExamenes.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image017.png")));
            mi_buscarHistoria.setFont(new Font("Dialog", Font.BOLD, 14));
            mi_buscarHistoria.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image034.png")));
            mi_buscarHistoria.setMnemonic(KeyEvent.VK_B);
            mi_buscarHistoria.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	//carga barra=new carga();
                	//System.out.println("Inicio...");
            		//barra.getJWindow().setVisible(true);
                	/*barra.mostrar();
                	frmImagen ventanaIma = new frmImagen();
                    desktopPanel.add(new FrmInternoVisorIma(ventanaIma).getVisorIma(), null);
                    desktopPanel.add(ventanaIma.getVtaImagen(), null);
                    desktopPanel.setSelectedFrame(ventanaIma.getVtaImagen());
                    barra.ocultar();*/
                    //System.out.println("Fin...");
                	gestor.buscarHistoria();
                }
            });
        }
        return mi_buscarHistoria;
    }
    
    /**
     * This method initializes m_conexion
     *
     * @return javax.swing.JMenu
     */
    private JMenu getM_conexion() {
        if (m_conexion == null) {
            m_conexion = new JMenu();
            m_conexion.setText("Aplicación");
            m_conexion.setMnemonic(KeyEvent.VK_A);
            m_conexion.setFont(new Font("Dialog", Font.BOLD, 14));
            //m_conexion.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image005.png")));
            m_conexion.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image004.png")));
            m_conexion.setForeground(Color.black);
            m_conexion.add(getMi_conectar());
            m_conexion.add(getMi_desconectar());
            m_conexion.add(getMi_servidor());
            m_conexion.add(getMi_minimizar());
            m_conexion.add(getMi_salir());
        }
        return m_conexion;
    }

    /**
     * This method initializes mi_desconectar
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMi_desconectar() {
        if (mi_desconectar == null) {
            mi_desconectar = new JMenuItem();
            mi_desconectar.setText("Cerrar Sesión");
            //mi_desconectar.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image001.png")));
            mi_desconectar.setFont(new Font("Dialog", Font.BOLD, 14));
            mi_desconectar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image001.png")));
            mi_desconectar.setMnemonic(KeyEvent.VK_C);
            mi_desconectar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {                
                	if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea cerrar sesión?\nSe cerarrán todas las ventanas.", "Cerrar Sesión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
                		JInternalFrame vec[] = desktopPanel.getAllFrames();
                    	for(int i=0; i<vec.length; i++){
                    		vec[i].dispose();
                    	}
                    	mi_desconectar.setEnabled(false);
                    	mi_conectar.setEnabled(true);
                    	gestor.desconectar();
                	}	
                }
            });
        }
        return mi_desconectar;
    }

    public void setGestor(GestorVentanaPrincipal g) {
        gestor = g;
    }

    public GestorVentanaPrincipal getGestor() {
        return gestor;
    }

    /**
     * This method initializes mi_conectar
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMi_conectar() {
        if (mi_conectar == null) {
            mi_conectar = new JMenuItem();
            mi_conectar.setText("Iniciar Sesión");
            //mi_conectar.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image001.png")));
            mi_conectar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image002.png")));
            mi_conectar.setFont(new Font("Dialog", Font.BOLD, 14));
            mi_conectar.setMnemonic(KeyEvent.VK_I);
            mi_conectar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    gestor.mostrarIniciarSesion();
                }
            });
        }
        return mi_conectar;
    }

    /**
     * This method initializes mi_servidor
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMi_servidor() {
        if (mi_servidor == null) {
            mi_servidor = new JMenuItem();
            mi_servidor.setMnemonic(KeyEvent.VK_S);
            //mi_servidor.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image003.png")));
            mi_servidor.setFont(new Font("Dialog", Font.BOLD, 14));
            mi_servidor.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image003.png")));
            mi_servidor.setText("Configurar Servidor");
            mi_servidor.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    gestor.mostrarConfigurarServidor();
                }
            });
        }
        return mi_servidor;
    }

    /**
     * This method initializes mi_servidor
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMi_salir() {
        if (mi_salir == null) {
            mi_salir = new JMenuItem();
            mi_salir.setMnemonic(KeyEvent.VK_S);
            mi_salir.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image008.png")));
            //mi_salir.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image008.png")));
            mi_salir.setFont(new Font("Dialog", Font.BOLD, 14));
            mi_salir.setText("Salir");
            mi_salir.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    salir();
                }
            });
        }
        return mi_salir;
    }

    /**
     * This method initializes mi_servidor
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMi_minimizar() {
        if (mi_minimizar == null) {
            mi_minimizar = new JMenuItem();
            mi_minimizar.setName("");
            mi_minimizar.setMnemonic(KeyEvent.VK_M);
            mi_minimizar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image009.png")));
            //mi_minimizar.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image009.png")));
            mi_minimizar.setFont(new Font("Dialog", Font.BOLD, 14));
            mi_minimizar.setText("Minimizar");
            mi_minimizar.addActionListener(new java.awt.event.ActionListener() {   
            	public void actionPerformed(java.awt.event.ActionEvent e) {    
            		EstadoCambiado();
                    trayIcon.displayMessage("Consultorio Dr.José Sierra", "El programa se está ejecutando...",TrayIcon.MessageType.INFO);
            	}
            
            });
        }
        return mi_minimizar;
    }

    private JPanel getPanel_accesos() {
        if (panel_accesos == null) {
            panel_accesos = new JPanel();
            panel_accesos.setLayout(null);
            panel_accesos.setSize(800, 70);
            panel_accesos.setLocation(0, 0);
            panel_accesos.add(getBtn_acceso1(), null);
            panel_accesos.add(getBtn_acceso3(), null);
            panel_accesos.add(getBtn_acceso2(), null);
            panel_accesos.add(getBtn_acceso4(), null);
            panel_accesos.add(getBtn_acceso5(), null);
            panel_accesos.add(getBtn_acceso6(), null);
            panel_accesos.add(getBtn_acceso7(), null);
        }
        return panel_accesos;
    }

    public JDesktopPane getDesktopPanel() {
        if (desktopPanel == null) {
            desktopPanel = new JDesktopPane();
            desktopPanel.setBackground(Color.WHITE);
            desktopPanel.setSize(800, 460);
            desktopPanel.setLocation(0, 70);
            desktopPanel.setDoubleBuffered(true);
            desktopPanel.add(getBtn_mensajes(), null);
        }
        return desktopPanel;
    }

    public String getRutaArchivos(String path){
        if(urlArchivos == null || urlArchivos.compareTo("") == 0){
            URI n = null;
            urlArchivos = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
            try {
                File aux = new File(new URL(urlArchivos).toURI());
                n = aux.getParentFile().toURI();
            } catch (Exception ex) {
            }
            urlArchivos = n.toString();
            int val = urlArchivos.indexOf("build");
            if (val > 0) {
                urlArchivos = urlArchivos.replaceAll("build/", "");
            }
        }
        return urlArchivos + path;
    }

    public URL getURLArchivos(String path){
        try{
            return new URL(getRutaArchivos(path));
        }
        catch(Exception ex){
            return null;
        }
    }

	/**
	 * This method initializes btn_acceso1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_acceso1() {
		if (btn_acceso1 == null) {
			btn_acceso1 = new JButton();
			btn_acceso1.setLocation(new Point(0, 0));
			btn_acceso1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image047.png")));
			btn_acceso1.setToolTipText("Crear nueva historia");
			btn_acceso1.setSize(new Dimension(70, 70));
			btn_acceso1.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					gestor.crearNuevaHistoria();
				}
			
			});
		}
		return btn_acceso1;
	}

	/**
	 * This method initializes btn_acceso3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_acceso3() {
		if (btn_acceso3 == null) {
			btn_acceso3 = new JButton();
			btn_acceso3.setLocation(new Point(320, 0));
			btn_acceso3.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image091.png")));
			btn_acceso3.setToolTipText("Buscar producto");
			btn_acceso3.setSize(new Dimension(70, 70));
			btn_acceso3.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					//gestor.GestionInventario();
					gestor.buscarProducto();
				}			
			});		
		}
		return btn_acceso3;
	}

	/**
	 * This method initializes btn_acceso2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_acceso2() {
		if (btn_acceso2 == null) {
			btn_acceso2 = new JButton();
			btn_acceso2.setLocation(new Point(80, 0));
			btn_acceso2.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image032.png")));
			btn_acceso2.setToolTipText("Buscar historia");
			btn_acceso2.setSize(new Dimension(70, 70));
			btn_acceso2.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					gestor.buscarHistoria();
				}
			
			});
		}
		return btn_acceso2;
	}

	/**
	 * This method initializes m_mantenimiento	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_mantenimiento() {
		if (m_mantenimiento == null) {
			m_mantenimiento = new JMenu();
			m_mantenimiento.setText("Mantenimiento");
			m_mantenimiento.setMnemonic(KeyEvent.VK_M);
			m_mantenimiento.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image050.png")));
			m_mantenimiento.setFont(new Font("Dialog", Font.BOLD, 14));
			m_mantenimiento.setForeground(Color.black);
			m_mantenimiento.add(getM_mantenerCaja());
			m_mantenimiento.add(getM_mantenerExamenes());
			m_mantenimiento.add(getM_mantenerInventario());
			m_mantenimiento.add(getMi_agregarMedicamentos());
			m_mantenimiento.add(getM_usuarios());		
			m_mantenimiento.add(getMi_vacunas());
		}
		return m_mantenimiento;
	}

	/**
	 * This method initializes m_usuarios	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_usuarios() {
		if (m_usuarios == null) {
			m_usuarios = new JMenu();
			m_usuarios.setText("Mantener usuarios");
			m_usuarios.setFont(new Font("Dialog", Font.BOLD, 14));
			m_usuarios.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image051.png")));
			m_usuarios.setMnemonic(KeyEvent.VK_U);
			m_usuarios.add(getMi_nuevoUsuario());
			m_usuarios.add(getMi_buscarUsuario());
		}
		return m_usuarios;
	}

	/**
	 * This method initializes mi_nuevoUsuario	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_nuevoUsuario() {
		if (mi_nuevoUsuario == null) {
			mi_nuevoUsuario = new JMenuItem();
			mi_nuevoUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_nuevoUsuario.setText("Agregar usuario");
			mi_nuevoUsuario.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image053.png")));
			mi_nuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.nuevoUsuario();
				}
			});
		}
		return mi_nuevoUsuario;
	}

	/**
	 * This method initializes mi_buscarUsuario	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_buscarUsuario() {
		if (mi_buscarUsuario == null) {
			mi_buscarUsuario = new JMenuItem();
			mi_buscarUsuario.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image054.png")));
			mi_buscarUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_buscarUsuario.setText("Buscar usuarios");
			mi_buscarUsuario.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.buscarUsuarios();
				}
			});
		}
		return mi_buscarUsuario;
	}

	/**
	 * This method initializes mi_reporteHistorias	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_reporteHistorias() {
		if (mi_reporteHistorias == null) {
			mi_reporteHistorias = new JMenuItem();
			mi_reporteHistorias.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_reporteHistorias.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image056.png")));
			mi_reporteHistorias.setMnemonic(KeyEvent.VK_H);
			mi_reporteHistorias.setText("Reporte de historias");
			mi_reporteHistorias.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.generarReporteHistorias();
				}
			});
		}
		return mi_reporteHistorias;
	}
	
	public void getTray(){
		java.awt.Image image = new ImageIcon(getClass().getResource("/Files/Imagenes/Image046.png")).getImage();
        trayIcon = new TrayIcon(image, "Consultorio Dr. José Sierra", null);
        trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupContextual.setLocation(e.getX(), e.getY());
                    popupContextual.setInvoker(popupContextual);
                    popupContextual.setVisible(true);
                }     
            }
        });
        trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    ventana.setVisible(true);                                               
                    ventana.toFront();
                    tray.remove(trayIcon);
                }
            }
        });
        
        popupContextual = new javax.swing.JPopupMenu();
        menuItemRestore = new javax.swing.JMenuItem();
        separator = new javax.swing.JSeparator();
        menuItemSalir = new javax.swing.JMenuItem();

        menuItemRestore.setText("Restaurar");
        menuItemRestore.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image009.png")));
        menuItemRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventana.setVisible(true);                                               
                ventana.toFront();
                tray.remove(trayIcon);
            }
        });
        popupContextual.add(menuItemRestore);
        popupContextual.add(separator);

        menuItemSalir.setText("Salir");
        menuItemSalir.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image008.png")));
        menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        popupContextual.add(menuItemSalir);
	}
	
	public void EstadoCambiado() { 
	       ventana.setState(JFrame.NORMAL);  
	       if (SystemTray.isSupported()) {  
	            ventana.setVisible(false);
	            tray = SystemTray.getSystemTray();
	            trayIcon.setImageAutoSize(true);
	            
	             try {
	                tray.add(trayIcon);
	             }
	             catch (Exception e) {                
	                ventana.setVisible(true);
	             }  
	       }
	    }

	/**
	 * This method initializes mi_vacunas	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_vacunas() {
		if (mi_vacunas == null) {
			mi_vacunas = new JMenuItem();
			mi_vacunas.setMnemonic(KeyEvent.VK_V);
			mi_vacunas.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image068.png")));
			mi_vacunas.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_vacunas.setText("Mantener vacunas");
			mi_vacunas.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.gestionarVacunas();
				}
			});
		}
		return mi_vacunas;
	}

	/**
	 * This method initializes m_reportes	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_reportes() {
		if (m_reportes == null) {
			m_reportes = new JMenu();
			m_reportes.setText("Reportes");
			m_reportes.setFont(new Font("Dialog", Font.BOLD, 14));
			m_reportes.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image055.png")));
			m_reportes.setMnemonic(KeyEvent.VK_R);
			m_reportes.setForeground(Color.black);
			m_reportes.add(getMi_reporteHistorias());
			m_reportes.add(getMi_reportesDocx());
		}
		return m_reportes;
	}

	/**
	 * This method initializes mi_reportesDocx	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_reportesDocx() {
		if (mi_reportesDocx == null) {
			mi_reportesDocx = new JMenuItem();
			mi_reportesDocx.setText("Reportes médicos");
			mi_reportesDocx.setMnemonic(KeyEvent.VK_M);
			mi_reportesDocx.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_reportesDocx.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image071.png")));
			mi_reportesDocx.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.mostrarReportesDocx();
				}
			});
		}
		return mi_reportesDocx;
	}

	/**
	 * This method initializes mi_agregarMedicamentos	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_agregarMedicamentos() {
		if (mi_agregarMedicamentos == null) {
			mi_agregarMedicamentos = new JMenuItem();
			mi_agregarMedicamentos.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_agregarMedicamentos.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image072.png")));
			mi_agregarMedicamentos.setMnemonic(KeyEvent.VK_A);
			mi_agregarMedicamentos.setText("Mantener medicamentos");                        
                        mi_agregarMedicamentos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarMedicamentos();
				}
			});
		}
		return mi_agregarMedicamentos;
	}

	/**
	 * This method initializes btn_mensajes	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtn_mensajes() {
		if (btn_mensajes == null) {
			btn_mensajes = new JButton();
			btn_mensajes.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image078.png")));
			btn_mensajes.setSize(new Dimension(130, 30));
			btn_mensajes.setText("Mensajes");
			btn_mensajes.setLocation(new Point(651, 420));
			btn_mensajes.setVisible(false);
			btn_mensajes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.maximizarCliente();
				}
			});
		}
		return btn_mensajes;
	}

	/**
	 * This method initializes m_caja	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_caja() {
		if (m_caja == null) {
			m_caja = new JMenu();
			m_caja.setText("Caja");
			m_caja.setFont(new Font("Dialog", Font.BOLD, 14));
			m_caja.setMnemonic(KeyEvent.VK_C);
			m_caja.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image081.png")));
			m_caja.setForeground(Color.black);
			m_caja.add(getMi_agregarRegistroCaja());
			m_caja.add(getMi_calculadora());
		}
		return m_caja;
	}

	/**
	 * This method initializes mi_agregarRegistroCaja	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_agregarRegistroCaja() {
		if (mi_agregarRegistroCaja == null) {
			mi_agregarRegistroCaja = new JMenuItem();
			mi_agregarRegistroCaja.setText("Registros de caja");
			mi_agregarRegistroCaja.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_agregarRegistroCaja.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image082.png")));
			mi_agregarRegistroCaja.setMnemonic(KeyEvent.VK_A);
			mi_agregarRegistroCaja.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarRegistroCaja();
				}
			});
		}
		return mi_agregarRegistroCaja;
	}

	/**
	 * This method initializes mi_agregarConceptos	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_agregarConceptos() {
		if (mi_agregarConceptos == null) {
			mi_agregarConceptos = new JMenuItem();
			mi_agregarConceptos.setText("Agregar concepto de pago");
			mi_agregarConceptos.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image085.png")));
			mi_agregarConceptos.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_agregarConceptos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarConceptos();
				}
			});
		}
		return mi_agregarConceptos;
	}

	/**
	 * This method initializes mi_agregarTipoPago	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_agregarTipoPago() {
		if (mi_agregarTipoPago == null) {
			mi_agregarTipoPago = new JMenuItem();
			mi_agregarTipoPago.setText("Agregar tipo de pago");
			mi_agregarTipoPago.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_agregarTipoPago.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image084.png")));
			mi_agregarTipoPago.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarTipoPago();
				}
			});
		}
		return mi_agregarTipoPago;
	}

	/**
	 * This method initializes m_mantenerCaja	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_mantenerCaja() {
		if (m_mantenerCaja == null) {
			m_mantenerCaja = new JMenu();
			m_mantenerCaja.setText("Mantener caja");
			m_mantenerCaja.setFont(new Font("Dialog", Font.BOLD, 14));
			m_mantenerCaja.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image082.png")));
			m_mantenerCaja.add(getMi_agregarConceptos());
			m_mantenerCaja.add(getMi_agregarTipoPago());
		}
		return m_mantenerCaja;
	}

	/**
	 * This method initializes m_mantenerInventario	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_mantenerInventario() {
		if (m_mantenerInventario == null) {
			m_mantenerInventario = new JMenu();
			m_mantenerInventario.setText("Mantener inventario");
			m_mantenerInventario.setFont(new Font("Dialog", Font.BOLD, 14));
			m_mantenerInventario.setMnemonic(KeyEvent.VK_I);
			m_mantenerInventario.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image089.png")));
			m_mantenerInventario.add(getMi_agregarTipoProducto());
		}
		return m_mantenerInventario;
	}

	/**
	 * This method initializes mi_agregarProducto	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_agregarProducto() {
		if (mi_agregarProducto == null) {
			mi_agregarProducto = new JMenuItem();
			mi_agregarProducto.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_agregarProducto.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image072.png")));
			mi_agregarProducto.setText("Agregar producto");
			mi_agregarProducto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarProducto();
				}
			});
		}
		return mi_agregarProducto;
	}

	/**
	 * This method initializes mi_buscarProducto	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_buscarProducto() {
		if (mi_buscarProducto == null) {
			mi_buscarProducto = new JMenuItem();
			mi_buscarProducto.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_buscarProducto.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image092.png")));
			mi_buscarProducto.setText("Buscar producto");
			mi_buscarProducto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.buscarProducto();
				}
			});
		}
		return mi_buscarProducto;
	}

	/**
	 * This method initializes mi_agregarTipoProducto	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_agregarTipoProducto() {
		if (mi_agregarTipoProducto == null) {
			mi_agregarTipoProducto = new JMenuItem();
			mi_agregarTipoProducto.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_agregarTipoProducto.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image072.png")));
			mi_agregarTipoProducto.setText("Agregar tipo de producto");
			mi_agregarTipoProducto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarTipoProducto();
				}
			});
		}
		return mi_agregarTipoProducto;
	}

	/**
	 * This method initializes m_inventario	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_inventario() {
		if (m_inventario == null) {
			m_inventario = new JMenu();
			m_inventario.setText("Inventario");
			m_inventario.setForeground(Color.black);
			m_inventario.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image088.png")));
			m_inventario.setMnemonic(KeyEvent.VK_I);
			m_inventario.setFont(new Font("Dialog", Font.BOLD, 14));
			m_inventario.add(getMi_agregarProducto());
			m_inventario.add(getMi_buscarProducto());
			m_inventario.add(getMi_reporteInventario());
			m_inventario.add(getMi_Eventos());
		}
		return m_inventario;
	}

	/**
	 * This method initializes btn_acceso4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_acceso4() {
		if (btn_acceso4 == null) {
			btn_acceso4 = new JButton();
			btn_acceso4.setLocation(new Point(240, 0));
			btn_acceso4.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image094.png")));
			btn_acceso4.setToolTipText("Agregar registro de caja");
			btn_acceso4.setSize(new Dimension(70, 70));
			btn_acceso4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarRegistroCaja();
				}
			});
		}
		return btn_acceso4;
	}

	/**
	 * This method initializes btn_acceso5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_acceso5() {
		if (btn_acceso5 == null) {
			btn_acceso5 = new JButton();
			btn_acceso5.setLocation(new Point(480, 0));
			btn_acceso5.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image093.png")));
			btn_acceso5.setToolTipText("Cerrar sesión");
			btn_acceso5.setSize(new Dimension(70, 70));
			btn_acceso5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea cerrar sesión?\nSe cerarrán todas las ventanas.", "Cerrar Sesión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/Files/Imagenes/Image069.png"))) == 0) {
                		JInternalFrame vec[] = desktopPanel.getAllFrames();
                    	for(int i=0; i<vec.length; i++){
                    		vec[i].dispose();
                    	}
                    	mi_desconectar.setEnabled(false);
                    	mi_conectar.setEnabled(true);
                    	gestor.desconectar();
                	}
				}
			});
		}
		return btn_acceso5;
	}

	/**
	 * This method initializes mi_ordenPacientes	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_ordenPacientes() {
		if (mi_ordenPacientes == null) {
			mi_ordenPacientes = new JMenuItem();
			mi_ordenPacientes.setText("Lista de pacientes");
			mi_ordenPacientes.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image095.png")));
			mi_ordenPacientes.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_ordenPacientes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.verListadePacientes();
				}
			});
		}
		return mi_ordenPacientes;
	}

	/**
	 * This method initializes btn_acceso6	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_acceso6() {
		if (btn_acceso6 == null) {
			btn_acceso6 = new JButton();
			btn_acceso6.setLocation(new Point(160, 0));
			btn_acceso6.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image096.png")));
			btn_acceso6.setToolTipText("Lista de pacientes");
			btn_acceso6.setSize(new Dimension(70, 70));
			btn_acceso6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.verListadePacientes();
				}
			});
		}
		return btn_acceso6;
	}

	/**
	 * This method initializes mi_reporteInventario	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_reporteInventario() {
		if (mi_reporteInventario == null) {
			mi_reporteInventario = new JMenuItem();
			mi_reporteInventario.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_reporteInventario.setText("Reporte de productos en inventario");
			mi_reporteInventario.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image097.png")));
			mi_reporteInventario.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.generarReporteInventario();
				}
			});
		}
		return mi_reporteInventario;
	}

	/**
	 * This method initializes mi_Eventos	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_Eventos() {
		if (mi_Eventos == null) {
			mi_Eventos = new JMenuItem();
			mi_Eventos.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image095.png")));
			mi_Eventos.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_Eventos.setText("Historial de eventos");
			mi_Eventos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.eventosInv();
				}
			});
		}
		return mi_Eventos;
	}

	/**
	 * This method initializes btn_acceso7	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtn_acceso7() {
		if (btn_acceso7 == null) {
			btn_acceso7 = new JButton();
			btn_acceso7.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image098.png")));
			btn_acceso7.setSize(new Dimension(70, 70));
			btn_acceso7.setLocation(new Point(400, 0));
			btn_acceso7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.mostrarCalculadora();
				}
			});
		}
		return btn_acceso7;
	}

	/**
	 * This method initializes mi_calculadora	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_calculadora() {
		if (mi_calculadora == null) {
			mi_calculadora = new JMenuItem();
			mi_calculadora.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_calculadora.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image099.png")));
			mi_calculadora.setText("Calculadora");
			mi_calculadora.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.mostrarCalculadora();
				}
			});
		}
		return mi_calculadora;
	}

	/**
	 * This method initializes m_mantenerExamenes	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_mantenerExamenes() {
		if (m_mantenerExamenes == null) {
			m_mantenerExamenes = new JMenu();
			m_mantenerExamenes.setFont(new Font("Dialog", Font.BOLD, 14));
			m_mantenerExamenes.setText("Mantener examenes");
			m_mantenerExamenes.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image105.png")));
			m_mantenerExamenes.add(getMi_agregarTipoExamen());
		}
		return m_mantenerExamenes;
	}

	/**
	 * This method initializes mi_agregarTipoExamen	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_agregarTipoExamen() {
		if (mi_agregarTipoExamen == null) {
			mi_agregarTipoExamen = new JMenuItem();
			mi_agregarTipoExamen.setText("Agregar tipo de examen");
			mi_agregarTipoExamen.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image105.png")));
			mi_agregarTipoExamen.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_agregarTipoExamen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarTipoExamen();
				}
			});
		}
		return mi_agregarTipoExamen;
	}

	/**
	 * This method initializes m_ayuda	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_ayuda() {
		if (m_ayuda == null) {
			m_ayuda = new JMenu();
			m_ayuda.setFont(new Font("Dialog", Font.BOLD, 14));
			m_ayuda.setMnemonic(KeyEvent.VK_Y);
			m_ayuda.setText("Ayuda");
			m_ayuda.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image108.png")));
			m_ayuda.add(getMi_manual());
			m_ayuda.add(getMi_acerca());
		}
		return m_ayuda;
	}

	/**
	 * This method initializes mi_acerca	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_acerca() {
		if (mi_acerca == null) {
			mi_acerca = new JMenuItem();
			mi_acerca.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_acerca.setText("Acerca de");
			mi_acerca.setMnemonic(KeyEvent.VK_A);
			mi_acerca.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image109.png")));
			mi_acerca.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.acercaDe();
				}
			});
		}
		return mi_acerca;
	}

	/**
	 * This method initializes mi_manual	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_manual() {
		if (mi_manual == null) {
			mi_manual = new JMenuItem();
			mi_manual.setText("Manual");
			mi_manual.setMnemonic(KeyEvent.VK_M);
			mi_manual.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_manual.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image110.png")));
			mi_manual.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.abrirManual();
				}
			});
		}
		return mi_manual;
	}
}
