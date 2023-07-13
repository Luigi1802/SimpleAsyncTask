package fr.luigi.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;
        for (int i = 0; i < 100; i += 1) {
            try {
                Thread.sleep(s / 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i+1);
        }
        return "Enfin réveillé après avoir dormi pendant " + s + " millisecondes !";
    }

    protected void onProgressUpdate(Integer... progress) {
        mProgressBar.get().setProgress(progress[0]);
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
