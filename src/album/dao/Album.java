package album.dao;

import java.time.LocalDate;
import album.model.photo.Photo;

public interface Album {
	
	boolean addPhoto(Photo photo);
	
	boolean removePhoto(int photoId,int albumId);
	
	boolean updatePhoto(int photoId, int albumId, String url);
	
	Photo getPhotoFromAlbum(int photoId, int albumId); // Photo[] getPhotoFromAlbum(int photoId, int albumId);
	
	Photo[] getAllPhotoFromAlbum(int albumId);
	
	Photo[] getPhotoBeetweenDate(LocalDate dateFrom , LocalDate dateTo);
	
	int size();
	
}
