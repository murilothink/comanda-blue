package br.com.nextgen2020.comandablue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.crypto.Cipher;
import java.security.NoSuchAlgorithmException;

@CrossOrigin(origins = "http://localhost:3000")
@SpringBootApplication
public class ComandablueApplication implements CommandLineRunner {

	private static Logger log = LoggerFactory.getLogger(ComandablueApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ComandablueApplication.class, args);
	}

	@Override
	public void run(String... strings) {

		log.info("Versao JAVA=" + System.getProperty("java.version"));
		if(!System.getProperty("java.version").contains("1.8.0")){
			log.error("Versao JAVA diferente de 1.8.0");
		}

		try{
			// REF: https://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html#getMaxAllowedKeyLength(java.lang.String)
			//		https://stackoverflow.com/questions/13238074/how-to-check-that-java-cryptography-extension-is-installed
			// Se jce_policy estiver instalado keyLen = 2147483647. Se nao estiver instalado classe EncryptDecrypt falha
			int keyLen = Cipher.getMaxAllowedKeyLength("AES");
			if(keyLen < 2147483647){
				log.warn("JCE POLICY provavelmente nao instalado, pois Crypto MaxAllowedKeyLength=" + keyLen + ", deveria ser 2147483647");
			}else{
				log.info("JCE POLICY OK, Crypto MaxAllowedKeyLength=" + keyLen);
			}
		} catch (NoSuchAlgorithmException e) {
			log.error("Cipher.getMaxAllowedKeyLength() falhou, verificar JCE POLICY");
		}

	}

}
