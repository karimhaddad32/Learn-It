package team.fourty.seven.learnit.models;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.util.Objects;



public class YoutubeVideo {

    public final String TAG = this.getClass().getSimpleName();

    private String title;
    private String channel;
    private Bitmap thumbnail;
    private String description;
    private Uri uri;
    private String category;
    private String videoId;

    public YoutubeVideo() {
    }

    public YoutubeVideo(String title, String channel, Bitmap thumbnail, String description, Uri uri, String category, String videoId) {
        this.title = title;
        this.channel = channel;
        this.thumbnail = thumbnail;
        this.description = description;
        this.uri = uri;
        this.category = category;
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(String uri) {

        Uri uriTemp = null;

        try {
            uriTemp = Uri.parse(uri);
        }
        catch (NullPointerException e){
            Log.e(TAG,e.getMessage());
        }
        this.uri = uriTemp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "\nYoutubeVideo{" +
                "title='" + title + '\'' +
                ", channel='" + channel + '\'' +
                ", thumbnail=" + thumbnail +
                ", description='" + description + '\'' +
                ", uri=" + uri +
                ", category='" + category + '\'' +
                ", videoId='" + videoId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YoutubeVideo)) return false;
        YoutubeVideo that = (YoutubeVideo) o;
        return Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getChannel(), that.getChannel()) &&
                Objects.equals(getThumbnail(), that.getThumbnail()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getUri(), that.getUri()) &&
                Objects.equals(getCategory(), that.getCategory()) &&
                Objects.equals(getVideoId(), that.getVideoId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getChannel(), getThumbnail(), getDescription(), getUri(), getCategory(), getVideoId());
    }
}
