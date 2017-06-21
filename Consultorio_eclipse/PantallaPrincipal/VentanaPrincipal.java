package PantallaPrincipal;

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
import java.awt.SystemColor;
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
    private JMenu m_salir = null;
    private JMenuItem mi_salir = null;
    private JMenuItem mi_minimizar = null;
    private JMenuItem mi_conectar = null;
    private JMenuItem mi_servidor = null;
    private JPanel panel_accesos = null;
    private JDesktopPane desktopPanel = null;
    private String urlArchivos = null;
	private JButton btn_acceso1 = null;
	private JButton Inventario = null;
	private JButton btn_acceso2 = null;
	private JMenu m_mantenimiento = null;
	private JMenu m_usuarios = null;
	private JMenuItem mi_nuevoUsuario = null;
	private JMenuItem mi_buscarUsuario = null;
	private JMenu m_reportes = null;
	private JMenuItem mi_reporteHistorias = null;
	private SystemTray tray = null;
    private TrayIcon trayIcon = null;
    private javax.swing.JMenuItem menuItemRestore;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JPopupMenu popupContextual;
    private javax.swing.JSeparator separator;
	private JMenu m_medicamentos = null;
	private JMenuItem mi_nuevoMedi = null;
	private JMenuItem buscarMedica = null;
	private JMenu m_GestiosInventario = null;
	private JMenuItem GestInventario = null;
	public VentanaPrincipal() {
        //JDialog.setDefaultLookAndFeelDecorated(true);        
        //JFrame.setDefaultLookAndFeelDecorated(true);
        getRutaArchivos(urlArchivos);
        getVentana();
        getTray();
       /* CrearConsulta obj = new CrearConsulta(desktopPanel.getWidth(), desktopPanel.getHeight());
        desktopPanel.add(obj.getVentana());
        obj.setVisible(true);*/
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
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            ventana.setLocation((int) (d.getWidth() / 2 - ventana.getWidth() / 2), (int) (d.getHeight() / 2 - ventana.getHeight() / 2));
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
        if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea Salir?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
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
    		btn_acceso1.setEnabled(false);
    		btn_acceso2.setEnabled(false);
    	}
    	else {
    		if(n <= 3){		//Habilito los del usuario 3 (Min)    	
	    		m_historias.setEnabled(true);
	    		m_reportes.setEnabled(true);
	    		btn_acceso1.setEnabled(true);
	    		btn_acceso2.setEnabled(true);
	    	}
	    	if(n <= 2){		//Habilito los del usuario 2 (Medio)
	    		m_mantenimiento.setEnabled(true);
	    	}
	    	if(n <= 1){		//Habilito los del usuario 1 (Max)
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
            menuBar.add(getM_reportes());
            menuBar.add(getM_mantenimiento());
            menuBar.add(getM_conexion());
            menuBar.add(getM_salir());
        }
        return menuBar;
    }

    private JMenu getM_historias() {
        if (m_historias == null) {
            m_historias = new JMenu();
            m_historias.setText("Historias");
            m_historias.setMnemonic(KeyEvent.VK_H);
            m_historias.setFont(new Font("Dialog", Font.BOLD, 14));
            //m_historias.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image016.png")));
            m_historias.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image016.png")));
            m_historias.add(getMi_crearHistoria());
            m_historias.add(getMi_buscarHistoria());
        }
        return m_historias;
    }
    
    private JMenuItem getMi_crearHistoria() {
        if (mi_crearhistoria == null) {
            mi_crearhistoria = new JMenuItem();
            mi_crearhistoria.setText("Crear Historia");
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
            mi_buscarHistoria.setText("Buscar Historia");
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
            m_conexion.setText("Conexión");
            m_conexion.setMnemonic(KeyEvent.VK_C);
            m_conexion.setFont(new Font("Dialog", Font.BOLD, 14));
            //m_conexion.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image005.png")));
            m_conexion.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image005.png")));
            m_conexion.add(getMi_conectar());
            m_conexion.add(getMi_desconectar());
            m_conexion.add(getMi_servidor());
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
            mi_desconectar.setText("Cerrar Sesion");
            //mi_desconectar.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image001.png")));
            mi_desconectar.setFont(new Font("Dialog", Font.BOLD, 14));
            mi_desconectar.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image001.png")));
            mi_desconectar.setMnemonic(KeyEvent.VK_C);
            mi_desconectar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {                
                	if (JOptionPane.showConfirmDialog(ventana, "Seguro que desea cerrar sesión?\nSe cerarrán todas las ventanas.", "Cerrar Sesión", JOptionPane.YES_NO_OPTION) == 0) {
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
     * This method initializes m_salir
     *
     * @return javax.swing.JMenu
     */
    private JMenu getM_salir() {
        if (m_salir == null) {
            m_salir = new JMenu();
            m_salir.setText("Ventana");
            m_salir.setMnemonic(KeyEvent.VK_V);
            m_salir.setFont(new Font("Dialog", Font.BOLD, 14));
            m_salir.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image004.png")));
            //m_salir.setIcon(new ImageIcon(getURLArchivos("Imagenes/Image004.png")));
            m_salir.add(getMi_minimizar());
            m_salir.add(getMi_salir());
        }
        return m_salir;
    }

    /**
     * This method initializes mi_conectar
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMi_conectar() {
        if (mi_conectar == null) {
            mi_conectar = new JMenuItem();
            mi_conectar.setText("Iniciar Sesion");
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
            panel_accesos.setBackground(SystemColor.activeCaption);
            panel_accesos.add(getBtn_acceso1(), null);
            panel_accesos.add(getInventario(), null);
            panel_accesos.add(getBtn_acceso2(), null);
        }
        return panel_accesos;
    }

    public JDesktopPane getDesktopPanel() {
        if (desktopPanel == null) {
            desktopPanel = new JDesktopPane();
            desktopPanel.setBackground(Color.WHITE);
            desktopPanel.setSize(800, 460);
            desktopPanel.setLocation(0, 70);
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
			btn_acceso1.setOpaque(false);
			btn_acceso1.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image047.png")));
			btn_acceso1.setToolTipText("Crear nueva historia");
			btn_acceso1.setBackground(Color.white);
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
	 * This method initializes Inventario	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInventario() {
		if (Inventario == null) {
			Inventario = new JButton();
			Inventario.setLocation(new Point(160, 0));
			Inventario.setOpaque(false);
			Inventario.setBackground(Color.white);
			Inventario.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/inventario.png")));
			Inventario.setToolTipText("Gestionar inventario");
			Inventario.setSize(new Dimension(70, 70));
			Inventario.setVisible(false);
			Inventario.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					gestor.GestioInventario();
				}
			
			});		
		}
		return Inventario;
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
			btn_acceso2.setOpaque(false);
			btn_acceso2.setBackground(Color.white);
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
			m_mantenimiento.add(getM_usuarios());
			m_mantenimiento.add(getM_medicamentos());
			m_mantenimiento.add(getM_GestiosInventario());
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
	 * This method initializes m_reportes	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_reportes() {
		if (m_reportes == null) {
			m_reportes = new JMenu();
			m_reportes.setText("Reportes");
			m_reportes.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image055.png")));
			m_reportes.setFont(new Font("Dialog", Font.BOLD, 14));
			m_reportes.setMnemonic(KeyEvent.VK_R);
			m_reportes.add(getMi_reporteHistorias());
		}
		return m_reportes;
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
			mi_reporteHistorias.setText("Reporte de Historias");
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
	 * This method initializes m_medicamentos	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_medicamentos() {
		if (m_medicamentos == null) {
			m_medicamentos = new JMenu();
			m_medicamentos.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/medicamentos.png")));
			m_medicamentos.setText("Mantener medicamento");
			m_medicamentos.setFont(new Font("Dialog", Font.BOLD, 14));
			m_medicamentos.add(getMi_nuevoMedi());
			m_medicamentos.add(getBuscarMedica());
			m_medicamentos.setVisible(true);
		}
		return m_medicamentos;
	}

	/**
	 * This method initializes mi_nuevoMedi	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_nuevoMedi() {
		if (mi_nuevoMedi == null) {
			mi_nuevoMedi = new JMenuItem();
			mi_nuevoMedi.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/newmedica.png")));
			mi_nuevoMedi.setFont(new Font("Dialog", Font.BOLD, 14));
			mi_nuevoMedi.setText("Agregar medicamentos");
			mi_nuevoMedi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarMedi();
				}
			});
		}
		return mi_nuevoMedi;
	}

	/**
	 * This method initializes buscarMedica	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getBuscarMedica() {
		if (buscarMedica == null) {
			buscarMedica = new JMenuItem();
			buscarMedica.setText("Buscar Medicamento");
			buscarMedica.setFont(new Font("Dialog", Font.BOLD, 14));
			buscarMedica.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/Image035.png")));
			buscarMedica.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.buscarMedicamento();
				}
			});
		}
		return buscarMedica;
	}

	/**
	 * This method initializes m_GestiosInventario	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_GestiosInventario() {
		if (m_GestiosInventario == null) {
			m_GestiosInventario = new JMenu();
			m_GestiosInventario.setText("Mantenar inventario");
			m_GestiosInventario.setIcon(new ImageIcon(getClass().getResource("/Files/Imagenes/kthememgr.png")));
			m_GestiosInventario.setFont(new Font("Dialog", Font.BOLD, 14));
			m_GestiosInventario.setVisible(true);
			m_GestiosInventario.add(getGestInventario());
		}
		return m_GestiosInventario;
	}

	/**
	 * This method initializes GestInventario	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getGestInventario() {
		if (GestInventario == null) {
			GestInventario = new JMenuItem();
			GestInventario.setText("Mantener Inventario");
			GestInventario.setFont(new Font("Dialog", Font.BOLD, 14));
			GestInventario.setVisible(true);
		}
		return GestInventario;
	}
}
