package org.bos.Achaoub.shared;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailServiceImpl{
	// activer acces Compte gmail
		// https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqbHZuM0JIMDVwV3MxTUV2b2swY29tLVJCS2ZnQXxBQ3Jtc0tsNWpPY2psSXhYU2Rfdk9iNU9vOTNPeEVlUm9rak5PZ3hsMUh3dXZTM1RmemV6R2JnYTA1ZXEzTHV5bFJnVjVIYmRlZThyS1BiZFFDeHZsOWNoMnVwUFBJNXhtdUFRQjh4bDJLdjA4dUp6M3V6RVpMSQ&q=https%3A%2F%2Fwww.google.com%2Fsettings%2Fsecurity%2Flesssecureapps
		//https://accounts.google.com/DisplayUnlockCaptcha
		@Autowired
		private JavaMailSender mailSender;

		
		public void envoyerEmail(String to, String subject, String text) {
			try {
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setTo(to);
				simpleMailMessage.setSubject(subject);
				simpleMailMessage.setText(text);

				mailSender.send(simpleMailMessage);

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}

		}
}
