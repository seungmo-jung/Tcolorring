package com.wizontech.kct.entity;

import java.util.List;
import java.util.Map;

public class AlbumVo {
	
	private int albumId;
	private Map album;
	private List<Map> list;
	
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public Map getAlbum() {
		return album;
	}
	public void setAlbum(Map album) {
		this.album = album;
	}
	public List<Map> getList() {
		return list;
	}
	public void setList(List<Map> list) {
		this.list = list;
	}
}
