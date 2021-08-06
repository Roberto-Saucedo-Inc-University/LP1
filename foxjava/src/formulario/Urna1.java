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
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;


public class Urna1 extends JFrame {
	
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
		txtValidacionCedula.setText(null);
		txtId.setText(null);
		txtNombre.setText(null);
		txtApellido.setText(null);
		txtCI.setText(null);
		txtMesa.setText(null);
		txtDistrito.setText(null);
		txtLista1.setText(null);
		txtLista2.setText(null);
	}

	
	

	private JPanel contentPane;
	private JTextField txtNombre;
	private JLabel lblNewLabel_1;
	private JButton btnLimpiar;
	private JButton btnSalir;
	private JTextField txtValidacionCedula;
	private JTextField txtId;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JTextField txtApellido;
	private JTextField txtCI;
	private JTextField txtMesa;
	private JLabel lblNewLabel_14;
	private JTextField txtDistrito;
	private JTextField txtLista1;
	private JTextField txtLista2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Urna1 frame = new Urna1();
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
	public Urna1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(31, 150, 59, 14);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(102, 147, 342, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		 this.setLocationRelativeTo(null); 
		 
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(67, 438, 89, 23);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO urna(nombre,apellido,cedula,mesa,localidad,candidato_presidencial,candidato_vicepresidencial) values(?,?,?,?,?,?,?)");
					ps.setString(1,txtNombre.getText());
					ps.setString(2,txtApellido.getText());
					ps.setInt(3,Integer.parseInt(txtCI.getText()));
					ps.setInt(4,Integer.parseInt(txtMesa.getText()));
					ps.setInt(5,Integer.parseInt(txtDistrito.getText()));
					ps.setInt(6,Integer.parseInt(txtLista1.getText()));
					ps.setInt(7,Integer.parseInt(txtLista2.getText()));
					
int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Tu Voto ha sido Guardado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al Guardar Tu Voto");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
				
				
				
			}
		});
		contentPane.add(btnGuardar);
		
		lblNewLabel_1 = new JLabel("URNA");
		lblNewLabel_1.setBounds(33, 4, 144, 86);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		contentPane.add(lblNewLabel_1);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(262, 438, 89, 23);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarcajas();
			}
		});
		contentPane.add(btnLimpiar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(436, 438, 89, 23);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
				
			}
		});
		contentPane.add(btnSalir);
		
		txtValidacionCedula = new JTextField();
		txtValidacionCedula.setBounds(203, 90, 244, 22);
		contentPane.add(txtValidacionCedula);
		txtValidacionCedula.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Validacion por Cedula");
		lblNewLabel_2.setBounds(31, 93, 137, 16);
		contentPane.add(lblNewLabel_2);
		
		txtId = new JTextField();
		txtId.setBounds(466, 45, 59, 22);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JButton btnBuscar1 = new JButton("Buscar");
		btnBuscar1.setBounds(459, 89, 89, 23);
		btnBuscar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con = getConnection();
					ps = con.prepareStatement("select * from urna where cedula = ?");
					ps.setString(1, txtValidacionCedula.getText());
					
					rs = ps.executeQuery();
					if (rs.next()) {
						txtId.setText(rs.getString("id_votante"));
						JOptionPane.showMessageDialog(null,"El Ciudadano ha Votado");
						limpiarcajas();
					}else {
						JOptionPane.showMessageDialog(null,"El Ciudadano NO ha Votado");
					}
				}catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		contentPane.add(btnBuscar1);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(436, 48, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_5 = new JLabel("Apellido");
		lblNewLabel_5.setBounds(31, 177, 75, 16);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Distrito");
		lblNewLabel_6.setBounds(31, 235, 56, 16);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Mesa");
		lblNewLabel_7.setBounds(246, 235, 56, 16);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Lista");
		lblNewLabel_8.setBounds(31, 322, 56, 16);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Candidato Presidente");
		lblNewLabel_9.setBounds(31, 293, 163, 16);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("C.I:");
		lblNewLabel_10.setBounds(31, 206, 56, 16);
		contentPane.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("Lista");
		lblNewLabel_11.setBounds(245, 322, 56, 16);
		contentPane.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("Candidato VicePresidente");
		lblNewLabel_12.setBounds(245, 293, 146, 16);
		contentPane.add(lblNewLabel_12);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(105, 175, 342, 20);
		txtApellido.setColumns(10);
		contentPane.add(txtApellido);
		
		txtCI = new JTextField();
		txtCI.setBounds(83, 202, 342, 20);
		txtCI.setColumns(10);
		contentPane.add(txtCI);
		
		txtMesa = new JTextField();
		txtMesa.setBounds(295, 233, 116, 20);
		txtMesa.setColumns(10);
		contentPane.add(txtMesa);
		
		lblNewLabel_14 = new JLabel("Electronica");
		lblNewLabel_14.setBounds(168, 48, 97, 25);
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_14);
		
		txtDistrito = new JTextField();
		txtDistrito.setBounds(83, 232, 116, 22);
		contentPane.add(txtDistrito);
		txtDistrito.setColumns(10);
		
		txtLista1 = new JTextField();
		txtLista1.setBounds(83, 319, 30, 22);
		contentPane.add(txtLista1);
		txtLista1.setColumns(10);
		
		txtLista2 = new JTextField();
		txtLista2.setBounds(308, 319, 30, 22);
		txtLista2.setColumns(10);
		contentPane.add(txtLista2);
	}
