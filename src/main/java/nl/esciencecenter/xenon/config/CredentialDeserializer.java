package nl.esciencecenter.xenon.config;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import nl.esciencecenter.xenon.credentials.Credential;
import nl.esciencecenter.xenon.credentials.DefaultCredential;
import nl.esciencecenter.xenon.credentials.PasswordCredential;
import nl.esciencecenter.xenon.credentials.CertificateCredential;

public class CredentialDeserializer extends JsonDeserializer<Credential> {

	@Override
	public Credential deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec oc = p.getCodec();
		JsonNode node = oc.readTree(p);
		
		if(!node.has("user")) {
			throw new JsonMappingException(p, "User field is required for credentials");
		}
		String user = node.get("user").asText();
		
		if(node.has("password")) {
			// @TODO Go through binaryValue instead of string
			char[] password = node.get("password").asText().toCharArray();
			return new PasswordCredential(user, password);
		} else if(node.has("certificatefile")){
			String certificateFile = node.get("certificatefile").asText();
			char[] passphrase = null;
			
			if(node.has("passphrase")) {
				// @TODO Go through binaryValue instead of string
				passphrase = node.get("passphrase").asText().toCharArray();
			}
			return new CertificateCredential(user, certificateFile, passphrase);
		} else {
			return new DefaultCredential(user);
		}
	}

}