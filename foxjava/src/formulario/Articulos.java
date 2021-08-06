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


public class Articulos extends JFrame {
	
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
	    txtcodigo.setText(null);
		txtcodigo.setText(null);
		
	}

	
	

	private JPanel contentPane;
	private JTextField txtcodigo;
	private JLabel lblNewLabel_1;
	private JButton btnLimpiar;
	private JButton btnSalir;
	private JTextField txtClave;
	private JTextField txtId;
	private JTextField txtcbarra;
	private JTextField txtpreciocosto;
	private JTextField txtprecioventa1;
	private JTextField txtprecioventa2;
	private JTextField txtstock;
	private JTextField txtstockm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Articulos frame = new Articulos();
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
	public Articulos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(26, 125, 59, 14);
		contentPane.add(lblNewLabel);
		
		txtcodigo = new JTextField();
		txtcodigo.setBounds(159, 122, 212, 20);
		contentPane.add(txtcodigo);
		txtcodigo.setColumns(10);
		 this.setLocationRelativeTo(null); 
		 
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(12, 400, 89, 23);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO Articulos(Codigo,Cbarra,Precioventa2,Precioventa1,Stockm,Preciocosto,Stock) values(?,?,?,?,?,?,?)");
					ps.setString(1,txtcodigo.getText());
					ps.setString(2,txtcbarra.getText());
					ps.setString(3,txtprecioventa2.getText());
					ps.setInt(4,Integer.parseInt(txtprecioventa1.getText()));
					ps.setInt(5,Integer.parseInt(txtstockm.getText()));
					ps.setString(6,txtpreciocosto.getText());
					ps.setInt(7,Integer.parseInt(txtstock.getText()));
					
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
		
		lblNewLabel_1 = new JLabel("Articulos");
		lblNewLabel_1.setBounds(145, 13, 143, 36);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel_1);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(333, 400, 89, 23);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarcajas();
			}
		});
		contentPane.add(btnLimpiar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(436, 400, 89, 23);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
				
			}
		});
		contentPane.add(btnSalir);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(222, 398, 97, 25);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("DELETE FROM Artiulos WHERE  Cbarra = ?");
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
		btnModificar.setBounds(121, 399, 97, 25);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("update Clientes set  (Codigo=?, Cbarra=?, Precioventa2=?, Precioventa1=?, S=?, direccion=?, edad=?)  where nombre = ?");
					ps.setString(1,txtcodigo.getText());
					ps.setString(2,txtcbarra.getText());
					ps.setString(3,txtprecioventa2.getText());
					ps.setInt(4,Integer.parseInt(txtprecioventa1.getText()));
					ps.setInt(5,Integer.parseInt(txtstockm.getText()));
					ps.setString(6,txtpreciocosto.getText());
					ps.setInt(7,Integer.parseInt(txtstock.getText()));
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
		txtClave.setBounds(159, 87, 212, 22);
		contentPane.add(txtClave);
		txtClave.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Codigo a Buscar");
		lblNewLabel_2.setBounds(26, 90, 99, 16);
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
					ps = con.prepareStatement("select * from Articulos where Cbarra = ?");
					ps.setString(1, txtClave.getText());
					
					rs = ps.executeQuery();
					if (rs.next()) {
						txtId.setText(rs.getString("Codigo"));
						txtcodigo.setText(rs.getString("Codigo"));
						txtcbarra.setText(rs.getString("Cbarra"));
						txtstock.setText(rs.getString("Stock"));
						txtprecioventa1.setText(rs.getString("Precioventa1"));
						txtstockm.setText(rs.getString("Stockm"));
						txtprecioventa2.setText(rs.getString("PrecioVenta2"));
						txtpreciocosto.setText(rs.getString("Preciocosto"));
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
		
		JLabel lblNewLabel_4 = new JLabel("CBarra");
		lblNewLabel_4.setBounds(26, 153, 89, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Precio de Costo");
		lblNewLabel_5.setBounds(26, 192, 89, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Precio de Venta 1");
		lblNewLabel_6.setBounds(26, 226, 121, 16);
		contentPane.add(lblNewLabel_6);
		
		txtcbarra = new JTextField();
		txtcbarra.setBounds(159, 150, 212, 22);
		contentPane.add(txtcbarra);
		txtcbarra.setColumns(10);
		
		txtpreciocosto = new JTextField();
		txtpreciocosto.setBounds(159, 189, 212, 22);
		contentPane.add(txtpreciocosto);
		txtpreciocosto.setColumns(10);
		
		txtprecioventa1 = new JTextField();
		txtprecioventa1.setBounds(159, 223, 212, 22);
		contentPane.add(txtprecioventa1);
		txtprecioventa1.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Precio de Venta 2");
		lblNewLabel_7.setBounds(27, 267, 110, 16);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Stock");
		lblNewLabel_8.setBounds(26, 299, 89, 16);
		contentPane.add(lblNewLabel_8);
		
		txtprecioventa2 = new JTextField();
		txtprecioventa2.setBounds(159, 264, 212, 22);
		contentPane.add(txtprecioventa2);
		txtprecioventa2.setColumns(10);
		
		txtstock = new JTextField();
		txtstock.setBounds(159, 296, 212, 22);
		contentPane.add(txtstock);
		txtstock.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("StockM");
		lblNewLabel_9.setBounds(24, 334, 56, 16);
		contentPane.add(lblNewLabel_9);
		
		txtstockm = new JTextField();
		txtstockm.setBounds(159, 331, 212, 22);
		contentPane.add(txtstockm);
		txtstockm.setColumns(10);
	}
}