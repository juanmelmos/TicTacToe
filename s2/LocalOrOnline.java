package s2;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

public class LocalOrOnline extends JFrame {

	private JPanel contentPane;
	public static LocalImposible imp;
	public static LocalFacil fac;
	public static LocalMedio med;
	public static LocalDificil dif;
	private JTabbedPane tabbedPane;
	private JScrollPane panelRankingFacil;
	private JScrollPane panelRankingMedio;
	private JScrollPane panelRankingDificil;
	private JScrollPane panelRankingImposible;
	private JButton btnAtras;
	private JButton btnOnline;
	private JButton btnLocal;
	private JButton btnFacil;
	private JButton btnMedio;
	private JButton btnDificil;
	private JButton btnImposible;
	private JButton btnMinijuego;
	private static JLabel lblBienvenidonombre;
	private static JLabel lblRanking;
	private static JLabel lblElige;
	private static JLabel lblLocal;
	private static JLabel lblOnline;
	public static Bienvenido bi;
	public static Word4Pictures pic;
	private static JTable tablef = new JTable();
	private static JTable tablem = new JTable();
	private static JTable tabled = new JTable();
	private static JTable tablei = new JTable();
	protected static DefaultTableModel model;
	private WThreadRankingFacil rf = new WThreadRankingFacil(tablef);
	private WThreadRankingMedio rm = new WThreadRankingMedio(tablem);
	private WThreadRankingDificil rd = new WThreadRankingDificil(tabled);
	private WThreadRankingImposible ri = new WThreadRankingImposible(tablei);
	
	
	/**
	 * Launch the application.
	 */
	
	
	
	public static Bienvenido getBi() {
		return bi;
	}
	
	public static JLabel getLblBienvenidonombre() {
		return lblBienvenidonombre;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocalOrOnline frame = new LocalOrOnline();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LocalOrOnline() {
		
		rf.start();
		rm.start();
		rd.start();
		ri.start();
		for (double i = 0; i < 999999999; i++) {
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1054, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(252, 233, 79));
		
		btnLocal = new JButton("");
		btnLocal.setIcon(new ImageIcon(LocalOrOnline.class.getResource("../img/UserVSpc.jpeg")));
		btnLocal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLocal.setVisible(false);
				btnOnline.setVisible(false);
				btnAtras.setVisible(true);
				btnFacil.setVisible(true);
				btnMedio.setVisible(true);
				btnDificil.setVisible(true);
				btnImposible.setVisible(true);
				tabbedPane.setVisible(true);
				lblBienvenidonombre.setText("Bienvenido "+Login.getUsuario());
				lblBienvenidonombre.setVisible(true);
				lblRanking.setVisible(true);
				lblElige.setVisible(false);
				lblLocal.setVisible(false);
				lblOnline.setVisible(false);
				btnMinijuego.setVisible(false);
			}
		});
		btnLocal.setBounds(154, 126, 271, 271);
		contentPane.add(btnLocal);
		
		btnOnline = new JButton("");
		btnOnline.setIcon(new ImageIcon(LocalOrOnline.class.getResource("../img/UserVSUser.jpeg")));
		btnOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bi=new Bienvenido();
				bi.setVisible(true);
				bi.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				Bienvenido.getLblBienvenidonombre().setText("Bienvenido "+Login.getUsuario());
				setVisible(false);
			}
		});
		btnOnline.setBounds(638, 126, 271, 271);
		contentPane.add(btnOnline);
		
		btnFacil = new JButton("Facil");
		btnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fac =new LocalFacil();
				fac.setVisible(true);
				fac.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setVisible(false);
			}
		});
		btnFacil.setBounds(154, 126, 105, 27);
		contentPane.add(btnFacil);
		btnFacil.setVisible(false);
		
		btnMedio = new JButton("Medio");
		btnMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				med =new LocalMedio();
				med.setVisible(true);
				med.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setVisible(false);
			}
		});
		btnMedio.setBounds(154, 182, 105, 27);
		contentPane.add(btnMedio);
		btnMedio.setVisible(false);
		
		btnDificil = new JButton("Dificil");
		btnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dif =new LocalDificil();
				dif.setVisible(true);
				dif.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setVisible(false);
			}
		});
		btnDificil.setBounds(154, 243, 105, 27);
		contentPane.add(btnDificil);
		btnDificil.setVisible(false);
		
		btnImposible = new JButton("Imposible");
		btnImposible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imp =new LocalImposible();
				imp.setVisible(true);
				imp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setVisible(false);
			}
		});
		btnImposible.setBounds(154, 300, 105, 27);
		contentPane.add(btnImposible);
		btnImposible.setVisible(false);
		
		lblBienvenidonombre = new JLabel("Bienvenido ");
		lblBienvenidonombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenidonombre.setFont(new Font("Latin Modern Math", Font.BOLD, 36));
		lblBienvenidonombre.setBounds(31, 8, 409, 55);
		contentPane.add(lblBienvenidonombre);
		lblBienvenidonombre.setVisible(false);
		
		lblRanking = new JLabel("Ranking");
		lblRanking.setFont(new Font("Latin Modern Math", Font.BOLD, 36));
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setBounds(433, 3, 595, 65);
		contentPane.add(lblRanking);
		lblRanking.setVisible(false);
		
		lblElige = new JLabel("Elige");
		lblElige.setFont(new Font("Latin Modern Math", Font.BOLD, 36));
		lblElige.setHorizontalAlignment(SwingConstants.CENTER);
		lblElige.setBounds(230, 3, 595, 65);
		contentPane.add(lblElige);
		
		lblLocal = new JLabel("Jugador VS PC");
		lblLocal.setFont(new Font("Latin Modern Math", Font.BOLD, 16));
		lblLocal.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocal.setBounds(154, 80, 271, 51);
		contentPane.add(lblLocal);
		
		lblOnline = new JLabel("Jugador VS Jugador");
		lblOnline.setFont(new Font("Latin Modern Math", Font.BOLD, 16));
		lblOnline.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnline.setBounds(638, 80, 271, 51);
		contentPane.add(lblOnline);
		
		    
        
        DefaultTableCellRenderer renderizadorFacil = new DefaultTableCellRenderer() {
            // Sobrescribir el método getTableCellRendererComponent
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Llamar al método de la superclase para obtener el componente de celda predeterminado
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Verificar si la fila actual es la que deseas cambiar el fondo
                if (row == WThreadRankingFacil.getFilaUsuario()) {
                    cell.setBackground(Color.ORANGE);
                } else {
                    // Establecer el fondo predeterminado para las demás filas
                    cell.setBackground(table.getBackground());
                }
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

                return cell;
            }
        };
        
        DefaultTableCellRenderer renderizadorMedio = new DefaultTableCellRenderer() {
            // Sobrescribir el método getTableCellRendererComponent
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Llamar al método de la superclase para obtener el componente de celda predeterminado
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Verificar si la fila actual es la que deseas cambiar el fondo
                if (row == WThreadRankingMedio.getFilaUsuario()) {
                    cell.setBackground(Color.ORANGE);
                } else {
                    // Establecer el fondo predeterminado para las demás filas
                    cell.setBackground(table.getBackground());
                }
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

                return cell;
            }
        };
        
        DefaultTableCellRenderer renderizadorDificil = new DefaultTableCellRenderer() {
            // Sobrescribir el método getTableCellRendererComponent
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Llamar al método de la superclase para obtener el componente de celda predeterminado
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Verificar si la fila actual es la que deseas cambiar el fondo
                if (row == WThreadRankingDificil.getFilaUsuario()) {
                    cell.setBackground(Color.ORANGE);
                } else {
                    // Establecer el fondo predeterminado para las demás filas
                    cell.setBackground(table.getBackground());
                }
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

                return cell;
            }
        };
        
        DefaultTableCellRenderer renderizadorImposible = new DefaultTableCellRenderer() {
            // Sobrescribir el método getTableCellRendererComponent
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Llamar al método de la superclase para obtener el componente de celda predeterminado
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Verificar si la fila actual es la que deseas cambiar el fondo
                if (row == WThreadRankingImposible.getFilaUsuario()) {
                    cell.setBackground(Color.ORANGE);
                } else {
                    // Establecer el fondo predeterminado para las demás filas
                    cell.setBackground(table.getBackground());
                }
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

                return cell;
            }
        };
        
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Establecer la fuente y el tipo de letra
                setFont(new Font("FreeSans", Font.BOLD, 19));
                
                // Establecer alineacion
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                
                // Establecer el color de fondo
                setBackground(new Color(238,238,238));
                
                // Establecer el color de la línea
                setForeground(Color.BLACK);
                
                // Establecer el borde inferior
                Border border = new MatteBorder(0, 0, 3, 0, Color.BLACK);
                setBorder(border);
                
                return this;
            }
        };
        
		

        // Establecer el centrado para todas las columnas
        for (int i = 0; i < tablef.getColumnCount(); i++) {
            
            tablef.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            tablef.getColumnModel().getColumn(i).setResizable(false);
            tablef.getColumnModel().getColumn(i).setCellRenderer(renderizadorFacil);
            
            tablem.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            tablem.getColumnModel().getColumn(i).setResizable(false);
            tablem.getColumnModel().getColumn(i).setCellRenderer(renderizadorMedio);
            
            tabled.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            tabled.getColumnModel().getColumn(i).setResizable(false);
            tabled.getColumnModel().getColumn(i).setCellRenderer(renderizadorDificil);
            
            tablei.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            tablei.getColumnModel().getColumn(i).setResizable(false);
            tablei.getColumnModel().getColumn(i).setCellRenderer(renderizadorImposible);
            
        }
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(433, 71, 595, 367);
		contentPane.add(tabbedPane);
		tabbedPane.setVisible(false);
		
		tablef.setShowGrid(false);
		tablef.getColumnModel().getColumn(1).setPreferredWidth(175);
		tablef.setRowHeight(40);
		tablef.setBackground(new Color(238,238,238));
		tablef.setFont(new Font("FreeSans", Font.BOLD, 17));
		
		tablem.setShowGrid(false);
		tablem.getColumnModel().getColumn(1).setPreferredWidth(175);
		tablem.setRowHeight(40);
		tablem.setBackground(new Color(238,238,238));
		tablem.setFont(new Font("FreeSans", Font.BOLD, 17));
		
		tabled.setShowGrid(false);
		tabled.getColumnModel().getColumn(1).setPreferredWidth(175);
		tabled.setRowHeight(40);
		tabled.setBackground(new Color(238,238,238));
		tabled.setFont(new Font("FreeSans", Font.BOLD, 17));
		
		tablei.setShowGrid(false);
		tablei.getColumnModel().getColumn(1).setPreferredWidth(175);
		tablei.setRowHeight(40);
		tablei.setBackground(new Color(238,238,238));
		tablei.setFont(new Font("FreeSans", Font.BOLD, 17));
		
		panelRankingFacil = new JScrollPane(tablef);
		tabbedPane.addTab("Fácil", null, panelRankingFacil, null);
		panelRankingFacil.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		panelRankingMedio = new JScrollPane(tablem);
		tabbedPane.addTab("Medio", null, panelRankingMedio, null);
		panelRankingMedio.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		panelRankingDificil = new JScrollPane(tabled);
		tabbedPane.addTab("Difícil", null, panelRankingDificil, null);
		panelRankingDificil.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		panelRankingImposible = new JScrollPane(tablei);
		tabbedPane.addTab("Imposible", null, panelRankingImposible, null);
		panelRankingImposible.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLocal.setVisible(true);
				btnOnline.setVisible(true);
				btnFacil.setVisible(false);
				btnMedio.setVisible(false);
				btnDificil.setVisible(false);
				btnImposible.setVisible(false);
				tabbedPane.setVisible(false);
				lblBienvenidonombre.setVisible(false);
				lblRanking.setVisible(false);
				lblElige.setVisible(true);
				lblLocal.setVisible(true);
				lblOnline.setVisible(true);
				btnAtras.setVisible(false);
				btnMinijuego.setVisible(true);
			}
		});
		btnAtras.setBounds(31, 434, 89, 23);
		contentPane.add(btnAtras);
		btnAtras.setVisible(false);
		
		btnMinijuego = new JButton("Minijuego");
		btnMinijuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pic=new Word4Pictures();
				pic.setVisible(true);
				setVisible(false);
			}
		});
		btnMinijuego.setBounds(301, 434, 433, 42);
		contentPane.add(btnMinijuego);
		btnAtras.setVisible(false);
		
	}
}
