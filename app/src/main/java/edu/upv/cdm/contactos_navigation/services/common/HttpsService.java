package edu.upv.cdm.contactos_navigation.services.common;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;

public class HttpsService extends AsyncTask {
    private SSLContext context;

    public HttpsService(Context contextGeneral){
        SslContextGenerator(contextGeneral);
    }
    public void SslContextGenerator(Context contextGeneral) {
        // Load CAs from an InputStream
        // (could be from a resource or ByteArrayInputStream or ...)
        CertificateFactory cf =null;
        try{
            cf=CertificateFactory.getInstance("X.509");
        }catch(CertificateException e){
            e.printStackTrace();
        }

        try{
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            InputStream caInput=contextGeneral.getAssets().open("load-der.cert");
            Certificate ca=null;
            try{
                ca=cf.generateCertificate(caInput);
            }catch(CertificateException e){
                e.printStackTrace();
            }finally{
                caInput.close();
            }


            String keyStoreType=KeyStore.getDefaultType();
            KeyStore keyStore=KeyStore.getInstance(keyStoreType);
            keyStore.load(null,null);
            keyStore.setCertificateEntry("ca",ca);

            String tmfAlgorithm=TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);


            // Create an SSLContext that uses our TrustManager
            context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);


        }
        catch (IOException e){ e.printStackTrace();}
        catch (KeyManagementException e){ e.printStackTrace();}
        catch (NoSuchAlgorithmException e){ e.printStackTrace();}
        catch (KeyStoreException e){ e.printStackTrace();}
        catch(CertificateException e){ e.printStackTrace();}








        /*try{
            System.out.println(urlConnection.getResponseMessage());
            System.out.println(urlConnection.getResponseCode());
            if(urlConnection.getResponseCode()==200){
                InputStream in= urlConnection.getInputStream();
                String line;
                BufferedReader reader= new BufferedReader(new InputStreamReader(in));
                StringBuilder out=new StringBuilder();
                while((line=reader.readLine())!=null){
                    out.append(line);
                }
                System.out.println(out.toString());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        */
    }
    public OkHttpClient GetHttpsClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(context.getSocketFactory())
                .build();
        return okHttpClient;
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        //httpsGet("https://comercioagricultura.me","GET","");
        //httpsGet("https://pokeapi.co/api/v2/pokemon/ditto","GET","");
        return null;
    }

    public void httpsPost(String url){

    }
    public void httpsGet(String url, String method,String body){


        //Se inicializa la url
        URL urlPeticion=null;
        try{
            urlPeticion= new URL(url);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        HttpsURLConnection urlConnection=null;


        //Se establecen los parametros de la petición
        try{
            urlConnection=(HttpsURLConnection)urlPeticion.openConnection();
            urlConnection.setRequestMethod(method);
            //urlConnection.setRequestProperty("Content-Type","application/json; utf-8");
            //urlConnection.setRequestProperty("Accept","application/json; utf-8");
            //final String basicAuth="Basic " + Base64.encodeToString("user:pass".getBytes(),Base64.NO_WRAP);
            //urlConnection.setRequestProperty("Authorization",basicAuth);
        }catch(IOException e){
            e.printStackTrace();
        }



        //Se establece el SSL
        urlConnection.setSSLSocketFactory(context.getSocketFactory());



        //Se genera la respuesta
        try{
            System.out.println(urlConnection.getResponseMessage());
            System.out.println(urlConnection.getResponseCode());
            if(urlConnection.getResponseCode()==200){
                InputStream in= urlConnection.getInputStream();
                String line;
                BufferedReader reader= new BufferedReader(new InputStreamReader(in));
                StringBuilder out=new StringBuilder();
                while((line=reader.readLine())!=null){
                    out.append(line);
                }
                System.out.println(out.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
