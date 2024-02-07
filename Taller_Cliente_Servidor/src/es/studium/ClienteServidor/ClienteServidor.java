package es.studium.ClienteServidor;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class ClienteServidor extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtResultado;
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ClienteServidor frame = new ClienteServidor();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	public ClienteServidor()
	{
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Andalus", Font.PLAIN, 16));
		lblNombre.setBounds(60, 49, 66, 14);
		contentPane.add(lblNombre);
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Andalus", Font.PLAIN, 16));
		lblApellidos.setBounds(60, 74, 66, 14);
		contentPane.add(lblApellidos);
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Obtener los datos
				String nombre = txtNombre.getText();
				String apellidos = txtApellidos.getText();
				// Obtener el resultado del servidor web
				String resultado = enviarDatos(nombre, apellidos);
				// Mostrar el resultado
				txtResultado.setText(resultado);
			}
		});
		btnEnviar.setBounds(106, 115, 89, 23);
		contentPane.add(btnEnviar);
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				txtNombre.setText("");
				txtApellidos.setText("");
				txtResultado.setText("");
				txtNombre.requestFocus();
			}
		});
		btnLimpiar.setBounds(240, 115, 89, 23);
		contentPane.add(btnLimpiar);
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 48, 186, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtApellidos = new JTextField();
		txtApellidos.setBounds(133, 73, 186, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);
		JLabel lblRespuesta = new JLabel("Respuesta:");
		lblRespuesta.setBounds(60, 189, 66, 14);
		contentPane.add(lblRespuesta);
		txtResultado = new JTextField();
		txtResultado.setEditable(false);
		txtResultado.setBounds(133, 186, 186, 20);
		contentPane.add(txtResultado);
		txtResultado.setColumns(10);
		}
		public String enviarDatos(String n, String a)
		{
		String r = new String("");
		try
		{
		String query = "nombre="+n;
		query += "&";
		query += "apellidos="+a;
		URL url = new URL("http://localhost/Servidor/index.php");
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		// Indicar que los datos se enviarán en modo POST
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-length", String.valueOf(query.length()));
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		DataOutputStream output = new DataOutputStream(connection.getOutputStream());
		output.writeBytes(query);
		output.close();
		// Procesar la respuesta del servidor
		DataInputStream input = new DataInputStream(connection.getInputStream());
		for(int c = input.read(); c != -1; c = input.read())
		{
		r += (char)c;
		}
		input.close();
		System.out.println("Resp Code:"+connection.getResponseCode());
		System.out.println("Resp Message:"+ connection.getResponseMessage());
		}
		catch (IOException e)
		{
		e.printStackTrace();
		}
		return(r);
		}
		}
