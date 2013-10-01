package br.com.hospitalsdirection.manager.communicationsmanager;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import br.com.hospitalsdirection.manager.metadadosmanager.Hospital;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;


@SuppressLint("NewApi")
public abstract class ServiceRequest<T> {

    /** The context. */
    private Context context;

    /** The entry. */
    private Cache.Entry entry = null;

    /** The tag. */
    private final String tag = getClass().getSimpleName();

    /** The name service. */
    private String nameService;

    /** The url. */
    private String url;

    // /** The str erro. */
    // private String strErro = "";

    /**
     * Creates the my req error listener.
     * @return the response. error listener
     */
    protected abstract Response.ErrorListener errorListener();

    /**
     * Metodo para definir o tempo de cache de requisição.
     * @param cacheExpired
     *            the cache expired
     * @return the cache. entry
     */
    protected final Cache.Entry setEntryTime(final long cacheExpired) {
        long now = System.currentTimeMillis();
        long serverDate = 0;
        String serverEtag = null;
        final long cacheHitButRefreshed = 1 * 60 * 1000; // in 3 minutes cache
                                                         // will be hit, but
                                                         // also refreshed on
                                                         // background
        // final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this
        // cache entry expires completely
        final long softExpire = now + cacheHitButRefreshed;
        final long ttl = now + cacheExpired;

        entry = new Cache.Entry();
        // entry.data = response.data;
        entry.etag = serverEtag;
        entry.softTtl = softExpire;
        entry.ttl = ttl;
        entry.serverDate = serverDate;

        return entry;
    }

    /**
     * Retorna o application context.
     * @return application context
     */
    public final Context getApplicationContext() {
        Context contextInt = null;
        if (this.context instanceof Application) {
            contextInt = this.context;
        } else {
            contextInt = this.context.getApplicationContext();
        }
        return contextInt;
    }

   

    /**
     * Retorna o url.
     * @return url
     */
    public final String getUrl() {
        return url;
    }

    /**
     * Envia o url.
     * @param urlExt
     *            o novo url
     */
    public final void setUrl(final String urlExt) {
        this.url = urlExt;
    }

    /**
     * Gets the url.
     * @param urlAppend
     *            the url append
     * @return the url
     */
    public final String getUrlRequest(final String urlAppend) {
        return nameService + urlAppend;
    }

    /**
     * Gets the name service.
     * @return the name service
     */
    public final String getNameService() {
        return nameService;
    }

    /**
     * Sets the name service.
     * @param nameServiceExt
     *            the new name service
     */
    public final void setNameService(final String nameServiceExt) {
        this.nameService = nameServiceExt;
    }

    /**
     * Retorna o context.
     * @return context
     */
    public final Context getContext() {
        return context;
    }

    /**
     * Envia o context.
     * @param contextExt
     *            o novo context
     */
    public final void setContext(final Context contextExt) {
        this.context = contextExt;
    }

    /**
     * Retorna o entry.
     * @return entry
     */
    public final Cache.Entry getEntry() {
        return entry;
    }

    /**
     * Parses the json respone.
     * @param response
     *            JSONObject
     * @throws JSONException
     *             the jSON exception
     * @see br.com.vi.mobile.services.ServiceRequest
     *      #parseJSONRespone(org.json.JSONObject).
     */
    public final void parseJSONRespone(final JSONObject response)
            throws JSONException {
        List<T> result = null;
        result = getResult(response);
        executeResult(result);
    }

    /**
     * Retorna o result.  
     * @param response
     *            the response
     * @return result
     * @throws JSONException
     *             the jSON exception
     */
    protected abstract List<T> getResult(final JSONObject response)
            throws JSONException;

    /**
     * Execute result.
     * @param resultList
     *            the result list
     * @throws JSONException
     *             the jSON exception
     */
    protected abstract void executeResult(List<T> resultList);
    
    
    
    public final void request(final Context context, final String strFiltro){

        setContext(context);
        setUrl(getUrlRequest(strFiltro));
        Log.i("url get:", getUrl());
        RequestQueue reqQueue = Volley.newRequestQueue(context);
        JORCache jr;
        jr = new JORCache(Request.Method.GET, getUrl(), null,
                successListenerJSON(), errorListener());
        if (getEntry() != null) {
            jr.setCacheEntry(getEntry());
        }
        reqQueue.add(jr);
    }

    /**
     * Success listener json.
     * @return the response. listener
     */
    protected final Response.Listener<JSONObject> successListenerJSON() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                try {
                    parseJSONRespone(response);
                } catch (JSONException e) {
                    Log.i("erro", e.getMessage());
                }

            }

        };
    }
}
