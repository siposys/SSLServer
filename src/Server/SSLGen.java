package Server;

public class SSLGen {
	
	private void createSelfSignedCert(File targetKeystore, String keyName,
		    String keystorePassword) {
		  if (targetKeystore.exists()) {
		    throw new RuntimeException("Keystore already exists: " + targetKeystore);
		  }

		  try {
		    KeyPair kp = generateKeyPair();

		    X509CertificateObject cert = generateCert(keyName, kp, true, kp.getPublic(),
		        kp.getPrivate());

		    char[] password = keystorePassword.toCharArray();
		    KeyStore keystore = KeyStore.getInstance("JKS");
		    keystore.load(null, null);
		    keystore.setCertificateEntry(keyName + "Cert", cert);
		    keystore.setKeyEntry(keyName + "Key", kp.getPrivate(), password, new Certificate[] {cert});
		    try (FileOutputStream fos = new FileOutputStream(targetKeystore)) {
		      keystore.store(fos, password);
		    }
		  } catch (Exception e) {
		    throw new RuntimeException(e);
		  }
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
