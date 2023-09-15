package com.hotelalura.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

@SuppressWarnings("serial")
public class Exito extends JDialog {

	private final JPanel contentPanel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			Exito dialog = new Exito();
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public Exito() {
		contentPanel = new JPanel();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/imagenes/aH-40px.png")));
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				llamarMenuUsuario();
			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Objects.requireNonNull(Exito.class.getResource("/imagenes/Ha-100px.png"))));
		lblNewLabel.setBounds(123, 11, 100, 100);
		contentPanel.add(lblNewLabel);


		JLabel lblNewLabel_1 = new JLabel("Datos guardados satisfactoriamente");
		lblNewLabel_1.setForeground(new Color (12, 138, 199));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(27, 122, 322, 21);
		contentPanel.add(lblNewLabel_1);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarMenuUsuario();
			}
		});


	}

	private void llamarMenuUsuario() {
		dispose();//sirve para cerrar la ventana actual
		MenuUsuario usuario = new MenuUsuario();
		usuario.setVisible(true);
	}

}
