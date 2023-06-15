package s2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class Word4Pictures extends JFrame {
	
	private JPanel contentPane;
	private String[] words= {"necesidad","raton","hielo","raiz","sonrisa","lento"};
	private char[] correctWord;
	private final char[] LETTERS= {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	private char[] opLetters;
	private JTextField[] textFields;
	private JButton[] BtnLetters;
	private String[] paths= {"../img/Necesidad.png","../img/raton.png","../img/hielo.png","../img/raiz.png","../img/sonrisa.png","../img/lento.png"};
	private Random r=new Random();
	private final int WIDTHIMG=248;
	private boolean congrats=false;
	private static Word4Pictures frame = new Word4Pictures();
	private static int level=0;


//	public void setFrameMini(LocalOrOnline frameLocal) {
//		this.frameLocal = frameLocal;
//	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	public Word4Pictures() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Word4Pictures.class.getResource("/img/4-Pics-1-Word-Perfil.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 490);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(215, 209, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		//Codigo crear imagen y añadirla
		JPanel panelImg = new JPanel();
		panelImg.setBackground(new Color(215, 209, 254));
		panelImg.setPreferredSize(new Dimension(200,300));
		
		ImageIcon imagenIcono = new ImageIcon(getClass().getResource(paths[level]));
		panelImg.setLayout(null);
		
		JLabel etiquetaImagen = new JLabel(imagenIcono);
		etiquetaImagen.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		etiquetaImagen.setBounds(97, 11, WIDTHIMG, WIDTHIMG);
		
		panelImg.add(etiquetaImagen);
		
		contentPane.add(panelImg);
		
		
		//creacion boton delete
		JButton btnDel = new JButton("");
		btnDel.setBorder(null);
		btnDel.setBorderPainted(false);
		btnDel.setIcon(new ImageIcon(Word4Pictures.class.getResource("/img/borrar.png")));
		btnDel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = textFields.length-1; i >=0; i--) {
					if(!textFields[i].getText().equals("")) {
						for (JButton jButton : BtnLetters) {
							if(textFields[i].getText().equals(jButton.getText())) {
								if(!jButton.isEnabled()) {
									jButton.setEnabled(true);
									break;
								}
							}
						}
						textFields[i].setText("");
						break;
					}
				}
			}
		});
		btnDel.setBackground(new Color(215, 209, 254));
		btnDel.setBounds(364, 288, 39, 23);
		panelImg.add(btnDel);
		
		
		//creacion panel arriba
		JPanel panelSolu = new JPanel();
		panelSolu.setBackground(new Color(215, 209, 254));
		panelSolu.setBounds(97, 285, 248, 31);
		panelImg.add(panelSolu);
		panelSolu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnMenu = new JButton("");
		btnMenu.setBorder(null);
		btnMenu.setBorderPainted(false);
		btnMenu.setIcon(new ImageIcon(Word4Pictures.class.getResource("/img/atras.png")));
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login.lol.setVisible(true);
			}
		});
		btnMenu.setBackground(new Color(215, 209, 254));
		btnMenu.setBounds(10, 11, 45, 44);
		panelImg.add(btnMenu);
		
		//asignamos la palabra correcta
		correctWord=words[level].toCharArray();
		
		//la metemos en las letras de abajo
		opLetters=new char[correctWord.length+5];
		
		System.arraycopy(correctWord, 0, opLetters, 0, correctWord.length);
		
		r=new Random();
		int let;
		
		
		//añadimos letras aleatorias para añadir dificultad
		for (int i = correctWord.length; i < opLetters.length; i++) {
			let = r.nextInt(0, LETTERS.length);
			opLetters[i]=LETTERS[let];
		}
		
		
		//creamos los huecos
		textFields = new JTextField[correctWord.length];
		
		for (int i = 0; i < correctWord.length; i++) {
			JTextField textField = new JTextField();
			textField.setBorder(new LineBorder(new Color(0, 0, 0), 1));
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Tahoma", Font.BOLD, 16));
//            textField.setBounds((78+(addW*mult)), 288, 20, 20);
            textFields[i] = textField;
            panelSolu.add(textField);
            textField.setPreferredSize(new Dimension(22,22));
            textField.setEditable(false);
		}
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		
		
		//creamos el panel de los botones de las letras
		JPanel panel_buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel_buttons.setBorder(new LineBorder(new Color(255, 165, 254), 2, true));
		panel_buttons.setBackground(new Color(254, 201, 253));
		contentPane.add(panel_buttons);
		
		//creamos los botones
		BtnLetters = new JButton[opLetters.length];
		
		for (int i = 0; i < BtnLetters.length; i++) {
			JButton BtnLetter = new JButton(Character.toString(opLetters[i]));
			BtnLetters[i] = BtnLetter;
			BtnLetter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String t = BtnLetter.getText();
					for (JTextField textF : textFields) {
						if(textF.getText().equals("")) {
							textF.setText(t);
							BtnLetter.setEnabled(false);
							break;
						}
					}
					
					for (int i = 0; i < textFields.length; i++) {
					    String textFieldText = Word4Pictures.toString(textFields);
					    
					    // Compara el contenido del JTextField con el array de caracteres
					    if (textFieldText.equals(String.valueOf(correctWord))) {
					    	congrats=true;
					    }
					}
					if (congrats) {
				    	JOptionPane.showMessageDialog(contentPane, "Has ganado, la palabra era: "+ words[level]);
				    	setVisible(false);
				    	if(level<words.length-1) {
							level++;
							frame = new Word4Pictures();
					    	frame.setVisible(true);
						} else {
							level=0;
							JOptionPane.showMessageDialog(contentPane, "Genial! Has completado todos los niveles.");
							setVisible(false);
							Login.lol.setVisible(true);
						}
					}
				}
			});
			BtnLetter.setPreferredSize(new Dimension(50,40));
			BtnLetter.setHorizontalTextPosition(SwingConstants.CENTER);
			
			
			
		}
		
		mezclarLetras(BtnLetters);
		
		for (JButton jButton : BtnLetters) {
			panel_buttons.add(jButton);
		}
		
	}
	
	protected static void cambiarLetras(JButton[] BtnLetters, int i, int j) {
		JButton btn = BtnLetters[i];
		BtnLetters[i] = BtnLetters[j];
		BtnLetters[j] = btn;
    }
	
	public static void mezclarLetras(JButton[] array) {
        Random random = new Random();
        int i = 0;
        for (int j : random.ints(array.length, 0, array.length).toArray()) {
        	cambiarLetras(array, i++, j);
        }
    }

	public static String toString(JTextField[] array) {
		String str="";
		for (JTextField t : array) {
			str+=t.getText();
		}
		return str;
	}
}
