package team.fourty.seven.learnit.models;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.util.Objects;

public class EBook {

    public final String TAG = this.getClass().getSimpleName();

    private String title;
    private String author;
    private Bitmap photo;
    private String description;
    private Uri uri;
    private String category;
    private boolean isRead;
    private boolean isFavourited;

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isFavourited() {
        return isFavourited;
    }

    public void setFavourited(boolean favourited) {
        isFavourited = favourited;
    }

    public EBook(String title, String author, Bitmap photo, String description, Uri uri, String category, boolean isRead, boolean isFavourited) {
        this.title = title;
        this.author = author;
        this.photo = photo;
        this.description = description;
        this.uri = uri;
        this.category = category;
        this.isRead = isRead;
        this.isFavourited = isFavourited;
    }


    @Override
    public String toString() {
        return "EBook{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", photo=" + photo +
                ", description='" + description + '\'' +
                ", uri=" + uri +
                ", category='" + category + '\'' +
                ", isRead=" + isRead +
                ", isFavourited=" + isFavourited +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EBook)) return false;
        EBook eBook = (EBook) o;
        return Objects.equals(getTitle(), eBook.getTitle()) &&
                Objects.equals(getAuthor(), eBook.getAuthor()) &&
                Objects.equals(getPhoto(), eBook.getPhoto()) &&
                Objects.equals(getDescription(), eBook.getDescription()) &&
                Objects.equals(getUri(), eBook.getUri()) &&
                Objects.equals(getCategory(), eBook.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getPhoto(), getDescription(), getUri(), getCategory());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
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

    public EBook() {
        this.isFavourited = false;
        this.isRead = false;
    }


}
