package com.ipartek.escritorio;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EjemploWindowBuilder {

	private JFrame frame;
	private JTextField tfNombre;
	private JLabel lblMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EjemploWindowBuilder window = new EjemploWindowBuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EjemploWindowBuilder() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 486, 338);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAceptar = new JButton("Saludar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensaje.setText("Hola " + tfNombre.getText());
			}
		});
		btnAceptar.setBounds(167, 10, 69, 23);
		frame.getContentPane().add(btnAceptar);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(10, 11, 147, 20);
		frame.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		lblMensaje = new JLabel("");
		lblMensaje.setBounds(246, 14, 214, 14);
		frame.getContentPane().add(lblMensaje);
	}
}
