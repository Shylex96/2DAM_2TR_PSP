package es.studium.EnvioCorreo;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class EnvioCorreo
{
	public static void main(String[] args)
	{
		String destinatario = "jorge@grupostudium.com";
				String asunto = "Correo de prueba enviado desde JAVA";
		String cuerpo = "<h1>Esta es una prueba de correo de tu alumno favorito: Esteban</h1>";
		enviarCorreo(destinatario, asunto, cuerpo);
	}
	private static void enviarCorreo(String destinatario, String asunto, String cuerpo)
	{
		String remitente = "curso20192020@grupostudium.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.ssl.trust", "grupostudium-com.correoseguro.dinaserver.com");
		props.put("mail.smtp.user", remitente);
		// Usar autenticación mediante usuario y clave
		props.put("mail.smtp.auth", "true");
		// Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587"); //El puerto SMTP seguro
		Session session = Session.getDefaultInstance(props);
		MimeMessage mensaje = new MimeMessage(session);
		try
		{
			mensaje.setFrom(new InternetAddress(remitente));
			mensaje.addRecipients(Message.RecipientType.TO, destinatario);
			//Se podrían añadir varios de la misma manera
			mensaje.setSubject(asunto);
			mensaje.setText(cuerpo,"UTF-8","html"); // Para envío en formato HTML
			Transport transport = session.getTransport("smtp");
			transport.connect("grupostudium-com.correoseguro.dinaserver.com", remitente, "Studium2020;");
			transport.sendMessage(mensaje, mensaje.getAllRecipients());
			transport.close();
			System.out.println("Mensaje enviado correctamente!");
		}
		catch (MessagingException me)
		{
			me.printStackTrace(); //Si se produce un error
		}
	}
}