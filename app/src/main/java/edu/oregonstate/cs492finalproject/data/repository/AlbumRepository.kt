package edu.oregonstate.cs492finalproject.data.repository

import edu.oregonstate.cs492finalproject.data.model.Album
import edu.oregonstate.cs492finalproject.data.model.AlbumDetail
//interface so recordClub repo can implement
interface AlbumRepository {

    suspend fun getTrendingAlbums(): List<Album>

    suspend fun searchAlbums(query: String): List<Album>

    suspend fun getAlbumDetail(albumId: String): AlbumDetail
}