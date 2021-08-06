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


public class grupo extends JFrame {
	
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
		txtClave.setText(null);
		txtnombre.setText(null);
		txtId.setText(null);
		
	}

	String pass;
	pass=(txtnombre.getText());
	con = getConnection();
	ps = con.prepareStatement("select * from votantes where cedula = ?");
	ps.setString(1, txtci.getText());
	rs = ps.executeQuery();
	if (rs.next()) {
		JOptionPane.showMessageDialog(null,"Datos Icorrectos del Votante",
				pass, JOptionPane.ERROR_MESSAGE);
		limpiarcajas();
	

	private JPanel contentPane;
	private JTextField txtnombre;
	private JLabel lblNewLabel_1;
	private JButton btnLimpiar;
	private JButton btnSalir;
	private JTextField txtClave;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					grupo frame = new grupo();
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
	public grupo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(31, 150, 59, 14);
		contentPane.add(lblNewLabel);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(123, 147, 342, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		 this.setLocationRelativeTo(null); 
		 
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO grupos(nombre) values(?)");
					ps.setString(1,txtnombre.getText());
					
int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Grupo Guardado");
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
		btnGuardar.setBounds(12, 237, 89, 23);
		contentPane.add(btnGuardar);
		
		lblNewLabel_1 = new JLabel("Grupo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(145, 13, 143, 36);
		contentPane.add(lblNewLabel_1);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarcajas();
			}
		});
		btnLimpiar.setBounds(335, 237, 89, 23);
		contentPane.add(btnLimpiar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
				
			}
		});
		btnSalir.setBounds(436, 237, 89, 23);
		contentPane.add(btnSalir);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("DELETE FROM grupos WHERE  nombre = ?");
					ps.setString(1,txtClave.getText());
					
int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Grupo Eliminado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al Eliminar Grupo");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
				
				
				
			}
		});
		btnEliminar.setBounds(214, 236, 97, 25);
		contentPane.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("update grupos set  nombre = ? where nombre = ?");
					ps.setString(1,txtnombre.getText());
					ps.setString(2,txtClave.getText());
					
int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Grupo Modificado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al Modificar Grupo");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
				
				
				
			
					

				
				
				
			}
		});
		btnModificar.setBounds(105, 236, 97, 25);
		contentPane.add(btnModificar);
		
		txtClave = new JTextField();
		txtClave.setBounds(123, 90, 244, 22);
		contentPane.add(txtClave);
		txtClave.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Clave:");
		lblNewLabel_2.setBounds(31, 93, 56, 16);
		contentPane.add(lblNewLabel_2);
		
		txtId = new JTextField();
		txtId.setBounds(466, 45, 59, 22);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con = getConnection();
					ps = con.prepareStatement("select * from grupos where nombre = ?");
					ps.setString(1, txtClave.getText());
					
					rs = ps.executeQuery();
					if (rs.next()) {
						txtId.setText(rs.getString("codigo"));
						
						txtnombre.setText(rs.getString("nombre"));
						//txtClave.getText(null);
					}else {
						JOptionPane.showMessageDialog(null,"No existe un grupo con ese Nombre");
					}
				}catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		btnBuscar.setBounds(415, 89, 97, 25);
		contentPane.add(btnBuscar);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(436, 48, 56, 16);
		contentPane.add(lblNewLabel_3);
	}
}
