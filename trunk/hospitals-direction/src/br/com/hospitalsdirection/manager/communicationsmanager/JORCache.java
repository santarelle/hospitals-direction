package br.com.hospitalsdirection.manager.communicationsmanager;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;


public final class JORCache extends JsonObjectRequest {

    /** The Constant SOCKET_TIMEOUT_MS. */
    private static final int SOCKET_TIMEOUT_MS = 120000;
    
    /** The Constant MAX_RETRIES. */
    private static final int MAX_RETRIES = 3;

    /**
     * Instantiates a new jOR cache.
     * @param method
     *            the method
     * @param url
     *            the url
     * @param params
     *            the params
     * @param listener
     *            the listener
     * @param errorListener
     *            the error listener
     * @throws JSONException
     *             the jSON exception
     */
    public JORCache(final int method, final String url,
            final JSONObject params, final Listener<JSONObject> listener,
            final ErrorListener errorListener) {
        super(method, url, params, listener, errorListener);
        this.setShouldCache(Boolean.FALSE);
        this.setRetryPolicy(new DefaultRetryPolicy(SOCKET_TIMEOUT_MS,
                MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}
