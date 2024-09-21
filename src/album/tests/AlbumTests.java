package album.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import album.dao.Album;
import album.dao.AlbumImpl;
import album.model.photo.Photo;

class AlbumTests {
	Album album;
	Photo[] photos;
	
	@BeforeEach
	void setUp() throws Exception {
		
		album = new AlbumImpl(4);
		photos = new Photo[3];
		photos[0] = new Photo(1, 101, "photoNumber1", "http://example.com/photo1.jpg", LocalDateTime.now());
		photos[1] = new Photo(1, 102, "photoNumber2", "http://example.com/photo2.jpg", LocalDateTime.now().minusDays(1));
		photos[2] = new Photo(2, 102, "photoNumber3", "http://example.com/photo3.jpg", LocalDateTime.now().minusDays(2));
		
		for (int i = 0; i < photos.length; i++) {
			album.addPhoto(photos[i]);
		}
		
	}

	@Test
	void testAlbumImpl() {
		album = new AlbumImpl(1);
		assertNotNull(album);
	}

	@Test
	void testAddPhoto() {
		assertFalse(album.addPhoto(photos[0]));
		assertTrue(album.addPhoto(new Photo(1, 104, "photoNumber4", "http://example.com/photo4.jpg", LocalDateTime.now().minusDays(5))));
		assertFalse(album.addPhoto(new Photo(1, 105, "photoNumber5", "http://example.com/photo5.jpg", LocalDateTime.now().minusDays(6))));
	}

	@Test
	void testRemovePhoto() {
		assertTrue(album.removePhoto(101, 1)); 

	
	    assertEquals(2, album.size());
	    
	    assertTrue(album.addPhoto(photos[0]));
	    
	    assertEquals(3, album.size());
	}

	

		@Test
		void testUpdatePhoto() {
		   
		   assertTrue(album.updatePhoto(101, 1, "http://example.com/photo1.jpg2"));
		   
		   assertFalse(album.updatePhoto(103, 1, "http://example.com/photo1.jpg2"));
		 
	
		}



	@Test
	void testGetPhotoFromAlbum() {
		Photo expected = photos[2];
		Photo actual = album.getPhotoFromAlbum(102, 2);
		assertEquals(expected, actual);
		
		actual = album.getPhotoFromAlbum(1, 999); 
	    assertNull(actual, "Null");
	}


	@Test
	void testGetAllPhotoFromAlbum() {
		Photo[] expected = {photos[0], photos[1]};
		
		Photo[] actual = album.getAllPhotoFromAlbum(1);
		
		assertArrayEquals(expected, actual);
	}

	@Test
	void testGetPhotoBeetweenDate() {
		LocalDate dateFrom = LocalDate.now().minusDays(3);
		LocalDate dateTo = LocalDate.now().minusDays(1);
		
		Photo[] expected = new Photo[] { photos[1], photos[2]};
		Photo[] actual = album.getPhotoBeetweenDate(dateFrom, dateTo);
		
		assertArrayEquals(expected, actual);
	}



}
