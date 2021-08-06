package formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class usuario extends JFrame {
	
	
	public static final String URL = "jdbc:postgresql://localhost:5432/FOXJAVA";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "123";
	
	PreparedStatement ps;
	ResultSet rs ;
	
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = (Connection) DriverManager.getConnection(URL,USERNAME,PASSWORD);
			JOptionPane.showMessageDialog(null,"Conexion exitosa");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
		
	}
	
	private void limpiarcajas() {
	//	txtcodigo.setText(null);
		textNOMBRE.setText(null);
		textAlias.setText(null);
		textClave.setText(null);
		
	}

	
	

	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JButton btnLimpiar;
	private JButton btnSalir;
	private JTextField textNOMBRE;
	private JTextField textAlias;
	private JTextField textClave;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usuario frame = new usuario();
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
	public usuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(29, 73, 64, 14);
		contentPane.add(lblNewLabel);
		
		textNOMBRE = new JTextField();
		textNOMBRE.setBounds(119, 70, 244, 20);
		contentPane.add(textNOMBRE);
		textNOMBRE.setColumns(10);
		 this.setLocationRelativeTo(null); 
		 
		JButton btnNewGUARDAR = new JButton("GUARDAR");
		btnNewGUARDAR.setBounds(29, 207, 89, 23);
		btnNewGUARDAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO usuario(nombre,alias,clave) values(?,?,?)");
					ps.setString(1,textNOMBRE.getText());
					ps.setString(2,textAlias.getText());
					ps.setString(3,textClave.getText());
					
int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Usuario Guardado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al Guardar Grupo");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
				
				
				
			}
		});
		contentPane.add(btnNewGUARDAR);
		
		lblNewLabel_1 = new JLabel("usuario");
		lblNewLabel_1.setBounds(145, 11, 143, 36);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel_1);
		
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setBounds(145, 207, 89, 23);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarcajas();
			}
		});
		contentPane.add(btnLimpiar);
		
		btnSalir = new JButton("SALIR");
		btnSalir.setBounds(258, 207, 89, 23);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
				
			}
		});
		contentPane.add(btnSalir);
		
		textAlias = new JTextField();
		textAlias.setBounds(119, 101, 244, 22);
		contentPane.add(textAlias);
		textAlias.setColumns(10);
		
		textClave = new JTextField();
		textClave.setBounds(118, 132, 245, 22);
		contentPane.add(textClave);
		textClave.setColumns(10);
		
		lblNewLabel_2 = new JLabel("alias");
		lblNewLabel_2.setBounds(24, 104, 56, 16);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("clave");
		lblNewLabel_3.setBounds(29, 135, 56, 16);
		contentPane.add(lblNewLabel_3);
	}
}
