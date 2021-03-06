package com.example.singold.MyActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.Song;
import com.example.singold.data.VideoItem;

import java.util.concurrent.ExecutionException;

public class AddSongActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText theSongName, singer, link;
    private Button save;
    private ImageButton sear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        theSongName = (EditText) findViewById(R.id.theSongName);
        singer = (EditText) findViewById(R.id.singer);
        link = (EditText) findViewById(R.id.link);
        sear=(ImageButton) findViewById(R.id.sear);
       // idPatient =(EditText) findViewById(R.id.idPatient);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
        sear.setOnClickListener(this);
    }

    private void dataHandler() {
       // String stIdPatient=idPatient.getText().toString();
        String stTheSongName = theSongName.getText().toString();
        String stSinger = singer.getText().toString();
        String stLink = link.getText().toString();
        String patientID="";
        Intent i=getIntent();
        if(i!=null)
        {
            patientID=i.getExtras().getString("patientID");
        }


        boolean isok = true;
//        if (stIdPatient.length() == 0) {
//            idPatient.setError("Enter the patient id");
//            isok = false;
//        }
        if (stTheSongName.length() < 3) {
            theSongName.setError("Enter the song name");
            isok = false;
        }
        if (stSinger.length() < 3) {
            singer.setError("Enter the singer name");
            isok = false;
        }
        if (stLink.length() == 0) {
            link.setError("Enter the link");
            isok = false;
        }


        if (isok == true) {
            Song song = new Song();
         //   song.setIdPatient(stIdPatient);
            song.setIdPatient(patientID);
            song.setName(stTheSongName);
            song.setSinger(stSinger);
            song.setLink(stLink);
            try {
                ConnectToServer.connect(this);
                ConnectToServer.addSongForPatient(song,(ProgressBar)findViewById(R.id.progressBar));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
        }
    public void onClick(View v){
        if (v==save)
            dataHandler();
        if(v ==sear)
        {
            Intent intent=new Intent (getBaseContext(),SearchSongActivity.class);
            intent.putExtra("toSearch",theSongName.getText().toString());
            startActivityForResult(intent,99);
        }
            

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnItmLogOutOut)
        {
            SharedPreferences preferences=getSharedPreferences("myfile",MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent=new Intent(getBaseContext(),LogInActivity.class);
            startActivity(intent);
            finish();
        }
        if (item.getItemId()==R.id.mnItmIcons){
            Intent intent = new Intent(getBaseContext(), icons_explainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode==RESULT_OK && requestCode==99)
        {
            VideoItem videoItem= (VideoItem) data.getExtras().get("CHOISED");

            link.setText(videoItem.getId());
        }

    }
    //    public void getVideos() {
//
//        youtubeSearch = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY,
//                new HttpRequestInitializer() {
//                    public void initialize(HttpRequest request)
//                            throws IOException {
//                    }
//                }).setApplicationName("youtube-cmdline-searchOnYoutube-sample").build();
//
//        YouTube.Search.List searchOnYoutube = null;
//
//        try {
//            searchOnYoutube = youtubeSearch.searchOnYoutube().list("id,snippet");
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        searchOnYoutube.setKey("YOUT_KEY");
//
//        searchOnYoutube.setQ(link.getText().toString());
//
//        searchOnYoutube.setType("video");
//
//        searchOnYoutube.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
//        searchOnYoutube.setMaxResults((long) NUMBER_OF_VIDEOS_RETURNED);
//
//        SearchListResponse searchResponse = null;
//        try {
//            searchResponse = searchOnYoutube.execute();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        searchResultList = searchResponse.getItems();
//
//        iteratorSearchResults = searchResultList.iterator();
//
//        if (!iteratorSearchResults.hasNext()) {
//            Toast.makeText(this, mResources.getString(R.string.There_arent_any_results_for_your_query),
//                    Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        int counter = 0;
//
//        int length = searchResultList.size();
//
//        titles = new String[length];
//        ids = new String[length];
//        images = new String[length];
//        urls = new String[length];
//
//        while (iteratorSearchResults.hasNext()) {
//
//            SearchResult singleVideo = iteratorSearchResults.next();
//            ResourceId rId = singleVideo.getId();
//
//            if (rId.getKind().equals("youtube#video")) {
//
//                String urlString = "https://i.ytimg.com/vi/" +rId.getVideoId() + "/0.jpg";
//
//                URL url = null;
//                try {
//                    url = new URL(urlString);
//                } catch (MalformedURLException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                String picture = null;
//                try {
//                    picture = encodeTobase64(getResizedBitmap(BitmapFactory.decodeStream(url.openConnection().getInputStream()), 100, 100));
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                titles[counter] = singleVideo.getSnippet().getTitle();
//                ids[counter] = rId.getVideoId();
//                images[counter] = picture;
//                urls[counter] = urlString;
//
//                counter++;
//            }
//        }
//
//        inflater = (LayoutInflater) DatePage.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        ArrayAdapter<String> parametersAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, titles) {
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//
//                View vi=convertView;
//                if(convertView==null)
//                    vi = inflater.inflate(R.layout.youtube_list, null);
//
//                TextView text = (TextView) vi.findViewById(R.id.youtubeText);
//                ImageView image = (ImageView) vi.findViewById(R.id.youtubeImage);
//
//                text.setText(titles[position]);
//                image.setImageDrawable(new BitmapDrawable(getResources(),decodeBase64(images[position])));
//
//                return vi;
//            }
//        };
//
//        builderYoutube = new AlertDialog.Builder(this);
//
//        builderYoutube.setSingleChoiceItems(parametersAdapter, -1, new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog,
//                                int which) {
//
//                youtube = ids[which];
//                thumbnailLink = urls[which];
//                youtubeTitle = titles[which];
//
//                if(youtubeFromText.isChecked())
//                    youtubeFromText.setChecked(false);
//
//                youtubeFromSearch.setChecked(true);
//
//                Toast.makeText(DatePage.this, mResources.getString(R.string.Picked) + " " + titles[which], Toast.LENGTH_SHORT).show();
//
//                dialog.dismiss();
//            }
//        });
//    }

}





