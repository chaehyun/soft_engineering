package communication;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

public class Communicator {

	// local wifi
	// private final static String serverAddress =
	// "http://192.168.137.1:8080/EverestServer/EverestServlet";
	// GoQual 12F-02
	private final static String serverAddress = "http://127.0.0.1:8080/KnowledgeBase/KnowledgeBaseServlet";

	public static JSONObject sendMessage(JSONObject request)
			throws ClientProtocolException, ConnectTimeoutException,
			IOException, JSONException{
		
		// Initialize HTTP connection
		HttpClient client = new DefaultHttpClient();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);

		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.//
		HttpConnectionParams.setSoTimeout(client.getParams(), 6000);

		HttpResponse httpResponse;

		// Send message and get response
		StringEntity se = new StringEntity(request.toString(), HTTP.UTF_8);
		se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json; charset=UTF-8"));
		HttpPost post = new HttpPost(serverAddress);

		post.setEntity(se);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-Type", "application/json; charset=UTF-8");
		httpResponse = client.execute(post);

		JSONObject response = null;
		/* Checking response */
		if (httpResponse != null) {
			// Convert response to JSON
			InputStream in = httpResponse.getEntity().getContent();
			Scanner s1 = new Scanner(in);
			Scanner s2 = s1.useDelimiter("\\A");
			response = new JSONObject(s2.next());
			s1.close();
			s2.close();
		} 

		return response;
	}
}
