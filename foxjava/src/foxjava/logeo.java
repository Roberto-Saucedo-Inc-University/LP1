package foxjava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class logeo extends JFrame {

	private JPanel contentPane;
	private JTextField txtusuario;
	private JPasswordField jpassClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logeo frame = new logeo();
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
	public logeo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(185, 35, 69, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(12, 98, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a");
		lblNewLabel_2.setBounds(12, 127, 77, 16);
		contentPane.add(lblNewLabel_2);
		
		txtusuario = new JTextField();
		txtusuario.setBounds(96, 95, 173, 22);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);
		
		JButton btningresar = new JButton("Ingresar");
		btningresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char [] clave=jpassClave.getPassword();
				String clavefinal = new String (clave);
				if (txtusuario.getText().equals("ADMIN") && clavefinal.equals("12345")) {
					dispose();
					JOptionPane.showMessageDialog(null, "Bienvenido al Sistema","Lp1",
						JOptionPane.INFORMATION_MESSAGE	);
					principal p = new principal();
					p.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Error al Ingresar","Lp1",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btningresar.setBounds(75, 171, 97, 56);
		contentPane.add(btningresar);
		
		JButton btnsalir = new JButton("Salir");
		btnsalir.setBounds(246, 171, 97, 56);
		contentPane.add(btnsalir);
		
		jpassClave = new JPasswordField();
		jpassClave.setBounds(96, 124, 173, 22);
		contentPane.add(jpassClave);
	}
}
