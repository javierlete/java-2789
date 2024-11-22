package com.ipartek.escritorio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EjemploWindowBuilder {

	private JFrame frame;
	private JTextField tfNombre;
	private JTextField textField;
	private JTextField textField_1;

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
		frame.setBounds(100, 100, 583, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pSaludo = new JPanel();
		frame.getContentPane().add(pSaludo, BorderLayout.NORTH);
		
		tfNombre = new JTextField();
		pSaludo.add(tfNombre);
		tfNombre.setColumns(10);
		
		JButton btnAceptar = new JButton("Saludar");
		pSaludo.add(btnAceptar);
		
		JLabel lblMensaje = new JLabel("");
		pSaludo.add(lblMensaje);
		
		JTextArea taMensajes = new JTextArea();
		frame.getContentPane().add(taMensajes, BorderLayout.SOUTH);
		
		JPanel pFormulario = new JPanel();
		frame.getContentPane().add(pFormulario, BorderLayout.CENTER);
		GridBagLayout gbl_pFormulario = new GridBagLayout();
		gbl_pFormulario.columnWidths = new int[]{0, 0, 0};
		gbl_pFormulario.rowHeights = new int[]{0, 0, 0, 0};
		gbl_pFormulario.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pFormulario.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		pFormulario.setLayout(gbl_pFormulario);
		
		JLabel lblNewLabel = new JLabel("Apellidos");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		pFormulario.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		pFormulario.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		pFormulario.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		pFormulario.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Dale al botón para terminar");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		pFormulario.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		pFormulario.add(btnNewButton, gbc_btnNewButton);
	}
}
