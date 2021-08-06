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


public class Cliente2 extends JFrame {
	
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
		txtnombre.setText(null);
		txtapellido.setText(null);
		txtedad.setText(null);
		txtcedula.setText(null);
		txttelefono.setText(null);
		txtdireccion.setText(null);
		txtfechanacimiento.setText(null);
		txtId.setText(null);
		txtClave.setText(null);
		
	}

	
	

	private JPanel contentPane;
	private JTextField txtnombre;
	private JLabel lblNewLabel_1;
	private JButton btnLimpiar;
	private JButton btnSalir;
	private JTextField txtClave;
	private JTextField txtId;
	private JTextField txtapellido;
	private JTextField txtdireccion;
	private JTextField txtcedula;
	private JTextField txtfechanacimiento;
	private JTextField txtedad;
	private JTextField txttelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente2 frame = new Cliente2();
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
	public Cliente2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(12, 128, 59, 14);
		contentPane.add(lblNewLabel);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(123, 125, 212, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		 this.setLocationRelativeTo(null); 
		 
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(12, 370, 89, 23);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO Clientes(nombre,apellido,fechanacimiento,cedula,telefono,direccion,edad) values(?,?,?,?,?,?,?)");
					ps.setString(1,txtnombre.getText());
					ps.setString(2,txtapellido.getText());
					ps.setString(3,txtfechanacimiento.getText());
					ps.setInt(4,Integer.parseInt(txtcedula.getText()));
					ps.setInt(5,Integer.parseInt(txttelefono.getText()));
					ps.setString(6,txtdireccion.getText());
					ps.setInt(7,Integer.parseInt(txtedad.getText()));
					
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
		contentPane.add(btnGuardar);
		
		lblNewLabel_1 = new JLabel("Clientes");
		lblNewLabel_1.setBounds(145, 13, 143, 36);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel_1);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(335, 370, 89, 23);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarcajas();
			}
		});
		contentPane.add(btnLimpiar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(436, 370, 89, 23);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
				
			}
		});
		contentPane.add(btnSalir);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(222, 369, 97, 25);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("DELETE FROM Clientes WHERE  nombre = ?");
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
		contentPane.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(113, 369, 97, 25);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("update Clientes set  (nombre=?, apellido=?, fechanacimiento=?, cedula=?, telefono=?, direccion=?, edad=?)  where nombre = ?");
					ps.setString(1,txtnombre.getText());
					ps.setString(2,txtapellido.getText());
					ps.setString(3,txtfechanacimiento.getText());
					ps.setInt(4,Integer.parseInt(txtcedula.getText()));
					ps.setInt(5,Integer.parseInt(txttelefono.getText()));
					ps.setString(6,txtdireccion.getText());
					ps.setInt(7,Integer.parseInt(txtedad.getText()));
					ps.setString(8,txtClave.getText());
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
		contentPane.add(btnModificar);
		
		txtClave = new JTextField();
		txtClave.setBounds(123, 90, 212, 22);
		contentPane.add(txtClave);
		txtClave.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre a Buscar");
		lblNewLabel_2.setBounds(12, 93, 99, 16);
		contentPane.add(lblNewLabel_2);
		
		txtId = new JTextField();
		txtId.setBounds(466, 45, 59, 22);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(415, 89, 97, 25);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con = getConnection();
					ps = con.prepareStatement("select * from Clientes where nombre = ?");
					ps.setString(1, txtClave.getText());
					
					rs = ps.executeQuery();
					if (rs.next()) {
						txtId.setText(rs.getString("codigo"));
						txtnombre.setText(rs.getString("nombre"));
						txtapellido.setText(rs.getString("apellido"));
						txtedad.setText(rs.getString("edad"));
						txtcedula.setText(rs.getString("cedula"));
						txttelefono.setText(rs.getString("telefono"));
						txtfechanacimiento.setText(rs.getString("fechanacimiento"));
					}else {
						JOptionPane.showMessageDialog(null,"No existe un grupo con ese Nombre");
					}
				}catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		contentPane.add(btnBuscar);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(436, 48, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Apellido");
		lblNewLabel_4.setBounds(12, 156, 89, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Direccion");
		lblNewLabel_5.setBounds(12, 195, 89, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Cedula");
		lblNewLabel_6.setBounds(12, 229, 56, 16);
		contentPane.add(lblNewLabel_6);
		
		txtapellido = new JTextField();
		txtapellido.setBounds(123, 153, 212, 22);
		contentPane.add(txtapellido);
		txtapellido.setColumns(10);
		
		txtdireccion = new JTextField();
		txtdireccion.setBounds(123, 192, 212, 22);
		contentPane.add(txtdireccion);
		txtdireccion.setColumns(10);
		
		txtcedula = new JTextField();
		txtcedula.setBounds(123, 226, 212, 22);
		contentPane.add(txtcedula);
		txtcedula.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Fecha Nacimiento");
		lblNewLabel_7.setBounds(12, 270, 110, 16);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Edad");
		lblNewLabel_8.setBounds(12, 299, 89, 16);
		contentPane.add(lblNewLabel_8);
		
		txtfechanacimiento = new JTextField();
		txtfechanacimiento.setBounds(123, 267, 212, 22);
		contentPane.add(txtfechanacimiento);
		txtfechanacimiento.setColumns(10);
		
		txtedad = new JTextField();
		txtedad.setBounds(123, 299, 212, 22);
		contentPane.add(txtedad);
		txtedad.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Telefono");
		lblNewLabel_9.setBounds(12, 341, 56, 16);
		contentPane.add(lblNewLabel_9);
		
		txttelefono = new JTextField();
		txttelefono.setBounds(123, 334, 212, 22);
		contentPane.add(txttelefono);
		txttelefono.setColumns(10);
	}
}
