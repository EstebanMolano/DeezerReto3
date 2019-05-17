package com.example.webserviceconsumer;

import com.google.gson.Gson;

import java.io.IOException;

public class ServiceManager {

    public static final String COMENTARIO_I ="https://api.deezer.com/search/playlist?q=";





        public static class Comentaio1GET {


            OnResponseListener listener;

            public Comentaio1GET(String busqueda,OnResponseListener listener) {

                this.listener = listener;

                HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
                try {
                    String response = util.GETrequest(COMENTARIO_I+busqueda);
                    listener.onResponse(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public interface OnResponseListener{


                void onResponse(String response);
            }
        }


    public static class Comentaio2GET {


        OnResponseListener listener;

        public Comentaio2GET(String track,OnResponseListener listener) {

            this.listener = listener;

            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(track);
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public interface OnResponseListener{


            void onResponse(String response);
        }
    }








}
