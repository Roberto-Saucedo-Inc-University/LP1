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


public class Automotores extends JFrame {
	
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
		txtCedula.setText(null);
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
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private JTextField txtMesa;
	private JTextField txtDistrito;
	private JTextField txtLista1;
	private JTextField txtLista2;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_9;
	private JTextField txtLista1F;
	private JTextField txtLista2F;
	private JTextField txtLista3F;
	private JButton btnActualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Automotores frame = new Automotores();
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
	public Automotores() {
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
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					String error;
					error=(txtCedula.getText());
					con = getConnection();
					ps = con.prepareStatement("select * from urna where cedula = ?");
					ps.setString(1, txtCedula.getText());
					rs = ps.executeQuery();
					if (rs.next()) {
						JOptionPane.showMessageDialog(null,"El Ciudadano ha Votado",
								error, JOptionPane.ERROR_MESSAGE);
						limpiarcajas();
					}else {	
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO urna(nombre,apellido,cedula,mesa,localidad,candidato_presidencial,candidato_vicepresidencial) values(?,?,?,?,?,?,?)");
					ps.setString(1,txtNombre.getText());
					ps.setString(2,txtApellido.getText());
					ps.setString(3,txtCedula.getText());
					ps.setInt(4,Integer.parseInt(txtMesa.getText()));
					ps.setInt(5,Integer.parseInt(txtDistrito.getText()));
					ps.setInt(6,Integer.parseInt(txtLista1.getText()));
					ps.setInt(7,Integer.parseInt(txtLista2.getText()));
					
					
					}
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
		btnGuardar.setBounds(17, 438, 89, 23);
		contentPane.add(btnGuardar);
		
		lblNewLabel_1 = new JLabel("URNA");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 55));
		lblNewLabel_1.setBounds(23, 6, 151, 67);
		contentPane.add(lblNewLabel_1);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarcajas();
			}
		});
		btnLimpiar.setBounds(156, 438, 89, 23);
		contentPane.add(btnLimpiar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
				
			}
		});
		btnSalir.setBounds(272, 438, 89, 23);
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
		btnBuscar1.setBounds(459, 89, 89, 23);
		contentPane.add(btnBuscar1);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(436, 48, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_5 = new JLabel("Apellido");
		lblNewLabel_5.setBounds(31, 177, 75, 16);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_7 = new JLabel("Mesa:");
		lblNewLabel_7.setBounds(167, 262, 56, 16);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Distrito:");
		lblNewLabel_8.setBounds(31, 262, 56, 16);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_10 = new JLabel("C.I:");
		lblNewLabel_10.setBounds(31, 206, 56, 16);
		contentPane.add(lblNewLabel_10);
		
		lblNewLabel_12 = new JLabel("Lista");
		lblNewLabel_12.setBounds(31, 361, 56, 16);
		contentPane.add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("Candidato Presidencial");
		lblNewLabel_13.setBounds(31, 317, 134, 16);
		contentPane.add(lblNewLabel_13);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(105, 175, 342, 20);
		contentPane.add(txtApellido);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(83, 202, 342, 20);
		contentPane.add(txtCedula);
		
		txtMesa = new JTextField();
		txtMesa.setColumns(10);
		txtMesa.setBounds(212, 260, 59, 20);
		contentPane.add(txtMesa);
		
		txtDistrito = new JTextField();
		txtDistrito.setColumns(10);
		txtDistrito.setBounds(83, 260, 59, 20);
		contentPane.add(txtDistrito);
		
		txtLista1 = new JTextField();
		txtLista1.setColumns(10);
		txtLista1.setBounds(69, 359, 41, 20);
		contentPane.add(txtLista1);
		
		txtLista2 = new JTextField();
		txtLista2.setColumns(10);
		txtLista2.setBounds(253, 359, 41, 20);
		contentPane.add(txtLista2);
		
		lblNewLabel_4 = new JLabel("Candidato Vicepresidencial");
		lblNewLabel_4.setBounds(195, 317, 166, 16);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_6 = new JLabel("Lista");
		lblNewLabel_6.setBounds(203, 361, 56, 16);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_9 = new JLabel("Electronico");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_9.setBounds(186, 34, 161, 39);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_11 = new JLabel("Resultado Parcial");
		lblNewLabel_11.setBounds(413, 262, 112, 16);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_14 = new JLabel("Lista 1");
		lblNewLabel_14.setBounds(387, 304, 56, 16);
		contentPane.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Lista 2");
		lblNewLabel_15.setBounds(387, 347, 56, 16);
		contentPane.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Lista 3");
		lblNewLabel_16.setBounds(387, 392, 56, 16);
		contentPane.add(lblNewLabel_16);
		
		txtLista1F = new JTextField();
		txtLista1F.setBounds(461, 301, 65, 22);
		contentPane.add(txtLista1F);
		txtLista1F.setColumns(10);
		
		txtLista2F = new JTextField();
		txtLista2F.setColumns(10);
		txtLista2F.setBounds(460, 341, 65, 22);
		contentPane.add(txtLista2F);
		
		txtLista3F = new JTextField();
		txtLista3F.setColumns(10);
		txtLista3F.setBounds(460, 389, 65, 22);
		contentPane.add(txtLista3F);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		btnActualizar.setBounds(413, 437, 97, 25);
		contentPane.add(btnActualizar);
	}
}