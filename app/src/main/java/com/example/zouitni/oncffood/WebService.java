package com.example.zouitni.oncffood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class WebService extends AsyncTask<String,String,String> {

    ProgressDialog loading;
    static int idTicket,numTrain;
    String result;
    Context context;
    private static String typ;

    public WebService(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(context, "Veuillez patientez...", null, true, true);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        typ = params[2];

        if (typ.equals("Debut")) {
            try {
                String url_php = "http://10.0.2.2:8081/projet/html/checkTicket.php";
                String idTicket = params[0];
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                String post_data = URLEncoder.encode("idTicket", "UTF-8") + "=" + URLEncoder.encode(idTicket, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        loading.dismiss();
        String[][] items = new String[4][5];
        if (s != null) {
            if (typ.equals("Debut")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject sized = reader.getJSONObject("data");
                    JSONObject data = sized.getJSONObject("data");
                    int result = data.getInt("success");
                    Toast.makeText(context, "success "+String.valueOf(result), Toast.LENGTH_SHORT).show();
                    if (result == 1) {
                        int size = data.getInt("size");
                        idTicket = data.getInt("idTicket");
                        numTrain = data.getInt("NumTrain");
                        items [0][0] =  String.valueOf(idTicket);
                        items [0][1] =  String.valueOf(numTrain);
                        Toast.makeText(context, "Train : "+String.valueOf(numTrain), Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "Ticket : "+String.valueOf(idTicket), Toast.LENGTH_SHORT).show();

                        JSONArray categories = reader.getJSONArray("categories");
                        for (int i = 0; i < categories.length(); i++) {
                            JSONObject jsonobject = categories.getJSONObject(i);
                            int Categorie = jsonobject.getInt("categorie"+String.valueOf(i+1));
                            items [0][i+2] = String.valueOf(Categorie);
                        }
                        items = new String[size+1][5];
                        String[] singleArray;
                        for (int i = 1; i <= size; i++) {
                            JSONObject JObject = reader.getJSONObject(String.valueOf(i-1));
                            int idItem = JObject.getInt("idItem");
                            int idCategorie = JObject.getInt("idCategorie");
                            String nom = JObject.getString("nom");
                            String description = JObject.getString("description");
                            int prix = JObject.getInt("prix");
                            singleArray = new String[]{String.valueOf(idItem), String.valueOf(idCategorie), String.valueOf(prix), nom, description};
                            items [i] = (String[]) singleArray;
                        }
                    }
                    move(result,items,idTicket,numTrain);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(context, "Erreur fatale", Toast.LENGTH_SHORT).show();
        }
    }

    private void move(int res, String[][] a,int i,int j) {
        if (res == 1) {
            final Intent intent = new Intent(context, secondSplash.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle b = new Bundle();
            b.putSerializable("restoData", a);
            b.putInt("idTicket", i);
            b.putInt("numTrain", j);
            context.startActivity(intent);
        } else {
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setTitle("Numéro de ticket invalide");
            error.setMessage("Réssayer!");
            error.create().show();
        }
    }

   /* public void moveAI(int r) {
        if (r == 1) {
            final Intent intent = new Intent(context, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setTitle("Succés");
            error.setMessage("Inscription réussite");
            error.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    context.startActivity(intent);
                }
            });
            error.create().show();

        } else if (r == 0) {
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setTitle("Erreur");
            error.setMessage("inscription échouée!");
            error.create().show();
        } else {
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setTitle("Erreur");
            error.setMessage("veuillez contactez les développeurs de l'application!");
            error.create().show();
        }
    }

   /* private void moveC(int res, String n, String p, int a, int r) {
        final int an = a;
        final int rn = r;
        if (res == 1) {
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setTitle("Connexion réussite");
            error.setMessage("Bienvenue Dr. " + n + " " + p);
            error.setPositiveButton("Entrer", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context, Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle b = new Bundle();
                    b.putInt("an", an);
                    b.putInt("rn", rn);
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
            error.create().show();

        } else if (res == -1) {
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setTitle("Erreur");
            error.setMessage("Votre inscription n'est pas encore approuvé par l'admin!");
            error.create().show();
        } else {
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setTitle("Erreur");
            error.setMessage("Erreur d'authentification!");
            error.create().show();
        }
    }

    private void toDr(String[][] a, int aa, String A, String B, int C, int D, String E, String F) {
        Intent intent = new Intent(context, Rapport.class);
        Bundle b = new Bundle();
        b.putSerializable("rapport", a);
        b.putInt("size", aa);
        b.putInt("id_rapport", D);
        b.putInt("id_patient", C);
        b.putString("nom", A);
        b.putString("prenom", B);
        b.putString("num_dossier", E);
        b.putString("tel", F);
        b.putString("date", dater);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }*/
}