package com.ipartek.escritorio;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EjemploManual {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		var frame = new JFrame();

		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(400, 300);

		var contenedor = frame.getContentPane();
		
		contenedor.setLayout(new FlowLayout());
		
		var texto = new JTextField(10);
		var boton = new JButton("Púlsame");
		var label = new JLabel("Introduce un texto para que te salude");

		boton.addActionListener(e -> 
			label.setText("Hola " + texto.getText())
		);
		
		contenedor.add(texto);
		contenedor.add(boton);
		contenedor.add(label);
		
		frame.setVisible(true);
	}
}
