package album.dao;

import java.time.LocalDate;

import album.model.photo.Photo;

public class AlbumImpl implements Album {

	private Photo[] photos;
	private int size;
	
	public AlbumImpl(int capacity) {
		photos = new Photo[capacity];
	}
	
	@Override
	public boolean addPhoto(Photo photo) {
		if(size >= photos.length) {
			return false;
		}
		for (int i = 0; i < size; i++) {
	        if (photos[i].getPhotoId() == photo.getPhotoId() && photos[i].getAlbumId() == photo.getAlbumId()) {
	            return false; 
	        }
	    }
		photos[size] = photo;
		size++;
		return true;
	}

	@Override
	public boolean removePhoto(int photoId, int albumId) {
		for (int i = 0; i < size; i++) {
			if(photos[i].getAlbumId() == albumId && photos[i].getPhotoId() == photoId) {
				for (int j = i; j < size - 1; j++) {
					photos[j] = photos[j + 1];
				}
				photos[size-1] = null;
				size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updatePhoto(int photoId, int albumId, String url) {
		for (int i = 0; i < size; i++) {
			if(photos[i].getAlbumId() ==albumId && photos[i].getPhotoId() == photoId) {
				photos[i].setUrl(url);
				return true;
			}
		}
		return false;
	}

	@Override
	public Photo getPhotoFromAlbum(int photoId, int albumId) {
		for (int i = 0; i < size; i++) {
			if(photos[i].getAlbumId()==albumId && photos[i].getPhotoId() == photoId) {
			return	photos[i];
			}
		}
		return null;
	}

	@Override
	public Photo[] getAllPhotoFromAlbum(int albumId) {
	    int count = 0;
	    for (int i = 0; i < size; i++) {
	        if (photos[i].getAlbumId() == albumId) {
	            count++;
	        }
	    }
	   
	    if (count == 0) {
	        return new Photo[0];
	    }

	  
	    Photo[] albumPhotos = new Photo[count];
	    int index = 0;

	    for (int i = 0; i < size; i++) {
	        if (photos[i].getAlbumId() == albumId) {
	            albumPhotos[index++] = photos[i];
	        }
	    }

	    return albumPhotos;
	}

	@Override
	public Photo[] getPhotoBeetweenDate(LocalDate dateFrom, LocalDate dateTo) {
		Photo[] result = new Photo[size];
		int count = 0;
		
		for (int i = 0; i < size; i++) {
			LocalDate photoDate = photos[i].getDate().toLocalDate();
			if(photoDate.isEqual(dateFrom) || photoDate.isAfter(dateFrom) &&
			(photoDate.isEqual(dateTo) || photoDate.isBefore(dateTo))){
				result[count] = photos[i];
				count++;
			}
		}
		Photo[] filtredPhotos= new Photo[count];		
		System.arraycopy(result, 0, filtredPhotos, 0, count);
				return filtredPhotos;
	}

	@Override
	public int size() {
		return size;
	}

}
