package com.github.yuyang226.j500px.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.yuyang226.j500px.J500pxConstants;
import com.github.yuyang226.j500px.oauth.OAuthUtils;
import com.github.yuyang226.j500px.utils.Base64;
import com.github.yuyang226.j500px.utils.IOUtilities;
import com.github.yuyang226.j500px.utils.StringUtilities;
import com.github.yuyang226.j500px.utils.UrlUtilities;

/**
 * Transport implementation using the REST interface.
 *
 * @author yayu
 */
public class REST extends Transport {
    private static final Logger logger = LoggerFactory.getLogger(REST.class);

    private boolean proxyAuth = false;
    private String proxyUser = "";
    private String proxyPassword = "";

    /**
     * Construct a new REST transport instance.
     *
     * @throws ParserConfigurationException
     */
    public REST() throws ParserConfigurationException {
        setTransportType(REST);
        setHost(J500pxConstants.DEFAULT_HOST);
        setPath(J500pxConstants.PATH_REST);
        setResponseClass(RESTResponse.class);
    }

    /**
     * Construct a new REST transport instance using the specified host endpoint.
     *
     * @param host The host endpoint
     * @throws ParserConfigurationException
     */
    public REST(String host) throws ParserConfigurationException {
        this();
        setHost(host);
    }

    /**
     * Construct a new REST transport instance using the specified host and port endpoint.
     *
     * @param host The host endpoint
     * @param port The port
     * @throws ParserConfigurationException
     */
    public REST(String host, int port) throws ParserConfigurationException {
        this();
        setHost(host);
        setPort(port);
    }

    /**
     * Set a proxy for REST-requests.
     *
     * @param proxyHost
     * @param proxyPort
     */
    public void setProxy(String proxyHost, int proxyPort) {
        System.setProperty("http.proxySet", "true");
        System.setProperty("http.proxyHost", proxyHost);
        System.setProperty("http.proxyPort", "" + proxyPort);
    }

    /**
     * Set a proxy with authentication for REST-requests.
     *
     * @param proxyHost
     * @param proxyPort
     * @param username
     * @param password
     */
    public void setProxy(
            String proxyHost, int proxyPort,
            String username, String password
    ) {
        setProxy (proxyHost, proxyPort);
        proxyAuth = true;
        proxyUser = username;
        proxyPassword = password;
    }

    /**
     * Invoke an HTTP GET request on a remote host.  You must close the InputStream after you are done with.
     *
     * @param path The request path
     * @param parameters The parameters (collection of Parameter objects)
     * @return The Response
     * @throws IOException
     * @throws JSONException
     */
    public Response get(String path, List<Parameter> parameters) throws IOException, JSONException {
        String data = getLine(path, parameters);
        return new RESTResponse(data);
    }

    private InputStream getInputStream(String path, List<Parameter> parameters) throws IOException {
        URL url = UrlUtilities.buildUrl(getHost(), getPort(), path, parameters);
        if (logger.isDebugEnabled()) {
            logger.debug("GET URL: {}", url.toString());
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.addRequestProperty("Cache-Control", "no-cache,max-age=0"); 
        conn.addRequestProperty("Pragma", "no-cache"); 
        conn.setRequestMethod("GET");
        if (proxyAuth) {
            conn.setRequestProperty(
                    "Proxy-Authorization",
                    "Basic " + getProxyCredentials()
            );
        }
        conn.connect();
        return conn.getInputStream();
    }

    /**
     * Send a GET request to the provided URL with the given parameters, 
     * then return the response as a String.
     * @param path
     * @param parameters
     * @return the data in String
     * @throws IOException
     */
    public String getLine(String path, List<Parameter> parameters) throws IOException {
        InputStream in = null;
        BufferedReader rd = null;
        try {
            in = getInputStream(path, parameters);
            rd = new BufferedReader(new InputStreamReader(in, OAuthUtils.ENC));
            final StringBuffer buf = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                buf.append(line);
            }

            return buf.toString();
        } finally {
            IOUtilities.close(in);
            IOUtilities.close(rd);
        }
    }

    /**
     * <p>A helper method for sending a GET request to the provided URL with the given parameters, 
     * then return the response as a Map.</p>
     * 
     * <p>Please make sure the response data is a Map before calling this method.</p> 
     * @param path
     * @param parameters
     * @return the data in Map with key value pairs
     * @throws IOException
     */
    public Map<String, String> getMapData(boolean getRequestMethod, String path, List<Parameter> parameters) throws IOException {
        String data = getRequestMethod ? getLine(path, parameters) : sendPost(path, parameters);
        return getDataAsMap(URLDecoder.decode(data, OAuthUtils.ENC));
    }

    public Map<String, String> getDataAsMap(String data) {
        Map<String, String> result = new HashMap<String, String>();
        if (data != null) {
            for (String string : StringUtilities.split(data, "&")) {
                String[] values = StringUtilities.split(string, "=");
                if (values.length == 2) {
                    result.put(values[0], values[1]);
                }
            }
        }
        return result;
    }
    
    public String sendPost(String path, List<Parameter> parameters) throws IOException {
    	return sendData(OAuthUtils.REQUEST_METHOD_POST, path, parameters);
    }
    
    public String sendData(String method, String path, List<Parameter> parameters) throws IOException{
        if (logger.isDebugEnabled()) {
            logger.debug("Send {} Input Params: path '{}'; parameters {}", new String[]{method, path, parameters.toString()});
        }
        HttpURLConnection conn = null;
        DataOutputStream out = null;
        String data = null;
        try {
            URL url = UrlUtilities.buildPostUrl(getHost(), getPort(), path);
            if (logger.isDebugEnabled()) {
                logger.debug("{} URL: {}", method, url.toString());
            }
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod(method);
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            boolean isDelete = OAuthUtils.REQUEST_METHOD_DELETE.equals(method);
            String postParam = encodeParameters(parameters);
            byte[] bytes = postParam.getBytes(J500pxConstants.UTF8);
            if (isDelete) {
            	//Android does not support DELETE to write to a OUTPUT stream
            	StringBuffer buf = new StringBuffer();
                for (int i = 0; i < parameters.size(); i++) {
                    if (i != 0) {
                        buf.append(",");
                    }
                    Parameter param = parameters.get(i);
                    buf.append(UrlUtilities.encode(param.getName()))
                            .append("=\"").append(UrlUtilities.encode(String.valueOf(param.getValue()))).append("\"");
                }
            	conn.setRequestProperty("Authorization", "OAuth " + buf.toString());
            } else {
            	conn.setRequestProperty("Content-Length", Integer.toString(bytes.length));
                conn.addRequestProperty("Cache-Control", "no-cache,max-age=0"); 
                conn.addRequestProperty("Pragma", "no-cache");
            }
            
            conn.setUseCaches(false);
            conn.setDoOutput(!isDelete);
            conn.setDoInput(true);
            conn.connect();
            if (!isDelete) {
            	out = new DataOutputStream(conn.getOutputStream());
            	out.write(bytes);
            	out.flush();
            	out.close();
            }
            
            int responseCode = HttpURLConnection.HTTP_OK;
            try {
                responseCode = conn.getResponseCode();
            } catch (IOException e) {
                logger.error("Failed to get the " + method + " response code", e);
                if (conn.getErrorStream() != null) {
                    responseCode = conn.getResponseCode();
                }
            }
            if ((responseCode != HttpURLConnection.HTTP_OK)) {
                String errorMessage = readFromStream(conn.getErrorStream());
                throw new IOException("Connection Failed. Response Code: "
                        + responseCode + ", Response Message: " + conn.getResponseMessage()
                        + ", Error: " + errorMessage);
            }

            String result = readFromStream(conn.getInputStream());
            data = result.trim();
            return data;
        } finally {
            IOUtilities.close(out);
            if (conn != null)
                conn.disconnect() ;
            if (logger.isDebugEnabled()) {
                logger.debug("Send {} Result: {}", method, data);
            }
        }
    }
    
    private String readFromStream(InputStream input) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(input));
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        }finally {
            IOUtilities.close(input);
            IOUtilities.close(reader);
        }
    }

    /* (non-Javadoc)
     * @see com.googlecode.flickrjandroid.Transport#post(java.lang.String, java.util.List, boolean)
     */
    @Override
    public Response post(String path, List<Parameter> parameters) throws IOException, JSONException {
        String data = sendPost(path, parameters);
        return new RESTResponse(data);
    }
    
    /* (non-Javadoc)
	 * @see com.github.yuyang226.j500px.http.Transport#delete(java.lang.String, java.util.List)
	 */
	@Override
	protected Response delete(String path, List<Parameter> parameters)
			throws IOException, JSONException {
		String data =  sendData(OAuthUtils.REQUEST_METHOD_DELETE, path, parameters);
        return new RESTResponse(data);
	}

	public boolean isProxyAuth() {
        return proxyAuth;
    }

    /**
     * Generates Base64-encoded credentials from locally stored
     * username and password.
     *
     * @return credentials
     */
    public String getProxyCredentials() {
        return new String(
                Base64.encode((proxyUser + ":" + proxyPassword).getBytes())
        );
    }
    
    public static String encodeParameters(List<Parameter> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < parameters.size(); i++) {
            if (i != 0) {
                buf.append("&");
            }
            Parameter param = parameters.get(i);
            buf.append(UrlUtilities.encode(param.getName()))
                    .append("=").append(UrlUtilities.encode(String.valueOf(param.getValue())));
        }
        return buf.toString();
    }
    
    private void writeParam(Parameter param, DataOutputStream out, String boundary)
            throws IOException {
        String name = param.getName();
        out.writeBytes("\r\n");
        if (param instanceof ImageParameter) {
            ImageParameter imageParam = (ImageParameter)param; 
            Object value = param.getValue();
            out.writeBytes(String.format(Locale.US, "Content-Disposition: form-data; name=\"%s\"; filename=\"%s\";\r\n", name, imageParam.getImageName()));
            out.writeBytes(String.format(Locale.US, "Content-Type: image/%s\r\n\r\n", imageParam.getImageType()));
            if (value instanceof InputStream) {
                InputStream in = (InputStream) value;
                byte[] buf = new byte[512];
                int res = -1;
                while ((res = in.read(buf)) != -1) {
                    out.write(buf,0,res);
                }
            } else if (value instanceof byte[]) {
                out.write((byte[]) value);
            }
        } else {
            out.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"\r\n");
            out.writeBytes("Content-Type: text/plain; charset=UTF-8\r\n\r\n");
            out.write(((String) param.getValue()).getBytes("UTF-8"));
        }
        out.writeBytes("\r\n");
        out.writeBytes(boundary);
    }
}
